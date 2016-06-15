/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import javafx.scene.paint.Color;

/**
 *
 * @author olmec
 */
public class MaterialTab extends FlatButton {
    
    private boolean toolbar=false;
    public MaterialTab(String text, boolean toolbar) {
        
        super(text);
        this.toolbar=toolbar;
        
        
        getStyleClass().remove("tab");
        getStyleClass().add("tab");
        
        getStyleClass().remove("medium-font");
        getStyleClass().add("medium-font");
        if(isInToolbar()){
        setColor(MaterialColor.WHITE_100);
        setRippleColor(Color.web("#FFEB3B90"));
        this.colorNormal=getColorActual();
        setColor(MaterialColor.WHITE_70);
        getStyleClass().remove("toolbar");
        getStyleClass().add("toolbar");
        }
        else{
            setRippleColor(Color.web("#2196F340"));
        this.colorNormal=getColorActual();
        setColor(MaterialColor.BLACK_54);
        }
    }
    private boolean isInToolbar(){
        return toolbar;
    }
    public MaterialTab(String text){
        this(text,false);
    }
    
    private MaterialTabs tabs=null;
    public void setTabsParent(MaterialTabs tabs){
        this.tabs=tabs;
    }
    private MaterialColor colorNormal;
    private boolean selected=false;
    public void select(){
        
        if(tabs!=null){
             tabs.unselectAll();
         }
        if(isInToolbar()){
            getStyleClass().remove("medium-font");
            getStyleClass().add("black-font");
            
        }
        getStyleClass().remove("selected");
         getStyleClass().add("selected");
         
         setColor(colorNormal);
         this.fire();
         
    }
    public void selectWithoutAction(){
        
        if(tabs!=null){
             tabs.unselectAll();
         }
        if(isInToolbar()){
            getStyleClass().remove("medium-font");
            getStyleClass().add("black-font");
            
        }
        getStyleClass().remove("selected");
         getStyleClass().add("selected");
         
         setColor(colorNormal);
    }
    public boolean isSelected(){
        return getStyleClass().contains("selected");
    }
    public void unselect(){
       
         getStyleClass().remove("selected");
        
         if(isInToolbar()){
             getStyleClass().remove("black-font");
            getStyleClass().add("medium-font");
             setColor(MaterialColor.WHITE_70);
         }
         else{
            setColor(MaterialColor.BLACK_54);
         }
    }
    
}
