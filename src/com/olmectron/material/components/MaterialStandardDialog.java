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
 * @author olmec
 */
public abstract class MaterialStandardDialog extends MaterialDialog {
    private final MaterialPane mainPane;
    
    
    public void setDialogWidth(double w){
        mainPane.getContentCard().setCardWidth(w);
    }
    public void setDialogHeight(double h){
        mainPane.getContentCard().setCardHeight(h);
    }
    
    public abstract void onPressedKey(KeyEvent event);
        
    
    public abstract void onReleasedKey(KeyEvent event);
    public MaterialStandardDialog(){
        this(null);
    }
    public abstract void onCreate(Pane pane);
    public MaterialStandardDialog(Pane pane){
        super();
        this.setRootPane(new MaterialPane() {

            @Override
            public void onShown() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void onKeyReleased(KeyEvent event) {
                onReleasedKey(event);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void onKeyPressed(KeyEvent event) {
                onPressedKey(event);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        mainPane=(MaterialPane)getRootPane();
        mainPane.getContentCard().setZ(MaterialPane.FLOATING_SHADOW);
        if(pane!=null){
            rootBox=pane;
        }
        else{
            rootBox=new VBox();
            ((VBox)rootBox).setAlignment(Pos.CENTER);
        }
        mainPane.getContentCard().setCardPadding(new Insets(0,0,0,0));
        mainPane.setRootComponent(rootBox);
        
        onCreate(rootBox);
        
    }
    public Pane getMainPane(){
        return mainPane;
    }
    public void setCardPadding(Insets insets){
        
        mainPane.getContentCard().setCardPadding(insets);
    }
    private Pane rootBox;
    public void addNode(Node node){
       
        rootBox.getChildren().add(node);
    }
    public void dismiss(){
        hide();
    }

    

}
