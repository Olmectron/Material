/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

/**
 *
 * @author olmec
 */
public class MultipartParameter {
    private String field, value;
    
    public MultipartParameter(String field, String value){
        this.field=field;
        this.value=value;
    }
    public String getFieldName(){
        return field;
    }
    public String getValue(){
        return value;
    }
}
