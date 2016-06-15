/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

import java.util.ArrayList;
import org.xml.sax.Attributes;

/**
 *
 * @author Edgar
 */
public class XMLAttribute {
    public static ArrayList<XMLAttribute> parseAttributes(Attributes attributes){
        ArrayList<XMLAttribute> list=new ArrayList<XMLAttribute>();
        
        for(int i=0;i<attributes.getLength();i++){
            list.add(new XMLAttribute(attributes.getQName(i),attributes.getValue(i)));
        }
        return list;
    }
    private String key;
    private String value;
    public XMLAttribute(String key, String value){
        this.key=key;
        this.value=value;
    }
    public String getValue(){
       return this.value;
       
    }
    public String getKey(){
        return this.key;
    }
}
