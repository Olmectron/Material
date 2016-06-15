/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.helpers;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialDropdownMenu;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */
public class FloatingStage {
    private Pane floatingPane;
    private Popup floating;
    public FloatingStage(Pane floatingPane){
        this(floatingPane,false);
    }
    public FloatingStage(){
        root=new StackPane();
        initFloatingStage();
    }
    private boolean centerOnScreen=false;
    public FloatingStage(Pane floatingPane, boolean center){
        this.floatingPane=floatingPane;
        this.centerOnScreen=center;
        root=new StackPane();
        
        initFloatingStage();
                
    }
    private StackPane root;
    private void initKeyListeners(Pane pane){
       pane.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                            //System.out.println("juerngviansgvijasndsv");
                           
                            MaterialDropdownMenu m=MaterialDropdownMenu.getLastMenu();
                            if(m!=null){
                                m.hideMenu();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
       pane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

           @Override
           public void handle(KeyEvent event) {
               if(event.getCode()==KeyCode.ESCAPE){
                   hide();
                   
               }
               onPopupKeyPressed(event);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });
       pane.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {

           @Override
           public void handle(KeyEvent event) {
               
               onPopupKeyReleased(event);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });
    }
    public void onPopupKeyReleased(KeyEvent event){};
    public void onPopupKeyPressed(KeyEvent event){};
    public Popup getPopupContainer(){
        return floating;
    }
    private boolean closeOnClick=true;
    public void setHideOnClick(boolean toHide){
        closeOnClick=toHide;
    }
    private void initFloatingStage(){
                
                    floating = new Popup();
                root.setCache(true);
root.setCacheShape(true);
root.setCacheHint(CacheHint.SPEED);
floating.setHideOnEscape(false);
                //floating.setOpacity(1.0);
                floating.setWidth(MaterialDesign.screen_bounds.getWidth());
                floating.setHeight(MaterialDesign.screen_bounds.getHeight());
                
                //floating.initOwner(MaterialDesign.primary);
                //floating.initModality(Modality.APPLICATION_MODAL);
                //floating.initStyle(StageStyle.TRANSPARENT);
                //System.out.println(MaterialDesign.screen_bounds.getWidth()+" coma "+MaterialDesign.screen_bounds.getHeight());
                //Scene floatingScene = new Scene(root, MaterialDesign.screen_bounds.getWidth(), MaterialDesign.screen_bounds.getHeight());
                
                //floatingScene.setFill(Color.TRANSPARENT);
                //new MaterialDesign().setScene(floatingScene);
                //initKeyListeners(root);
                root.getStyleClass().remove("transparent-container");
                
                root.getStyleClass().add("transparent-container");
                root.setMinWidth(floating.getWidth());
                root.setMinHeight(floating.getHeight());
                if(floatingPane!=null){
                    root.getChildren().add(this.floatingPane);
                   
                
                }
                root.setLayoutX(0);
                root.setLayoutY(0);
                root.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if(closeOnClick){
                            setVisible(false);
                        
                        }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                floating.getContent().add(root);
                floating.setOnShowing(new EventHandler<WindowEvent>() {

                    @Override
                    public void handle(WindowEvent event) {
                        initKeyListeners(root);
                        if(MaterialDesign.isAnimated()){
                        
                         FadeTransition ft=new FadeTransition(Duration.millis(300),root);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        root.setOpacity(1.0);
                        onShown();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                ft.play();
                        
                        }
                        else{
                            
                            root.setOpacity(1.0);
                            onShown();
                        }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                
                
                
                
                //floating.setScene(floatingScene);   
    }
    public void onShown(){}
    public void onHidden(){}
    public void hide(){
        System.out.println("Escondido");
        setVisible(false);
    }
    public void show(){
        setVisible(true);
    }
    private void hideTransition(){
       if(MaterialDesign.isAnimated()){
        FadeTransition ft=new FadeTransition(Duration.millis(200),root);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        root.setOpacity(0.0);
                        floating.hide();
                        onHidden();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                ft.play();
       }
       else{
           root.setOpacity(0.0);
           floating.hide();
           onHidden();
       }

    }
    private boolean visible=false;
    
    public void setVisible(boolean visible){
        if(visible){
            if(FloatingStage.lastStage!=null){
                lastStage.hide();
            }
            setLastStage(floating);
            
            floating.setX(0);
            floating.setY(0);
            
                floating.show(MaterialDesign.primary);
                
        }
        else{
            
            hideTransition();
        }
        this.visible=visible;
    }
    private static void setLastStage(Popup p){
        lastStage=p;
    }
    
    private static Popup lastStage=null;
}
