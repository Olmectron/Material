/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;
import javafx.stage.Window;
import javafx.util.Duration;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
/**
 *
 * @author Édgar
 */
public class MaterialPasswordField extends VBox implements MaterialComponent {
    private Path findCaret(Parent parent) {
    // Warning: this is an ENORMOUS HACK
    for (Node n : parent.getChildrenUnmodifiable()) {
      if (n instanceof Path) {
        return (Path) n;
      } else if (n instanceof Parent) {
        Path p = findCaret((Parent) n);
        if (p != null) {
          return p;
        }
      }
    }
    return null;
  }
    public Point2D getCaretScenePosition(){
        Path caret = findCaret(textField());
          Point2D screenLoc = findScreenLocation(caret);
          return screenLoc;
    }
  private Point2D findScreenLocation(Node node) {
    double x = 0;
    double y = 0;
    for (Node n = node; n != null; n=n.getParent()) {
      Bounds parentBounds = n.getBoundsInParent();
      x += parentBounds.getMinX();
      y += parentBounds.getMinY();
    }
    Scene scene = node.getScene();
    x += scene.getX();
    y += scene.getY();
    Window window = scene.getWindow();
    x += window.getX();
    y += window.getY();
    Point2D screenLoc = new Point2D(x, y);
    return screenLoc;
  }
    
    public MaterialPasswordField(String texto, String label){
        super();
        setLabel(label,texto);
        initAll();
    }
    public MaterialPasswordField(){
        super();
        setLabel("","");
        initAll();
    }
    
    public MaterialPasswordField(String label){
        super();
        setLabel(label,"");
        initAll();
    }
    public void setHelperText(String helper){
        MaterialDisplayText m=new MaterialDisplayText(helper);
        m.setColor(MaterialColor.BLACK_38);
        m.setFontSize(14);
        m.setWrapText(true);
        m.setPadding(new Insets(4,0,4,0));
        getChildren().add(m);
        
    }
    private MaterialLabel mLabel=null;
    private void onTimelineFinished(){
        mLabel.translateYProperty().set(0);
                
    }
    private void onTimeline2Finished(){
        mLabel.translateYProperty().set(30);
                mLabel.hide();
                textField.clear();
    }
    public void clear(){
        textField.clear();
    }
    public PasswordField textField(){
        return textField;
    }

    /**
     *
     * @param handler
     */
    
    public void setOnAction(EventHandler<ActionEvent> handler){
        textField.setOnAction(handler);
    }
    public void showError(){
        setColor(MaterialColor.RED);
                    mLabel.setColor(MaterialColor.RED);
                    mLabel.setText(errorText);
    }
    private String matchText="[\\\\!\"#$%&()*+ \\-,./:;<=>?@\\[\\]^_{|}~]+";
    public void setLimite(int limite){
        setMaxLength(limite);
    }
    //private int maxLength=30;
    private ArrayList<String> matchesArray=new ArrayList<String>();
     final ObjectProperty<String> warningColor = new SimpleObjectProperty<>("132%");
        final StringProperty colorStringProperty = new SimpleStringProperty();
        private PasswordField textField;
        private String normalText;
        public void allowSpace(){
        matchText=matchText.replace(" ", "");
    }    
    public void allowDot(){
        matchText=matchText.replace(".", "");
    }    
    public void allowMoney(){
        matchText=matchText.replace("$","");
        
    }
    public void allowPercentage(){
        matchText=matchText.replace("%","");
    }
    public void lockLetters(){
        
        matchesArray.add("[a-z]");
        matchesArray.add("[A-Z]");
        
    }
   
