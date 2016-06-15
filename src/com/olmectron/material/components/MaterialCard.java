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
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;


/**
 *
 * @author Édgar
 */
public class MaterialCard extends StackPane implements MaterialComponent {
    private VBox card;
    public MaterialCard(){
        super();
        initAll();
 
    }
    private DropShadow dropShadow;
    private int zLevel=0;
    public void activateShadow(int zLevel){
        if(this.zLevel!=zLevel){
            card.setEffect(null);
            dropShadow=null;
            this.zLevel=zLevel;
        }
        if(dropShadow==null && zLevel>0){
        dropShadow = new DropShadow();
        dropShadow.setBlurType(BlurType.ONE_PASS_BOX);
        
        
        if(zLevel==1){
            dropShadow.setRadius(4.0);
 dropShadow.setOffsetX(0.0);
 dropShadow.setOffsetY(1.0);
 dropShadow.setColor(Color.rgb(0, 0, 0,0.37));
           
        }
        
        else if(zLevel==2){
 dropShadow.setRadius(10.0);
 dropShadow.setOffsetX(0.0);
 dropShadow.setOffsetY(6.0);
 dropShadow.setColor(Color.rgb(0, 0, 0,0.3)); 
        }
 
 
        card.setEffect(dropShadow);
        card.setCache(true);
        }
    }
    public MaterialFloatingButton getFloatingButton(){
        return floatButton;
    }
    private MaterialFloatingButton floatButton;
    public void setFloatingButton(MaterialFloatingButton floatButton){
        //MaterialFloatingButton anterior=this.floatButton;
        this.floatButton=floatButton;
        floatButtonBox.getChildren().clear();
        
        if(floatButton!=null){
            
            floatButtonBox.getChildren().add(floatButton);
        }
        
    }
    private Node node=null;
    
    public MaterialCard(Node node){
        super();
        this.node=node;
        initAll();
    }
    public void setZ(int numero){
        card.getStyleClass().remove(z);
        z="z"+numero;
        card.getStyleClass().add(z);
    }
    public int getZ(){
        return Integer.parseInt(z.replace("z", ""));
    }
    private String z="z1";
    public void setCardPadding(Insets in){
        card.setPadding(in);
    }
    public StackPane getCoreStackCard(){
        return this;
    }
    public void removeBackgroundColor(){
        if(actualColorCode!=null)
         card.setStyle(card.getStyle().replace(actualColorCode, ""));
    }private String actualColorCode=null;
    public void setBackgroundColor(MaterialColor color){
        actualColorCode="-fx-background-color: "+color.getSmallestWebCode()+" !important;";
        card.setStyle(card.getStyle()+actualColorCode);
    }
    public VBox getPlainCard(){
        card.getStyleClass().removeAll("material-card", "material-plain-card");
        card.getStyleClass().add("material-plain-card");
        return card;
    }
    private HBox floatButtonBox;
    
    public void align(Pos POS){
        card.setAlignment(POS);
    }
    private void initAll(){
       // getStyleClass().add("rojo");
        //this.mouseTransparentProperty().set(true);
        floatButtonBox=new HBox();
        floatButtonBox.setMinHeight(72);
        floatButtonBox.setMinWidth(72);
        floatButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        floatButtonBox.setPadding(new Insets(16)); //BOTTOM_RIGHT
        //floatButtonBox.setPadding(new Insets(-30,16,0,16)); //TOP_LEFT
//floatButtonBox.setPadding(new  Insets(-30,16,0,16)); TOP_RIGHT
        floatButtonBox.setPickOnBounds(false);
       //this.setAlignment(Pos.BASELINE_RIGHT);
        this.setPickOnBounds(false);
        card=new VBox();
        if(node!=null){
            card.getChildren().add(node);
        }
        card.setPadding(new Insets(24,24,24,24));
        card.getStyleClass().removeAll("material-card",z);
        card.getStyleClass().addAll("material-card",z,colorActual.toString());
        card.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //System.out.println("Presionado");
                event.consume();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        card.setPrefWidth(250);
        card.setMaxWidth(250);
        card.mouseTransparentProperty().set(false);
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), card);
     tt.setByX(200f);
     
     //tt.setAutoReverse(true);
 
     
        card.setOnSwipeDown(new EventHandler<SwipeEvent>() {

            @Override
            public void handle(SwipeEvent event) {
                System.out.println("Card right swipe");
                tt.play();
 
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //MouseControlUtil.makeDraggable(card);
        getChildren().add(card);
        getChildren().add(floatButtonBox);
        setPadding(new Insets(0,0,0,0));
        
    }
    private double oldMousePos=0;
    public DoubleProperty cardWidth;
    public DoubleProperty cardWidthProperty(){
        if(cardWidth==null){
            cardWidth=new SimpleDoubleProperty(this,"cardWidth");
            cardWidth.addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    card.setPrefWidth(newValue.doubleValue());
                    card.setMaxWidth(newValue.doubleValue());
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return cardWidth;
    }
    public DoubleProperty cardHeight;
    public DoubleProperty cardHeightProperty(){
        if(cardHeight==null){
            cardHeight=new SimpleDoubleProperty(this,"cardHeight");
            cardHeight.addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    
                    //card.setMinHeight(newValue.doubleValue());
                    card.setPrefHeight(newValue.doubleValue());
                    card.setMaxHeight(newValue.doubleValue());
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return cardHeight;
    }
    public double getCardWidth(){
      
                return card.getWidth();
        
        
    }
    public double getCardHeight(){
        return card.getHeight();
    }
    public void setCardWidth(double width){
        //card.setMinWidth(width);
        cardWidthProperty().set(width);
        //card.setMaxWidth(width);
    }
    public void setExactWidth(double width){
        //card.setMinWidth(width);
        card.setMinWidth(width);
        card.setPrefWidth(width);
        card.setMaxWidth(width);
        //card.setMaxWidth(width);
    }
    
    public void setCardHeight(double width){
        //card.setMinWidth(width);
        cardHeightProperty().set(width);
        //card.setMaxWidth(width);
    }
    public void addComponent(Node node){
        card.getChildren().add(node);
    }
    public void removeComponent(Node node){
        card.getChildren().remove(node);
    }
    public void removeAllComponents(){
        for(int i=0;i<card.getChildren().size();i++){
            card.getChildren().remove(card.getChildren().get(i));
        }
    }
 private MaterialColor colorActual=MaterialDesign.primaryColor;
    @Override
    public void setColor(MaterialColor color) {
        getStyleClass().remove(colorActual.toString());
        getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    
    
    
}
