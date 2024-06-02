package com.datnd.component.core;

import org.springframework.stereotype.Component;

@Component("bikini")
public class Bikini implements IOutfit{

    @Override
    public void wear() {
        System.out.println("Dang mac bikini");
    }
    
}
