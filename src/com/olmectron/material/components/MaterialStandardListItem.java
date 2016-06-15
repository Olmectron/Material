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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author olmec
 */
public class MaterialStandardListItem<T> extends StackPane {
    private T item;
    public MaterialStandardListItem(T item){
        super();
        this.item=item;
        
    }
    public T getItem(){
       return item;
    }
    private double originalY=0, originalX=0;
    public void setFlowLayoutOriginalYPosition(double y){
        originalY=y;
    }
    public void setFlowLayoutOriginalXPosition(double x){
        originalX=x;
    }
    public double getFlowLayoutOriginalXPosition(){
        return originalX;
    }
    public double getFlowLayoutOriginalYPosition(){
        return originalY;
    }
    
    private CheckBox selectedBox=null;
    public void setSelectedBox(CheckBox selectedBox){
        this.selectedBox=selectedBox;
    }
    private BooleanProperty chosen;
    public BooleanProperty chosenProperty(){
        if(chosen==null){
            chosen=new SimpleBooleanProperty(this,"chosen");
            chosen.set(false);
            chosen.addListener(new ChangeListener<Boolean>(){

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue){
                        getStyleClass().remove("super-sombra");
                        getStyleClass().add("super-sombra");
                        
                    }
                    else{
                        getStyleClass().remove("super-sombra");
                        
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return chosen;
    }
    public void setChosen(boolean chosen){
        chosenProperty().set(chosen);
    }
    public boolean isChosen(){
        return chosenProperty().get();
    }
    public void setSelected(boolean selected){
        this.selectedBox.setSelected(selected);
        
    }
    public void setRippleColor(String hexColor){
        
        ((MaterialDisplayText)this.lookup("#ripple_container")).setRippleColor(Color.web("#"+hexColor));
    }
    public String getRippleColor(){
        
       return  ((MaterialDisplayText)this.lookup("#ripple_container")).getRippleColorString();
    }
    public void removeRipple(){
        this.lookup("#check-box-container").setVisible(false);
        this.lookup("#check-box-container").setManaged(false);
        
        this.lookup("#ripple_container").setVisible(false);
        this.lookup("#ripple_container").setManaged(false);
    }
    public void restoreRipple(){
                this.lookup("#check-box-container").setVisible(true);
        this.lookup("#check-box-container").setManaged(true);
         this.lookup("#ripple_container").setManaged(true);
         this.lookup("#ripple_container").setVisible(true);
    }
    public boolean isSelected(){
        if(selectedBox!=null){
            return selectedBox.isSelected();
        }
        return false;
    }
        /*private MaterialStandardList menu;
        private MaterialIconButton iconButton;
        //private HBox rootBox;
        private Pane openPane;
        
        public MaterialStandardListItem(String texto, String icon, MaterialStandardList menu,  Pane openNode){
           this.menu=menu;
           initItem(texto, icon);
           selected=false;
           this.openPane=openNode;
        }
        public MaterialStandardListItem(String texto, String icon){
            this(texto,icon,null,null);
        }
        boolean selected;
        private MaterialDisplayText itemText;
        private void openPane(){
            
                menu.getContainerDrawer().getStandardLayout().setRootView(openPane);
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
                
                
                new Timeline(new KeyFrame(
        Duration.millis(200),
        ae ->  openPane()
               ))
    .play();
                
            }
        }
        public String getName(){
            return name;
        }
        private String name;
        private void initItem(String texto, String icon){
            getStyleClass().removeAll("material-menu-item");
            getStyleClass().addAll("material-menu-item");
            name=texto;
            itemText=new MaterialDisplayText(texto,MaterialDisplayText.MENU_ITEM){
                @Override
                public void onRippleEffectFinished(){
                    
                    getStyleClass().remove("background_grey_e0");
                    getStyleClass().add("background_grey_e0");
                
                }
            
            };
            itemText.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if(menu!=null){
                        for(int i=0;i<menu.size();i++){
                            menu.getItem(i).setSelected(false);
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
            itemText.setFillContainer(this);
            iconButton=new MaterialIconButton();
            iconButton.setMini(true);
            
            if(icon!=null){
                iconButton.setIcon(icon);
            }
            iconButton.setColor(MaterialColor.BLACK_54);
            VBox buttonStack=new VBox(iconButton);
            buttonStack.prefHeightProperty().bind(this.heightProperty());
            buttonStack.setAlignment(Pos.CENTER_LEFT);
           
            buttonStack.setPadding(new Insets(0,0,0,16));
            getChildren().add(buttonStack);
            
            getChildren().add(itemText);
            
        }
        public void setParentMenu(MaterialStandardList menu){
            this.menu=menu;
        }
        public MaterialStandardList getParentMenu(){
            return menu;
        }
        public void closeParentDrawer(){
            menu.getContainerDrawer().hide();
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
                    itemText.setColor(MaterialColor.BLACK_87);
            }
                    this.selected=selected;
        }
        */
        
        
        
      
    }