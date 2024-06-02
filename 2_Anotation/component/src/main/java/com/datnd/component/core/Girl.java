package com.datnd.component.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Girl {
    public IOutfit outfit;

    String name;

    //@Autowired
    public Girl(@Qualifier("bikini") IOutfit outfit) {
        System.out.println("Contructor");
        this.outfit = outfit;
    }
    
    // public Girl(String name) {
    //     this.name = name;
    // }
    // @Autowired
    // public void setOutfit(IOutfit outfit){
    //     System.out.println("setter");
    //     this.outfit = outfit;
    // }

    @PostConstruct
    public void postConstructFunction(){
        System.out.println("Bean vừa được khởi tạo xong");
    }

    @PreDestroy
    public void preDestroyFunction(){
        System.out.println("Trước khi bị xóa khỏi Application Context");
    }
}
