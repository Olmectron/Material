/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.MaterialPopupComponent;
import com.olmectron.material.constants.MaterialColor;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */
public class MaterialToast extends Popup implements MaterialPopupComponent {
    private String message;
    private static MaterialToast lastToast=null;
    public static double LENGTH_SHORT=4000;
    public static double LENGTH_UNDEFINED=0;
    public static double LENGTH_LONG=9000;
    
    public static void setLastToast(MaterialToast toast){
        lastToast=toast;
    }
    
    /**
     *
     * @param message
     */
    public MaterialToast(String message){
       this(message,null,4000);

    }
    public MaterialToast(String message, double length){
       this(message,null,length);

    }
    private MaterialButton button;
    public MaterialToast(String message, MaterialButton button, double length){
         super();
         
        this.button=button;
        this.toastLength=length;
        initAll(message);
        
    }
    private Pane customPane;
    public MaterialToast(Pane pane){
        super();
        this.button=null;
        this.toastLength=MaterialToast.LENGTH_SHORT;
        this.customPane=pane;
        initAll("");
    }
    public void setLength(double length){
        this.toastLength=length;
    }
    private double toastLength=4000;
    
    private void initAll(String m){
        this.message=m;
        //setAutoHide(true);
        
        
        //this.setOpacity(0.0);
        HBox cL=new HBox();
        Label lab=new Label(message);
        if(customPane==null){
        lab.getStyleClass().add("material-toast");
        
        cL.getChildren().add(lab);
        }
        else{
            cL.getChildren().add(customPane);
        }
        cL.setOpacity(0.0);
        
        if(button!=null){
            button.setRippleActive(false);
            button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    //hide();
                   toastHide(cL);
                    onButtonClicked();
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            button.setPadding(new Insets(0,24,0,0));
            //HBox cButton=new HBox(button);
            //cButton.setAlignment(Pos.CENTER);
            //cButton.setPadding(new Insets(0,24,0,0));
            cL.getChildren().add(button);
        }
        cL.setAlignment(Pos.CENTER_LEFT);
        cL.getStyleClass().add("material-toast-box");
        //l.getStyleClass().add("medium-font");
        cL.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                toastHide(cL);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        getContent().add(cL);
        
        //setX(16);
        //setY(16);
        //setAnchorLocation(AnchorLocation.WINDOW_BOTTOM_LEFT);
        //this.setAnchorY(MaterialDesign.primary.getHeight()-100);
          /*    TranslateTransition tt = new TranslateTransition(Duration.millis(1000), l);
              
              KeyValue posValue=new KeyValue(l.translateYProperty(),500);
                
        KeyFrame keyFrame=new KeyFrame(Duration.millis(1000),posValue);
        
        Timeline time=new Timeline(keyFrame);*/
        this.setOnShown(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent event) {
                
                if(MaterialDesign.primary.isFullScreen()){
                    if(MaterialDesign.windowCoord!=null && MaterialDesign.sceneCoord!=null){
                        setX(MaterialDesign.windowCoord.getX()+16);
                        setY(MaterialDesign.windowCoord.getY()+MaterialDesign.primary.getHeight()-16-getHeight());
                    }
                    else{
                        setX(16);
                        setY(MaterialDesign.primary.getHeight()-16-getHeight());
                    }

                    setX(MaterialDesign.primary.getWidth()/2-getWidth()/2);
                    setY(16);
                }
                else{
                   
                //Point2D point = nodo.localToScene(0.0,  0.0);
          setX(MaterialDesign.primary.getX()+MaterialDesign.getScene().getX()+16);
                  setY(MaterialDesign.primary.getY()+MaterialDesign.getScene().getY()+MaterialDesign.getScene().getHeight()-getHeight()-16); 
        
            
            
                }
                /*TranslateTransition tt = new TranslateTransition(Duration.millis(1000), l);
                tt.setToY(0);
                l.translateYProperty().set(getHeight()+16);
                tt.play();
                */
                MaterialDesign.audio.error.play();
                FadeTransition ft=new FadeTransition(Duration.millis(400),cL);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        cL.setOpacity(1.0);
         
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                ft.play();
                if(toastLength>0){
                    
                    new Timeline(new KeyFrame(
                Duration.millis(toastLength),
                ae -> toastHide(cL)))
            .play();
                
                
                
                }
            }
        
        });
        /*this.setOnShown(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent event) {
                setX(MaterialDesign.primary.getWidth()/2-getWidth()/2);
                
                //tt.setFromY(getHeight());
                //tt.setToY(0);
                //setY(MaterialDesign.primary.getHeight()-getHeight());
                l.translateYProperty().set(MaterialDesign.primary.getHeight()-getHeight());
                System.out.println(
                l.translateYProperty().get()+"");
                        setOpacity(1.0);
                time.playFromStart();
                
                new Timeline(new KeyFrame(
        Duration.millis(3000),
        ae -> hide()))
    .play();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
        
    }
    public void onButtonClicked(){};
    

    @Override
    public void unhide() {
        if(lastToast!=null){
            lastToast.hide();
        }
        
        setLastToast(this);
        show(MaterialDesign.primary);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void toastHide(Node n){
        FadeTransition ft=new FadeTransition(Duration.millis(500),n);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        n.setOpacity(0.0);
                        hide();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                ft.play();
        
        
    }

    
    
}
