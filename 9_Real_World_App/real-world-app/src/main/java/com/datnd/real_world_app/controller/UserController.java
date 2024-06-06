package com.datnd.real_world_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.datnd.real_world_app.model.user.dto.UserDTOLoginRequest;
import com.datnd.real_world_app.model.user.dto.UserDTOResponse;
import com.datnd.real_world_app.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/login")
    public Map<String, UserDTOResponse> login(@RequestBody Map<String, UserDTOLoginRequest> userLoginRequestMap){
        return userService.authenticate(userLoginRequestMap);
    }
}
