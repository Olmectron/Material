/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.menu.MaterialMenuItem;
import com.olmectron.material.components.menu.MenuPane;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.ripple.CircleRipple;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import com.sun.javafx.scene.control.skin.LabelSkin;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */
public class MaterialMenu extends MenuPane implements MaterialComponent{
    public static final int MEDIUM=1;
    public static final int BIG=2;
    public static final int HOUR_SIZE=3;
    public static final int USER_SIZE=4;
    public static final int USER_SIZE_REGULAR=5;
    
    private VBox rootLayout;
    private Pane containerPane;
    public Pane getContainerPane(){
        return containerPane;
    }
    public MaterialDrawer getContainerDrawer(){
        
        return (MaterialDrawer)containerPane;
    }
    public MaterialMenu(Pane container){
        
        setContainer(container);
        
        initAll();
        
    }
    public void setContainer(Pane pane){
        this.containerPane=pane;
        this.prefWidthProperty().bind(pane.widthProperty());
        
    }
    public MaterialMenuItem getItem(int id){
        try{
            return (MaterialMenuItem)rootLayout.getChildren().get(id);
            
        }
        catch(ClassCastException ex){
            return null;
        }
            
    }
    public int size(){
        return rootLayout.getChildren().size();
    }
    public void addItem(String texto, String icon, Pane openNode){
        MaterialMenuItem ictem=new MaterialMenuItem(texto,icon,this,openNode);
        rootLayout.getChildren().add(ictem);
        items.add(ictem.clonar());
    }
    public void addNodeAsItem(Node node){
        rootLayout.getChildren().add(node);
    }
    public void addItem(MaterialMenuItem item){
        rootLayout.getChildren().add(item);
        items.add(item.clonar());
        item.setParentMenu(this);
        
    }
    public void restoreItems(){
        rootLayout.getChildren().clear();
        for(MaterialMenuItem objeto: items){
            rootLayout.getChildren().add(objeto.clonar());
        }
    }
    private ArrayList<MaterialMenuItem> items;
    public void setMiniMenu(boolean miniDrawer){
        for(int i=0;i<size();i++){
            MaterialMenuItem objecto=getItem(i);
            if(objecto!=null){
                objecto.setMiniMenuItem(miniDrawer);
            
            }
            
            //items.get(i).setMiniMenuItem(miniDrawer);
        }
        //restoreItems();
        
    }
    
    private void initAll(){
        //getStyleClass().addAll(colorActual.toString());
        items=new ArrayList<MaterialMenuItem>();
        getStyleClass().removeAll("material-menu");
        getStyleClass().addAll("material-menu");
        rootLayout=new VBox();
        getChildren().add(rootLayout);
        
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

