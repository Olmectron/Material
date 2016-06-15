/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.menu.MenuFlowPane;
import com.olmectron.material.components.menu.MenuPane;
import com.olmectron.material.constants.MaterialColor;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author olmec
 */
public abstract class MaterialStandardList<T> extends MenuPane implements MaterialComponent {
    
    private VBox rootLayout;
    private Pane containerPane;
    public Pane getContainerPane(){
        return containerPane;
    }
    public void onItemContainerClick(MaterialStandardListItem<T> itemBox, MouseEvent event){
        
    }
    
    public void onRippleEnd(T item,MaterialStandardListItem<T> itemContainer){}
    public abstract void onItemClick(T item,MouseEvent event);
    private IntegerProperty itemCount;
    public IntegerProperty itemCountProperty(){
        if(itemCount==null){
            itemCount=new SimpleIntegerProperty(this,"itemCount");
            
        }
        return itemCount;
    }
    public void setItemCount(int count){
        itemCountProperty().set(count);
        
    }
    public ObservableList<Node> getItemNodes(){
        return rootLayout.getChildren();
    }
    public int getItemCount(){
        return itemCountProperty().get();
    }
    public abstract Node itemConverter(T item, MaterialStandardListItem<T> itemContainer);
    public Node chosenConverter(Node itemBox){return null;}
    public MaterialStandardList(Pane container){
        this(container,false);
    }
    private boolean scroll=false;
    public MaterialStandardList(Pane container, boolean scroll){
        this.scroll=scroll;
        setContainer(container);
        initAll();
        
        
    }
    public void setContainer(Pane pane){
        this.containerPane=pane;
        if(pane!=null){
            this.prefWidthProperty().bind(pane.widthProperty());
        }
    }
    public T getItem(int id){
            return ((MaterialStandardListItem<T>)rootLayout.getChildren().get(id)).getItem();
            
    }
    public MaterialStandardListItem<T> getItemBox(int id){
            return ((MaterialStandardListItem<T>)rootLayout.getChildren().get(id));
            
    }
    
    public int size(){
        return rootLayout.getChildren().size();
    }
    public void addItem(T item){
        this.addItem(item,false);
    }
    public void addItem(T item, boolean selectBox){
        MaterialDisplayText itemText=new MaterialDisplayText("");
        itemText.setRippleActive(true);
       
            itemText.setRippleColor(Color.web("#2196F340"));
            //itemText.setPadding(new Insets(0,0,0,72));
            MaterialStandardListItem<T> itemContainer=new MaterialStandardListItem<T>(item);
            
            Node p=itemConverter(item,itemContainer);
            
            itemContainer.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                for(int i=0;i<size();i++){
                    MaterialStandardListItem<T> itemBox=getItemBox(i);
                    itemBox.setChosen(false);
                }
                itemContainer.setChosen(true);
               onItemClick(item,event);
                
               onItemContainerClick(itemContainer,event);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
            itemText.getRipple().setOnRippleEffect(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                onRippleEnd(item,itemContainer);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
            //itemContainer.getStyleClass().remove("material-list-item");
            //itemContainer.getStyleClass().add("material-list-item");
            
            
            if(selectBox){
                CheckBox selectedBox=new CheckBox(){
                    @Override
                    public void requestFocus(){
                        
                    }
                
                };
                itemContainer.setSelectedBox(selectedBox);
                VBox chequeo=new VBox(selectedBox);
                chequeo.setOnMouseClicked(new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        
                            selectedBox.setSelected(!selectedBox.isSelected());
                        
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                chequeo.setAlignment(Pos.CENTER);
                chequeo.setPadding(new Insets(0,8,0,8));
                StackPane pPane=new StackPane();
                pPane.getChildren().add(p);
                pPane.getChildren().add(itemText);
                HBox finalBox=new HBox(chequeo, pPane);
                itemContainer.getChildren().add(finalBox);
            //itemContainer.getChildren().add(itemText);
            
                itemText.setFillContainer(finalBox);
                
                rootLayout.getChildren().add(itemContainer);
            }
            else{
                itemContainer.getChildren().add(p);
            
                itemContainer.getChildren().add(itemText);
            
                itemText.setFillContainer(itemContainer);
                rootLayout.getChildren().add(itemContainer);
            }
            setItemCount(this.size());
    }
    public MaterialStandardListItem<T> removeItem(int index){
        MaterialStandardListItem<T> item=(MaterialStandardListItem<T>)rootLayout.getChildren().get(index);
        rootLayout.getChildren().remove(index);
        return item;
    }
    public MaterialStandardListItem<T> removeItem(T item){
        for (Node children : rootLayout.getChildren()) {
           MaterialStandardListItem<T> itemContainer=(MaterialStandardListItem<T>)children;
           if(itemContainer.getItem().equals(item)){
               rootLayout.getChildren().remove(itemContainer);
               return itemContainer;
           }
        }
        return null;
    }
    public MaterialStandardListItem<T> removeItem(MaterialStandardListItem<T> item){
        
        rootLayout.getChildren().remove(item);
        return item;
    }
    public void insertItemAt(MaterialStandardListItem<T> item, int index){
        rootLayout.getChildren().add(index, item);
    }
    public void clear(){
        rootLayout.getChildren().clear();
    }
    public void addObservableItems(ObservableList<T> itemList){
        for(T item: itemList){
            addItem(item,false);
        }
    }
    public void addItems(ArrayList<T> itemList, boolean selectedBox){
        for(T item: itemList){
            addItem(item,selectedBox);
        }
    }
    public void addItems(ArrayList<T> itemList){
        this.addItems(itemList,false);
    }
    public void transparentScroll(){
        if(scrollPane!=null){
            
            scrollPane.getStyleClass().remove("material-scroll");
            scrollPane.getStyleClass().add("material-scroll");
        }
    }
    private ScrollPane scrollPane;
    private void initAll(){
        //getStyleClass().addAll(colorActual.toString());
        //getStyleClass().removeAll("material-menu");
        //getStyleClass().addAll("material-menu");
        rootLayout=new VBox();
        if(scroll){
            scrollPane=new ScrollPane(rootLayout){
                @Override
                public void requestFocus(){
                    
                }
            };
            scrollPane.widthProperty().addListener(new  ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    
                    rootLayout.setMaxWidth(newValue.doubleValue());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            if(getContainerPane()!=null){
            scrollPane.setPrefHeight(getContainerPane().getPrefHeight());
            }
            //scrollPane.setMinHeight(getContainerPane().getPrefHeight());
            //scrollPane.setMaxHeight(getContainerPane().getPrefHeight());
            
            getChildren().add(scrollPane);
            
        rootLayout.setPrefWidth(1000);
        
        }
        else{
            getChildren().add(rootLayout);
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
        setVisible(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unhide() {
        setVisible(true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
