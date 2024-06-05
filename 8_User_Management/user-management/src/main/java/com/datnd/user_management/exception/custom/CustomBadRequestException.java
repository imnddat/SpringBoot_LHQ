package com.datnd.user_management.exception.custom;

import com.datnd.user_management.model.CustomError;

public class CustomBadRequestException extends BaseException{

    public CustomBadRequestException(CustomError customError) {
        super(customError);
        
    }
    
}
