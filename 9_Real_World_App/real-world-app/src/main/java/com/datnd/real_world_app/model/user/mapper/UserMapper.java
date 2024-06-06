package com.datnd.real_world_app.model.user.mapper;

import com.datnd.real_world_app.entity.User;
import com.datnd.real_world_app.model.user.dto.UserDTOResponse;

public class UserMapper {
    public static UserDTOResponse toUserDTOResponse(User user) {
        return UserDTOResponse.builder().email(user.getEmail()).username(user.getUsername()).bio(user.getBio())
                .image(user.getImage()).build();
    }
}
