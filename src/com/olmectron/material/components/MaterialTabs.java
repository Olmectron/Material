/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.constants.MaterialColor;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author olmec
 */
public class MaterialTabs extends HBox {
    private boolean toolbar=false;
    public MaterialTabs(boolean toolbar){
        super();
        this.toolbar=toolbar;
        initAll();
        
    }
    public MaterialTabs(){
        this(false);
    }
    private void initAnimation(ScrollPane scroller)
    {
        final double scrollSpeed = 0.5;
         AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0 ;
            @Override
            public void handle(long time) {
                if (lastUpdate > 0) {
                    long elapsedNanos = time - lastUpdate ;
                    double elapsedSeconds = elapsedNanos / 1_000_000_000.0 ;
                    double delta = 0 ;
                    if (leftButton.isArmed()) {
                        delta = -scrollSpeed * elapsedSeconds ;
                    }
                    if (leftButton.isArmed()) {
                        delta = scrollSpeed * elapsedSeconds ;
                    }
                    double newValue = 
                            clamp(scroller.getHvalue() + delta, scroller.getHmin(), scroller.getHmax());
                    scroller.setVvalue(newValue);
                }
                lastUpdate = time ;
            }
        };
         timer.start();
    }
    private double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

    private int max=0;
    public MaterialTabs(int maxWidth){
        this(maxWidth,false);
    }
    public MaterialTabs(int maxWidth, boolean toolbar){
        super();
        this.max=maxWidth;
        this.toolbar=toolbar;
        initAll();
    }
    private MaterialIconButton leftButton;
    private MaterialIconButton rightButton;
    private ScrollPane scroll;
    private HBox tabContainer;
    private VBox leftButtonContainer;
    private VBox rightButtonContainer;
    private void initScroll(){
        scroll=new ScrollPane(tabContainer);
        
        scroll.getStyleClass().remove("material-scroll");
        scroll.getStyleClass().add("material-scroll");
        scroll.setFitToHeight(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.hvalueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.doubleValue()<=scroll.getHmin()){
                    leftButton.setColor(MaterialColor.TRANSPARENT);
                    
                }
                else {
                    leftButton.setColor(MaterialColor.BLACK_54);
                    
                }
                if(newValue.doubleValue()>=scroll.getHmax()){
                    rightButton.setColor(MaterialColor.TRANSPARENT);
                    
                }
                else {
                    rightButton.setColor(MaterialColor.BLACK_54);
                    
                }
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        scroll.widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //tabContainer.setPrefWidth(newValue.doubleValue());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        tabContainer.widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(max>0){
                        if(newValue.doubleValue()>max){
                            if(!areButtonsAppended()){
                                appendButtons();
                            }
                            
                        }
                    }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    private boolean areButtonsAppended(){
        return leftButtonContainer.getChildren().size()>0;
        
    }
    private Timeline c=null;
    private void moveRightRepeat(){
        scroll.setHvalue(scroll.getHvalue()+0.02);
         c=new Timeline(new KeyFrame(
        Duration.millis(20),
        ae -> moveRightRepeat()));
                c.play();
    }
    private Timeline t=null;
    private void moveLeftRepeat(){
        scroll.setHvalue(scroll.getHvalue()-0.02);
         t=new Timeline(new KeyFrame(
        Duration.millis(20),
        ae -> moveLeftRepeat()));
                t.play();
    }
    private void appendButtons(){
        leftButtonContainer.getChildren().add(leftButton);
        leftButton.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                t=new Timeline(new KeyFrame(
        Duration.millis(150),
        ae -> moveLeftRepeat()));
                t.play();
    
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        leftButton.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(t!=null){
                    t.stop();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        rightButtonContainer.getChildren().add(rightButton);
        rightButton.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                c=new Timeline(new KeyFrame(
        Duration.millis(150),
        ae -> moveRightRepeat()));
               c.play();
    
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        rightButton.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(c!=null){
                    c.stop();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //initAnimation(scroll);
    }
    private void initAll(){
        
        tabContainer=new HBox();
        tabContainer.setAlignment(Pos.CENTER);
        
        
        getStyleClass().remove("material-tabs");
        
        getStyleClass().add("material-tabs");
        initScroll();
        initButtons();
        this.getChildren().addAll(leftButtonContainer,scroll,rightButtonContainer);
        
        this.setAlignment(Pos.CENTER);
    }
    private void initButtons(){
        leftButton=new MaterialIconButton(MaterialIconButton.MOVE_LEFT_ICON);
        rightButton=new MaterialIconButton(MaterialIconButton.MOVE_RIGHT_ICON);
        leftButton.setColor(MaterialColor.TRANSPARENT);
        if(toolbar){
            getStyleClass().add("toolbar");
            rightButton.setColor(MaterialColor.WHITE_70);
        }else{
            rightButton.setColor(MaterialColor.BLACK_54);
        }
        leftButtonContainer=new VBox();
        leftButtonContainer.setAlignment(Pos.CENTER);
        
        
        rightButtonContainer=new VBox();
        rightButtonContainer.setAlignment(Pos.CENTER);
        //VBox.setVgrow(leftButtonContainer, Priority.ALWAYS);
        //VBox.setVgrow(rightButtonContainer, Priority.ALWAYS);
    }
    public void unselectAll(){
        tabContainer.getChildren().stream().forEach((children) -> {
            ((MaterialTab)children).unselect();
        });
    
    }
   
    public void addTab(MaterialTab tab){
        tab.setTabsParent(this);
        tab.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //unselectAll(); 
                if(event.getButton().equals(MouseButton.PRIMARY))
                tab.select();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

            tab.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //new MaterialToast("Presionado tabs en x: "+event.getX()+", y: "+event.getY()).unhide();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        tab.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                 //new MaterialToast("Liberado tabs en x: "+event.getX()+", y: "+event.getY()).unhide();
               
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        tab.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //new MaterialToast("Dragged tabs en x: "+event.getX()+", y: "+event.getY()).unhide();
               
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        tabContainer.getChildren().add(tab);
        
    }
    public MaterialTab getTabAt(int index){
        return (MaterialTab)tabContainer.getChildren().get(index);
    }
    public int getSelectedTabIndex(){
        int count=0;
        int selected=-1;
        for(int i=0;i<tabContainer.getChildren().size();i++){
            MaterialTab tab=(MaterialTab)tabContainer.getChildren().get(i);
            if(tab.isSelected()){
                selected=i;
                count++;
            }
        }
        if(count!=1){
            throw new IllegalStateException(count+" tabs are selected");
        }
        
        return selected;
    }
    public int getTabCount(){
        return tabContainer.getChildren().size();
    }
    
    public void addTab(String tabText){
        addTab(new MaterialTab(tabText,toolbar));
    }
    
}
