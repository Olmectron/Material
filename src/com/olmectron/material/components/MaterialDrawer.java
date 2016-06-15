/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.menu.MaterialMenuItem;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.helpers.FloatingStage;
import com.olmectron.material.layouts.MaterialStandardLayout;
import java.io.File;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 *
 * @author Édgar
 */
public class MaterialDrawer extends FlowPane implements MaterialComponent {
    //private VBox card;
    public MaterialDrawer(){
        super();
        initAll();
 
    }
    private Node node=null;
    private FloatingStage drawerStage;
    private Pane container=null;
    public Pane getContainerLayout(){
        return container;
    }
    public MaterialStandardLayout getStandardLayout(){
        return (MaterialStandardLayout)container;
    }
    public MaterialDrawer(Pane containerLayout){
        super();
        this.container=containerLayout;
        initAll();
    }
    public MaterialDrawer(Node node, boolean nodeBoolean){
        super();
        this.node=node;
        initAll();
    }
    private MaterialMenu drawerMenu;
    private MaterialDisplayText tipoUsuario;
    public void addDrawerMenuItem(String texto, String icon, Pane node){
      drawerMenu.addItem(texto, icon,node);
    }
    private MaterialDisplayText nombreUsuario;
    public void addDrawerMenuItem(MaterialMenuItem item){
        drawerMenu.addItem(item);
    }
    private ImageView userImage;
    private void initAll(){
        getStyleClass().remove("material-drawer");
        getStyleClass().add("material-drawer");
        BorderPane containerPane=new BorderPane();
        containerPane.setLeft(this);
        StackPane materialDrawerTop=new StackPane();
        materialDrawerTop.getStyleClass().remove("material-drawer-top");
        materialDrawerTop.getStyleClass().add("material-drawer-top");
        StackPane bottomUserPane=new StackPane();
        
        VBox userBox=new VBox();
        
        //userBox.getStyleClass().remove("transparent-black25-container");
        userBox.setStyle(getStyle()+"-fx-background-color: rgba(0,0,0,0.20);");
        bottomUserPane.getStyleClass().remove("usuario-top");
        bottomUserPane.getStyleClass().add("usuario-top");
        userImage=new ImageView(MaterialDesign.images.avatar);
        
        userImage.setFitWidth(102);
         userImage.setPreserveRatio(true);
         Circle circle = new Circle();
circle.setCenterX(userImage.getFitWidth()/2);
circle.setCenterY(userImage.getFitWidth()/2);
circle.setRadius(userImage.getFitWidth()/2);
userImage.setClip(circle);
        VBox userImageContainer=new VBox(userImage);
        
        userImageContainer.getStyleClass().remove("usuario-image");
        userImageContainer.getStyleClass().add("usuario-image");
        nombreUsuario=new MaterialDisplayText("Ejemplo",MaterialDisplayText.USER_SIZE);
        nombreUsuario.setColor(MaterialColor.WHITE);
        nombreUsuario.setPadding(new Insets(12,0,0,0));
        
        
        tipoUsuario=new MaterialDisplayText("Administrador",MaterialDisplayText.USER_SIZE_REGULAR);
        tipoUsuario.setColor(MaterialColor.WHITE);
        tipoUsuario.setPadding(new Insets(0,0,0,0));
        
        
        
        userImageContainer.getChildren().addAll(nombreUsuario,tipoUsuario);
        
        
        userBox.getChildren().add(materialDrawerTop);
        userBox.getChildren().add(userImageContainer);
        bottomUserPane.getChildren().add(userBox);
            getChildren().add(bottomUserPane);
        drawerMenu=new MaterialMenu(this);
        
        this.getChildren().add(drawerMenu);
        //this.getChildren().add(new MaterialButton("Genial"));
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //System.out.println("Presionado");
                event.consume();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        drawerStage=new FloatingStage(containerPane) {

            @Override
            public void onPopupKeyPressed(KeyEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
       // getStyleClass().add("rojo");
        //this.mouseTransparentProperty().set(true);
        
    }
    public void setMiniDrawer(boolean miniDrawer){
        drawerMenu.setMiniMenu(miniDrawer);
        
        getStyleClass().remove("mini");
        if(miniDrawer){
            getStyleClass().add("mini");
        }
    }
    public ImageView getUserImage(){
        return userImage;
    }
    
    public void addComponent(Node node){
        this.getChildren().add(node);
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
        drawerStage.setVisible(false);
        //setVisible(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unhide() {
        //this.translateXProperty().set(-288);
        drawerStage.setVisible(true);
        //setVisible(true);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
