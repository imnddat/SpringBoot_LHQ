package com.datnd.user_management.exception.custom;

import com.datnd.user_management.model.CustomError;

public class CustomNotFoundException extends BaseException{

    public CustomNotFoundException(CustomError customError) {
        super(customError);
        
    }
    
}
