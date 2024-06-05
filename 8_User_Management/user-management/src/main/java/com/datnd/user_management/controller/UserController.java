package com.datnd.user_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datnd.user_management.exception.custom.CustomBadRequestException;
import com.datnd.user_management.exception.custom.CustomNotFoundException;
import com.datnd.user_management.model.dto.UserDTO;
import com.datnd.user_management.model.dto.UserDTOCreate;
import com.datnd.user_management.model.dto.UserDTOUpdate;
import com.datnd.user_management.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users") // root endpoint
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) throws CustomNotFoundException{ // trường hợp muốn đặt tên khác thì phải khai báo biến: (@PathVariable(name = "id") int abc)
        return userService.getUserById(id);
    }

    @PostMapping("")
    public UserDTO createUser(@RequestBody UserDTOCreate userDTOCreate) throws CustomBadRequestException {
        return userService.createUser(userDTOCreate);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTOUpdate userDTOUpdate){
        userDTOUpdate.setId(id);
        return userService.updateUser(userDTOUpdate);
    }

    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable int id) throws CustomNotFoundException{
        return userService.deleteUser(id);
    }
}
