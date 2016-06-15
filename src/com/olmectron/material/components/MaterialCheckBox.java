/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialRippleable;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.ripple.CircleRipple;
import com.olmectron.material.ripple.FixedRipple;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import com.sun.javafx.scene.control.skin.CheckBoxSkin;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Skin;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author olmec
 */
public class MaterialCheckBox extends CheckBox implements MaterialComponent, MaterialRippleable {
    public MaterialCheckBox(){
        super();
        initAll();
        ripple.setNode(this);
    }
    private CircleRipple ripple=new CircleRipple(){
        @Override
        public void onRippleFinished(){
            
        }
    
    };
    @Override
    public Color getRippleColor() {
        return ripple.getMainColor();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Skin<?> createDefaultSkin() {
        
        //final ButtonSkin buttonSkin = new ButtonSkin(this);
        final CheckBoxSkin buttonSkin = new CheckBoxSkin(this);
        // Adding circleRipple as fist node of button nodes to be on the bottom
        this.getChildren().add(0, ripple.getShape());
        return buttonSkin;
    }
    
    public MaterialCheckBox(String text){
        super(text);
        
        initAll();
       
        ripple.setNode(this);
    }
   
    private void initAll(){
        //caja=new CheckBox();
        setPadding(new Insets(5));
        getStyleClass().remove("material-check-box");
        getStyleClass().add("material-check-box");
        ripple.setMainColor(Color.web(MaterialColor.material.BLUE.getStandardWebCode()+"40"));
         /*
        setAlignment(Pos.CENTER);
        StackPane rippleBox=new StackPane();
        
        //StackPane containerBox=new StackPane(caja,rippleBox);
        
        //getChildren().add(containerBox);
        //containerBox.getStyleClass().remove("material-check-box-container");
        //containerBox.getStyleClass().add("material-check-box-container");
        rippleBox.getStyleClass().remove("material-check-box-container");
        rippleBox.getStyleClass().add("material-check-box-container");
        //containerBox.setAlignment(Pos.CENTER);
        getChildren().add(rippleBox);
        rippleBox.getChildren().addAll(caja, ripple.getShape());
        ripple.setNode(rippleBox);*/
    }
    
    @Override
    public void setColor(MaterialColor color) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeColor() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        this.setVisible(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unhide() {
        this.setVisible(true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
    public void onRippleEffectFinished() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRippleActive(boolean active) {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRippleColor(Color color) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public void onRippleEffectFinished() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRippleActive(boolean active) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRippleColor(Color color) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
