package com.datnd.real_world_app.service;

import java.util.Map;

import com.datnd.real_world_app.exception.custom.CustomBadRequestException;
import com.datnd.real_world_app.model.user.dto.UserDTOCreate;
import com.datnd.real_world_app.model.user.dto.UserDTOLoginRequest;
import com.datnd.real_world_app.model.user.dto.UserDTOResponse;

public interface UserService {

    public Map<String, UserDTOResponse> authenticate(Map<String, UserDTOLoginRequest> userLoginRequestMap) throws CustomBadRequestException;

    public Map<String, UserDTOResponse> registerUser(Map<String, UserDTOCreate> userDTOCreateMap);
    
}
