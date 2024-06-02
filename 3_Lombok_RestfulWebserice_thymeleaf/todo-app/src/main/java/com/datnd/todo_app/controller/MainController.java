package com.datnd.todo_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    

    @GetMapping("/hello")
    public String hello(){
        //xử lý logic ...
        return "hello";
    }

    
}
