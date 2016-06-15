/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.constants.MaterialColor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author olmec
 */
public class MaterialFloatingButton extends MaterialIconButton {
    
    public MaterialFloatingButton(String icon){
        super(icon);
        getStyleClass().remove("floating");
        getStyleClass().add("floating");
        this.setColor(MaterialColor.BLUE);
        
        
        
    }
    private String colorCode="";
    @Override
    public void setColorCode(MaterialColor color){
        getStyleClass().remove(getColorActual().toString());
        setStyle(getStyle().replace(colorCode, ""));
        colorCode="-fx-background-color: "+color.getStandardWebCode()+" !important;";
        setStyle(this.getStyle()+colorCode);
    }
    
    
    
    
}
