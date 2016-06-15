/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.MaterialRippleable;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.ripple.CircleRipple;
import javafx.scene.control.Button;

/**
 *
 * @author Édgar
 */

import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MaterialButton extends Button implements  MaterialComponent, MaterialRippleable {
@Override
    public Color getRippleColor() {
        return ripple.getMainColor();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private CircleRipple ripple=new CircleRipple(){
        @Override
        public void onRippleFinished(){
            onRippleEffectFinished();
        }
    
    };
    public MaterialButton(String text, String tipo) {
        super(text.toUpperCase());
        setCache(true);
        setCacheShape(true);
        setCacheHint(CacheHint.SPEED);
        getStyleClass().addAll("material-button",tipo,"medium-font",colorActual.toString());
        
        //System.out.println(getWebColor(this.getTextFill()));
        
        ripple.setNode(this);
        
    }
    public static String FLAT="flat";
    public static String RAISED="raised";
    public MaterialColor getColorActual(){
        return colorActual;
    }
    public MaterialButton(String text) {
        this(text,FLAT);
        
    }
    
    @Override
    public void requestFocus(){
       
    }
    @Override
    public Skin<?> createDefaultSkin() {
        
        final ButtonSkin buttonSkin = new ButtonSkin(this);
        // Adding circleRipple as fist node of button nodes to be on the bottom
        this.getChildren().add(0, ripple.getShape());
        return buttonSkin;
    }

    private MaterialColor colorActual=MaterialDesign.primaryColor;
    @Override
    public void setColor(MaterialColor color) {
        getStyleClass().remove(colorActual.toString());
        getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeColor() {
        getStyleClass().remove(colorActual.toString());

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        setVisible(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unhide() {
        setVisible(true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onRippleEffectFinished() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRippleActive(boolean active) {
        ripple.setActive(active);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRippleColor(Color color) {
        ripple.setMainColor(color);
        //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}