/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.layouts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialCard;
import com.olmectron.material.components.MaterialDrawer;
import com.olmectron.material.components.MaterialDropdownMenuItem;
import com.olmectron.material.components.MaterialEditableToolbar;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.components.MaterialMenu;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.components.MaterialToolbar;
import com.olmectron.material.components.menu.MaterialMenuItem;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.helpers.IconButtonContainer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public abstract class MaterialEditableLayout extends BorderPane {
    private VBox bodyBox;
    private MaterialDrawer drawer;
    public MaterialEditableLayout(String title){
        this(title,false,false,false,true);
        
    }
    public MaterialEditableLayout(boolean leftPane){
        this("",false,false,true,leftPane);
    }
    public MaterialEditableLayout(String title,boolean leftPane){
        this(title,false,false,false,leftPane);
        
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
                    }else{
                setDrawerMenuVisible(true);
            }
            drawerSizeAnimation(minDrawerSize, new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    miniButton.setMaterialIcon(MaterialIconButton.MOVE_RIGHT_ICON);
                    miniBox.setAlignment(Pos.CENTER);
                    drawerMenu.setMiniMenu(miniDrawer);
                    drawerBox.setMaxWidth(minDrawerSize);
                    //drawerMenu.setMinWidth(minDrawerSize);
                    //drawerMenu.setMaxWidth(minDrawerSize);
                    
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
                    
                    //    if(minDrawerSize==0){
                        
                    drawerBox.setMaxWidth(normalDrawerWidth);
                        setDrawerMenuVisible(true);
                        
                   // }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        }
        
    }
    public void setBigToolbar(boolean big){
        theToolbar.setBig(big);
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



final KeyValue toolbarBottomMax=new KeyValue(getToolbar().getBottomExpander().maxWidthProperty(),widthTo,Interpolator.EASE_BOTH);
final KeyValue toolbarBottomMin=new KeyValue(getToolbar().getBottomExpander().minWidthProperty(),widthTo,Interpolator.EASE_BOTH);

final KeyFrame kf = new KeyFrame(Duration.millis(350), kvmax, kvmin,toolbarBottomMax,toolbarBottomMin);
timeline.getKeyFrames().add(kf);
timeline.play();

    }
    private double normalDrawerWidth=280;
    public void setDrawerWidth(double width){
        this.normalDrawerWidth=width;
        drawerBox.setMinWidth(width);
        drawerBox.setMaxWidth(width);
    }
    private EventHandler<MouseEvent> enterEvent=(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(miniDrawer){
                    setMiniDrawer(false);
                }
            }
        });
        private EventHandler<MouseEvent> exitEvent=(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!miniDrawer){
                    setMiniDrawer(true);
                }
            }
        });
    public void setShowOnHover(boolean show){
        if(show){
            drawerBox.setOnMouseEntered(enterEvent);
            drawerBox.setOnMouseExited(exitEvent);
            
        }
        else{
            drawerBox.setOnMouseEntered(null);
            drawerBox.setOnMouseExited(null);
        }
    }
    public void setLogoImage(ImageView img){
        imageBox.getChildren().clear();
        imageBox.getChildren().add(img);
    }
    private VBox imageBox;
    public MaterialEditableLayout(String title, boolean big, boolean stackedUp, boolean fullSize,boolean leftPane){
        super();
        initStyles();
        this.fullSize=fullSize;
        
        drawerBox=new VBox();
        
        drawerBox.setCache(true);
        drawerBox.setMinWidth(normalDrawerWidth);
        drawerBox.setMaxWidth(normalDrawerWidth);
         drawerBox.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                            //System.out.println("juerngviansgvijasndsv");
                            
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        drawerBox.getStyleClass().remove("material-drawer");
        drawerBox.getStyleClass().add("material-drawer");
        
        drawerMenu=new MaterialMenu(drawerBox);
        drawerMenu.setCache(true);
        VBox spaceBox=new VBox();
        VBox.setVgrow(spaceBox, Priority.ALWAYS);
        drawerBox.getChildren().add(drawerMenu);
    
        drawerBox.getChildren().add(spaceBox);
         miniBox=new HBox();
         imageBox=new VBox();
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
        drawerBox.getChildren().add(imageBox);
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
        theToolbar=new MaterialEditableToolbar(title,big) {

            @Override
            public void onMenuButton(Button button) {
                if(getBackButton()){
                    onBackButtonPressed(button);
                
                }
                else{
                    
                    onMenuButtonPressed(button);
                }
                
                //if(showMenuOnClick){
                //    drawer.unhide();
                
                //}
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        setTop(getToolbar());
        
        setCenter(container);
        if(leftPane){
            setLeft(drawerBox);
        }
        
    }
    private MaterialEditableToolbar theToolbar;
    private BooleanProperty backButton;
public BooleanProperty backButtonProperty(){
   if(backButton==null){
       backButton=new SimpleBooleanProperty(this,"backButton");
       backButton.set(false);
   }
   return backButton;
}
public boolean getBackButton(){
   return backButtonProperty().get();
}

    public void setBackButton(boolean b){
        backButtonProperty().set(b);
        if(b){
         theToolbar.getMenuButton().setMaterialIcon(MaterialIconButton.ARROW_BACK);
        }
        else{
            theToolbar.getMenuButton().setMaterialIcon(MaterialIconButton.MENU_ICON);
        }
    }
    public void setTitle(String texto){
       getToolbar().setTitle(texto);
       
       
       
    }
    public String getTitle(){
        return getToolbar().getTitle();
    }
    
    public void fullSize(){
        getMainStackPane().setPadding(new Insets(0));
    }
    public StackPane getMainStackPane(){
        return container;
    }
    public MaterialEditableToolbar getToolbar(){
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
    public void showModule(String title, Pane pane){
        
        this.showModule(title,pane,null);
    }
    public void showModule(String title, Pane pane, Pane extra){
        
        getToolbar().setTitle(title);
        getToolbar().setBottomNode(extra);
        setRootView(pane);
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
                    
                    if(getSelectedCounter()>0){
                        ((MaterialPane)vista).onPressedTwice();
                    }
                    //System.out.println("Counter: "+getSelectedCounter());
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
    public IconButtonContainer addToolbarActionButton(String materialIcon,EventHandler<ActionEvent> onClick,String tooltip){
        return getToolbar().addActionButton(materialIcon, onClick,tooltip);
    }
    public IconButtonContainer addToolbarActionButton(String materialIcon,EventHandler<ActionEvent> onClick){
        return getToolbar().addActionButton(materialIcon, onClick);
    }
    public IconButtonContainer addToolbarActionButton(MaterialIconButton iconButton){
        return getToolbar().addActionButton(iconButton);
    }
    
    public IconButtonContainer addToolbarActionButton(String materialIcon,EventHandler<ActionEvent> onClick,String tooltip, int index){
        return getToolbar().addActionButton(materialIcon, onClick,tooltip,index);
    }
    public IconButtonContainer addToolbarActionButton(String materialIcon,EventHandler<ActionEvent> onClick,int index){
        return getToolbar().addActionButton(materialIcon, onClick,index);
    }
    public IconButtonContainer addToolbarActionButton(MaterialIconButton iconButton,int index){
        return getToolbar().addActionButton(iconButton,index);
    }
    public void switchToolbarActions(int index){
        getToolbar().switchToolbarActions(index);
    }
    
    
    
    public void addNodeAsDrawerItem(Node node){
        drawerMenu.addNodeAsItem(node);
    }
    public void onBackButtonPressed(Button button){}
    public abstract void onMenuButtonPressed(Button button);
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
            if(MaterialDesign.getAnimationType()==MaterialDesign.ANIMATION_TRANSLATE)
            {
            createInverseTransition();
            inverseTransition.play();
            }
            else if(MaterialDesign.getAnimationType()==MaterialDesign.ANIMATION_FADE){
            createInverseFadeTransition();
            inverseFadeTransition.play();
            
            }
        }
        else{
            if(MaterialDesign.getAnimationType()==MaterialDesign.ANIMATION_TRANSLATE)
            bodyBox.translateXProperty().set(0);
            else if(MaterialDesign.getAnimationType()==MaterialDesign.ANIMATION_FADE)
            bodyBox.opacityProperty().set(1.0);
                 ((MaterialPane)rootView).onShown();
        }
    }
    private FadeTransition inverseFadeTransition;
    private void createInverseFadeTransition(){
         inverseFadeTransition = new FadeTransition(Duration.millis(500), bodyBox);
       //System.out.println(bodyBox.layoutXProperty().get()+" layoutx");
        inverseFadeTransition.setFromValue(0.0);
        inverseFadeTransition.setToValue(1.0);
        
         inverseFadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 bodyBox.opacityProperty().set(1.0);
                 ((MaterialPane)rootView).onShown();
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
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
            if(MaterialDesign.getAnimationType()==MaterialDesign.ANIMATION_TRANSLATE){
                
        createTranslateTransition();
        
          translateTransition.play();
            }
            else if(MaterialDesign.getAnimationType()==MaterialDesign.ANIMATION_FADE){
                createFadeTransition();
                fadeTransition.play();
            }
        }
        else{
            realRootView();
        }
    }
    private FadeTransition fadeTransition;
    private void createFadeTransition(){
         fadeTransition = new FadeTransition(Duration.millis(600), bodyBox);
       //System.out.println(bodyBox.layoutXProperty().get()+" layoutx");
       fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
         fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

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
        if(rootView!=null)
       ((MaterialPane)rootView).onHidden();
        rootView=auxiliarPane;
        bodyBox.getChildren().remove(0, bodyBox.getChildren().size());
        if(postedPane!=null){
            bodyBox.getChildren().add(postedPane);
        }
        else{
            bodyBox.getChildren().add(auxiliarPane);
        }
        
        if(MaterialDesign.getAnimationType()==MaterialDesign.ANIMATION_TRANSLATE)
        bodyBox.translateXProperty().set(-(bodyBox.layoutXProperty().get()+bodyBox.widthProperty().get()));
        else if(MaterialDesign.getAnimationType()==MaterialDesign.ANIMATION_FADE)
            bodyBox.opacityProperty().set(0.0);
        
        playInverseTransition();
        
        //bodyBox.translateXProperty().set(0);
    }
    private Pane auxiliarPane=null;
    private Pane postedPane=null;
    public void setRootView(Pane pane){
        auxiliarPane=pane;
        if(pane!=null){
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
        
        
        
        
        
        
    }
    
    private void initStyles(){
            this.getStyleClass().add("full-transparent-container");
        //this.getStyleClass().add("principal-container");
    }
}
