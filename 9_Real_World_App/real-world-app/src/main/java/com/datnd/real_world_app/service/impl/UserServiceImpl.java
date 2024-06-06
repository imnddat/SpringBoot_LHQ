package com.datnd.real_world_app.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.datnd.real_world_app.entity.User;
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
    @Override
    public Map<String, UserDTOResponse> authenticate(Map<String, UserDTOLoginRequest> userLoginRequestMap) {
        UserDTOLoginRequest userDTOLoginRequest = userLoginRequestMap.get("user");

        Optional<User> userOptional = userRepository.findByEmail(userDTOLoginRequest.getEmail());

        boolean isAuthen = false;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(userDTOLoginRequest.getPassword())) {
                isAuthen = true;
            }
        }

        if (!isAuthen) {
            System.out.println("User name and password incorrect");
        }

        Map<String, UserDTOResponse> wrapper = new HashMap<>();
        UserDTOResponse userDTOResponse = UserMapper.toUserDTOResponse(userOptional.get());
        userDTOResponse.setToken(jwtTokenUtil.generateToken(userOptional.get(), (long) (24*60*60)));
        wrapper.put("user", userDTOResponse);
        return wrapper;
    }

}