    public void setLabel(String label, String text){
        
        mLabel=new MaterialLabel(label);
        normalText=label;
        
        textField=new PasswordField(){
        
        @Override
    public void replaceText(int start, int end, String text) {
        String oldValue = getText();
        boolean boo=!text.matches(matchText);
            for (String match1 : matchesArray) {
                boo = boo && !text.matches(match1);
            }
        if (boo) {
            super.replaceText(start, end, text);
        }
        if (getText().length() > getMaxLength() ) {
            setText(oldValue);
        }
    }

    @Override
    public void replaceSelection(String text) {
        String oldValue = getText();
        boolean boo=!text.matches(matchText);
            for (String match1 : matchesArray) {
                boo = boo && !text.matches(match1);
            }
        if (boo) {
            super.replaceSelection(text);
        }
        if (getText().length() > getMaxLength() ) {
            setText(oldValue);
        }
    }
        
        
        
        };
        textField.setText(text);
        textField.getStyleClass().removeAll("material-input","giant",colorActual.toString());
        //textField.setMinHeight(20);
        textField.getStyleClass().addAll("material-input","giant",colorActual.toString());
        mLabel.removeColor();
        getChildren().add(mLabel);
        getChildren().add(textField);


    }
    private MaterialDisplayText limitText;
    public void showLimite(int min){
        limitText=new MaterialDisplayText("0 / "+getMaxLength());
        limitText.setColor(MaterialColor.BLACK_38);
        limitText.setFontSize(14);
        limitText.setWrapText(true);
        
        limitText.setPadding(new Insets(4,0,4,0));
        if(extraBox==null){
            initExtraBox();
        }
        limiteBox.getChildren().add(limitText);
    }
    private void initExtraBox(){
        extraBox=new HBox();
        helperBox=new HBox();
        extraBox.getChildren().add(helperBox);
        extraSizedBox=new HBox();
        extraBox.getChildren().add(extraSizedBox);
        HBox.setHgrow(extraSizedBox, Priority.ALWAYS);
        limiteBox=new HBox();
        extraBox.getChildren().add(limiteBox);
        getChildren().add(extraBox);
    }
    private HBox extraBox;
    private HBox helperBox;
    private HBox extraSizedBox;
    private HBox limiteBox;
    
    
     private IntegerProperty maxLength;
     public final void setMaxLength(int value) { maxLengthProperty().set(value); }
     public int getMaxLength() { return maxLengthProperty().get(); }
     public IntegerProperty maxLengthProperty() { 
         if (maxLength == null){ maxLength = new SimpleIntegerProperty(this, "maxLength");
         maxLength.set(30);
         maxLength.addListener(new ChangeListener<Number>(){

             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(limitText!=null){
                    limitText.setText(textField().getText().length()+" / "+getMaxLength());
                }

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
         }
         return maxLength; 
     }
     
    public void setSuperGiantSize(){
        textField.getStyleClass().remove(actualSize);
        textField.getStyleClass().add("super_giant");
        actualSize="super_giant";
    }
    public void setGiantSize(){
        textField.getStyleClass().remove(actualSize);
        textField.getStyleClass().add("giant");
        actualSize="giant";
    }
    private String actualSize="big";
    private Timeline timeline;
    private Timeline timeline2;
    @Override
    public void requestFocus(){
        super.requestFocus();
        textField.requestFocus();
    }
    private void initAll(){
        textField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(onError(newValue)){
                    setColor(MaterialColor.RED);
                    mLabel.setColor(MaterialColor.RED);
                    mLabel.setText(errorText);
                    
                }
                else{
                    setColor(colorNormal);
                    mLabel.setColor(colorNormal);
                    mLabel.setText(normalText);
                }
                
                
                 if(textField.isFocused()){
                    mLabel.getStyleClass().remove(colorActual.toString());
                    
                    mLabel.getStyleClass().add(colorActual.toString());
        
                    //System.out.println("Focuseado");
                }
                else{
                    mLabel.getStyleClass().remove(colorActual.toString());
                    
                    //System.out.println("Off");
                }
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    mLabel.getStyleClass().remove(colorActual.toString());
                    
                    mLabel.getStyleClass().add(colorActual.toString());
        
                    //System.out.println("Focuseado");
                }
                else{
                    mLabel.getStyleClass().remove(colorActual.toString());
                    
                    //System.out.println("Off");
                }
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        textField.parentProperty().addListener(new ChangeListener<Parent>() {

            @Override
            public void changed(ObservableValue<? extends Parent> observable, Parent oldValue, Parent newValue) {
                if(newValue!=null){
                    newValue.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                requestFocus();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    }
    public String getText(){
        return textField.getText();
    }
    public void setText(String text){
        textField.setText(text);
    }
    private String errorText="";
    public void setErrorText(String text){
        this.errorText=text;
    }
    public boolean onError(String valor){
        return false;
    }
    public boolean hasError(){
        return onError(getText());
    }
 private MaterialColor colorActual=MaterialDesign.primaryColor;
 private MaterialColor colorNormal=MaterialDesign.primaryColor;
    @Override
    public void setColor(MaterialColor color) {
        
        
        textField.getStyleClass().remove(colorActual.toString());
        textField.getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeColor() {
        
        textField.getStyleClass().remove(colorActual.toString());
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
}
