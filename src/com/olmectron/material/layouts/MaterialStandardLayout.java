/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.layouts;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialCard;
import com.olmectron.material.components.MaterialDrawer;
import com.olmectron.material.components.MaterialDropdownMenuItem;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.components.MaterialMenu;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.components.MaterialToolbar;
import com.olmectron.material.components.menu.MaterialMenuItem;
import com.olmectron.material.constants.MaterialColor;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */
public abstract class MaterialStandardLayout extends BorderPane {
    private VBox bodyBox;
    private MaterialDrawer drawer;
    public MaterialStandardLayout(String title){
        this(title,false,false,false);
        
    }
    public MaterialDrawer getDrawer(){
        return drawer;
    }
    private StackPane container;
    private boolean scrollable=false;
    private ScrollPane scrollPane=null;
    public void setScrollable(boolean scrollable){
        this.scrollable=scrollable;
        if(scrollable){
            scrollPane=new ScrollPane();
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            setCenter(scrollPane);
            scrollPane.getStyleClass().remove("material-scroll");
            scrollPane.getStyleClass().add("material-scroll");
            //VBox c=new VBox(container);
            //c.setAlignment(Pos.CENTER);
            scrollPane.setContent(container);
            scrollPane.widthProperty().addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    container.setPrefWidth(newValue.doubleValue());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        else{
            if(scrollPane!=null){
                setCenter(container);
                scrollPane=null;
            }
        }
    }
    public boolean isScrollable(){
        return scrollable;
    }
    private boolean showMenuOnClick=true;
    public void setShowDrawerOnClick(boolean showm){
        showMenuOnClick=showm;
    }
    private VBox drawerBox;
    private MaterialMenu drawerMenu;
    
    private boolean fullSize=false;
    public boolean isFullSize(){
        return fullSize;
    }
    private double minDrawerSize=66;
    public void setMiniDrawerSize(double size){
        this.minDrawerSize=size;
    }
    private void setDrawerMenuVisible(boolean mostrar){
        if(mostrar){
            drawerMenu.setVisible(true);
            menuOpacityAnimation(1,new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        else{
            menuOpacityAnimation(0,new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    drawerMenu.setVisible(false);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
            
    }
    public void setMiniDrawer(boolean miniDrawer){
        
        
        this.miniDrawer=miniDrawer;
        
        //drawerBox.getStyleClass().remove("mini");
        if(miniDrawer){
            if(minDrawerSize==0){
                        
                        setDrawerMenuVisible(false);
                    }
            drawerSizeAnimation(minDrawerSize, new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    miniButton.setMaterialIcon(MaterialIconButton.MOVE_RIGHT_ICON);
                    miniBox.setAlignment(Pos.CENTER);
                    drawerMenu.setMiniMenu(miniDrawer);
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            
            });
            //drawerBox.getStyleClass().add("mini");
            
            
        }
        else{
            drawerMenu.setMiniMenu(miniDrawer);
            miniBox.setAlignment(Pos.CENTER_RIGHT);
            
            drawerSizeAnimation(normalDrawerWidth, new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    miniButton.setMaterialIcon(MaterialIconButton.MOVE_LEFT_ICON);
                        if(minDrawerSize==0){
                        
                        setDrawerMenuVisible(true);
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        }
        
    }
    public void setDrawerMenuPadding(Insets s){
        drawerBox.setPadding(s);
    }
    private MaterialIconButton miniButton;
    private HBox miniBox;
    private boolean miniDrawer=false;
    private void menuOpacityAnimation(double opacityTo, EventHandler<ActionEvent> onFinish){
        final Timeline timeline = new Timeline();
        timeline.setOnFinished(onFinish);
timeline.setCycleCount(1);
timeline.setAutoReverse(true);
final KeyValue kvmax = new KeyValue(drawerMenu.opacityProperty(), opacityTo);

final KeyFrame kf = new KeyFrame(Duration.millis(250), kvmax);
timeline.getKeyFrames().add(kf);
timeline.play();

    }
    private void drawerSizeAnimation(double widthTo, EventHandler<ActionEvent> onFinish){
        final Timeline timeline = new Timeline();
        timeline.setOnFinished(onFinish);
        
timeline.setCycleCount(1);
timeline.setAutoReverse(true);
final KeyValue kvmax = new KeyValue(drawerBox.maxWidthProperty(), widthTo,Interpolator.EASE_BOTH);
final KeyValue kvmin = new KeyValue(drawerBox.minWidthProperty(), widthTo,Interpolator.EASE_BOTH);

final KeyFrame kf = new KeyFrame(Duration.millis(350), kvmax, kvmin);
timeline.getKeyFrames().add(kf);
timeline.play();

    }
    private double normalDrawerWidth=280;
    public void setDrawerWidth(double width){
        this.normalDrawerWidth=width;
        drawerBox.setMinWidth(width);
        drawerBox.setMaxWidth(width);
    }
    public MaterialStandardLayout(String title, boolean big, boolean stackedUp, boolean fullSize){
        super();
        initStyles();
        this.fullSize=fullSize;
        drawerBox=new VBox();
        drawerBox.setCache(true);
        drawerBox.setMinWidth(normalDrawerWidth);
        drawerBox.setMaxWidth(normalDrawerWidth);
        drawerBox.getStyleClass().remove("material-drawer");
        drawerBox.getStyleClass().add("material-drawer");
        
        drawerMenu=new MaterialMenu(drawerBox);
        drawerMenu.setCache(true);
        VBox spaceBox=new VBox();
        VBox.setVgrow(spaceBox, Priority.ALWAYS);
        drawerBox.getChildren().add(drawerMenu);
        drawerBox.getChildren().add(spaceBox);
         miniBox=new HBox();
        miniButton=new MaterialIconButton(MaterialIconButton.MOVE_LEFT_ICON);
        miniButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                setMiniDrawer(!miniDrawer);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        miniBox.getChildren().add(miniButton);
        miniBox.setAlignment(Pos.CENTER_RIGHT);
        miniBox.setPadding(new Insets(0,8,8,0));
        miniButton.setColorCode(MaterialColor.material.BLACK_54);
        drawerBox.getChildren().add(miniBox);
        drawer=new MaterialDrawer(this);
        bodyBox=new VBox();
        bodyBox.setCache(true);
bodyBox.setCacheShape(true);
bodyBox.setCacheHint(CacheHint.SPEED);
        container=new StackPane(bodyBox);
        
        container.setPickOnBounds(false);
        //bodyBox.getStyleClass().add("rojo");
        bodyBox.setPickOnBounds(false);
        if(stackedUp){
            container.setPadding(new Insets(-55,80,30,80));
        
        }
        else{
            container.setPadding(new Insets(0,80,30,80));
        }
        if(isFullSize()){
            
            container.setPadding(new Insets(0,0,0,0));
        }
        //container.getStyleClass().add("rojo");
        theToolbar=new MaterialToolbar(title,big) {

            @Override
            public void onMenuButton(Button button) {
                onMenuButtonPressed(button);
                //if(showMenuOnClick){
                //    drawer.unhide();
                
                //}
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void onUpdateButton(Button button){
                onUpdateButtonPressed(button);
            }
        };
        setTop(getToolbar());
        
        setCenter(container);
        setLeft(drawerBox);
    }
    private MaterialToolbar theToolbar;
    public void setTitle(String texto){
       getToolbar().setTitle(texto);
       
       
       
    }
    
    public void addOptionsItem(MaterialDropdownMenuItem mItem){
        getToolbar().addOptionsItem(mItem);
    }
    public MaterialToolbar getToolbar(){
        return theToolbar;
    }
    public void showTab(int index){
        drawerMenu.getItem(index).onItemClick();
    }
    public void showTab(String name){
        for(int i=0;i<drawerMenu.size();i++){
            if(drawerMenu.getItem(i).getName().equalsIgnoreCase(name)){
                drawerMenu.getItem(i).onItemClick();
            }
            
        }
        
    }
    public void addDrawerItem(String texto, String icon, Pane vista, Node extraPane){
       
        MaterialMenuItem newItem=new MaterialMenuItem(texto,icon){
            @Override   
            public void onItemClick(){
                
                if(vista!=null){
                    getToolbar().setTitle(texto);
                    getToolbar().setBottomNode(null);
                    if(extraPane!=null){
                        getToolbar().setBottomNode(extraPane);
                    }
                    
                    setRootView(vista);
                }
                else{
                    new MaterialToast("La sección de '"+texto+"' no se encuentra disponible por el momento").unhide();
                }
            }
        };
        newItem.setOnCloneItemClick(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(vista!=null){
                    getToolbar().setTitle(texto);
                    getToolbar().setBottomNode(null);
                    if(extraPane!=null){
                        getToolbar().setBottomNode(extraPane);
                    }
                    
                    setRootView(vista);
                }
                else{
                    new MaterialToast("La sección de '"+texto+"' no se encuentra disponible por el momento").unhide();
                } //To change body of generated methods, choose Tools | Templates.
            }
        });
        drawerMenu.addItem(newItem);
        //drawer.addDrawerMenuItem(texto, icon,vista);
    }
    public void addDrawerItem(String texto, String icon, Pane vista){
       addDrawerItem(texto,icon,vista,null);
        //drawer.addDrawerMenuItem(texto, icon,vista);
    }
    public void addDrawerItem(String texto, String icon){
        addDrawerItem(texto,icon,null);
        //drawer.addDrawerMenuItem(texto, icon,null);
    }
    public void addDrawerItem(MaterialMenuItem item){
        drawerMenu.addItem(item);
        
        //drawer.addDrawerMenuItem(item);
    }
    public void addNodeAsDrawerItem(Node node){
        drawerMenu.addNodeAsItem(node);
    }
    public abstract void onMenuButtonPressed(Button button);
    public abstract void onUpdateButtonPressed(Button button);
    public void addCard(MaterialCard card){
        if(rootView==null){
            
            bodyBox.getChildren().add(card);
            
            //card.pickOnBoundsProperty().set(false);
            //bodyBox.pickOnBoundsProperty().set(false);
        }
        else{
            System.out.println("Error al agregar 'card'. Existe otra vista principal.");
        }
        
    }
    private Pane rootView=null;
    private Timeline timelineTranslate;
    private TranslateTransition translateTransition, inverseTransition;
    private void playInverseTransition(){
        if(MaterialDesign.isAnimated()){
            createInverseTransition();
            inverseTransition.play();
        }
        else{
            bodyBox.translateXProperty().set(0);
            
                 ((MaterialPane)rootView).onShown();
        }
    }
    private void createInverseTransition(){
         inverseTransition = new TranslateTransition(Duration.millis(500), bodyBox);
       //System.out.println(bodyBox.layoutXProperty().get()+" layoutx");
        inverseTransition.setToX(0);
         inverseTransition.setOnFinished(new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 bodyBox.translateXProperty().set(0);
                 ((MaterialPane)rootView).onShown();
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
    }
    public void setToolbarColor(MaterialColor color){
        this.getToolbar().setColor(color);
    }
    public void setToolbarColorFromCode(MaterialColor webColor){
        this.getToolbar().setColorFromCode(webColor);
    }
    private void playTranslateTransition(){
        if(MaterialDesign.isAnimated()){
        createTranslateTransition();
        
          translateTransition.play();
        }
        else{
            realRootView();
        }
    }
    private void createTranslateTransition(){
         translateTransition = new TranslateTransition(Duration.millis(600), bodyBox);
       //System.out.println(bodyBox.layoutXProperty().get()+" layoutx");
        translateTransition.setToX(MaterialDesign.primary.getWidth()-bodyBox.layoutXProperty().get());
         translateTransition.setOnFinished(new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 realRootView();
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
              //KeyValue posValue=new KeyValue(rootView.translateXProperty(),500);
                
        //KeyFrame keyFrame=new KeyFrame(Duration.millis(2000),posValue);
        
        //timelineTranslate=new Timeline(keyFrame);{
        
    }
    private void realRootView(){
        rootView=auxiliarPane;
        bodyBox.getChildren().remove(0, bodyBox.getChildren().size());
        if(postedPane!=null){
            bodyBox.getChildren().add(postedPane);
        }
        else{
            bodyBox.getChildren().add(auxiliarPane);
        }
        bodyBox.translateXProperty().set(-(bodyBox.layoutXProperty().get()+bodyBox.widthProperty().get()));
        playInverseTransition();
        
        //bodyBox.translateXProperty().set(0);
    }
    private Pane auxiliarPane=null;
    private Pane postedPane=null;
    public void setRootView(Pane pane){
        auxiliarPane=pane;
        
        if(((MaterialPane) pane).usesFullscreen()){
            bodyBox.setMinWidth(MaterialDesign.primary.getWidth());
            container.setMinWidth(MaterialDesign.primary.getWidth());
            
            container.setPadding(new Insets(16,80,30,80));
        }
        
        if(this.isFullSize()){
            ((MaterialPane)pane).getContentCard().setCardHeight(2000);
            ((MaterialPane)pane).getContentCard().setCardWidth(2000);
            
            Pane plainCard=((MaterialPane)pane).getCoreSmartCard();
            plainCard.getChildren().get(0).getStyleClass().removeAll("material-card","material-plain-card");
            plainCard.getChildren().get(0).getStyleClass().removeAll("material-plain-card");
        //plainCard.getStyleClass().add("material-plain-card");
            postedPane=((MaterialPane)pane).getCoreSmartCard();
        
        }
        
        
        
        if(rootView!=null){
            
            
        playTranslateTransition();
            
        }
        else{
            realRootView();
        }
            
        
        
        
        
        
        
        
        
    }
    
    private void initStyles(){
            this.getStyleClass().add("full-transparent-container");
        //this.getStyleClass().add("principal-container");
    }
}
