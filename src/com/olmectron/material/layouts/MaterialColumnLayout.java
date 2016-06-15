/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.layouts;

import com.olmectron.material.components.MaterialCard;
import com.olmectron.material.components.MaterialToolbar;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 *
 * @author Édgar
 */
public abstract class MaterialColumnLayout extends BorderPane {
    private HBox bodyBox;
    private Rectangle2D screenBounds;
    private ArrayList<VBox> columnas;
    public MaterialColumnLayout(String title, int column){
        super();
        initStyles();
        bodyBox=new HBox();
        StackPane container=new StackPane(bodyBox);
        container.setPadding(new Insets(20,80,30,80));
        columnas=new ArrayList<VBox>();
        
        screenBounds=Screen.getPrimary().getBounds();
        for(int i=0;i<column;i++){
            VBox columna=new VBox();
            //System.out.println(bodyBox.getWidth()+"");
            columnas.add(columna);
            
            bodyBox.getChildren().add(new StackPane(columnas.get(i)));
        }
        bodyBox.widthProperty().addListener(new ChangeListener<Number>() {
    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
        System.out.println("Width: " + newSceneWidth);
        for (VBox columna : columnas) {
            columna.setMinWidth(((newSceneWidth.doubleValue()-12)/(double)columnas.size()));
            //System.out.println(columna.getMinWidth()+"");
        }
    }
});
        
widthProperty().addListener(new ChangeListener<Number>() {
    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
        //bodyBox.setMinWidth(newSceneHeight.doubleValue()-300);
    }
});
        setTop(new MaterialToolbar(title) {

            @Override
            public void onMenuButton(Button button) {
                onMenuButtonPressed();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


            @Override
            public void onUpdateButton(Button button) {
                 onUpdateButtonPressed();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        setCenter(new ScrollPane(container));
        
    }
    public abstract void onMenuButtonPressed();
    public abstract void onUpdateButtonPressed();
    public MaterialColumnLayout(String title){
        this(title,2);
    }
    public void addCard(MaterialCard card){
        this.addCard(card,1);
    }
    public void addCard(MaterialCard card, int columna){
        columnas.get(columna-1).getChildren().add(card);
        //System.out.println("Height Window: "+this.getHeight());
    }
     private void removeLastCard(int columna){
        int tama=columnas.get(columna).getChildren().size();
        columnas.get(columna).getChildren().remove(tama-1);
    }
   
    private void initStyles(){
        this.getStyleClass().add("principal-container");
    }
}
