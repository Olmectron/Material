/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material;

import com.olmectron.material.components.MaterialButton;
import com.olmectron.material.constants.MaterialColor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author olmec
 */
public abstract class ButtonBox extends HBox{
    public abstract void onNegativeButtonClick();
    public abstract void onPositiveButtonClick();

    /**
     *
     * @param positiveButton
     */
    public ButtonBox(String positiveButton){
        this(positiveButton,null);
    }
    public ButtonBox(String positiveButton, String negativeButton){
        super();
        MaterialButton positive=null;
        if(positiveButton!=null){
            positive=new MaterialButton(positiveButton);
            positive.getStyleClass().add("dialog");
            positive.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    onPositiveButtonClick();
                    //if(fullItems.isSelected()){
                        //saveSelectedChanges();
                    //}
                    //else{
                    //    new MaterialToast("No option was selected").unhide();
                    //}
//nPositiveButton();
                    
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
                    onNegativeButtonClick();
                    //System.exit(0);
//dismiss();        
                    //onNegativeButton();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        setAlignment(Pos.CENTER_RIGHT);
        
        HBox positiveBox=new HBox();
        if(positive!=null){
            positiveBox.getChildren().add(positive);
            positiveBox.setPadding(new Insets(0,0,0,8));
        
        }
        HBox negativeBox=new HBox();
        if(negative!=null){
        
            negativeBox.getChildren().add(negative);
            negative.setColor(MaterialColor.BLACK_87);
        
        }
        setPadding(new Insets(8));
        getChildren().addAll(negativeBox,positiveBox);
    }
}
