package com.datnd.user_management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.datnd.user_management.entity.User;
import com.datnd.user_management.exception.custom.CustomBadRequestException;
import com.datnd.user_management.exception.custom.CustomNotFoundException;
import com.datnd.user_management.model.CustomError;
import com.datnd.user_management.model.dto.UserDTO;
import com.datnd.user_management.model.dto.UserDTOCreate;
import com.datnd.user_management.model.dto.UserDTOUpdate;
import com.datnd.user_management.model.mapper.UserMapper;
import com.datnd.user_management.repository.UserRepository;
import com.datnd.user_management.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(UserMapper.toUserDTO(user));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(int id) throws CustomNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("User not found").build());
        }
        return UserMapper.toUserDTO(userOptional.get());
    }

    @Override
    public UserDTO createUser(UserDTOCreate userDTOCreate) throws CustomBadRequestException {
        if (userDTOCreate.getEmail().equals("")) {
            throw new CustomBadRequestException(CustomError.builder().code("400").message("Emaill not blank").build());
        }

        User user = UserMapper.toUser(userDTOCreate);
        user = userRepository.save(user);
        return UserMapper.toUserDTO(user); // vì hiện thi cho ng dùng nên sẽ trả về UserDTO
    }

    @Override
    public UserDTO updateUser(UserDTOUpdate userDTOUpdate) {
        User user = UserMapper.toUser(userDTOUpdate);
        user = userRepository.save(user);
        return UserMapper.toUserDTO(user); 
    }

    @Override
    public UserDTO deleteUser(int id) throws CustomNotFoundException{
        
        Optional<User> userOptional= userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("User not found").build());
        }
        userRepository.deleteById(id);
        return UserMapper.toUserDTO(userOptional.get());
    }

}
