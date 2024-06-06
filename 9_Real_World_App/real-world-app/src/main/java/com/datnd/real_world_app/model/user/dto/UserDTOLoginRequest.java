package com.datnd.real_world_app.model.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTOLoginRequest {
    private String email;
    private String password;
}
