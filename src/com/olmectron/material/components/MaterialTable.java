/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialRippleable;
import com.olmectron.material.constants.MaterialColor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Skin;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Édgar
 */
public abstract class MaterialTable<T> extends StackPane implements MaterialComponent {
    
    public MaterialTable(){
        super();
        initAll();
        
    }
    public void setCardSpaced(){
        getStyleClass().remove("material-card");
        getStyleClass().add("material-card");
        this.setPadding(new Insets(12));
        
    }
    public T getSelectedItem(){
        try{
            return ((T)getTable().getSelectionModel().getSelectedItem());
        }
        catch(NullPointerException nullEx){
            return null;
        }
        
    }
    public ObservableList<T> getSelectedItems(){
        return getTable().getSelectionModel().getSelectedItems();
    }
    public void setSelectedItem(T objeto){
        getTable().getSelectionModel().select(objeto);
    }
    public void setSelectedIndex(int index){
        try{
        getTable().getSelectionModel().select(index);
        
        }
        catch(ArrayIndexOutOfBoundsException ex){
            
        }
    }
    
    public int getSelectedIndex(){
        return getTable().getSelectionModel().getSelectedIndex();
        
    }
    
    private TableView<T> table;
    private ObservableList<T> list;
    public abstract void onRowAdded(ListChangeListener.Change c, Object addedObject);
    public abstract void onRowRemoved(ListChangeListener.Change c, Object removedObject);
    private void initAll(){
        
        table = new TableView<T>(){
        @Override
    public void requestFocus(){
       
    }
        
        };
        
        
        
        list=FXCollections.observableArrayList();
        table.setItems(list);
        table.setPlaceholder(new StackPane());
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try{
                T objeto=list.get(getTable().getSelectionModel().getFocusedIndex());
                        onItemClicked(objeto,event);
                        onClicked(event);
                }
                catch(ArrayIndexOutOfBoundsException ex){
                    System.out.println("La tabla no tiene objetos");
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        table.getStyleClass().add("material-table");
        
       
        list.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change c) {
                boolean sig=c.next();
                if(sig && c.wasAdded()){
                    onRowAdded(c,c.getAddedSubList().get(0));
                }
                else if(sig && c.wasPermutated()){
                    System.out.println("Permutated");
                }
                else if(sig && c.wasReplaced()){
                    System.out.println("Replaced");
                }
                else if(sig && c.wasRemoved()){
                    onRowRemoved(c,c.getRemoved().get(0));
                    //System.out.println("Removed");
                }
                else if(sig && c.wasUpdated()){
                    System.out.println("Updated");
                }
                   
                //System.out.println("Ahora hay "+list.size()+" artículos");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        getChildren().add(table);
        
    }
    public void addItem(T item){
        list.add(item);
    }
    
    public void addObservableItems(ObservableList<T> items){
        list.addAll(items);
    }
    public void addListItems(List<T> items){
        list.addAll(items);
    }
    
    public void addItems(ArrayList<T> items){
        list.addAll(items);
    }
    public void removeItem(T item){
        list.remove(item);
    }
    public void selectUp(){
        int toSelect=getTable().getSelectionModel().getSelectedIndex()-1;
        if(toSelect>=0){
        getTable().getSelectionModel().select(toSelect);
            if(toSelect-4>=0){
            getTable().scrollTo(toSelect-4);

            }
        }
        
    }
    public void selectUp(int visible){
        int toSelect=getTable().getSelectionModel().getSelectedIndex()-1;
        if(toSelect>=0){
        getTable().getSelectionModel().select(toSelect);
            if(toSelect-visible>=0){
            getTable().scrollTo(toSelect-visible);

            }
        }
        
    }
     public void selectDown(int visible){
        int toSelect=getTable().getSelectionModel().getSelectedIndex()+1;
        if(toSelect<getTable().getItems().size()){
        getTable().getSelectionModel().select(toSelect);
            if(toSelect-visible>=0){
            getTable().scrollTo(toSelect-visible);

            }
        }
    }
    public void selectDown(){
        int toSelect=getTable().getSelectionModel().getSelectedIndex()+1;
        if(toSelect<getTable().getItems().size()){
        getTable().getSelectionModel().select(toSelect);
            if(toSelect-4>=0){
            getTable().scrollTo(toSelect-4);

            }
        }
    }
    public void removeAllItems(){
        list.clear();
    }
    public ObservableList<T> getItemList(){
        return list;
    }
    public ArrayList<T> getAllItems(){
        ArrayList<T> arrayList=new ArrayList<T>();
        list.stream().forEach((list1) -> {
            arrayList.add(list1);
        });
        return arrayList;
    }
    public void setDataSource(ObservableList<T> values){
        this.list=values;
        list.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change c) {
                boolean sig=c.next();
                if(sig && c.wasAdded()){
                    onRowAdded(c,c.getAddedSubList().get(0));
                }
                else if(sig && c.wasPermutated()){
                    System.out.println("Permutated");
                }
                else if(sig && c.wasReplaced()){
                    System.out.println("Replaced");
                }
                else if(sig && c.wasRemoved()){
                    System.out.println("Removed");
                }
                else if(sig && c.wasUpdated()){
                    System.out.println("Updated");
                }
                //System.out.println("Ahora hay "+list.size()+" artículos");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        table.setItems(list);
    }
    public TableView getTable(){
        return table;
    }
   
    
    @Override
    public void setColor(MaterialColor color) {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeColor() {
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
 public void onClicked(MouseEvent event){
        //table.
    }
    public void onItemClicked(T e, MouseEvent event){
        
    }
    
}
