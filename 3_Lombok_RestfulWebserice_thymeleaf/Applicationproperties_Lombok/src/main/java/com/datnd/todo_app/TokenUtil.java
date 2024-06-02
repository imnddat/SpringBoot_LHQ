package com.datnd.todo_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    @Value("${secret.key}")
    private String SECRET_KEY;
    private final String PUBLIC_KEY = " LOI TAI AI???";

    public String generateToken(){
        return SECRET_KEY + PUBLIC_KEY;
    }


}
