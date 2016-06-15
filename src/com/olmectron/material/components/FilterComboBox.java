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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Édgar
 */
public class FilterComboBox extends ComboBox<String> implements MaterialComponent {
    private ObservableList<String> initialList;
    private ObservableList<String> bufferList = FXCollections.observableArrayList();
    private String previousValue = "";
    private MaterialLabel label;
    public FilterComboBox(MaterialLabel label){
        super();
        super.setEditable(true);
        this.label=label;
        this.configAutoFilterListener();
        initAll();
    }
    public FilterComboBox(ObservableList<String> items) {
        super(items);
        super.setEditable(true);
        this.initialList = items;
        
        this.configAutoFilterListener();
        initAll();
    }

    private void configAutoFilterListener() {
        final FilterComboBox currentInstance = this;
        this.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                previousValue = oldValue;
                final TextField editor = currentInstance.getEditor();
                editor.getStyleClass().add("full-transparent-container");
                final String selected = currentInstance.getSelectionModel().getSelectedItem();

                if (selected == null || !selected.equals(editor.getText())) {
                    filterItems(newValue, currentInstance);

                    currentInstance.show();
                    if (currentInstance.getItems().size() == 1) {
                        setUserInputToOnlyOption(currentInstance, editor);
                    }
                }
            }
        });
    }

    private void filterItems(String filter, ComboBox<String> comboBox) {
        if (filter.startsWith(previousValue) && !previousValue.isEmpty()) {
            ObservableList<String> filteredList = this.readFromList(filter, bufferList);
            bufferList.clear();
            bufferList = filteredList;
        } else {
            bufferList = this.readFromList(filter, initialList);
        }
        comboBox.setItems(bufferList);
    }

    private ObservableList<String> readFromList(String filter, ObservableList<String> originalList) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        for (String item : originalList) {
            if (item.toLowerCase().startsWith(filter.toLowerCase())) {
                filteredList.add(item);
            }
        }

        return filteredList;
    }

    private void setUserInputToOnlyOption(ComboBox<String> currentInstance, final TextField editor) {
        final String onlyOption = currentInstance.getItems().get(0);
        final String currentText = editor.getText();
        if (onlyOption.length() > currentText.length()) {
            editor.setText(onlyOption);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    editor.selectRange(currentText.length(), onlyOption.length());
                }
            });
        }
    }
    public void setLabel(MaterialLabel label){
        this.label=label;
        label.removeColor();
        
    }
    
    public void onSelectionChange(String valor){
        
    }
    private void initAll(){
        getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try{
                onSelectionChange(getItems().get((Integer)newValue).toString());
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
    }
    public void addItem(String item){
        getItems().add(item);
        
    }
    public void clear(){
        while(getItems().size()>0){
            getItems().remove(0);
        }
    }
    public void addItems(ArrayList<String> items){
        
        items.stream().forEach((item) -> {
            addItem(item);
        });
        this.initialList = getItems();
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