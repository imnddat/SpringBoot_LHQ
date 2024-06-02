package com.datnd.component.core;

import org.springframework.stereotype.Component;

@Component("naked")
//@Primary
public class Naked implements IOutfit{

    @Override
    public void wear() {
        System.out.println("Dang mac ...");
    }
    
}
