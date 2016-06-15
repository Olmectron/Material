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
public class XMLAttributeList extends ArrayList<XMLAttribute> {
    
    public XMLAttributeList(Attributes attrs){
        super();
        for(int i=0;i<attrs.getLength();i++){
            add(new XMLAttribute(attrs.getQName(i),attrs.getValue(i)));
        }
    }
    public String getValue(String key){
        for (XMLAttribute attr : this) {
            if(attr.getKey().equalsIgnoreCase(key)){
                return attr.getValue();
            }
        }
        return null;
    }
}
