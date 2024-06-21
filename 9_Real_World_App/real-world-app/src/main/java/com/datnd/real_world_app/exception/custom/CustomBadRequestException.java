package com.datnd.real_world_app.exception.custom;

import com.datnd.real_world_app.model.CustomError;

public class CustomBadRequestException extends BaseCustomException{

    public CustomBadRequestException(CustomError customError) {
        super(customError);
        //TODO Auto-generated constructor stub
    }
    
}
