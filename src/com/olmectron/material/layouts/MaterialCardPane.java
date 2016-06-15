/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.layouts;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author olmec
 */
public abstract class MaterialCardPane extends MaterialPane {

    
    
    public MaterialCardPane(){
        super();
        initAll();
    }
    private HBox rootBox;
    private void initAll(){
        
        setUsesFullscreen(true);
        rootBox=new HBox();
        rootBox.setAlignment(Pos.CENTER);
        rootBoxListener();
        ScrollPane scroll=new ScrollPane(rootBox);
        
        scroll.getStyleClass().remove("material-scroll");
        scroll.getStyleClass().add("material-scroll");
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setOnScroll(new EventHandler<ScrollEvent>() {
        
            

            @Override
            public void handle(ScrollEvent event) {
              //      node.setTranslateX(node.getTranslateX() + event.getDeltaX());
            //node.setTranslateY(node.getTranslateY() + event.getDeltaY());
                System.out.println("Delta x: "+event.getDeltaX()+", Delta Y: "+event.getDeltaY());
                ///if(event.getDeltaY())
                scroll.setHvalue(scroll.getHvalue()+(0.002*event.getDeltaY()));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    });
        this.setContentNode(scroll);
        
    }
    private void rootBoxListener(){
        rootBox.getChildren().addListener(new ListChangeListener<Node>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Node> c) {
                if(rootBox.getChildren().size()<=0){
                  
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    public void addCardPane(MaterialPane pane){
        pane.setPadding(new Insets(0,0,0,24));
        
        rootBox.getChildren().stream().map((children) -> {
            TranslateTransition translateAnim=new TranslateTransition();
            //translateAnim.setToX(((Pane)children).getWidth()+12);
            translateAnim.setNode(children);
            return translateAnim;
        }).map((translateAnim) -> {
            translateAnim.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            return translateAnim;
        }).forEach((translateAnim) -> {
            translateAnim.play();
        });
        //pane.setOpacity(0.0);
        rootBox.getChildren().add(0, pane);
        System.out.println(-pane.getWidth()-150+" bajado");
        pane.setTranslateX(-pane.getWidth()-150);
        
        
        //pane.setOpacity(1.0);
        TranslateTransition translate=new TranslateTransition();
            translate.setToX(0);
            translate.setNode(pane);
            translate.play();
        
      
        
        
    }
    
}
