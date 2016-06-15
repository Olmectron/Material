/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.layouts.MaterialPane;
import com.sun.javafx.scene.control.behavior.TextFieldBehavior;
import com.sun.javafx.scene.control.skin.TextFieldSkin;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import org.utilities.Fecha;

/**
 *
 * @author olmec
 */
public class MoneyTextField extends MaterialTextField{
    
    public MoneyTextField(String label){
        this(label,false);
        
    }
    private boolean round=false;
    private DoubleProperty value;
    private static MaterialCalculator calc;
    public DoubleProperty valueProperty(){
        if(value==null){
            value=new SimpleDoubleProperty(this,"value");
            value.set(0);
        }
        return value;
    }
    public double getValue(){
        return valueProperty().get();
    }
    public void setValue(double val){
        valueProperty().set(val);
    }
    public MoneyTextField(String label, boolean round){
        super(label);
        this.round=round;
       initAll();
       
       //textField().getStyleClass().remove("big");
       //textField().getStyleClass().add("giant");
    }
    public void setFieldAlignment(Pos c){
        textField().setAlignment(c);
        setAlignment(c);
        
    }
    private static MaterialDropdownMenu lastCalculator;
    public static double roundToHalfUp(double from){
        int multiWithoutDecimals=((int)(from))*100;
        int multiWithDecimals=(int)(from*100);
        int diferencia=multiWithDecimals-multiWithoutDecimals;
        if(diferencia>=75){
            multiWithoutDecimals=multiWithoutDecimals+100;
        }
        else if(diferencia>=25){
            multiWithoutDecimals=multiWithoutDecimals+50;
        }
        return ((double)multiWithoutDecimals)/100;
    }
    public static double roundToHalf(double from){
        int multiWithoutDecimals=((int)(from))*100;
        int multiWithDecimals=(int)(from*100);
        int diferencia=multiWithDecimals-multiWithoutDecimals;
        if(diferencia>=75){
            multiWithoutDecimals=multiWithoutDecimals+100;
        }
        else if(diferencia>=25){
            multiWithoutDecimals=multiWithoutDecimals+50;
        }
        return ((double)multiWithoutDecimals)/100;
    }
    private void initAll(){
           final ContextMenu contextMenu = new ContextMenu();
contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
    public void handle(WindowEvent e) {
        System.out.println("showing");
    }
});
contextMenu.setOnShown(new EventHandler<WindowEvent>() {
    public void handle(WindowEvent e) {
        System.out.println("shown");
    }
});

MenuItem item1 = new MenuItem("About");
item1.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e) {
        System.out.println("About");
    }
});
MenuItem item2 = new MenuItem("Preferences");
item2.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e) {
        System.out.println("Preferences");
    }
});

//contextMenu.getItems().addAll(item1, item2);
textField().setContextMenu(contextMenu);
textField().setOnKeyTyped(new EventHandler<KeyEvent>(){
               @Override
               public void handle(KeyEvent event) {
                   if(calc!=null){
                       event.consume();
                       calc.setNumber(event.getCharacter());
                   }
                   //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }
           });
textField().setOnKeyPressed(new EventHandler<KeyEvent>(){
               @Override
               public void handle(KeyEvent event) {
                   if(event.getCode().equals(KeyCode.BACK_SPACE) && calc!=null){
                       event.consume();
                       calc.backspace();
                       
               }
                   if(event.getCode().equals(KeyCode.ENTER) && calc!=null){
                       event.consume();
                       calc.enterResult();
                   }
                   //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }
           });
        textField().setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                if(event.getButton().equals(MouseButton.SECONDARY)){
                    
                    MaterialDropdownMenu menu=new MaterialDropdownMenu(event.getScreenX(),event.getScreenY());
                    menu.addItem(new MaterialDropdownMenuItem("Abrir Calculadora"){
                        @Override
                        public void onItemClick(){
                            

                
                    if(lastCalculator==null){
                        
                        MaterialDropdownMenu calendarMenu=new MaterialDropdownMenu(textField());
                        calendarMenu.setOffset(textField().getWidth(), textField().getHeight()+10);
                        calendarMenu.setOnHidden(new EventHandler<WindowEvent>(){

                            @Override
                            public void handle(WindowEvent event) {
                                lastCalculator=null;
                                calc=null;
                                textField().selectAll();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                        
                        calc=new MaterialCalculator(){
                            @Override
                            public void onResultChanged(String result){
                                setText(result);
                            }
                        
                        
                        };
                        calendarMenu.addNodeAsItem(calc);
                        calendarMenu.getMaterialContainer().setCardPadding(new Insets(0));
                        lastCalculator=calendarMenu;
                        calendarMenu.unhide();
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                            
                            
                        }});
                            
                        
                    
                    
                    menu.unhide();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        textField().setAlignment(Pos.CENTER);
        textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setValue(getValueFromText(newValue));
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        setAlignment(Pos.CENTER);
        lockLetters();
        allowDot();
        allowMoney();
        allowPercentage();
        textField().focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue ov, Boolean t, Boolean t1) {

          
                    if(f!=null){
                        if(t1){
                            textField().setText(generico.format(getValue()));
                        }
                        else{
                            
                                textField().setText(f.format(getValue()));
                            
                        }
                    }
                
        }
    });
    }
    private NumberFormat f=null;
    private static final NumberFormat generico=new DecimalFormat("########.###");
    public void setFocusFormat(NumberFormat f){
        this.f=f;
    }
    public MoneyTextField(String texto, String label){
        this(texto,label,false);
    }
    public MoneyTextField(String texto, String label, boolean round){
        super(texto,label);
        
        this.round=round;
        setValue(getValueFromText(texto));
        initAll();
        
    }
    private double getValueFromText(String text){
        if(round){
        return roundToHalf(currencyToDouble(text));
        }
        else{
            return currencyToDouble(text);
        }
    }
    public static double currencyToDouble(String cantidadAux){
        if(!cantidadAux.equals("")){
            cantidadAux=cantidadAux.replace(" ","");
            cantidadAux=cantidadAux.replace("%","");
            cantidadAux=cantidadAux.replace("kg","");
                cantidadAux=cantidadAux.replace("$","");
                cantidadAux=cantidadAux.replace(",","");
try{
                double decimal=Double.parseDouble(cantidadAux);
                return decimal;
}
catch(NumberFormatException ex){
    return 0;
}
        }
        else return 0;
    }
    
}
