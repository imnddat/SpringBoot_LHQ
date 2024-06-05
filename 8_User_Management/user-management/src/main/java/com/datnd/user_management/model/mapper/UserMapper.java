package com.datnd.user_management.model.mapper;

import com.datnd.user_management.entity.User;
import com.datnd.user_management.model.dto.UserDTO;
import com.datnd.user_management.model.dto.UserDTOCreate;
import com.datnd.user_management.model.dto.UserDTOUpdate;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).email(user.getEmail()).age(user.getAge()).phone(user.getPhone())
                .build();
    }

    public static User toUser(UserDTOCreate userDTOCreate) {
        return User.builder().email(userDTOCreate.getEmail()).age(userDTOCreate.getAge())
                .phone(userDTOCreate.getPhone()).password(userDTOCreate.getPassword())
                .build();
    }

    public static User toUser(UserDTOUpdate userDTOUpdate) {
        return User.builder().id(userDTOUpdate.getId()).email(userDTOUpdate.getEmail()).age(userDTOUpdate.getAge())
                .phone(userDTOUpdate.getPhone()).password(userDTOUpdate.getPassword())
                .build();
    }
}
