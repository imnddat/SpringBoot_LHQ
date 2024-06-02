package com.datnd.configuration;

public class SimpleBean {
    String name;

    public SimpleBean(String name) {
        this.name = name;
    }

    public void showSimpleBean(){
        System.out.println("[SimpleBean] : " + this.name);
    }
}
