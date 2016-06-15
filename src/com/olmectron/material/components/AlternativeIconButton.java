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
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */
public class AlternativeIconButton extends Button implements MaterialComponent, MaterialRippleable {
    public static final String MENU_ICON="ic_menu";
    public static final String MORE_VERT_ICON="ic_more_vert";
    public static final String REFRESH_ICON="ic_refresh";
    public static final String PLUS_BOX="ic_plus_box";
    public static final String MOVE_LEFT_ICON="ic_move_left";
    public static final String MOVE_RIGHT_ICON="ic_move_right";
    @Override
    public Color getRippleColor() {
        return ripple.getMainColor();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static final String PLUS_ICON="ic_plus";
    private CircleRipple ripple=new CircleRipple(){
        @Override
        public void onRippleFinished(){
            onRippleEffectFinished();
        }
    
    };
    
    public AlternativeIconButton(){
        this("");
    }
    public String getShownText(){
        return getText();
    }
    public AlternativeIconButton(String iconButton){
        super();
        getStyleClass().addAll("icon-button",iconButton/*, colorActual.toString()*/);
        ripple.setNode(this);
        ripple.setMainColor(Color.web("FFFFFF40"));
        setCache(true);
        setCacheShape(true);
        setCacheHint(CacheHint.SPEED);
        
        /*textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getChildren().clear();
                getChildren().add(0,ripple.getShape());
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
    }
    
    public void setMini(boolean miniat){
       
        if(miniat){
            getStyleClass().remove("mini");
            getStyleClass().add("mini");
        }
        else{
            getStyleClass().remove("mini");
        }
    }
    public void setIcon(String imageName){
        
        String imagen=this.getClass().getResource(MaterialDesign.customPath+imageName).toExternalForm();
        //System.out.println("ICON "+imagen);
        this.setStyle(this.getStyle()+"-fx-background-image: url('" + imagen + "');");
    }
    @Override
    public void requestFocus(){
       
    }
    

    @Override
    public Skin<?> createDefaultSkin() {
       
        final ButtonSkin buttonSkin = new ButtonSkin(this);
        this.getChildren().remove(ripple.getShape());
        // Adding circleRipple as fist node of button nodes to be on the bottom
        this.getChildren().add(0, ripple.getShape());
        return buttonSkin;
        
    }
 
    
    public void setShownText(String texto){
        
        setText(texto);
        
        
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
