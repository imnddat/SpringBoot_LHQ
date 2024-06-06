package com.datnd.real_world_app.service;

import java.util.Map;

import com.datnd.real_world_app.model.user.dto.UserDTOLoginRequest;
import com.datnd.real_world_app.model.user.dto.UserDTOResponse;

public interface UserService {

    public Map<String, UserDTOResponse> authenticate(Map<String, UserDTOLoginRequest> userLoginRequestMap);
    
}
