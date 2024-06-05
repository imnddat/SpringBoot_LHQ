package com.datnd.user_management.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTOUpdate {
    private int id;
    private String email;
    private String password;
    private String phone;
    private int age;
}
