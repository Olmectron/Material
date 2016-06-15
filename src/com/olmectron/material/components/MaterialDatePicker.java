/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.helpers.IconButtonContainer;
import com.olmectron.material.layouts.MaterialPane;
import java.util.ArrayList;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import javax.management.timer.Timer;
import org.utilities.Dates;
import org.utilities.Fecha;
import org.utilities.Focus;
import org.utilities.Mes;

/**
 *
 * @author olmec
 */
public abstract class MaterialDatePicker extends MaterialStandardDialog {
    private int firstYear=2015;
    public MaterialDatePicker(){
        this(Dates.getToday());
    }
    private static MaterialDropdownMenu lastCalendar;
    private static Fecha lastSelectedDate=Dates.getToday();
    public static Fecha getLastSelectedDate(){
        return lastSelectedDate;
    }
    public static MaterialDropdownMenu getFloatingCalendar(Region node,EventHandler<ActionEvent> action){
       
                    if(lastCalendar==null){
                        MaterialDropdownMenu calendarMenu=new MaterialDropdownMenu(node);
                        
                        MaterialDatePicker datePicker=new MaterialDatePicker() {

                            @Override
                            public void onSelectedDate(Fecha fechaSeleccionada) {
                                lastSelectedDate=fechaSeleccionada;
                                calendarMenu.hideMenu();
                                action.handle(null);
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onDialogKeyReleased(KeyEvent event) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                            @Override
                            public void onNegativeButton(){
                                calendarMenu.hideMenu();
                            }
                        };
                        calendarMenu.setOnHidden(new EventHandler<WindowEvent>(){

                            @Override
                            public void handle(WindowEvent event) {
                                lastCalendar=null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                        
                        //datePicker.hideButtons(true);
                        calendarMenu.setOffset(-48, 29);

                        calendarMenu.addNodeAsItem(((MaterialPane)datePicker.getMainPane()).getCoreSmartCard());
                        calendarMenu.getMaterialContainer().setCardPadding(new Insets(0));
                        lastCalendar=calendarMenu;
                    }
                    return lastCalendar;
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
    }
    public Pane getDatePickerPane(){
        return ((MaterialPane)getMainPane()).getCoreSmartCard();
    }
    public static MaterialDropdownMenu getFloatingDatePicker(Node node){
       
                    if(lastCalendar==null){
                        MaterialDropdownMenu calendarMenu=new MaterialDropdownMenu(null);
                        
                        MaterialDatePicker datePicker=new MaterialDatePicker() {

                            @Override
                            public void onSelectedDate(Fecha fechaSeleccionada) {
                                calendarMenu.hideMenu();
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onDialogKeyReleased(KeyEvent event) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                            @Override
                            public void onNegativeButton(){
                                calendarMenu.hideMenu();
                            }
                        };
                        calendarMenu.setOnHidden(new EventHandler<WindowEvent>(){

                            @Override
                            public void handle(WindowEvent event) {
                                lastCalendar=null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                        
                        datePicker.hideButtons(true);
                        calendarMenu.setOffset(-48, 29);

                        calendarMenu.addNodeAsItem(((MaterialPane)datePicker.getMainPane()).getCoreSmartCard());
                        calendarMenu.getMaterialContainer().setCardPadding(new Insets(0));
                        lastCalendar=calendarMenu;
                    }
                    return lastCalendar;
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
    }
    public void onPositiveButton(){
        
    }
    public void onNegativeButton(){
        
    }
    public abstract void onSelectedDate(Fecha fechaSeleccionada);
    private Fecha actual;
    public MaterialDatePicker(Fecha fecha){
        super();
        this.actual=fecha;
        
        initAll();
        
        initBindings();
        createCalendar();
        updateDate(actual);
        
        populateCalendar();
        initButtons();
    }
    public void hideButtons(boolean hide){
        positive.setVisible(!hide);
        negative.setVisible(!hide);
    }
    private MaterialButton positive;
    private MaterialButton negative;
    private void initButtons(){
        HBox buttonBox=new HBox();
        buttonBox.setPadding(new Insets(8,8,8,8));
        positive=null;
        if("Aceptar"!=null){
            positive=new MaterialButton("Aceptar");
            positive.getStyleClass().add("dialog");
            positive.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    onPositive();
                    onPositiveButton();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
        }
        negative=null;
        if("Cancelar"!=null){
            negative=new MaterialButton("Cancelar");
            negative.getStyleClass().add("dialog");
            negative.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    dismiss();
                    onNegative();
                    onNegativeButton();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        
        HBox positiveBox=new HBox();
        if(positive!=null){
            positiveBox.getChildren().add(positive);
            positiveBox.setPadding(new Insets(0,0,0,8));
        
        }
        HBox negativeBox=new HBox();
        if(negative!=null){
            negativeBox.getChildren().add(negative);
        }
        negative.setColor(MaterialColor.BLACK_87);
        FlatButton hoyButton=new FlatButton("Hoy");
        HBox fillBox=new HBox();
        HBox.setHgrow(fillBox, Priority.ALWAYS);
        
        buttonBox.getChildren().addAll(hoyButton, fillBox,negativeBox,positiveBox);
        hoyButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean changeDay=false;
                if(getMonth()==Dates.getToday().getMonth() && getYear()==Dates.getToday().getYear() && getDay()!=Dates.getToday().getDay()){
                 changeDay=true;   
                }
                
                updateDate(Dates.getToday());
                if(changeDay){
                    for(MaterialIconButton iButton: calendarDayButtons){
                        if(iButton.getText().equals(getDay()+"")){
                            clearAllDays();
                            selectDay(iButton);
                            break;
                        }
                    }
                }else{
                    
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        addNode(buttonBox);
    }
    
    private void onNegative(){
        
    }
    private void onPositive(){
       dismiss();
       onSelectedDate(getSelectedDate());
       
    }
    private void initBindings(){
        dayProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int valor=newValue.intValue();
                dayField.setText(valor+"");
                weekDayField.setText(Dates.getWeekDayName(getSelectedDate()));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        monthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int valor=newValue.intValue();
                monthField.setText(getSelectedDate().getMonthSpanishName());
                monthSelectorText.setText(getSelectedDate().getMonthSpanishName());
                weekDayField.setText(Dates.getWeekDayName(getSelectedDate()));
                
                if(getYear()>0){    
                    populateCalendar();
                }
//weekDayField.setText(Dates.getWeekDayName(getSelectedDate()));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        yearProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int valor=newValue.intValue();
                yearField.setText(valor+"");
                yearSelectorText.setText(valor+"");
                weekDayField.setText(Dates.getWeekDayName(getSelectedDate()));
                
                //weekDayField.setText(Dates.getWeekDayName(getSelectedDate()));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    public Fecha getSelectedDate(){
        return new Fecha(getDay(),getMonth(),getYear());
    }
    private void updateDate(Fecha fecha){
        setDay(fecha.getDay());
        setYear(fecha.getYear());
        int actualMonth=getMonth();
        setMonth(fecha.getMonth());
        int afterMonth=getMonth();
        if(actualMonth==afterMonth && getYear()!=0){
            populateCalendar();
        }
        
    }
    
    private IntegerProperty day;
     private final void setDay(int value) { day.setValue(value); }
     public int getDay() { return dayProperty().get(); }
     public IntegerProperty dayProperty() { 
         if (day == null) day = new SimpleIntegerProperty(this, "day");
         return day; 
     }
     
    private IntegerProperty month;
     private final void setMonth(int value) { month.setValue(value); }
     public int getMonth() { return monthProperty().get(); }
     public IntegerProperty monthProperty() { 
         if (month == null) month = new SimpleIntegerProperty(this, "month");
         return month; 
     }
     
     private IntegerProperty year;
     private final void setYear(int value) { year.setValue(value); }
     public int getYear() { return yearProperty().get(); }
     public IntegerProperty yearProperty() { 
         if (year == null) year = new SimpleIntegerProperty(this, "year");
         return year; 
     }
     
    private void initAll(){
        setDialogWidth(200);
        initDateHeader();
        initCalendar();
        
    }
    private MaterialDisplayText weekDayField, dayField, monthField, yearField;
    private void initDateHeader(){
        VBox dateHeader=new VBox();
        dateHeader.setAlignment(Pos.CENTER);
         weekDayField=new MaterialDisplayText("");
        weekDayField.setColor(MaterialColor.WHITE);
         dayField=new MaterialDisplayText("",MaterialDisplayText.BIG);
        dayField.setColor(MaterialColor.WHITE);
         monthField=new MaterialDisplayText("");
        monthField.setColor(MaterialColor.WHITE);
         yearField=new MaterialDisplayText("");
        yearField.setColor(new MaterialColor("transparent-white54-text"));
        /*MaterialDisplayText elseField=
                new MaterialDisplayText(Dates.getWeekDayName(actual)+", "+
                        new Mes(actual.getMonth()).toString()+" "+
                        actual.getDay(),MaterialDisplayText.BIG);*/
        VBox weekDayHeader=new VBox(weekDayField);
        weekDayHeader.setAlignment(Pos.CENTER);
        weekDayHeader.setPadding(new Insets(2,0,2,0));
        weekDayHeader.getStyleClass().add("transparent-black25-container");
        weekDayField.setFontSize(13);
        weekDayField.setFontWeight(FontWeight.MEDIUM);
        VBox elseHeader=new VBox(monthField,dayField,yearField);
        elseHeader.setAlignment(Pos.CENTER);
        elseHeader.setPadding(new Insets(8,0,8,0));
        monthField.setFontSize(18);
        monthField.setFontWeight(FontWeight.MEDIUM);
        dayField.setFontSize(56);
        yearField.setFontSize(17);
        yearField.setFontWeight(FontWeight.MEDIUM);
        
        
        
        dateHeader.getChildren().addAll(weekDayHeader,elseHeader);
        //dateHeader.setPadding(new Insets(0,16,0,16));
        dateHeader.getStyleClass().add("material-calendar-header");
        addNode(dateHeader);
        
    }
    private String[] dias=new String[]{"D","L","M","X","J","V","S"};
    private MaterialDisplayText monthSelectorText, yearSelectorText;
    private VBox calendarBox;
    private void initCalendar(){
        calendarBox=new VBox();
        
        calendarBox.setPadding(new Insets(0,16,16,16));
        monthSelectorText=
                new MaterialDisplayText("");
        monthSelectorText.setFontSize(15);
        monthSelectorText.setFontWeight(FontWeight.MEDIUM);
        monthSelectorText.setColor(MaterialColor.BLACK_87);
        monthSelectorText.setPadding(new Insets(0,7,0,0));
        
        yearSelectorText=
                new MaterialDisplayText("");
        yearSelectorText.setFontSize(15);
        yearSelectorText.setFontWeight(FontWeight.MEDIUM);
        yearSelectorText.setColor(MaterialColor.BLACK_87);
        //yearSelectorText.setPadding(new Insets(12,0,12,0));
        calendarBox.setAlignment(Pos.CENTER);
        MaterialIconButton leftButton=new MaterialIconButton(MaterialIconButton.MOVE_LEFT_ICON);
        MaterialIconButton rightButton=new MaterialIconButton(MaterialIconButton.MOVE_RIGHT_ICON);
        leftButton.setColor(MaterialColor.BLACK_54);
        rightButton.setColor(MaterialColor.BLACK_54);
        
        HBox rightBox=new HBox();
        HBox.setHgrow(rightBox, Priority.ALWAYS);
        HBox leftBox=new HBox();
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        leftButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(getMonth()-1<=0){
                    setYear(getYear()-1);
                    setMonth(12);
                }
                else{
                    setMonth(getMonth()-1);
                
                }
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        rightButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(getMonth()+1>=13){
                    setYear(getYear()+1);
                    setMonth(1);
                
                }
                else{
                    setMonth(getMonth()+1);
                
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        HBox selectorBox=new HBox(leftButton,leftBox,monthSelectorText, yearSelectorText,rightBox, rightButton);
        selectorBox.setAlignment(Pos.CENTER);
        
        selectorBox.setPadding(new Insets(14,0,12,0));
        calendarBox.getChildren().add(selectorBox);
        
        addNode(calendarBox);
        
        //populateCalendar();
        
    }
    private void selectDay(MaterialIconButton b){
        b.getStyleClass().addAll("bold-font","blue_background");
        if(day!=null){
            setDay(Integer.parseInt(b.getText()));
        }
        
    }
    private void unselectDay(MaterialIconButton b){
        b.getStyleClass().removeAll("bold-font","blue_background");
    }
    private void clearAllDays(){
        for (MaterialIconButton calendarDayButton : calendarDayButtons) {
            unselectDay(calendarDayButton);
        }
        
    }
    private ArrayList<MaterialIconButton> calendarDayButtons;
    @Override
    public void onPressedKey(KeyEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onReleasedKey(KeyEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCreate(Pane pane) {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void populateCalendar(){
        this.clearAllDays();
        
        int counter=0;
        int fila=0;
        int maxConteo=new Mes(getSelectedDate().getMonth()).getNumberOfDays();
                   
        boolean contar=false;
        Fecha firstDay=new Fecha(1,getSelectedDate().getMonth(),getSelectedDate().getYear());
        int conteo=1;
        for(int i=0;i<calendarDayButtons.size();i++){
            if(counter>=7){
                counter=0;
                fila++;
            }
            
            MaterialIconButton dayButton=calendarDayButtons.get(i);
            
            if(fila==0){
                           if(Dates.getWeekDay(firstDay)==(counter+1)){
                               contar=true;
                           }
                       }
                       
                       if(contar){
                        if(conteo<=maxConteo){
                            
                            dayButton.setText(conteo+"");
                            dayButton.getStyleClass().remove("medium-font");
                            if(conteo==getSelectedDate().getDay()){
                                
                                selectDay(dayButton);
                                
                                
                            }
                            else{
                                
                                if(getSelectedDate().getDay()>maxConteo && conteo==maxConteo){
                                    selectDay(dayButton);
                                }
                            }
                            //if(conteo==getSelectedDate().getDay()){
                            if(conteo==Dates.getToday().getDay() && getMonth()==Dates.getToday().getMonth()
                                    && Dates.getToday().getYear()==getYear()){
                                dayButton.setColor(MaterialColor.BLUE);
                                dayButton.getStyleClass().add("medium-font");
                            }
                            else{
                                
                                dayButton.setColor(MaterialColor.BLACK);
                            }
                            conteo++;
                        }
                        else{
                            dayButton.setText("");
                        }
                         
                       }
                       else{
                           dayButton.setText("");
                       }
            
            
            
            
            
            
            
            counter++;
         dayButton.setRippleColor(Color.web("#2196F3"));   
        }
    }
    private void createCalendar(){
        /*try{
            calendarBox.getChildren().remove(1, calendarBox.getChildren().size());
        
        }
        catch(Exception es){
            es.printStackTrace();
            //System.out.println("No había elementos por remover");
        }*/
        
        //Fecha firstDay=new Fecha(1,getSelectedDate().getMonth(),getSelectedDate().getYear());
        
        ArrayList<HBox> daysRows=new ArrayList<HBox>();
        calendarDayButtons=new ArrayList<MaterialIconButton>();
        //boolean contar=false;
        //           int conteo=1;
        for(int i=0;i<7;i++){
           switch(i){
               case 0:
                   HBox letraBox=new HBox();
                   for(int k=0;k<7;k++){
                       MaterialIconButton letraButton=new MaterialIconButton();
                       letraButton.setText(dias[k]);
                       letraButton.setColor(MaterialColor.BLACK_54);
                       letraButton.setRippleActive(false);
                       //letraButton.setRippleColor(Color.BLACK);
                       letraBox.getChildren().add(letraButton);
                   }
                   
                   
                   
                   daysRows.add(letraBox);
                   break;
               default:
                   HBox row=new HBox();
                   
                   //int maxConteo=new Mes(getSelectedDate().getMonth()).getNumberOfDays();
                   for(int e=0;e<7;e++){
                       MaterialIconButton dayButton=new MaterialIconButton();
                       calendarDayButtons.add(dayButton);
                       
                       //populateCalendar();
                       /*if(i==1){
                           if(Dates.getWeekDay(firstDay)==(e+1)){
                               contar=true;
                           }
                       }
                       
                       if(contar){
                        if(conteo<=maxConteo){
                            
                            dayButton.setText(conteo+"");
                            
                            if(conteo==getSelectedDate().getDay()){
                                
                                selectDay(dayButton);
                                
                                
                            }
                            else{
                                if(getSelectedDate().getDay()>maxConteo && conteo==maxConteo){
                                    selectDay(dayButton);
                                }
                            }
                            //if(conteo==getSelectedDate().getDay()){
                            if(conteo==Dates.getToday().getDay() && getMonth()==Dates.getToday().getMonth()
                                    && Dates.getToday().getYear()==getYear()){
                                dayButton.setColor(MaterialColor.BLUE);
                                dayButton.getStyleClass().add("medium-font");
                            }
                            else{
                                dayButton.setColor(MaterialColor.BLACK);
                            }
                            conteo++;
                        }
                         
                       }*/
                       
                       dayButton.setRippleColor(Color.web("#2196F3"));
                       dayButton.setOnAction(new EventHandler<ActionEvent>() {

                           @Override
                           public void handle(ActionEvent event) {
                              
                               if(!dayButton.getText().equals("")){
                                   clearAllDays();
                                   TimerTask debounceTask=new TimerTask() {

                        @Override
                        public void run() {
         Platform.runLater(new Runnable() {
            public void run() {
               
                              selectDay(dayButton);
               
               //System.out.println("I'm playing ");
            }
        });
                        }};
                            
                            
                            
                        
                new java.util.Timer().schedule(
                debounceTask, 120);
                                    }
                               
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                           }
                       });
//dayButton.setPrefWidth(32);
                       //dayButton.setPrefHeight(32);
//dayButton.setText((e+1)+"");
                       row.getChildren().add(dayButton);
                   }
                   daysRows.add(row);
                   break;
           }
        }
        
        for(int i=0;i<daysRows.size();i++){
            calendarBox.getChildren().add(daysRows.get(i));
        }
        
    }

    @Override
    public void onDialogShown() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDialogHidden() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDialogKeyPressed(KeyEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
