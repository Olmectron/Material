/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

/**
 *
 * @author Edgar
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.menu.MenuFlowPane;
import com.olmectron.material.components.menu.MenuPane;
import com.olmectron.material.constants.MaterialColor;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author olmec
 */
public abstract class MaterialFlowList<T> extends MenuPane implements MaterialComponent {
    
    private FlowPane rootLayout;
    private Pane containerPane;
    public Pane getContainerPane(){
        return containerPane;
    }
   private ScrollPane perfectScroll;
   public void scrollToBottom(){
       perfectScroll.setVvalue(1.0);
   }
    public Pane getPerfectSizeFlowListPane(){
        setReplaceAnimation(true);
        transparentScroll();
        StackPane c=new StackPane(this);
        
        c.setPadding(new Insets(24));
        perfectScroll=new ScrollPane(c);

       perfectScroll.widthProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                c.setPrefWidth(newValue.doubleValue()-18);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       perfectScroll.setPrefHeight(1080);
       VBox characterBox=new VBox();
       perfectScroll.getStyleClass().add("material-scroll");
       //characterBox.setPrefHeight(1080);
        characterBox.getChildren().add(perfectScroll);
        this.setContainer(c);
        return characterBox;
    }
    public void onItemContainerClick(MaterialStandardListItem<T> itemBox, MouseEvent event){
        
    }
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
    public int getItemCount(){
        return itemCountProperty().get();
    }
    public boolean asCard(){return false;}
    
