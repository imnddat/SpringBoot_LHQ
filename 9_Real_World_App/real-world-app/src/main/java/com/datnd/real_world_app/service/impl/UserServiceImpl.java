package com.datnd.real_world_app.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datnd.real_world_app.entity.User;
import com.datnd.real_world_app.exception.custom.CustomBadRequestException;
import com.datnd.real_world_app.exception.custom.CustomNotFoundException;
import com.datnd.real_world_app.model.CustomError;
import com.datnd.real_world_app.model.profile.dto.ProfileDTOResponse;
import com.datnd.real_world_app.model.user.dto.UserDTOCreate;
import com.datnd.real_world_app.model.user.dto.UserDTOLoginRequest;
import com.datnd.real_world_app.model.user.dto.UserDTOResponse;
import com.datnd.real_world_app.model.user.mapper.UserMapper;
import com.datnd.real_world_app.repository.UserRepository;
import com.datnd.real_world_app.service.UserService;
import com.datnd.real_world_app.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, UserDTOResponse> authenticate(Map<String, UserDTOLoginRequest> userLoginRequestMap)
            throws CustomBadRequestException {
        UserDTOLoginRequest userDTOLoginRequest = userLoginRequestMap.get("user");

        Optional<User> userOptional = userRepository.findByEmail(userDTOLoginRequest.getEmail());

        boolean isAuthen = false;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userDTOLoginRequest.getPassword(), user.getPassword())) {
                isAuthen = true;
            }
        }

        if (!isAuthen) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("400").message("User name and password incorrect").build());

        }

        return buildDTOResponse(userOptional.get());
    }

    @Override
    public Map<String, UserDTOResponse> registerUser(Map<String, UserDTOCreate> userDTOCreateMap) {
        UserDTOCreate userDTOCreate = userDTOCreateMap.get("user");
        User user = UserMapper.toUser(userDTOCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return buildDTOResponse(user);
    }

    private Map<String, UserDTOResponse> buildDTOResponse(User user) {
        Map<String, UserDTOResponse> wrapper = new HashMap<>();
        UserDTOResponse userDTOResponse = UserMapper.toUserDTOResponse(user);
        userDTOResponse.setToken(jwtTokenUtil.generateToken(user, 24 * 60 * 60));
        wrapper.put("user", userDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, UserDTOResponse> getCurrentUser() throws CustomNotFoundException {
        User userLoggedIn = getUserLoggedIn();
        if (userLoggedIn != null) {
            return buildDTOResponse(userLoggedIn);
        }
        throw new CustomNotFoundException(CustomError.builder().code("404").message("User not exist").build());
    }

    @Override
    public Map<String, ProfileDTOResponse> getProfile(String username) throws CustomNotFoundException {
        User userLoggedIn = getUserLoggedIn();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("User not found").build());
        }

        User user = userOptional.get();
        Set<User> followers = user.getFollowers();
        boolean isFollowing = false;
        for (User u : followers) {
            if (u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }
        return buildProfileResponse(userOptional.get(), isFollowing);
    }

    public User getUserLoggedIn() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userRepository.findByEmail(email).get();
            return user;
        }
        return null;
    }

    private Map<String, ProfileDTOResponse> buildProfileResponse(User user, boolean isFollowing) {
        Map<String, ProfileDTOResponse> wrapper = new HashMap<>();
        ProfileDTOResponse profileDTOResponse = ProfileDTOResponse.builder().username(user.getUsername())
                .bio(user.getBio()).image(user.getImage()).following(isFollowing).build();

        wrapper.put("profile", profileDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, ProfileDTOResponse> followUser(String username) throws CustomNotFoundException {
        User userLoggedIn = getUserLoggedIn();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("User not found").build());
        }

        User user = userOptional.get();
        Set<User> followers = user.getFollowers();
        boolean isFollowing = false;
        for (User u : followers) {
            if (u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }

        if (!isFollowing) {
            isFollowing = true;
            user.getFollowers().add(userLoggedIn);
            user = userRepository.save(user);
            isFollowing = true;
        }
        return buildProfileResponse(userOptional.get(), isFollowing);
    }

    @Override
    public Map<String, ProfileDTOResponse> unfollowUser(String username) throws CustomNotFoundException {
        User userLoggedIn = getUserLoggedIn();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("User not found").build());
        }

        User user = userOptional.get();
        Set<User> followers = user.getFollowers();
        boolean isFollowing = false;
        for (User u : followers) {
            if (u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }

        if (isFollowing) {
            isFollowing = true;
            user.getFollowers().remove(userLoggedIn);
            user = userRepository.save(user);
            isFollowing = false;
        }
        return buildProfileResponse(userOptional.get(), isFollowing);
    }

}
