/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author olmec
 */
public class ColoredStackPane extends StackPane {
    private String colorActual=null;
    public ColoredStackPane(){
        super();
    }
    public ColoredStackPane(Node clip){
        super();
        if(clip!=null){
            setClip(clip);
        }
    }
    public void setColorStyle(Color color){
        String colorString=color.toString().substring(2);
        colorString="#"+colorString.substring(0,colorString.length()-2);
        
        if(colorActual!=null){
            setStyle(getStyle().replace(colorActual,""));
            
        }
        colorActual="-fx-background-color: "+colorString+";";
       
        setStyle(getStyle()+colorActual);
    }
    
}