    public void cardConverter(MaterialCard card,T item, MaterialStandardListItem<T> itemContainer){};
    public abstract Node itemConverter(T item, MaterialStandardListItem<T> itemContainer);
    public Node chosenConverter(Node itemBox){return null;}
    public MaterialFlowList(Pane container){
        this(container,false);
    }
    private boolean scroll=false;
    public MaterialFlowList(Pane container, boolean scroll){
        this.scroll=scroll;
        setContainer(container);
        
        initAll();
        
    }
    public void setContainer(Pane pane){
        this.containerPane=pane;
        if(pane!=null){
            this.prefWidthProperty().bind(pane.widthProperty());
            //}this.prefHeightProperty().bind(pane.heightProperty());
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
    private boolean m=true;
    public void setRippleActive(boolean n){
        this.m=n;
    }
    public void setItemChosen(MaterialStandardListItem<T> chosenItem){
        for(int i=0;i<size();i++){
                    MaterialStandardListItem<T> itemBox=getItemBox(i);
                    itemBox.setChosen(false);
                }
        chosenItem.setChosen(true);
    }private boolean justShown=false;
    public void selectAllCheckBoxes(){
        for(int i=0;i<this.size();i++){
            MaterialCheckBox actualBox=((MaterialCheckBox)getItemBox(i).lookup("#check-box"));
            if(actualBox.isVisible() && !actualBox.isSelected()){
                actualBox.setSelected(true);
                
            }
            
        }
       
    }
    private void showCheckBoxes(MaterialCheckBox box){
        for(int i=0;i<this.size();i++){
            MaterialCheckBox actualBox=((MaterialCheckBox)getItemBox(i).lookup("#check-box"));
            if(!actualBox.isVisible()){
                actualBox.selectedProperty().addListener(new ChangeListener<Boolean>(){
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        countSelectedItems();
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                actualBox.setVisible(true);
                
                //actualBox.setSelected(false);
            }
            
        }justShown=true;
        box.setSelected(true);
       
    }
    private boolean justHidden=false;
    public void hideCheckBoxes(){
         for(int i=0;i<this.size();i++){
            MaterialCheckBox actualBox=((MaterialCheckBox)getItemBox(i).lookup("#check-box"));
            if(actualBox.isVisible()){
                actualBox.setVisible(false);
                
            }
            if(actualBox.isSelected()){
                
                actualBox.setSelected(false);
            }
         }
         justHidden=true;
    }
    
    private void countSelectedItems(){
        int suma=0;
        for(int g=0;g<size();g++){
                            if(((MaterialCheckBox)getItemBox(g).lookup("#check-box")).isSelected()){
                                suma++;
                            }
                        }
        if(suma==0){
            hideCheckBoxes();
        }
        onLongPressSelection(suma);
    }
    public void onLongPressSelection(int selected){
       
    }
   public void addItem(int index, T item){
        MaterialDisplayText itemText=new MaterialDisplayText("");
        itemText.setRippleActive(m);
            itemText.setRippleColor(Color.web("#2196F340"));
            //itemText.setPadding(new Insets(0,0,0,72));
            MaterialStandardListItem<T> itemContainer=new MaterialStandardListItem<T>(item);
            
            Node p=null;
            
            MaterialCard card=null;
            
            if(asCard()){
                
                card=new MaterialCard();
                card.setId("the_card");
                card.setCardPadding(new Insets(0));
                 
                card.activateShadow(1);
                
                cardConverter(card,item,itemContainer);
                p=card;
                
            }
            else{
                p=itemConverter(item,itemContainer);
            
            }
            
            if(replaceAnimation){
                itemContainer.setOnMousePressed(onCardPressed);
            itemContainer.setOnMouseDragged(onCardDragged);
            itemContainer.setOnMouseReleased(onCardReleased);
            }
            
            if(asCard()){
                addPressAndHoldHandler(itemContainer, Duration.millis(850), 
                event -> showCheckBoxes(((MaterialCheckBox)itemContainer.lookup("#check-box"))));
            }
            itemContainer.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                if(allowClick){
                for(int i=0;i<size();i++){
                    MaterialStandardListItem<T> itemBox=getItemBox(i);
                    itemBox.setChosen(false);
                }
                if(asCard()){
                    if(!justShown){
                    MaterialCheckBox box=((MaterialCheckBox)itemContainer.lookup("#check-box"));
                    if(box.isVisible()){
                        box.setSelected(!box.isSelected());
                    }
                    }
                    else
                    justShown=false;
                }
                
                if(!asCard()){
                    itemContainer.setChosen(true);
                }
                
                
                if(asCard() && !((MaterialCheckBox)itemContainer.lookup("#check-box")).isVisible() && !justHidden){
               
                    onItemClick(item,event);
               onItemContainerClick(itemContainer,event);
              
                }
                else if(!asCard()){
                    onItemClick(item,event);
               onItemContainerClick(itemContainer,event);
               
                }
                if(justHidden){
                    justHidden=false;
                }
                }     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
            itemText.setId("ripple_container");
            itemText.getRipple().setOnRippleEffect(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                onRippleEnd(item,itemContainer);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
            //itemContainer.getStyleClass().remove("material-list-item");
            //itemContainer.getStyleClass().add("material-list-item");
            
            
           
                if(asCard()){
                    StackPane sP=new StackPane();
                    StackPane inner=new StackPane(p);
                    VBox b=new VBox(inner);
                    
                    sP.getChildren().add(b);
                    VBox b2=new VBox();
                    inner.getChildren().add(b2);
                    
                    MaterialCheckBox c=new  MaterialCheckBox();
                    
                    c.setId("check-box");
                    c.setVisible(false);
                    b2.getChildren().add(c);
                    b2.setId("check-box-container");
                    sP.getChildren().add(itemText);
                    
                    itemText.setFillContainer(b);
                    sP.setPadding(new Insets(10,11,12,11));
                    itemContainer.getChildren().add(sP);
                    c.selectedProperty().addListener(new ChangeListener<Boolean>(){
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if(newValue){
                                ((MaterialCard)itemContainer.lookup("#the_card")).setBackgroundColor(MaterialColor.material.BLUE);
                            }
                                
                            else{
                               ((MaterialCard)itemContainer.lookup("#the_card")).removeBackgroundColor();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                    rootLayout.getChildren().add(index,itemContainer);
                    onCardAttached(itemContainer);
                }
                else{
                    itemContainer.getChildren().add(p);

                    itemContainer.getChildren().add(itemText);

                    itemText.setFillContainer(itemContainer);
                    rootLayout.getChildren().add(index,itemContainer);
                }
            
            setItemCount(rootLayout.getChildren().size());
   }
    public void addItem(T item, boolean selectBox){
        MaterialDisplayText itemText=new MaterialDisplayText("");
        itemText.setRippleActive(m);
            itemText.setRippleColor(Color.web("#2196F340"));
            //itemText.setPadding(new Insets(0,0,0,72));
            MaterialStandardListItem<T> itemContainer=new MaterialStandardListItem<T>(item);
            
            Node p=null;
            
            MaterialCard card=null;
            
            if(asCard()){
                
                card=new MaterialCard();
                card.setId("the_card");
                card.setCardPadding(new Insets(0));
                 
                card.activateShadow(1);
                
                cardConverter(card,item,itemContainer);
                p=card;
                
            }
            else{
                p=itemConverter(item,itemContainer);
            
            }
            
            if(replaceAnimation){
                itemContainer.setOnMousePressed(onCardPressed);
            itemContainer.setOnMouseDragged(onCardDragged);
            itemContainer.setOnMouseReleased(onCardReleased);
            }
            
            if(asCard()){
                addPressAndHoldHandler(itemContainer, Duration.millis(850), 
                event -> showCheckBoxes(((MaterialCheckBox)itemContainer.lookup("#check-box"))));
            }
            itemContainer.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                if(allowClick){
                for(int i=0;i<size();i++){
                    MaterialStandardListItem<T> itemBox=getItemBox(i);
                    itemBox.setChosen(false);
                }
                if(asCard()){
                    if(!justShown){
                    MaterialCheckBox box=((MaterialCheckBox)itemContainer.lookup("#check-box"));
                    if(box.isVisible()){
                        box.setSelected(!box.isSelected());
                    }
                    }
                    else
                    justShown=false;
                }
                
                if(!asCard()){
                    itemContainer.setChosen(true);
                }
                
                
                if(asCard() && !((MaterialCheckBox)itemContainer.lookup("#check-box")).isVisible() && !justHidden){
               
                    onItemClick(item,event);
               onItemContainerClick(itemContainer,event);
              
                }
                else if(!asCard()){
                    onItemClick(item,event);
               onItemContainerClick(itemContainer,event);
               
                }
                if(justHidden){
                    justHidden=false;
                }
                }     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
            itemText.setId("ripple_container");
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
                if(asCard()){
                    StackPane sP=new StackPane();
                    StackPane inner=new StackPane(p);
                    VBox b=new VBox(inner);
                    
                    sP.getChildren().add(b);
                    VBox b2=new VBox();
                    inner.getChildren().add(b2);
                    
                    MaterialCheckBox c=new  MaterialCheckBox();
                    
                    c.setId("check-box");
                    c.setVisible(false);
                    b2.getChildren().add(c);
                    b2.setId("check-box-container");
                    sP.getChildren().add(itemText);
                    
                    itemText.setFillContainer(b);
                    sP.setPadding(new Insets(10,11,12,11));
                    itemContainer.getChildren().add(sP);
                    c.selectedProperty().addListener(new ChangeListener<Boolean>(){
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if(newValue){
                                ((MaterialCard)itemContainer.lookup("#the_card")).setBackgroundColor(MaterialColor.material.BLUE);
                            }
                                
                            else{
                               ((MaterialCard)itemContainer.lookup("#the_card")).removeBackgroundColor();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                    rootLayout.getChildren().add(itemContainer);
                    onCardAttached(itemContainer);
                }
                else{
                    itemContainer.getChildren().add(p);

                    itemContainer.getChildren().add(itemText);

                    itemText.setFillContainer(itemContainer);
                    rootLayout.getChildren().add(itemContainer);
                }
            }
            
            setItemCount(rootLayout.getChildren().size());
    }
    public void onCardAttached(MaterialStandardListItem<T> itemContainer){};
    private void addPressAndHoldHandler(Node node, Duration holdTime, 
            EventHandler<MouseEvent> handler) {

        class Wrapper<T> { T content ; }
        Wrapper<MouseEvent> eventWrapper = new Wrapper<>();

        PauseTransition holdTimer = new PauseTransition(holdTime);
        holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));


        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eventWrapper.content = event ;
            holdTimer.playFromStart();
        });
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
        node.addEventHandler(MouseEvent.DRAG_DETECTED, event -> holdTimer.stop());
    }
    public void removeItem(MaterialStandardListItem<T> item){
        rootLayout.getChildren().remove(item);
        
            setItemCount(rootLayout.getChildren().size());
        
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
    private StackPane floatingPane;
    public void onRippleEnd(T item,MaterialStandardListItem<T> itemContainer){}
    private ScrollPane scrollPane;
    private void initAll(){
        //getStyleClass().addAll(colorActual.toString());
        //getStyleClass().removeAll("material-menu");
        //getStyleClass().addAll("material-menu");
        floatingPane=new StackPane();
        rootLayout=new FlowPane();
        rootLayout.widthProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rootLayout.setPrefWidth(rootLayout.getWidth());
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //rootLayout.setPrefHeight(1500);
        rootLayout.setAlignment(Pos.CENTER);
        //this.setAlignment(Pos.CENTER);
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
            scrollPane.setPrefHeight(getContainerPane().getPrefHeight());
            
            //scrollPane.setMinHeight(getContainerPane().getPrefHeight());
            //scrollPane.setMaxHeight(getContainerPane().getPrefHeight());
            StackPane papaPane=new StackPane(scrollPane, floatingPane);
            getChildren().add(papaPane);
            
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
    
     public MaterialStandardListItem<T> removeItem(int index){
        MaterialStandardListItem<T> item=(MaterialStandardListItem<T>)rootLayout.getChildren().get(index);
        rootLayout.getChildren().remove(index);
        
            setItemCount(rootLayout.getChildren().size());
        return item;
    }
    public MaterialStandardListItem<T> removeItem(T item){
        for (Node children : rootLayout.getChildren()) {
           MaterialStandardListItem<T> itemContainer=(MaterialStandardListItem<T>)children;
           if(itemContainer.getItem().equals(item)){
               rootLayout.getChildren().remove(itemContainer);
               
            setItemCount(this.size());
               return itemContainer;
           }
        }
        return null;
    }
   
    public void insertItemAt(MaterialStandardListItem<T> item, int index){
        rootLayout.getChildren().add(index, item);
    }
    public void nullLastDraggedItem(){
        lastDraggedItem=null;
    }
    
    
    
    private double orgSceneX=0, orgSceneY=0;
    private double orgTranslateX=0, orgTranslateY=0;
    private int pressedIndex=-1;
    private double itemHeight=0;
    private double itemWidth=0;
    
    
    private int getIndexOfItem(MaterialStandardListItem<T> item){
        for(int i=0;i<size();i++){
            if(getItemBox(i).equals(item)){
                return i;
            }
        }
        return -1;
    }
    private MaterialStandardListItem<T> lastDraggedItem=null;
    
    private EventHandler<MouseEvent> onCardPressed=new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            
            orgSceneX=event.getSceneX();
            orgSceneY=event.getSceneY();
            orgTranslateX=((MaterialStandardListItem<T>)event.getSource()).getTranslateX();
            orgTranslateY=((MaterialStandardListItem<T>)event.getSource()).getTranslateY();
            pressedIndex=getIndexOfItem(((MaterialStandardListItem<T>)event.getSource()));
            itemHeight=(((MaterialStandardListItem<T>)event.getSource()).getHeight());
            itemWidth=(((MaterialStandardListItem<T>)event.getSource()).getWidth());
            //System.out.println("orgScenex "+orgSceneX);
            //System.out.println("orgSceneY "+orgSceneY);
            //System.out.println("orgTransX "+orgTranslateY);
            //System.out.println("orgTransY "+orgTranslateY);
            
            
            //System.out.println(orgTranslateY+" y-move");
            //System.out.println(((MaterialStandardListItem<InstallableFile>)event.getSource()).getLayoutY()+" y-layout");
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    public void onItemSwap(int index1, int index2,T item1, T item2){}
    private void swapNodes(int index1, int index2){
        //System.out.println("index1: "+index1+", index2: "+index2);
        boolean continuar=true;
        if(index1>index2){
            int aux=index2;
            index2=index1;
            index1=aux;
            
        }
        else if(index1==index2){
            continuar=false;
            System.err.println("Both indexes are the same. They should be different");
        }
        if(continuar){
            
            MaterialStandardListItem<T> item2=removeItem(index2);
            MaterialStandardListItem<T> item1=removeItem(index1);
            
            insertItemAt(item2, index1);
            insertItemAt(item1, index2);
            
            setItemCount(rootLayout.getChildren().size());
            onItemSwap(index1,index2,item1.getItem(),item2.getItem());
        }
            
    }
    private void moveOriginal(MaterialStandardListItem<T> pressedItem,MaterialStandardListItem<T> cycleItem, int indexToGo){
        Timeline timeline=new Timeline();

                        KeyValue kv1=new KeyValue(pressedItem
                        .translateXProperty(),cycleItem.getLayoutX()-pressedItem.getLayoutX());
                        KeyValue kv2=new KeyValue(pressedItem
                        .translateYProperty(),cycleItem.getLayoutY()-pressedItem.getLayoutY());
                        KeyFrame kf=new KeyFrame(Duration.millis(130),kv2,kv1);
                        timeline.getKeyFrames().add(kf);
                        timeline.setOnFinished(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event) {
                                    pressedItem.setTranslateX(0);
                                    pressedItem.setTranslateY(0);
                                    cycleItem.setTranslateX(0);
                                    cycleItem.setTranslateY(0);
                                    swapNodes(indexToGo, pressedIndex);
                                    allowClick=true;
                                    //queueList.insertItemAt(queueList.removeItem(draggedFile), 1);
                                    //queueList.getItemBox(0).setTranslateY(0);
                                    //queueList.getItemBox(0).setTranslateX(0);
                                    //queueList.getItemBox(1).setTranslateY(0);
                                    //queueList.getItemBox(1).setTranslateX(0);



                                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }
                            });
                        timeline.play();
    }
    private void moveReplaced(MaterialStandardListItem<T> cycleItem,MaterialStandardListItem<T> releasedItem, int myIndex){
        Timeline timeline=new Timeline();

        KeyValue kv1=new KeyValue(cycleItem
                        .translateXProperty(),releasedItem.getLayoutX()-cycleItem.getLayoutX());
                        KeyValue kv2=new KeyValue(cycleItem
                        .translateYProperty(),releasedItem.getLayoutY()-cycleItem.getLayoutY());
                        KeyFrame kf=new KeyFrame(Duration.millis(250),kv2,kv1);
                        timeline.getKeyFrames().add(kf);
                        timeline.setOnFinished(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event) {
                                    moveOriginal(releasedItem,cycleItem,myIndex);
                                    //queueList.insertItemAt(queueList.removeItem(draggedFile), 1);
                                    //queueList.getItemBox(0).setTranslateY(0);
                                    //queueList.getItemBox(0).setTranslateX(0);
                                    //queueList.getItemBox(1).setTranslateY(0);
                                    //queueList.getItemBox(1).setTranslateX(0);



                                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }
                            });
                        timeline.play();
    }
    public  MaterialStandardListItem<T> getLastDraggedItem(){
        return lastDraggedItem;
    }
    private boolean replaceAnimation=false;
    public void setReplaceAnimation(boolean m){
        this.replaceAnimation=m;
        if(m){
            for(int i=0;i<size();i++){
                MaterialStandardListItem<T> itemBox=getItemBox(i);
                itemBox.setOnMousePressed(onCardPressed);
            itemBox.setOnMouseDragged(onCardDragged);
            itemBox.setOnMouseReleased(onCardReleased);
            
            }
        }
        else{
            for(int i=0;i<size();i++){
                MaterialStandardListItem<T> itemBox=getItemBox(i);
                itemBox.setOnMousePressed(null);
            itemBox.setOnMouseDragged(null);
            itemBox.setOnMouseReleased(null);
            
            }
        }
    }
    private EventHandler<MouseEvent> onCardReleasedTest=new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
        MaterialStandardListItem<T> releasedItem=((MaterialStandardListItem<T>)event.getSource());
            //System.out.println(releasedItem.getLayoutY()+" great layout Y");
        }};
    private EventHandler<MouseEvent> onCardReleased=new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            
            
            startDragged=0;
            
            MaterialStandardListItem<T> releasedItem=((MaterialStandardListItem<T>)event.getSource());
            
            MaterialCard carta;
                Node n=releasedItem.lookup("#the_card");
                if(n!=null){
                    carta=(MaterialCard)n;
                    carta.activateShadow(1);
                }
            
            boolean replace=false;
            //System.out.println(releasedItem.getTranslateY()+" y-released");
                double newLayoutY=releasedItem.getLayoutY()+releasedItem.getTranslateY();
                    double newLayoutX=releasedItem.getLayoutX()+releasedItem.getTranslateX();
                
            for(int i=0;i<size();i++){
                if(!getItemBox(i).equals(releasedItem)){
                    MaterialStandardListItem<T> cycleItem=getItemBox(i);
                    
                    if((newLayoutY<cycleItem.getLayoutY()+33 && newLayoutY>cycleItem.getLayoutY()-33) &&
                            (newLayoutX<cycleItem.getLayoutX()+33 && newLayoutX>cycleItem.getLayoutX()-33)){
                            replace=true;
                            moveReplaced(cycleItem,releasedItem,i);
                            
                        }
                }
            }
            if(!replace){
                Timeline timeline=new Timeline();
            KeyValue kv=new KeyValue(((MaterialStandardListItem<T>)event.getSource())
            .translateXProperty(),orgTranslateX);
            KeyValue kv2=new KeyValue(((MaterialStandardListItem<T>)event.getSource())
            .translateYProperty(),orgTranslateY);
            KeyFrame kf=new KeyFrame(Duration.millis(250),kv,kv2);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        lastDraggedItem=null;
                        allowClick=true;
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            timeline.play();
            }
            
            
            
            
            //System.out.println(+" y-move");
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    };
    private int startDragged=0;
    private EventHandler<MouseEvent> onCardDragged=new  EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
             MaterialStandardListItem<T> draggedFile=((MaterialStandardListItem<T>)event.getSource());
             
             
            if(startDragged==0){
                lastDraggedItem=draggedFile;
                allowClick=false;
                startDragged++;
                MaterialCard carta;
                Node n=draggedFile.lookup("#the_card");
                if(n!=null){
                    carta=(MaterialCard)n;
                    carta.activateShadow(2);
                }
                
            }
            double offsetX=event.getSceneX()-orgSceneX;
            double offsetY=event.getSceneY()-orgSceneY;
            double newTranslateX=orgTranslateX+offsetX;
            double newTranslateY=orgTranslateY+offsetY;
            //System.out.println(newTranslateY+" y-dragged");
           
            draggedFile.setTranslateX(newTranslateX);
            draggedFile.setTranslateY(newTranslateY);     
            
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
        }
    };
    private boolean allowClick=true;
}
