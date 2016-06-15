/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components.menu;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.components.MaterialMenu;
import com.olmectron.material.constants.MaterialColor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
public class MaterialMenuItem extends StackPane {
        private MaterialMenu menu;
        private MaterialIconButton iconButton;
        //private HBox rootBox;
        private Pane openPane;
        public MaterialMenuItem clonar(){
            MaterialMenuItem cas=new MaterialMenuItem(name,icono){
                @Override
                public void onItemClick(){
                    onCloneHandler.handle(null);
                }
            
            
            };
            cas.setParentMenu(menu);
            return cas;
            
        }
        public boolean isSelected(){
            return selected;
        }
        public MaterialMenuItem(String texto, String icon, MaterialMenu menu,  Pane openNode){
           this.menu=menu;
           initItem(texto, icon);
           
           selected=false;
           this.openPane=openNode;
           
           MaterialDesign.primaryColorCodeProperty().addListener(new ChangeListener<MaterialColor>() {
               @Override
               public void changed(ObservableValue<? extends MaterialColor> observable, MaterialColor oldValue, MaterialColor newValue) {
                   itemText.setRippleColor(Color.web(newValue.getStandardWebCode()+"40"));
             
                   if(selected){
                       if(!miniDrawer){
                         itemText.setColorCode(newValue);
                       }
                   iconButton.setColorCode(newValue);
                   
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }
           });
        }
        public MaterialMenuItem(String texto, String icon){
            this(texto,icon,null,null);
        }
        boolean selected;
        private MaterialDisplayText itemText;
        private void openPane(){
            
                //menu.getContainerDrawer().getStandardLayout().setRootView(openPane);
            menu.getContainerDrawer().hide();
            menu.getContainerDrawer().getStandardLayout().setTitle(getName());
        }
        private EventHandler<ActionEvent> secondAction=null;
        private void setSecondAction(EventHandler<ActionEvent> action){
            this.secondAction=action;
        }
        public void onSecondClick(){
           onItemClick();
        }
        public void onItemClick(){
            
        }
        private void secondClick(){
            new Timeline(new KeyFrame(
        Duration.millis(200),
        ae ->  onSecondClick()
               ))
    .play();
        }
        private void onClick(){
            if(openPane!=null){
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
        }
        public String getName(){
            return name;
            
        }
        private void resetMenuSelection(){
            for(int i=0;i<menu.size();i++){
                            if(!menu.getItem(i).equals(this))
                            menu.getItem(i).setSelected(false);
                        }
        }
        private String name;
        private String icono;
        private void initItem(String texto, String icon){
            
            getStyleClass().removeAll("material-menu-item");
            getStyleClass().addAll("material-menu-item");
            name=texto;
            icono=icon;
            itemText=new MaterialDisplayText(texto,MaterialDisplayText.MENU_ITEM){
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
                        
                        resetMenuSelection();
                        setSelected(true);
                        onClick();
                        secondClick();
                    }
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            //itemText.setPickOnBounds(false);
            itemText.setRippleActive(true);
            itemText.setRippleColor(Color.web(MaterialDesign.getPrimaryColorCode().getStandardWebCode()+"40"));
            itemText.setPadding(new Insets(0,0,0,72));
            itemText.setFillContainer(this);
            
            iconButton=new MaterialIconButton();
            iconButton.setMini(true);
            
            if(icon!=null){
                iconButton.setIcon(icon);
            }
            iconButton.setColorCode(MaterialColor.material.BLACK_54);
            VBox buttonStack=new VBox(iconButton);
            buttonStack.prefHeightProperty().bind(this.heightProperty());
            buttonStack.setAlignment(Pos.CENTER_LEFT);
           
            buttonStack.setPadding(new Insets(0,0,0,16));
            getChildren().add(buttonStack);
            
            getChildren().add(itemText);
            
        }
        private boolean miniDrawer=false;
        public void setMiniMenuItem(boolean miniDrawer){
            this.miniDrawer=miniDrawer;
            if(miniDrawer){
                //itemText.setText("");
                //itemText.setVisible(false);
                //itemText.setMaxWidth(66);
                //itemText.setPickOnBounds(false);
                itemText.setColor(MaterialColor.TRANSPARENT);
                itemText.setFontSize(1);
                itemText.setPadding(new Insets(0));
                
            }
            else{
                //itemText.setVisible(true);
                //itemText.setMaxWidth(280);
                //itemText.setPickOnBounds(true);
                if(this.selected){
                    itemText.setColorCode(MaterialDesign.getPrimaryColorCode());
                }
                else{
                    itemText.setColor(MaterialColor.BLACK_87);
                }
                itemText.setFontSize(14);
                //itemText.setText(name);
                itemText.setPadding(new Insets(0,0,0,72));
            }
        }
        public void setParentMenu(MaterialMenu menu){
            this.menu=menu;
        }
        public MaterialMenu getParentMenu(){
            return menu;
        }
        public void closeParentDrawer(){
            menu.getContainerDrawer().hide();
        }
        
        public void setSelected(boolean selected){
            if(selected){
                //itemText.getStyleClass().remove("background_grey_e0");
                    //itemText.getStyleClass().add("background_grey_e0");
                     iconButton.setColorCode(MaterialDesign.getPrimaryColorCode());
                    
                    if(miniDrawer){
                        itemText.setColor(MaterialColor.TRANSPARENT);
                    }
                    else{
                   itemText.setColorCode(MaterialDesign.getPrimaryColorCode());
                    
                        }
            }
            else{
                if(miniDrawer){
                    itemText.setColor(MaterialColor.TRANSPARENT);
                }
                else{
                    itemText.setColorCode(MaterialColor.material.BLACK_87);
                }
                iconButton.setColorCode(MaterialColor.material.BLACK_54);
                       
                //itemText.getStyleClass().remove("background_grey_e0");
                    
            }
            if(this.selected && selected){
                counter++;
            }
            else{
                counter=0;
            }
                    this.selected=selected;
        }
        private int counter=0;
        public int getSelectedCounter(){
            return counter;
        }
        private EventHandler<ActionEvent> onCloneHandler;
    public void setOnCloneItemClick(EventHandler<ActionEvent> eventHandler) {
        onCloneHandler=eventHandler;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
        
        
      
    }