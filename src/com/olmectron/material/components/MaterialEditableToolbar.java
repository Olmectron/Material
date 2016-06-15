/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public abstract class MaterialEditableToolbar extends VBox implements MaterialComponent{
    private boolean big=false;
    private ToolBar t;
    public MaterialEditableToolbar(String title){
        this(title,false);
    }
    
    public void setBig(boolean grande){
        this.big=grande;
        
            getStyleClass().remove("big");
        if(big){
            
            getStyleClass().add("big");
            
        }
        
    }
    public MaterialEditableToolbar(String title, boolean big){
       
            t=new ToolBar(new StackPane(new MaterialIconButton(MaterialIconButton.MENU_ICON)),new MaterialLabel(title,MaterialLabel.TOOLBAR_TITLE),
                new HBox(), new HBox());
                this.big=big;
                //System.out.println(big);
                initAll();
        
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
             //updateButton.setColor(MaterialColor.BLACK_87);
             
             //optionsButton.setColor(MaterialColor.BLACK_87);
         }
         else{
             titleLabel.setColor(MaterialColor.WHITE);
             menuButton.setColor(MaterialColor.WHITE);
             //updateButton.setColor(MaterialColor.WHITE);
             
             //optionsButton.setColor(MaterialColor.WHITE);
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
    //private MaterialDropdownMenu dropdownMenu;
    public abstract void onMenuButton(Button button);
    public MaterialIconButton getMenuButton(){
        return menuButton;
    }
    public void setTitle(String titulo){
        titleLabel.setText(titulo);
    }
    public String getTitle(){
        return titleLabel.getText();
    }
    /*public void addOptionsItem(MaterialDropdownMenuItem item){
        dropdownItems.add(item);
    }*/
    //private ArrayList<MaterialDropdownMenuItem> dropdownItems;
    private MaterialLabel titleLabel;
    private HBox caja, thinBox;
    //private MaterialIconButton updateButton;
    //private MaterialDisplayText theButton;
    private HBox extraContainer;
    private MaterialIconButton menuButton;//,optionsButton;
    private ArrayList<HBox> rootActionsBoxes;
    public ArrayList<HBox> getRootActionsBoxes(){
        if(rootActionsBoxes==null){
            rootActionsBoxes=new ArrayList<HBox>();
        }
        return rootActionsBoxes;
    }
    private HBox getActionBox(int boxIndex){
        HBox box=null;
        try{
            box=getRootActionsBoxes().get(boxIndex);}
        catch(IndexOutOfBoundsException ex){
            
            while(getRootActionsBoxes().size()<=boxIndex){
                HBox box2=new  HBox();
                box2.setAlignment(Pos.TOP_RIGHT);
                getRootActionsBoxes().add(box2);
       
            }
            box=getRootActionsBoxes().get(boxIndex);
        }
        return box;
    }
    
    public void switchToolbarActions(int index){
        rootActionsBoxContainer.getChildren().clear();
        rootActionsBoxContainer.getChildren().add(getActionBox(index));
    }
    private void addNewAction(int boxIndex,IconButtonContainer action){
        HBox box=getActionBox(boxIndex);
        box.getChildren().add(action);
        
    }
    private HBox rootActionsBoxContainer;
    public IconButtonContainer addActionButton(String materialIcon, EventHandler<ActionEvent> event){
        return addActionButton(materialIcon,event,null,0);
        
    }
    public IconButtonContainer addActionButton(String materialIcon, EventHandler<ActionEvent> event, int index){
        return addActionButton(materialIcon,event,null,index);
        
    }
    public IconButtonContainer addActionButton(MaterialIconButton iconButton){
        return addActionButton(iconButton,0);
    }
    public IconButtonContainer addActionButton(MaterialIconButton iconButton, int index){
        IconButtonContainer newAction=new IconButtonContainer(iconButton);
                //newAction.setStyle(newAction.getStyle()+"-fx-background-color: red;");
        //newAction.setAlignment(Pos.TOP_CENTER);
        //newAction.getIconButton().setOnClick(event);
        addNewAction(index,newAction);
        //rootActionsBox.getChildren().add(newAction);
        //if(tooltip!=null){
        //    new MaterialTooltip(tooltip,newAction.getIconButton());
        //}
        return newAction;
    }
    public IconButtonContainer addActionButton(String materialIcon, EventHandler<ActionEvent> event, String tooltip){
        return addActionButton(materialIcon,event,tooltip,0);
    }
    public IconButtonContainer addActionButton(String materialIcon, EventHandler<ActionEvent> event, String tooltip, int index){
        IconButtonContainer newAction=new IconButtonContainer(materialIcon);
        //newAction.setStyle(newAction.getStyle()+"-fx-background-color: red;");
//newAction.setAlignment(Pos.TOP_CENTER);
        newAction.getIconButton().setOnClick(event);
        addNewAction(index,newAction);
        //rootActionsBox.getChildren().add(newAction);
        if(tooltip!=null){
            new MaterialTooltip(tooltip,newAction.getIconButton());
        }
        return newAction;
        
    }
    private void initAll(){
        extraContainer=new HBox();
        //dropdownItems=new ArrayList<MaterialDropdownMenuItem>();
        //this.set
       StackPane menuButtonContainer=(StackPane)t.getItems().get(0);
        titleLabel=(MaterialLabel)t.getItems().get(1);
        
        //StackPane updateButtonContainer=(StackPane)t.getItems().get(4);
        //StackPane optionsButtonContainer=(StackPane)t.getItems().get(5);
       caja=(HBox)t.getItems().get(2);
       rootActionsBoxContainer=(HBox)t.getItems().get(3);
       rootActionsBoxContainer.setAlignment(Pos.TOP_RIGHT);
       HBox box2=new  HBox();
                box2.setAlignment(Pos.TOP_RIGHT);
                getRootActionsBoxes().add(box2);
                rootActionsBoxContainer.getChildren().add(getRootActionsBoxes().get(0));
       //caja.setAlignment(Pos.CENTER_RIGHT);
       //theButton=(MaterialDisplayText)t.getItems().get(3);
       //theButton.setColor(MaterialColor.WHITE);
       //theButton.setFontSize(12);
        HBox.setHgrow(caja, Priority.ALWAYS);
        
        menuButtonContainer.getStyleClass().add("menu-button-container");
        menuButton=(MaterialIconButton)menuButtonContainer.getChildren().get(0);
        //menuButton.setIcon("/icons/buy-48.png");
        //tasdas=new MaterialTooltip("Opciones",optionsButton);
        /*rootActionsBox.getChildren().addAll(new IconButtonContainer(MaterialIconButton.REFRESH_ICON),
                );
        */
        
        
        
        menuButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                    onMenuButton(menuButton);
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
        expanderBox=new HBox();
        expanderBox.setMaxWidth(280);
        expanderBox.setMinWidth(280);
        
        //extraContainer.setPadding(new Insets(0,0,0,280));
        getChildren().add(new HBox(expanderBox,extraContainer));
        
       
    }
    private HBox expanderBox;
    public Pane getBottomExpander(){
        return expanderBox;
    }
    public Pane getBottomContainer(){
        return extraContainer;
    }
    public void setBottomNode(Node node){
        extraContainer.getChildren().clear();
        if(node!=null){
            extraContainer.getChildren().add(node);
        }
        
    }
    /*public void addMiddleNode(String text,String tooltip){
        //thinBox.getChildren().add(node);
        //theButton.setText(text);
        MaterialTooltip tils=new MaterialTooltip(tooltip,theButton);
        
    }*/
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
