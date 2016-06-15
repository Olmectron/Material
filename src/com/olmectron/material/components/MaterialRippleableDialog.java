/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;
import com.olmectron.material.components.FlatButton;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.components.MaterialPasswordField;
import com.olmectron.material.components.MaterialStandardDialog;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.components.RaisedButton;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
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
public abstract class MaterialRippleableDialog extends MaterialStandardDialog {
    @Override
    public void setDialogHeight(double h){
        super.setDialogHeight(h);
        if(rippleParent!=null){
            rippleParent.setPrefHeight(h);
        }
    }
    private StackPane rippleParent;
    private MaterialDisplayText rippleContainer;
    private VBox rootBox;
    public MaterialRippleableDialog(){
        super();
        MaterialPasswordField codigoClienteField=new MaterialPasswordField("Escanea el código de cliente");
        codigoClienteField.setPadding(new Insets(0,0,8,0));
        codigoClienteField.allowDot();
        this.setDialogWidth(300);
        this.setDialogHeight(300);
        rippleContainer=new MaterialDisplayText("");
        //clienteText.setRippleActive(true);
        //clienteText.setRippleColor(Color.web("#F44336"));
        /*clienteText.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                System.out.println("asa");
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
        //rippleContainer.setColor(MaterialColor.BLACK_87);
        
        
        rippleParent=new StackPane();
        super.addNode(rippleParent);
        //setCardPadding(new Insets(24));
        
        //p.getChildren().add(codigoClienteField);
        rippleContainer.setFillContainer(rippleParent);
        //clienteText.setPickOnBounds(true);
        
        //clienteText.getStyleClass().add("rounded-corners");
        //p.getStyleClass().add("rounded-corners");
       
               
        
        //FlatButton b=new FlatButton("Salir");
        //b.setColor(MaterialColor.RED);
        
        //FlatButton b2=new FlatButton("Continuar");
        //HBox buttonBox=new HBox(codigoClienteField,b,b2);
        rootBox=new VBox();
        rippleParent.getChildren().add(rippleContainer);
        rippleParent.getChildren().add(rootBox);
        
        /*b.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                clienteText.setRippleActive(true);
                clienteText.toFront();
                Event.fireEvent(clienteText, event);
                
                //p.getChildren().remove(buttonBox);
                clienteText.setRippleActive(false);
                MaterialDisplayText cs=new MaterialDisplayText("Terminando...");
                cs.setColor(MaterialColor.WHITE_100);
                p.getChildren().add(cs);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        b2.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                clienteText.setRippleColor(Color.web("#2196F3"));
                clienteText.setRippleActive(true);
                clienteText.toFront();
                
                Event.fireEvent(clienteText,event);
                //p.getChildren().remove(buttonBox);
                
                clienteText.setRippleActive(false);
                MaterialDisplayText cs=new MaterialDisplayText("Reanudando...");
                cs.setColor(MaterialColor.WHITE_100);
                p.getChildren().add(cs);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
//addNode(clienteText);
        //addNode(codigoClienteField);
        //((MaterialPane)getRootPane()).getCoreSmartCard().getChildren().add(clienteText);
            //addNode(clienteText);
    
    }
    @Override
    public void addNode(Node node){
        rootBox.getChildren().add(node);
        
    }
    public Pane getDialogPane(){
        return rootBox;
    }
    public Point2D getSceneToLocalRippleContainer(double x, double y){
        return rippleContainer.sceneToLocal(x, y);
    }
    @Override
    public void setCardPadding(Insets insets){
        rootBox.setPadding(insets);
    }
    public void playRipple(double x, double y, Color rippleColor, EventHandler<ActionEvent> onFinished,Node addedNode, boolean removeOnEnd){
        
            rippleContainer.setRippleActive(true);
            rippleContainer.toFront();
            
            if(rippleColor!=null){
                rippleContainer.setRippleColor(rippleColor);
            }
            else{
                rippleContainer.setRippleColor(Color.web("#2196F3"));
            }
            
        rippleContainer.getRipple().setOnRippleEffect(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    if(removeOnEnd){
                        rippleParent.getChildren().remove(rootBox);
                    
                    }
                    if(onFinished!=null){
                        onFinished.handle(event);
                        
                    }
                    if(addedNode!=null){
            rippleParent.getChildren().add(addedNode);
        }

                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
            rippleContainer.getRipple().playRipple(x, y);
                //Event.fireEvent(rippleContainer, event);
        rippleContainer.setRippleActive(false);
        
       
    }
    public void playRipple(MouseEvent event, Color rippleColor, EventHandler<ActionEvent> onFinished, Node addedNode){
       if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            rippleContainer.setRippleActive(true);
            rippleContainer.toFront();
            
            if(rippleColor!=null){
                rippleContainer.setRippleColor(rippleColor);
            }
            else{
                rippleContainer.setRippleColor(Color.web("#2196F3"));
            }
            
        rippleContainer.getRipple().setOnRippleEffect(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    rippleParent.getChildren().remove(rootBox);
                    if(onFinished!=null){
                        onFinished.handle(event);
                    }

                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
                Event.fireEvent(rippleContainer, event);
        rippleContainer.setRippleActive(false);
        if(addedNode!=null){
            rippleParent.getChildren().add(addedNode);
        }
       }
       else{
           System.out.println("Debe ser un evento MOUSE_PRESSED");
       }
       
    }
    
    
}
