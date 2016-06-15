/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

import java.util.Locale;

/**
 *
 * @author Edgar
 */
public class LanguageRegion {
    private String code;
    public String getCode(){
        return code;
    }
    private LanguageRegion(String code){
        this.code=code;
    }
    public static final LanguageRegion IDENTIFY=new LanguageRegion(Locale.getDefault().getLanguage());
    public static final LanguageRegion SPANISH=new LanguageRegion("es");
    public static final LanguageRegion ENGLISH=new LanguageRegion("en");
    
    //public static final LanguageRegion ENGLISH=new LanguageRegion("en");
}
