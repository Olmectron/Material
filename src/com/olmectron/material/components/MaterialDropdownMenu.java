/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.MaterialPopupComponent;
import static com.olmectron.material.components.MaterialToast.setLastToast;

import com.olmectron.material.components.menu.MenuPane;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialPane;
import com.olmectron.material.utils.LayoutMath;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */
public class MaterialDropdownMenu extends Popup implements MaterialPopupComponent {
    public static final int MEDIUM=1;
    public static final int BIG=2;
    public static final int HOUR_SIZE=3;
    public static final int USER_SIZE=4;
    public static final int USER_SIZE_REGULAR=5;
    
    private VBox rootLayout;
    private MaterialCard materialMenu;
    public MaterialCard getMaterialContainer(){
        return materialMenu;
    }
    private double offsetX=0;
    private double offsetY=0;
    public void setOffset(double x,double y){
        offsetX=x;
        offsetY=y;
    }
    public MaterialDropdownMenu(double x, double y){
        materialMenu=new MaterialCard();
        materialMenu.setCardWidth(250);
        materialMenu.setCardPadding(new Insets(8,0,8,0));
        materialMenu.setZ(MaterialPane.FLOATING_SHADOW);
        materialMenu.setOpacity(0.0);
        //materialMenu.getStyleClass().add("material-toast");
        //l.getStyleClass().add("medium-font");
        getContent().add(materialMenu);
        
        setPosition(x,y);
        initAll();
        
        //setOutsideListeners();
        
    }
    public MaterialDropdownMenu(Region node){
        this(node,false);
    }
    private boolean dialog=false;
    public MaterialDropdownMenu(Region node, boolean dialog){
        this.dialog=dialog;
        this.nodo=node;
        materialMenu=new MaterialCard();
        materialMenu.setCardWidth(250);
        materialMenu.setCardPadding(new Insets(8,0,8,0));
        materialMenu.setZ(MaterialPane.FLOATING_SHADOW);
        materialMenu.setOpacity(0.0);
        //materialMenu.getStyleClass().add("material-toast");
        //l.getStyleClass().add("medium-font");
        getContent().add(materialMenu);
        
        setPosition(LayoutMath.getAbsoluteX(nodo),LayoutMath.getAbsoluteY(nodo)-8);
        initAll();
        
    }
    private Region nodo=null;
    public void setPosition(double x, double y){
        
        //if(x+250>=MaterialDesign.screen_bounds.getWidth() && nodo!=null){
        if(x+250>=MaterialDesign.primary.getScene().getWindow().getWidth() && nodo!=null){
            this.setX(x-250+nodo.getWidth()+offsetX);
        }
        else{
            this.setX(x+offsetX);
        }
        
        this.setY(y);
    }
    
    public MaterialDropdownMenuItem getItemAt(int id){
            return (MaterialDropdownMenuItem)rootLayout.getChildren().get(id);
            
    }
    public int size(){
        return rootLayout.getChildren().size();
    }
    public void addItem(String texto){
        rootLayout.getChildren().add(new MaterialDropdownMenuItem(texto,null,this,null));
    }
    public void addNodeAsItem(Node node){
        rootLayout.getChildren().add(node);
    }
    public void addItem(String texto, String icon, Pane openNode){
        rootLayout.getChildren().add(new MaterialDropdownMenuItem(texto,icon,this,openNode));
    }
    public void addItems(ArrayList<MaterialDropdownMenuItem> items){
        for (MaterialDropdownMenuItem item : items) {
            addItem(item);
        }
    }
    public void addItem(MaterialDropdownMenuItem item){
        rootLayout.getChildren().add(item);
        
        item.setParentMenu(this);
    }
    private void initAll(){
        //getStyleClass().addAll(colorActual.toString());
        DropShadow dropShadow = new DropShadow();
        dropShadow.setBlurType(BlurType.ONE_PASS_BOX);
        
 dropShadow.setRadius(10.0);
 dropShadow.setOffsetX(0.0);
 dropShadow.setOffsetY(6.0);
 dropShadow.setColor(Color.rgb(0, 0, 0,0.3)); 
        materialMenu.getCoreStackCard().setEffect(dropShadow);
        materialMenu.getCoreStackCard().setCache(true);
        
 
 rootLayout=new VBox();
        materialMenu.addComponent(rootLayout);
        
        
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
                FadeTransition ft=new FadeTransition(Duration.millis(250),materialMenu);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        materialMenu.setOpacity(1.0);
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
        materialMenu.getStyleClass().remove(colorActual.toString());
        materialMenu.getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    public void removeColor() {
        materialMenu.getStyleClass().remove(colorActual.toString());
        
        //throw new UnsupportedOerationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unhide() {
        if(nodo!=null){
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
                  point.getY()+offsetY); 
                  
            }
            else{
                
                Point2D point = nodo.localToScene(0.0,  0.0);
          setPosition(MaterialDesign.primary.getX()+MaterialDesign.getScene().getX()+point.getX(),
                  MaterialDesign.primary.getY()+MaterialDesign.getScene().getY()+point.getY()+offsetY); 
        
            }
            
          /*Point2D point = nodo.localToScene(0.0,  0.0);
          setX(MaterialDesign.primary.getX() + point.getX());
          setY(MaterialDesign.primary.getY() + point.getY()); */
        
        }
            
            
            
            
            
            
        //setPosition(LayoutMath.getAbsoluteX(nodo),LayoutMath.getAbsoluteY(nodo)-8);
        }
        
        if(lastToast!=null){
            lastToast.hide();
        }
        
        setLastMenu(this);
        
        show(MaterialDesign.primary);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void hideMenu(){
        toastHide(materialMenu);
        
    }
    private void toastHide(Node n){
        FadeTransition ft=new FadeTransition(Duration.millis(250),n);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        n.setOpacity(0.0);
                        hide();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                ft.play();
        
        
    }
     private static MaterialDropdownMenu lastToast=null;
    public static void setLastMenu(MaterialDropdownMenu toast){
        lastToast=toast;
    }
    public static MaterialDropdownMenu getLastMenu(){
        return lastToast;
    }
    
    
}

