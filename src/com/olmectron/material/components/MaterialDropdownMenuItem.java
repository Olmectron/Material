/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */

public class MaterialDropdownMenuItem extends StackPane {
        private MaterialDropdownMenu menu;
        private MaterialIconButton iconButton;
        //private HBox rootBox;
        private Pane openPane;
        private boolean materialIcon=false;
        public MaterialDropdownMenuItem(String texto, String icon, MaterialDropdownMenu menu,  Pane openNode, boolean materialIcon){
           this.menu=menu;
           this.materialIcon=materialIcon;
           initItem(texto, icon);
           selected=false;
           this.openPane=openNode;
        }
        public MaterialDropdownMenuItem(String texto, String icon, MaterialDropdownMenu menu,  Pane openNode){
           this(texto,icon,menu,openNode,false);
        }
        
        public MaterialDropdownMenuItem(String texto){
            this(texto,null,null,null);
        }
        
        public MaterialDropdownMenuItem(String texto, String icon,boolean materialIcon){
            this(texto,icon,null,null,materialIcon);
        }
        public MaterialDropdownMenuItem(String texto, String icon){
            this(texto,icon,null,null);
        }
        boolean selected;
        private MaterialDisplayText itemText;
        private void openPane(){
            menu.hideMenu();
              /*  menu.getContainerDrawer().getStandardLayout().setRootView(openPane);
            menu.getContainerDrawer().hide();
            menu.getContainerDrawer().getStandardLayout().setTitle(getName());
            */
        }
        private EventHandler<ActionEvent> secondAction=null;
        private void setSecondAction(EventHandler<ActionEvent> action){
            this.secondAction=action;
        }
        public void onItemClick(){
            onSecondClick();
        }
        public void onSecondClick(){
           
        }
        private void secondClick(){
            new Timeline(new KeyFrame(
        Duration.millis(200),
        ae ->  onItemClick()
               ))
    .play();
        }
        private void onClick(){
            
                new Timeline(new KeyFrame(
        Duration.millis(200),
        ae ->  openPane()
               ))
    .play();
                
                
                /*new Timeline(new KeyFrame(
        Duration.millis(200),
        ae ->  openPane()
               ))
    .play();*/
                
            
        }
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name=name;
            itemText.setText(name);
        }
        private String name;
        private void initItem(String texto, String icon){
            getStyleClass().removeAll("material-menu-item");
            getStyleClass().addAll("material-menu-item");
            if(getTextColor()!=null){
                actual=getTextColor();
            }
            
            name=texto;
            itemText=new MaterialDisplayText(texto,MaterialDisplayText.MENU_DROPDOWN_ITEM){
                @Override
                public void onRippleEffectFinished(){
                    
                    /*getStyleClass().remove("background_grey_e0");
                    getStyleClass().add("background_grey_e0");*/
                
                }
            
            };
            
            itemText.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if(menu!=null){
                        for(int i=0;i<menu.size();i++){
                            try{
                                menu.getItemAt(i).setSelected(false);
                            }
                            catch(ClassCastException classCast){
                                System.out.println("Un item no es dropdownmenuitem");
                            }
                        }
                        setSelected(true);
                        onClick();
                        secondClick();
                    }
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            itemText.setRippleActive(true);
            itemText.setRippleColor(Color.web("#2196F340"));
            itemText.setPadding(new Insets(0,0,0,72));
            if(icon==null){
                itemText.setPadding(new Insets(0,0,0,24));
            }
            itemText.setColor(actual);
            itemText.setFillContainer(this);
            iconButton=new MaterialIconButton();
            iconButton.setMini(true);
            
            if(icon!=null){
                if(materialIcon){
                    iconButton.setMaterialIcon(icon);
                }
                else
                iconButton.setIcon(icon);
            }
            iconButton.setColor(MaterialColor.BLACK_54);
            VBox buttonStack=new VBox(iconButton);
            buttonStack.prefHeightProperty().bind(this.heightProperty());
            buttonStack.setAlignment(Pos.CENTER_LEFT);
           
            buttonStack.setPadding(new Insets(0,0,0,16));
            if(icon!=null){
            getChildren().add(buttonStack);
            }
            
            getChildren().add(itemText);
            
        }
        public void setParentMenu(MaterialDropdownMenu menu){
            this.menu=menu;
        }
        public MaterialDropdownMenu getParentMenu(){
            return menu;
        }
        public void setSelected(boolean selected){
            if(selected){
                //itemText.getStyleClass().remove("background_grey_e0");
                    //itemText.getStyleClass().add("background_grey_e0");
                    itemText.setColor(MaterialDesign.primaryColor);
                   iconButton.setColor(MaterialDesign.primaryColor);
                    
            }
            else{
                iconButton.setColor(MaterialColor.BLACK_54);
                       
                //itemText.getStyleClass().remove("background_grey_e0");
                    itemText.setColor(actual);
            }
                    this.selected=selected;
        }
        private MaterialColor actual=MaterialColor.BLACK_87;
        public void setTextColor(MaterialColor color){
            this.actual=color;
            itemText.setColor(actual);
        }
        public MaterialColor getTextColor(){
            return null;
        }
        
        
        
        
      
    }