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
import com.olmectron.material.helpers.IconButtonContainer;
import com.olmectron.material.utils.LayoutMath;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Édgar
 */
public abstract class MaterialToolbar extends VBox implements MaterialComponent{
    private boolean big=false;
    private ToolBar t;
    
    public MaterialToolbar(String title, String save){
        if(save.equalsIgnoreCase("EMBLEM")){
            t=new ToolBar(new StackPane(new MaterialIconButton()),new MaterialLabel(title,MaterialLabel.TOOLBAR_TITLE),
                new HBox(), new MaterialDisplayText(""), new IconButtonContainer(MaterialIconButton.FOLDER_OPEN_ICON),
                new IconButtonContainer(MaterialIconButton.SAVE_ICON));
                this.big=false;
                //System.out.println(big);
                initAll();
                //tasdas.setLocked(true);
        }
        else {
            t=new ToolBar(new StackPane(new MaterialIconButton(MaterialIconButton.MENU_ICON)),new MaterialLabel(title,MaterialLabel.TOOLBAR_TITLE),
                new HBox(), new MaterialDisplayText(""), new IconButtonContainer(MaterialIconButton.REFRESH_ICON),
                new IconButtonContainer(MaterialIconButton.MORE_VERT_ICON));
                this.big=false;
                //System.out.println(big);
                initAll();
        }
    }
    public MaterialToolbar(String title, boolean big){
        
        t=new ToolBar(new StackPane(new MaterialIconButton(MaterialIconButton.MENU_ICON)),new MaterialLabel(title,MaterialLabel.TOOLBAR_TITLE),
                new HBox(),new MaterialDisplayText(""), new IconButtonContainer(MaterialIconButton.REFRESH_ICON),
                new IconButtonContainer(MaterialIconButton.MORE_VERT_ICON));
        this.big=big;
        //System.out.println(big);
        initAll();
        
    }
    public MaterialToolbar(String title){
        this(title,false);
    }
    public MaterialStatusBar getStatusBar(){
        
        return statusBar;
    }
     private MaterialColor colorActual=MaterialDesign.primaryColor;
     private String colorStyle=null;
     public void setColorFromCode(MaterialColor webColor){
         if(colorStyle!=null){
            setStyle(getStyle().replace(colorStyle, ""));
        }
         getStyleClass().remove(colorActual.toString());
         colorStyle="-fx-background-color: "+webColor.getStandardWebCode()+";";
         setStyle(getStyle()+colorStyle);
         if(webColor.isBlackForeground("500")){
             titleLabel.setColor(MaterialColor.BLACK_87);
             menuButton.setColor(MaterialColor.BLACK_87);
             updateButton.setColor(MaterialColor.BLACK_87);
             
             optionsButton.setColor(MaterialColor.BLACK_87);
         }
         else{
             titleLabel.setColor(MaterialColor.WHITE);
             menuButton.setColor(MaterialColor.WHITE);
             updateButton.setColor(MaterialColor.WHITE);
             
             optionsButton.setColor(MaterialColor.WHITE);
         }
         //System.out.println("El estilo label: "+titleLabel.getStyle());
         
         //System.out.println("Brightness: "+MaterialColor.getPerceivedBrightness(webCode));
     }
     
    @Override
    public void setColor(MaterialColor color) {
        //statusBar.getStyleClass().remove(colorActual.toString());
        //statusBar.getStyleClass().add(color.toString());
        if(colorStyle!=null){
            setStyle(getStyle().replace(colorStyle, ""));
        }
        getStyleClass().remove(colorActual.toString());
        getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void removeColor(){
        //statusBar.getStyleClass().remove(colorActual.toString());
        
        getStyleClass().remove(colorActual.toString());
    }
    private MaterialDropdownMenu dropdownMenu;
    public void onOptionsButton(){};
    public abstract void onMenuButton(Button button);
    public abstract void onUpdateButton(Button button);
    public void setTitle(String titulo){
        titleLabel.setText(titulo);
    }
    public void addOptionsItem(MaterialDropdownMenuItem item){
        dropdownItems.add(item);
    }
    private ArrayList<MaterialDropdownMenuItem> dropdownItems;
    private MaterialLabel titleLabel;
    private MaterialTooltip tasdas;
    private HBox caja, thinBox;
    private MaterialIconButton updateButton;
    private MaterialDisplayText theButton;
    private HBox extraContainer;
    private MaterialIconButton menuButton,optionsButton;
    private void initAll(){
        extraContainer=new HBox();
        dropdownItems=new ArrayList<MaterialDropdownMenuItem>();
        //this.set
       StackPane menuButtonContainer=(StackPane)t.getItems().get(0);
        titleLabel=(MaterialLabel)t.getItems().get(1);
        
        StackPane updateButtonContainer=(StackPane)t.getItems().get(4);
        StackPane optionsButtonContainer=(StackPane)t.getItems().get(5);
       caja=(HBox)t.getItems().get(2);
       //caja.setAlignment(Pos.CENTER_RIGHT);
       theButton=(MaterialDisplayText)t.getItems().get(3);
       theButton.setColor(MaterialColor.WHITE);
       theButton.setFontSize(12);
        HBox.setHgrow(caja, Priority.ALWAYS);
        
        menuButtonContainer.getStyleClass().add("menu-button-container");
        menuButton=(MaterialIconButton)menuButtonContainer.getChildren().get(0);
        //menuButton.setIcon("/icons/buy-48.png");
        updateButton=(MaterialIconButton)updateButtonContainer.getChildren().get(0);
        optionsButton=(MaterialIconButton)optionsButtonContainer.getChildren().get(0);
        //tasdas=new MaterialTooltip("Opciones",optionsButton);
        
        
        
        
        optionsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                dropdownMenu=new MaterialDropdownMenu(optionsButton);
                dropdownMenu.addItems(dropdownItems);
                /*dropdownMenu.setPosition(event.getX()+LayoutMath.getAbsoluteParentsX(optionsButton),
                        event.getY()+LayoutMath.getAbsoluteParentsY(optionsButton));*/
                if(dropdownItems.size()>0){
                    dropdownMenu.unhide();
                }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        optionsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                onOptionsButton();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        menuButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                    onMenuButton(menuButton);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        updateButton.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                onUpdateButton(updateButton);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        t.getStyleClass().add("material-toolbar-raw");
        
        getStyleClass().addAll("material-toolbar",colorActual.toString());
        if(big==true){
            getStyleClass().add("big");
            
        }
        statusBar=new MaterialStatusBar();
        //statusBar.getStyleClass().add(colorActual.toString());
        getChildren().add(statusBar);
        getChildren().add(t);
        extraContainer.setPadding(new Insets(0,0,0,280));
        getChildren().add(extraContainer);
        
       
    }
    public void setBottomNode(Node node){
        extraContainer.getChildren().clear();
        if(node!=null){
            extraContainer.getChildren().add(node);
        }
        
    }
    public void addMiddleNode(String text,String tooltip){
        //thinBox.getChildren().add(node);
        theButton.setText(text);
        MaterialTooltip tils=new MaterialTooltip(tooltip,theButton);
        
    }
    private MaterialStatusBar statusBar;
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
