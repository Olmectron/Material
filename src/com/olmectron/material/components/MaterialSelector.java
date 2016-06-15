/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 *
 * @author Édgar
 */
public class MaterialSelector<T> extends ChoiceBox<T> implements MaterialComponent {
    public MaterialSelector(){
        super();
        initAll();
    }
    private MaterialLabel label;
    public MaterialSelector(MaterialLabel label){
        super();
        this.label=label;
        label.removeColor();
        initAll();
        
    }
    public void setLabel(MaterialLabel label){
        this.label=label;
        label.removeColor();
        
    }
    
    public String getDataString(T valor){
        
        return valor.toString();
    }
    public void onSelectionChange(T valor){
        
    }
    
    private void initAll(){
        getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try{
                onSelectionChange(getItems().get((Integer)newValue));
            }
            catch(ArrayIndexOutOfBoundsException ex){
                onSelectionChange(null);
            }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        });
        
        
        getStyleClass().removeAll("material-input","material-choice-box",colorActual.toString());
        getStyleClass().addAll("material-input","material-choice-box",colorActual.toString());
        this.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(label!=null){
                    if(newValue){
                        label.setColor(colorActual);
                    }
                    else{
                        label.removeColor();
                    }
                }
                
            }
        });
        setConverter(new StringConverter<T>() {
              @Override
              public String toString(T valor) {
                return  getDataString(valor);
              }

            @Override
            public T fromString(String id) {
                return null;
            }
        });
    }
    public void addItem(T item){
        getItems().add(item);
    }
    public void clear(){
        while(getItems().size()>0){
            getItems().remove(0);
        }
    }
    public void addItems(ArrayList<T> items){
        items.stream().forEach((item) -> {
            addItem(item);
        });
       
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
    public void unhide() {
        //setVisible(true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
