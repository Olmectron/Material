/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.MaterialPopupComponent;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialPane;
import com.olmectron.material.utils.LayoutMath;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author olmec
 */
public class MaterialTooltip extends Popup implements MaterialPopupComponent {
    public static final int MEDIUM=1;
    public static final int BIG=2;
    public static final int HOUR_SIZE=3;
    public static final int USER_SIZE=4;
    public static final int USER_SIZE_REGULAR=5;
    
    private double putX=0, putY=0;
    public MaterialTooltip(String message, double x, double y){
        
        tooltip=new VBox();
        tooltip.getStyleClass().remove("material-tooltip");
        tooltip.getStyleClass().add("material-tooltip");
        tooltip.setAlignment(Pos.CENTER);
        Label mainLabel=new Label(message);
        mainLabel.getStyleClass().addAll("medium-font","material-tooltip-label");
        tooltip.getChildren().add(mainLabel);
        //materialMenu.getStyleClass().add("material-toast");
        //l.getStyleClass().add("medium-font");
        getContent().add(tooltip);
        putX=x;
        putY=y;
        setPosition(x,y);
        initAll();
        
        //setOutsideListeners();
        
    }
    private VBox tooltip;
    public MaterialTooltip(String message, Region node){
        this(message,node,false);
    }
    private boolean dialog=false;
    private Label mainLabel;
    public void setText(String me){
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                
        mainLabel.setText(me);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    public MaterialTooltip(String message, Region node, boolean dialog){
        this.dialog=dialog;
        this.nodo=node;
        nodo.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                showTooltip();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        nodo.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                hideMenu();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        tooltip=new VBox();
        tooltip.getStyleClass().remove("material-tooltip");
        tooltip.getStyleClass().add("material-tooltip");
        tooltip.setMaxWidth(300);
        tooltip.setAlignment(Pos.CENTER);
        mainLabel=new Label(message);
        mainLabel.setWrapText(true);
        mainLabel.setLineSpacing(2);
        mainLabel.getStyleClass().addAll("medium-font","material-tooltip-label");
        tooltip.getChildren().add(mainLabel);
        //materialMenu.getStyleClass().add("material-toast");
        //l.getStyleClass().add("medium-font");
        getContent().add(tooltip);
        
        setPosition(LayoutMath.getAbsoluteX(nodo),LayoutMath.getAbsoluteY(nodo)-8);
        initAll();
        
    }
    
    private Region nodo=null;
    public void setPosition(double x, double y){
        
       
            this.setX(x);
        
        
        this.setY(y);
    }
    
    private void checkPosition(double x){
         /*if(x+tooltip.getWidth()>=MaterialDesign.screen_bounds.getWidth() && nodo!=null){
            this.setX(x-10);
            //System.out.println("Es mayor cim gallets");
        }
         else{
             this.setX(x);
             
         }*/
         
    }
    private void initAll(){
        //getStyleClass().addAll(colorActual.toString());
        
        //materialMenu.addComponent(rootLayout);
        
        
        this.setOnShown(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent event) {
                //setX(16);
                //setY(MaterialDesign.primary.getHeight()-16-getHeight());
                /*TranslateTransition tt = new TranslateTransition(Duration.millis(1000), l);
                tt.setToY(0);
                l.translateYProperty().set(getHeight()+16);
                tt.play();
                */
                if(!topPos){
                    checkPosition(
                    getX()-tooltip.getWidth()/2);
                }
               
                FadeTransition ft=new FadeTransition(Duration.millis(250),tooltip);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        tooltip.setOpacity(1.0);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                ft.play();
               /* new Timeline(new KeyFrame(
        Duration.millis(4000),
        ae -> toastHide(l)))
    .play();*/
            }
        
        });
        
        
        
        //addItem("Ejemplo",null,null);
        //addItem("Ver datos",null,null);
        
    }

     private MaterialColor colorActual=MaterialDesign.primaryColor;
    
    public void setColor(MaterialColor color) {
        tooltip.getStyleClass().remove(colorActual.toString());
        tooltip.getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    private void showTooltip(){
        unhide();
    }
    public void removeColor() {
        tooltip.getStyleClass().remove(colorActual.toString());
        //throw new UnsupportedOerationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setTopPos(boolean b){
        topPos=b;
    }
    private boolean shord=false;
    public void setShortDistance(boolean shortd){
        shord=shortd;
    }
    private boolean topPos=false;
    @Override
    public void unhide() {
        if(nodo!=null){
            /*if(topPos){
                setPosition(LayoutMath.getAbsoluteX(nodo), LayoutMath.getAbsoluteY(nodo)-8-14);
            }
            else{
                if(shord){
                    setPosition(LayoutMath.getAbsoluteX(nodo)+nodo.getWidth()/2,LayoutMath.getAbsoluteY(nodo)-8 + nodo.getHeight()-5);
        
                }
                else
                setPosition(LayoutMath.getAbsoluteX(nodo)+nodo.getWidth()/2,LayoutMath.getAbsoluteY(nodo)-8 + nodo.getHeight()+14);
        
            }*/
            if(dialog){
        Point2D point = nodo.localToScene(0.0,  0.0);
          setPosition(point.getX(),
                  point.getY()+nodo.getHeight()+14); 
            }
            else{
                Point2D point = nodo.localToScene(0.0,  0.0);
          setPosition(MaterialDesign.primary.getX()+MaterialDesign.getScene().getX()+point.getX(),
                  MaterialDesign.primary.getY()+MaterialDesign.getScene().getY()+point.getY()+nodo.getHeight()+14); 
        
            }
          /*Point2D point = nodo.localToScene(0.0,  0.0);
          setX(MaterialDesign.primary.getX() + point.getX());
          setY(MaterialDesign.primary.getY() + point.getY()); */
        
        }
        else{
            setPosition(putX,
                  putY+14); 
        }
        
        if(lastToast!=null && !lastToast.equals(this)){
            lastToast.hide();
        }
        
        /*if(lastToast!=null && lastToast.equals(this)){
            if(fts!=null){
                fts.stop();
                if(nodo!=null){
            
                setPosition(LayoutMath.getAbsoluteX(nodo)+nodo.getWidth()/2,LayoutMath.getAbsoluteY(nodo)-8 + nodo.getHeight()+14);
                }
                tooltip.setOpacity(1.0);
            }
        }*/
        
        setLastTooltip(this);
        if(!locked)
        show(MaterialDesign.primary);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setLocked(boolean cs){
        locked=cs;
    }
    private boolean locked=false;
    public void hideMenu(){
        hide();
//toastHide(tooltip);
    }
    private FadeTransition fts;
    private void toastHide(Node n){
        fts=new FadeTransition(Duration.millis(250),n);
                fts.setFromValue(1.0);
                fts.setToValue(0.0);
                fts.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        n.setOpacity(0.0);
                        hide();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                fts.play();
        
        
    }
     private static MaterialTooltip lastToast=null;
    public static void setLastTooltip(MaterialTooltip toast){
        lastToast=toast;
    }
    public static MaterialTooltip getLastTooltip(){
        return lastToast;
    }
}
