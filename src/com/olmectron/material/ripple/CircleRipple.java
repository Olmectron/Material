/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.ripple;

import com.olmectron.material.components.MaterialButton;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */

public class CircleRipple {
    private Node node;
    private boolean active=true;
    public void setActive(boolean act){
        this.active=act;
    }
    public CircleRipple(){
        createRippleEffect();
    }
    public void setNode(Node node){
        this.node=node;
        if(isRegionClass(this.node)){
            //((MaterialButton)node).getChildrenUnmodifiable().add(0, circleRipple);
            assignMouseEvents();
        
        }
        else{
            try {
                throw new Exception("Debe ser un nodo con superclase Region");
            } catch (Exception ex) {
                Logger.getLogger(CircleRipple.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void onRippleFinished(){};
    public Circle getShape(){
        return circleRipple;
    }
        private Circle circleRipple;
    private Rectangle rippleClip = new Rectangle();
    private Duration rippleDuration =  Duration.millis(430);
    private double lastRippleHeight = 0;
    private double lastRippleWidth = 0;
    private Color rippleColor = null;

    private String getWebColor(Paint paint){
        
        String color=paint.toString();
        String webColor=color.substring(2, 8);
        return webColor;
        
    }

    private boolean isRegionClass(Node n){
        return Region.class.isAssignableFrom(n.getClass());
    }
    private boolean isLabeledClass(Node n){
        return Labeled.class.isAssignableFrom(n.getClass());
    }
    final FadeTransition fadeTransition = new FadeTransition(Duration.millis(530));
    final SequentialTransition parallelTransition = new SequentialTransition();
     final Timeline scaleRippleTimeline = new Timeline();
     public void playRipple(double localX, double localY){
         playRippleAnimation(localX,localY);
     }
     public void playFade(){
         playFadeAnimation();
     }
     private EventHandler<ActionEvent> rippleAccion=null;
     public void setOnRippleEffect(EventHandler<ActionEvent> accion){
         this.rippleAccion=accion;
     }
    private void createRippleEffect() {
        
        circleRipple = new Circle(0.1, new Color(0,0,0,0.1));
        
// Optional box blur on ripple - smoother ripple effect
//        circleRipple.setEffect(new BoxBlur(3, 3, 2));

        // Fade effect bit longer to show edges on the end
        
        fadeTransition.setNode(circleRipple);
        fadeTransition.setInterpolator(Interpolator.EASE_OUT);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                onRippleFinished();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       

        
        parallelTransition.getChildren().addAll(
                scaleRippleTimeline
        );

        parallelTransition.setOnFinished(event1 -> {
            //circleRipple.setOpacity(0.0);
            //circleRipple.setRadius(0.1);
        });
        
        
    }
    private void playFadeAnimation(){
        if(active){
                
                fadeTransition.playFromStart();
                
            }
    }
    private void assignMouseEvents(){
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event ->{
            playFadeAnimation();
            
        });
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            //System.out.println("Ripple on Action");
            
            playRippleAnimation(event.getX(),event.getY());
        });
    }
    private void playRippleAnimation(double x, double y){
        if(active){
            circleRipple.setOpacity(1.0);
            circleRipple.setRadius(0.1);
            if(rippleColor!=null){
                setRippleColor(rippleColor);
            }
            else{
                if(isLabeledClass(node)){

                setRippleColor(Color.web(getWebColor(((Labeled)node).getTextFill())+"40"));        

                }
            }
            fadeTransition.stop();
            parallelTransition.stop();
            parallelTransition.getOnFinished().handle(null);

            circleRipple.setCenterX(x);//event.getX());
            circleRipple.setCenterY(y);//event.getY());

            // Recalculate ripple size if size of button from last time was changed
            if (((Region)node).getWidth() != lastRippleWidth || ((Region)node).getHeight() != lastRippleHeight)
            {
                lastRippleWidth = ((Region)node).getWidth();
                lastRippleHeight = ((Region)node).getHeight();

                rippleClip.setWidth(lastRippleWidth);
                rippleClip.setHeight(lastRippleHeight+1);
                //System.out.println("rippleClip "+lastRippleWidth+" coma "+lastRippleHeight);

                try {
                    
                    rippleClip.setArcHeight(((Region)node).getBackground().getFills().get(0).getRadii().getTopLeftHorizontalRadius());
                    rippleClip.setArcWidth(((Region)node).getBackground().getFills().get(0).getRadii().getTopLeftHorizontalRadius());
                    //System.out.println("ARC: height:"+rippleClip.getArcHeight()+", width:"+rippleClip.getArcWidth());
                    circleRipple.setClip(rippleClip);
                    //.out.println(rippleClip.getWidth()+" R "+rippleClip.getHeight());
                } catch (Exception e) {
                    circleRipple.setClip(rippleClip);
                    //e.printStackTrace();
                }

                // Getting 45% of longest button's length, because we want edge of ripple effect always visible (0.45)
                double circleRippleRadius = Math.pow(Math.pow(((Region)node).getHeight(), 2)+Math.pow(((Region)node).getWidth(), 2),0.5);
                        
                        //Math.max(getHeight(), getWidth()) * 0.6;
                final KeyValue keyValue = new KeyValue(circleRipple.radiusProperty(), circleRippleRadius, Interpolator.EASE_OUT);
                final KeyFrame keyFrame = new KeyFrame(rippleDuration, keyValue);
                scaleRippleTimeline.getKeyFrames().clear();
                scaleRippleTimeline.getKeyFrames().add(keyFrame);
            }
                if(rippleAccion!=null){
                    parallelTransition.setOnFinished(rippleAccion);
                }
                parallelTransition.playFromStart();
            }
    }

    public void setRippleColor(Color color) {
        circleRipple.setFill(color);
    }
    public void setMainColor(Color color){
        this.rippleColor=color;
    }
    public Color getMainColor(){
        return this.rippleColor;
    }
}
