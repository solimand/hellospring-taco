package com.tutorial.hellospringtaco.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TacoOrder {
    private String userName;
    private String userAddr;
    private String ccNum;
    
    private List<Taco> tacos = new ArrayList<>();
    
    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }
}
