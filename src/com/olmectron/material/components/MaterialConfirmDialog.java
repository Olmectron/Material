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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Édgar
 */
public abstract class MaterialConfirmDialog extends MaterialDialog{
    private final MaterialPane mainPane;
    private Label titleText, contentText;
    public void setDialogWidth(double w){
        mainPane.getContentCard().setCardWidth(w);
    }
    public void setDialogHeight(double h){
        mainPane.getContentCard().setCardHeight(h);
    }
    public MaterialConfirmDialog(){ 
        this("Use Google's location service?",
                "Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.","Agree","Disagree");
    }
    
    public void onPositiveButton(){
        
    }
    public void onNegativeButton(){
        
    }
    public void setCustomContent(Node node){
        externalBox.getChildren().add(node);
    }
    private VBox externalBox;
    public  MaterialConfirmDialog(String title, String message){
        this(title,message, null,null);
    }
    public MaterialConfirmDialog(String title, String message, String positiveButton){
        this(title,message,positiveButton,null);
    }
    public void setTitle(String t){
        titleText.setText(t);
    }
    public void setMessage(String message){
        contentText.setText(message);
    }
    public void onPressedKey(KeyEvent event){
        
    }
    public void onReleasedKey(KeyEvent event){
        
    }
    public MaterialConfirmDialog(String title, String message, String positiveButton, String negativeButton){
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
        VBox rootBox=new VBox();
        mainPane.getContentCard().setCardPadding(new Insets(0,0,0,0));
        mainPane.getContentCard().setCardWidth(280);
        mainPane.setRootComponent(rootBox);
        
        VBox messageBox=new VBox();
        titleText=new Label(title);
        titleText.getStyleClass().addAll("material-text","dialog-title","medium-font");
        
        titleText.setWrapText(true);
        titleText.setPadding(new Insets(0,0,20,0));
        contentText=new Label(message);
        contentText.getStyleClass().add("dialog-content-text");
        contentText.setWrapText(true);
        contentText.setLineSpacing(7);
        messageBox.getChildren().addAll(titleText,contentText);
        messageBox.setPadding(new Insets(24,24,16,24));
        externalBox=new VBox();
        
        externalBox.setPadding(new Insets(0,24,8,24));
        HBox buttonBox=new HBox();
        buttonBox.setPadding(new Insets(8,8,8,8));
        MaterialButton positive=null;
        if(positiveButton!=null){
            positive=new MaterialButton(positiveButton);
            positive.getStyleClass().add("dialog");
            positive.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    onPositiveButton();
                    
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
        }
        MaterialButton negative=null;
        if(negativeButton!=null){
            negative=new MaterialButton(negativeButton);
            negative.getStyleClass().add("dialog");
            negative.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    dismiss();
                    onNegativeButton();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        
        HBox positiveBox=new HBox();
        if(positive!=null){
            positiveBox.getChildren().add(positive);
            positiveBox.setPadding(new Insets(0,0,0,8));
        
        }
        HBox negativeBox=new HBox();
        if(negative!=null){
            negativeBox.getChildren().add(negative);
        }
        
        buttonBox.getChildren().addAll(negativeBox,positiveBox);
        
        rootBox.getChildren().add(messageBox);
        rootBox.getChildren().add(externalBox);
        rootBox.getChildren().add(buttonBox);
    }
    public void dismiss(){
        hide();
    }

    
}
