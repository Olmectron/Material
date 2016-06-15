/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialPane;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;
import org.utilities.Fecha;

/**
 *
 * @author olmec
 */
public class MaterialStatusBar extends HBox{
        public MaterialStatusBar(){
            
            initBar();
            
        }
        private String getNow(){
        
        int second=Calendar.getInstance().get(Calendar.SECOND);
        int minute=Calendar.getInstance().get(Calendar.MINUTE);
        int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String segundo=second+"";
        String minuto=minute+"";
        String hora=hour+"";
        if(hour>12){
         hour=hour-12;   
         hora=""+hour;
        }
        if(second<10){
            segundo="0"+second;
        }
        if(minute<10){
            minuto="0"+minute;
        }
        if(hour<10 && hour>0){
            hora=""+hour;
        }
        if(hour==0){
            hora="12";
        }
        
        return hora+":"+minuto;
    }
        private static MaterialDropdownMenu lastCalendar=null;
        private MaterialDisplayText horaText;
        private HBox extraBox;
        private void initBar(){
            getStyleClass().remove("material-status-bar");
            getStyleClass().add("material-status-bar");
            this.setAlignment(Pos.CENTER_RIGHT);
            horaText=new MaterialDisplayText("",MaterialDisplayText.HOUR_SIZE);
            horaText.setOnMouseClicked(new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent event) {
                    if(lastCalendar==null){
                        MaterialDropdownMenu calendarMenu=new MaterialDropdownMenu(horaText);
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
                        calendarMenu.unhide();
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            horaText.setColor(MaterialColor.WHITE);
            extraBox=new  HBox();
            extraBox.setPadding(new Insets(0,12,0,12));
            startHour();
            HBox miniBox=new HBox();
            miniBox.getStyleClass().add("square-box");
            miniBox.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    MaterialDesign.primary.setIconified(true);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            miniBox.setPadding(new Insets(0,8,0,0));
            miniBox.setAlignment(Pos.CENTER);
            getChildren().addAll(horaText);
           
        }
        public void addElementToLeft(Node node){
            getChildren().add(0, node);
            //extraBox.getChildren().add(node);
        }
        public void addElementToRight(Node node){
            getChildren().add(node);
            //extraBox.getChildren().add(node);
        }
        
        private void startHour(){
            TimerTask debounceTask=new TimerTask() {

                        @Override
                        public void run() {
         Platform.runLater(new Runnable() {
            public void run() {
               String ahora=getNow();
               if(!ahora.equals(horaText.getText())){
               horaText.setText(ahora);
               }
               
               //System.out.println("I'm playing ");
            }
        });
                        }};
                            
                            
                            
                        
                new Timer().schedule(
                debounceTask, 0,1000);
        }
    }
    