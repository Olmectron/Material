/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.constants.MaterialColor;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author olmec
 */
public abstract class SimpleSelector<T> extends HBox{
    private ArrayList<T> items;
    private MaterialDisplayText editField;
    private IntegerProperty position;
    public abstract String getStringValue(T item);
    public abstract int getItemPosition(ArrayList<T> items, String showText);
    public IntegerProperty positionProperty(){
        if(position==null){
            position=new SimpleIntegerProperty(this,"position");
           
            position.addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(newValue.intValue()>-1){
                    editField.setText(getStringValue(items.get(newValue.intValue())));
                    
                    onItemChange(items.get(newValue.intValue()),newValue.intValue());
                    }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return position;
    }
    private void setPosition(int pos){
        positionProperty().set(pos);
    }
    private int getPosition(){
        return positionProperty().get();
    }
    private void decreasePosition(){
        if(getPosition()==0){
            setPosition(items.size()-1);
        }
        else{
            setPosition(getPosition()-1);
        }
    }
    private void increasePosition(){
        if(getPosition()==items.size()-1){
            setPosition(0);
        }
        else{
            setPosition(getPosition()+1);
        }
    }
    public  SimpleSelector(String field, ArrayList<T> items){
        this(field,items,-1);
    }
    public SimpleSelector(String field,ArrayList<T> items, int selected){
        
        super();
        this.items=items;
        MaterialDisplayText fieldDisplay=new MaterialDisplayText(field);
        fieldDisplay.setFontWeight(FontWeight.MEDIUM);
        fieldDisplay.setMinWidth(60);
        MaterialIconButton leftButton=new MaterialIconButton(MaterialIconButton.MOVE_LEFT_ICON);
        editField=new MaterialDisplayText("");
        editField.textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                setPosition(getItemPosition(items,newValue));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
 
        if(selected!=-1){
            editField.setText(getStringValue(items.get(selected)));
        }
        else{
                   editField.setText(getStringValue(items.get(0)));
        }
        
        
        editField.setColor(MaterialColor.BLACK_87);
        MaterialIconButton rightButton=new MaterialIconButton(MaterialIconButton.MOVE_RIGHT_ICON);
        editField.setMinWidth(100);
        editField.setAlignment(Pos.CENTER);
        leftButton.setColor(MaterialColor.BLACK_54);
        leftButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                decreasePosition();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        rightButton.setColor(MaterialColor.BLACK_54);
        rightButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                increasePosition();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        setAlignment(Pos.CENTER);
        //setStyle(getStyle()+"-fx-background-color: #FF0000;");
        setPrefWidth(700);
        getChildren().addAll(fieldDisplay, leftButton, editField, rightButton);
    }
    public abstract void onItemChange(T item, int pos);
}
