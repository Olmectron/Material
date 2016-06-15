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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
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
public class MaterialIconButton extends Button implements MaterialComponent, MaterialRippleable {
    public static final String MENU_ICON="ic_menu";
    public static final String MORE_VERT_ICON="ic_more_vert";
    public static final String REFRESH_ICON="ic_refresh";
    public static final String PLUS_BOX="ic_plus_box";
    public static final String MOVE_LEFT_ICON="ic_move_left";
    public static final String MOVE_RIGHT_ICON="ic_move_right";
    public static final String DELETE_ICON="ic_delete";
    public static final String ARROW_BACK="ic_arrow_back";
    public static final String SELECT_ALL_ICON="ic_select_all";
    public static final String SEARCH_ICON="ic_search";
    
    public static final String CONNECTED_ICON="ic_connected";
    public static final String DISCONNECTED_ICON="ic_disconnected";
    public static final String SAVE_ICON="ic_save";
    public static final String FOLDER_OPEN_ICON="ic_folder_open";
    public static final String RESTORE_ICON="ic_restore";
    public static final String CLOSE_ICON="ic_close";
    
    public static final String PLUS_ICON="ic_plus";
    private CircleRipple ripple=new CircleRipple(){
        @Override
        public void onRippleFinished(){
            onRippleEffectFinished();
        }
    
    };
    @Override
    public Color getRippleColor() {
        return ripple.getMainColor();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public MaterialIconButton(){
        this("");
    }
    private String actualIcon=null;
    
    public MaterialIconButton(String iconButton){
        super();
        getStyleClass().addAll("icon-button",iconButton/*, colorActual.toString()*/);
        actualIcon=iconButton;
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
    public void setSuperMini(boolean minisu){
        if(minisu){
            getStyleClass().remove("super_mini");
            getStyleClass().add("super_mini");
        }
        else{
            getStyleClass().remove("super_mini");
        }
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
    public void setMaterialIcon(String imageName){
        if(actualIcon!=null){
         getStyleClass().remove(actualIcon);
        
        }
        actualIcon=imageName;
        getStyleClass().add(actualIcon);
        
        //String imagen=this.getClass().getResource(MaterialDesign.customPath+imageName).toExternalForm();
        //System.out.println("ICON "+imagen);
        //this.setStyle(this.getStyle()+"-fx-background-image: url('" + imagen + "');");
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
        // Adding circleRipple as fist node of button nodes to be on the bottom
        this.getChildren().add(0, ripple.getShape());
        return buttonSkin;
        
    }
 
    
    public MaterialColor getColorActual(){
        return colorActual;
    }
 private MaterialColor colorActual=MaterialDesign.primaryColor;
    @Override
    public void setColor(MaterialColor color) {
        setStyle(getStyle().replace(colorCode, ""));
        getStyleClass().remove(colorActual.toString());
        getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String colorCode="";
    public void setColorCode(MaterialColor color){
        getStyleClass().remove(colorActual.toString());
        setStyle(getStyle().replace(colorCode, ""));
        colorCode="-fx-effect: innershadow( gaussian , "+color.getStandardWebCode()+" , 7 , 1 , 1 , 1 );";
        setStyle(this.getStyle()+colorCode);
        
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

    public void setOnClick(EventHandler<ActionEvent> eventHandler) {
        setOnAction(eventHandler);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
