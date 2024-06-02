package com.datnd.component.core;

import org.springframework.stereotype.Component;

@Component

public class Tshirt implements IOutfit{

    @Override
    public void wear() {
        System.out.println("Dang mac Tshirt");
    }
    
}
