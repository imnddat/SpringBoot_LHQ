package com.datnd.user_management.service;

import java.util.List;

import com.datnd.user_management.exception.custom.CustomBadRequestException;
import com.datnd.user_management.exception.custom.CustomNotFoundException;
import com.datnd.user_management.model.dto.UserDTO;
import com.datnd.user_management.model.dto.UserDTOCreate;
import com.datnd.user_management.model.dto.UserDTOUpdate;

public interface UserService {

    public List<UserDTO> getAllUsers();

    public UserDTO getUserById(int id) throws CustomNotFoundException;

    public UserDTO createUser(UserDTOCreate userDTOCreate) throws CustomBadRequestException;

    public UserDTO updateUser(UserDTOUpdate userDTOUpdate);

    public UserDTO deleteUser(int id) throws CustomNotFoundException;
    
}
