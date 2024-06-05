package com.datnd.user_management.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// dùng để trả về cho người dùng (vì 1 số trường nhạy cảm như password cần được
// che thông tin)
public class UserDTO {
    private int id;
    private String email;
    private String phone;
    private int age;
}
