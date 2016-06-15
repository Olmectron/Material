/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

import com.olmectron.material.MaterialDesign;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Edgar
 */
public class LanguageReader {
    private static HashMap data=null;
    
    public static HashMap getLanguageMap(LanguageRegion regionCode){
            if(data==null){
                data=new XMLString().readAll(regionCode);
            
            }
            return data;
            
            
        }    
    public static HashMap getLanguageMap(){
            return getLanguageMap(null);
            
            
        }
        public static StringProperty getProperty(String name){
            StringProperty prop=new SimpleStringProperty(LanguageReader.class,name);
           try{
                prop.setValue(LanguageReader.getLanguageMap(MaterialDesign.getLanguageRegion()).get(prop.getName()).toString());
           }
           catch(NullPointerException ex){
               prop.setValue("ERR_LOADING_STRING");
           }
            return prop;
        }
        
}
