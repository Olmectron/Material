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
import com.sun.javafx.scene.control.skin.CheckBoxSkin;
import com.sun.javafx.scene.control.skin.RadioButtonSkin;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

/**
 *
 * @author olmec
 */
public class MaterialRadioButton extends RadioButton implements MaterialComponent/*, MaterialRippleable*/ {
    public MaterialRadioButton(){
        super();
        initAll();
        //ripple.setNode(this);
    }
    /*private CircleRipple ripple=new CircleRipple(){
        @Override
        public void onRippleFinished(){
            onRippleEffectFinished();
        }
    
    };
    @Override
    public Skin<?> createDefaultSkin() {
        
        //final ButtonSkin buttonSkin = new ButtonSkin(this);
        final RadioButtonSkin buttonSkin = new RadioButtonSkin(this);
        // Adding circleRipple as fist node of button nodes to be on the bottom
        this.getChildren().add(0, ripple.getShape());
        return buttonSkin;
    }*/
    public MaterialRadioButton(String text){
        super(text);
        initAll();
        
        //ripple.setNode(this);
    }
    private void initAll(){
        getStyleClass().remove("material-radio-button");
        getStyleClass().add("material-radio-button");
        
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
    @Override
    public void requestFocus(){
        
    }
    
}

