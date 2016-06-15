/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import java.util.ArrayList;

/**
 *
 * @author olmec
 */
public abstract class SimpleStringSelector extends SimpleSelector<String> {
    
    public  SimpleStringSelector(String field, ArrayList<String> items){
       super(field,items);
    }
    public SimpleStringSelector(String field,ArrayList<String> items, int selected){
    super(field,items,selected);
    }
    @Override
    public String getStringValue(String item) {
        return item;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int getItemPosition(ArrayList<String> objetos, String item){
        return objetos.indexOf(item);
    }

    
    
}
