/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Edgar
 */
public abstract class MaterialCalculator extends VBox {
    private boolean justResult=true;
    private MaterialDisplayText showText,resultText;
    public static MaterialToast getCalculatorToast(){
        MaterialToast t=new MaterialToast(new MaterialCalculator(){
            @Override
            public void onResultChanged(String value) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       t.setLength(MaterialToast.LENGTH_UNDEFINED);
       return t;
    }
    private VBox statusBox;
    public MaterialCalculator(){
        super();
        
        
        setMinWidth(266);
        setMaxWidth(266);
        setMinHeight(430);
        setMaxHeight(430);
        
        VBox resultBox=new VBox();
        showText=new MaterialDisplayText("");
        showText.setFontSize(40);
        showText.setPadding(new Insets(12));
        
        showText.setColorCode(MaterialColor.material.BLACK_87);
        resultText=new MaterialDisplayText("");
        resultText.setFontSize(22);
        resultText.setPadding(new Insets(12));
        resultText.setColorCode(MaterialColor.material.BLACK_54);
        resultText.getStyleClass().add("light-font");
        showText.getStyleClass().add("light-font");
        resultBox.getChildren().addAll(showText,resultText);
        resultBox.setAlignment(Pos.CENTER_RIGHT);
        this.parentProperty().addListener(new ChangeListener<Parent>(){
            @Override
            public void changed(ObservableValue<? extends Parent> observable, Parent oldValue, Parent parent) {
                if(parent!=null){
                    
                    MaterialDesign.primary.getScene().setOnKeyTyped(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                //System.out.println(event.toString());
                
                setNumber(event.getCharacter());
                
                    
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        MaterialDesign.primary.getScene().setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    enterResult();
                }
                else if(event.getCode().equals(KeyCode.BACK_SPACE)){
                    //System.out.println("Borrar");
                    if(justResult){
                        clear();
                    }
                    else{
                        backspace();
                    }
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
      
        showText.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               resultText.setText(new DecimalFormat("0.##").format(getResultFromString(newValue)));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        resultText.textProperty().addListener(new  ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.equals("")){
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        resultBox.setStyle(resultBox.getStyle()+"-fx-background-color: #FFFFFF !important;");
        
 statusBox=new VBox();
        statusBox.setStyle(statusBox.getStyle()+"-fx-background-color: #00BCD4; -fx-min-height: 24px;");
        getChildren().add(statusBox);
        getChildren().add(resultBox);
        FlatButton button1=new FlatButton("1");
        FlatButton button2=new FlatButton("2");
        FlatButton button3=new FlatButton("3");
        FlatButton button4=new FlatButton("4");
        FlatButton button5=new FlatButton("5");
        FlatButton button6=new FlatButton("6");
        FlatButton button7=new FlatButton("7");
        FlatButton button8=new FlatButton("8");
        FlatButton button9=new FlatButton("9");
        FlatButton button0=new FlatButton("0");
        FlatButton buttonPoint=new FlatButton(".");
        FlatButton buttonEqual=new FlatButton("=");
        
        buttonList=new ArrayList<FlatButton>();
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);
        buttonList.add(button0);
        buttonList.add(buttonPoint);
        buttonList.add(buttonEqual);
        
        FlatButton buttonDivision=new FlatButton("÷");
        FlatButton buttonMultiply=new FlatButton("×");
        FlatButton buttonPlus=new FlatButton("+");
        FlatButton buttonMinus=new FlatButton("−");
        FlatButton buttonDelete=new FlatButton("C");
        
        operList=new ArrayList<FlatButton>();
        operList.add(buttonDivision);
        operList.add(buttonMultiply);
        operList.add(buttonPlus);
        operList.add(buttonMinus);
        operList.add(buttonDelete);
        
        
        HBox sevenNine=new HBox(button7,button8,button9);
        
        HBox fourSix=new HBox(button4,button5,button6);
        HBox oneThree=new HBox(button1,button2,button3);
        HBox pointEqual=new HBox(buttonPoint,button0,buttonEqual);
        VBox buttonPane=new VBox(sevenNine,fourSix,oneThree,pointEqual);
        VBox operPane=new VBox(buttonDelete,buttonDivision,buttonMultiply,buttonMinus,buttonPlus);
        
        HBox buttonBox=new HBox(buttonPane,operPane);
        getChildren().add(buttonBox);
        
        
       setStyles();
        
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
       
    }
    public abstract void onResultChanged(String value);
    private void clear(){
        showText.setText("");
        resultText.setText("");
    }
    public void backspace(){
        if(!showText.getText().trim().equals("")){
                        String actualText=showText.getText();
                        showText.setText(actualText.substring(0,actualText.length()-1));
                        if(showText.getText().equals("")){
                            resultText.setText("");
                        }
                    }
    }
    public void setTextField(MaterialTextField textField){
        statusBox.getChildren().add(textField
        );
        textField.setOpacity(0);
        textField.textField().setOnKeyTyped(new EventHandler<KeyEvent>(){
               @Override
               public void handle(KeyEvent event) {
                   
                       event.consume();
                       setNumber(event.getCharacter());
                   
                   //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }
           });
textField.textField().setOnKeyPressed(new EventHandler<KeyEvent>(){
               @Override
               public void handle(KeyEvent event) {
                   if(event.getCode().equals(KeyCode.BACK_SPACE)){
                       event.consume();
                       backspace();
                       
               }
                   if(event.getCode().equals(KeyCode.ENTER)){
                       event.consume();
                       enterResult();
                   }
                   //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }
           });
    }
    public void enterResult(){
        if(!justResult){
        showText.setText(resultText.getText());
        
                    onResultChanged(resultText.getText());
                    resultText.setText("");
                    justResult=true;
                    
        }
    }
    public void setNumber(String character){
        if(character.equals("-")){
            character="−";
            
        }
        if(character.equals("*")){
            character="×";
        }
        if(character.equals("/")){
            character="÷";
        }
        
        if(justResult && (character.equals("1") || character.equals("2") || character.equals("3")
                            || character.equals("4") || character.equals("5") || character.equals("6")
                            || character.equals("7") || character.equals("8") || character.equals("9"))){
                    
                    showText.setText("");
                }
                
                
                    if(character.equals("1") || character.equals("2") || character.equals("3")
                            || character.equals("4") || character.equals("5") || character.equals("6")
                            || character.equals("7") || character.equals("8") || character.equals("9") 
                            || character.equals("0") || character.equals("+") || character.equals("−")
                            || character.equals(".")
                            || character.equals("×") || character.equals("÷")){
                            
                        if((showText.getText().endsWith("+") || showText.getText().endsWith("−") || showText.getText().endsWith("÷") || showText.getText().endsWith("×")) &&
                                (character.equals("+") || character.equals("−") || character.equals("÷") || character.equals("×"))){
                            if(!showText.getText().equals("−")){
                                
                                String actualText=showText.getText().substring(0,showText.getText().length()-1);
                                showText.setText(actualText+character);
                            }    
                            
                        }
                        else{
                            if((showText.getText().equals("") || showText.getText().equals("−"))&& 
                                    (character.equals("+") || character.equals("÷") || character.equals("×"))){
                                
                            }else
                                if(showText.getText().endsWith(".") && character.equals(".")){
                        
                        }else{
                            showText.setText(showText.getText()+character);
                                }
                        }
                        
                        
                        justResult=false;
                    }
    }
    private void setStyles(){
        for(int i=0;i<operList.size();i++){
            final FlatButton fb=operList.get(i);
            fb.getStyleClass().addAll("square","small");
            fb.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    if(!fb.getText().equals("C")){
                    setNumber(fb.getText());
                    }
                    else{
                        if(justResult){
                            clear();
                        }
                        else{
                            backspace();
                        }
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        }
        for(int i=0;i<getButtons().size();i++){
            final FlatButton fb=getButtons().get(i);
            fb.getStyleClass().addAll("square","big");
            fb.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    if(!fb.getText().equals("=")){
                    setNumber(fb.getText());
                    }
                    else{
                        enterResult();
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        }
    }
    private ArrayList<FlatButton> operList;
    private ArrayList<FlatButton> getOperButtons(){
        return operList;
    }
    private ArrayList<FlatButton> buttonList;
    private ArrayList<FlatButton> getButtons(){
        return buttonList;
    }
    private boolean isMinus(String text){
        return text.equals("−");
    }
    private boolean isLowSign(String text){
        return (text.equals("+") || text.equals("−"));
    }
    private boolean isHighSign(String text){
        return (text.equals("×") || text.equals("÷"));
    }
    
    private boolean startsWithSign(String text){
        return (text.startsWith("−"));
    }
    private double getResultFromString(String text){
        ArrayList<Double> numbers=new ArrayList<Double>();
        boolean signed=false;
        int start=0;
        if(startsWithSign(text)){
            signed=true;
            start=1;
        }
            for(int i=start;i<text.length();i++){
                
                String charAt=text.charAt(i)+"";
                String number="";
                while(!isLowSign(charAt)){
                    try{
                    number=number+charAt;
                    
                    i++;
                    charAt=text.charAt(i)+"";
                    }
                    catch(StringIndexOutOfBoundsException ex){
                        break;
                    }
                }
                ArrayList<Double> inside=new ArrayList<Double>();
                String kind="";
                for(int c=0;c<number.length();c++){
                    String no=number.charAt(c)+"";
                    String n="";
                    while(!isHighSign(no)){
                        try{
                            n=n+no;

                            c++;
                            no=number.charAt(c)+"";
                            }
                            catch(StringIndexOutOfBoundsException ex){
                                break;
                            }
                        
                    
                    }
                    
                    if(kind.equals("÷")){
                        double result=inside.get(inside.size()-1).doubleValue()/Double.parseDouble(n);
                        inside.remove(inside.size()-1);
                        inside.add(result);
                    }
                    else if(kind.equals("×")){
                        
                        double result=inside.get(inside.size()-1).doubleValue()*Double.parseDouble(n);
                        inside.remove(inside.size()-1);
                        inside.add(result);
                    }
                    else if(kind.equals("")){
                        inside.add(Double.parseDouble(n));
                    
                    }
                    
                    kind=no;
                    
                    
                }
                
                
                if(signed){
                numbers.add(-inside.get(0).doubleValue());    
                }
                else{
                    
                    numbers.add(inside.get(0).doubleValue());
                }
                if(isMinus(charAt)){
                    signed=true;
                }
                else{
                    signed=false;
                }
                
            }
        
        
        double suma=0;
        for(int i=0;i<numbers.size();i++){
            suma+=numbers.get(i).doubleValue();
        }
        return suma;
    }
    /**
     * @param args the command line arguments
     */
  
    
}
