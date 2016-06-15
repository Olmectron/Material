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
import com.olmectron.material.layouts.MaterialPane;
import com.olmectron.material.layouts.MaterialStandardLayout;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

/**
 *
 * @author Édgar
 */
public abstract class MaterialDialog extends StackPane implements MaterialComponent {
    //private VBox card;
    public MaterialDialog(){
        super();
        initAll();
 
    }
    public MaterialDialog(Pane pane){
        super();
        this.rootPane=pane;
        initAll();
    }
    public void setHideOnClick(boolean hide){
        dialogStage.setHideOnClick(hide);
    }
    public void setRootPane(Pane pane){
        this.rootPane=pane;
        initAll();
    }
    public Pane getRootPane(){
        return rootPane;
    }
    private FloatingStage dialogStage;
    private Pane rootPane;
    public Popup getPopupContainer(){
        return dialogStage.getPopupContainer();
    }
    private VBox cView;
    public void addBeforeRoot(Node node){
        if(cView!=null){
            cView.getChildren().add(0,node);
        }
    }
    private void initAll(){
        //getStyleClass().remove("material-drawer");
        //getStyleClass().add("material-drawer");
        if(rootPane!=null){
            //BorderPane cPane=new BorderPane();
            cView=new VBox(rootPane);
            cView.setAlignment(Pos.CENTER);
            cView.setPadding(new Insets(-30,0,0,0));
            //cPane.setCenter(cView);
            dialogStage=new FloatingStage(cView){
                @Override
                public void onShown(){
                    onDialogShown();
                }
                @Override
                public void onHidden(){
                    onDialogHidden();
                }
@Override
                public void onPopupKeyReleased(KeyEvent event) {
                    onDialogKeyReleased(event);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                @Override
                public void onPopupKeyPressed(KeyEvent event) {
                    onDialogKeyPressed(event);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };    
            
        
        }
        else{
            cView=null;
            dialogStage=new FloatingStage(this){
                   @Override
                public void onShown(){
                    onDialogShown();
                }
                @Override
                public void onHidden(){
                    onDialogHidden();
                }
                @Override
                public void onPopupKeyReleased(KeyEvent event) {
                    onDialogKeyReleased(event);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                @Override
                public void onPopupKeyPressed(KeyEvent event) {
                    onDialogKeyPressed(event);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            
            
        }
        
        
        
        
       // getStyleClass().add("rojo");
        //this.mouseTransparentProperty().set(true);
        
    }
    
    public abstract void onDialogShown();
    public abstract void onDialogHidden();
    public abstract void onDialogKeyReleased(KeyEvent event);
    public abstract void onDialogKeyPressed(KeyEvent event);
    public void addComponent(Node node){
        if(rootPane!=null){
            rootPane.getChildren().add(node);
        }
        else{
            this.getChildren().add(node);
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
        dialogStage.hide();
            //dialogStage.setVisible(false);
        //setVisible(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unhide() {
        //this.translateXProperty().set(-288);
        dialogStage.show();
            //dialogStage.setVisible(true);
        //setVisible(true);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    
    
}
