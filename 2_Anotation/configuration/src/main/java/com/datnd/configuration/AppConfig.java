package com.datnd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public SimpleBean createBean(){
        SimpleBean simpleBean = new SimpleBean("Datnd");
        return simpleBean;
    }
}
