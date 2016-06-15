/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

/**
 *
 * @author olmec
 */
public class FontWeight {
    public static final FontWeight LIGHT=new FontWeight("light-font");
    public static final FontWeight THIN=new FontWeight("thin-font");
    public static final FontWeight MEDIUM=new FontWeight("medium-font");
    public static final FontWeight BOLD=new FontWeight("bold-font");
    public static final FontWeight BLACK=new FontWeight("black-font");
    private String weight;
    private FontWeight(String weight){
        this.weight=weight;
    }
    public String getWeightClass(){
        return weight;
    }
    
    
}
