/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.layouts;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialCard;
import com.olmectron.material.components.MaterialTabs;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Édgar
 */
public abstract class MaterialPane extends StackPane {
    public abstract void onShown();
    public void onHidden(){};
    private String colorClass=null;
    public void onPressedTwice(){
       
    }
    public void setBackgroundColor(String color){
        if(colorClass!=null){
            getContentCard().getStyleClass().remove(colorClass);
        }
        getContentCard().getStyleClass().add(color);
        colorClass=color;
    }
    public Pane getFixedFillPane(){
        
        getContentCard().setCardHeight(2000);
            getContentCard().setCardWidth(2000);
            
            Pane plainCard=getCoreSmartCard();
            plainCard.getChildren().get(0).getStyleClass().removeAll("material-card","material-plain-card");
            plainCard.getChildren().get(0).getStyleClass().removeAll("material-plain-card");
        //plainCard.getStyleClass().add("material-plain-card");
            Pane postedPane=getCoreSmartCard();
            return postedPane;
    }
    public Pane getFixedPane(){
        
        //getContentCard().setCardHeight(2000);
          //  getContentCard().setCardWidth(2000);
            
            Pane plainCard=getCoreSmartCard();
            plainCard.getChildren().get(0).getStyleClass().removeAll("material-card","material-plain-card");
            plainCard.getChildren().get(0).getStyleClass().removeAll("material-plain-card");
        //plainCard.getStyleClass().add("material-plain-card");
            Pane postedPane=getCoreSmartCard();
            return postedPane;
    }
    public abstract void onKeyPressed(KeyEvent event);
    private MaterialTabs tabs=null;
    public void setTabs(MaterialTabs tabs){
        this.tabs=tabs;
    }
    public MaterialTabs getTabs(){
        return tabs;
    }
    
    public boolean hasTabs(){
            return tabs!=null;
        
    }
    public void onKeyReleased(KeyEvent event){
        
    }
    private Node mainCard=null;
    public static int NORMAL_SHADOW=1;
    public static int FLOATING_SHADOW=2;
    
    public MaterialPane(){
        this(1);
    }
    public MaterialPane(int shadow){
        super();
        this.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                onKeyPressed(event);

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                onKeyReleased(event);

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        MaterialCard card=new MaterialCard();
        
        card.setZ(shadow);
        setContentCard(card);
    }
    public void setCardFullWidth(){
        
        getContentCard().setCardHeight(MaterialDesign.primary.getHeight()*0.65);
        
    }
    public Pane getCoreSmartCard(){
        Pane coreCard=getContentCard().getCoreStackCard();
        coreCard.setOnKeyPressed(this.getOnKeyPressed());
        coreCard.setOnKeyReleased(this.getOnKeyReleased());
        return coreCard;
    }
    public void setContentCard(MaterialCard card){
        checkRemoveMain();
        card.setCardWidth(600); //ERA 600
        //card.setCardHeight(2000); //NO ESTABA DEFINIDO ANTES
        //card.setCardWidth(2000);
        this.mainCard=card;
        
        getChildren().add(mainCard);
    }
    private void checkRemoveMain(){
        if(mainCard!=null){
            getChildren().remove(mainCard);
        }
    }
    protected void setUsesFullscreen(boolean f){
        this.full=f;
    }
    private boolean full=false;
    public boolean usesFullscreen(){
        return full;
    }
    public void setContentPane(Pane pane){
        checkRemoveMain();
//pane.prefWidthProperty().bind(this.widthProperty());
        
        this.mainCard=pane;
        getChildren().add(pane);
    }
    public void setContentNode(Node node){
        checkRemoveMain();
        this.mainCard=node;
        getChildren().add(node);
    }
    
    public void setRootComponent(Node node){
        
        try{
            ((MaterialCard)mainCard).removeAllComponents();
            ((MaterialCard)mainCard).addComponent(node);
        }
        catch(Exception ex){
            ((Pane)mainCard).getChildren().clear();
            ((Pane)mainCard).getChildren().add(node);
        }
        
    }
    
    public MaterialCard getContentCard(){
        return (MaterialCard)mainCard;
    }
}
