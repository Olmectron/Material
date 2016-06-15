/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.layouts.MaterialPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Édgar
 */
public abstract class MaterialCardDialog extends MaterialDialog{
    private final MaterialPane mainPane;
    
   public MaterialCardDialog(){
       this(null);
   }
   public void setCardWidth(int ancho){
       
        mainPane.getContentCard().setCardWidth(ancho);
   }
  
   
   public void setCardPadding(Insets insets){
       mainPane.getContentCard().setCardPadding(insets);
   }
   private Pane rootBox;
    public MaterialCardDialog(Pane pane){
        super(new MaterialPane() {

            @Override
            public void onShown() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onKeyPressed(KeyEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        mainPane=(MaterialPane)getRootPane();
        mainPane.getContentCard().setZ(MaterialPane.FLOATING_SHADOW);
        
        
        if(pane==null){
            rootBox=new VBox();
        
            mainPane.setRootComponent(rootBox);
        }
        else{
            rootBox=pane;
            mainPane.setRootComponent(rootBox);
        }
        
        
    }
    public void add(Node node){
        rootBox.getChildren().add(node);
    }
    public void dismiss(){
        this.hide();
    }

    
}
