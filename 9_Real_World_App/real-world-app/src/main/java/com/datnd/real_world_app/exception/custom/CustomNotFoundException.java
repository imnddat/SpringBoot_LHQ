package com.datnd.real_world_app.exception.custom;

import com.datnd.real_world_app.model.CustomError;

public class CustomNotFoundException extends BaseCustomException{

    public CustomNotFoundException(CustomError customError) {
        super(customError);
        //TODO Auto-generated constructor stub
    }
    
}
