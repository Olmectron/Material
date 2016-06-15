/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

/**
 *
 * @author Édgar
 */
public abstract class BackgroundTask<T> {
    private Service<T> service;
    public BackgroundTask(){
        init();
        
    }
    public BackgroundTask(int debounce){
        init();
        setDebounce(debounce);
    }
    private ProgressIndicator progressIndicator;
    public ProgressIndicator getProgressIndicator(){
        return progressIndicator;
        
    }
    private TimerTask debounceTask=null;
    
    public void play(){
        
          
              if(debounce>0){
                if(debounceTask!=null){
                debounceTask.cancel();
                    
                }
                debounceTask=new TimerTask() {

                        @Override
                        public void run() {
         Platform.runLater(new Runnable() {
            public void run() {
               realPlay();
               //System.out.println("I'm playing ");
            }
        });
    
                            
                            
                            
                        }
                    };
                new Timer().schedule(
                debounceTask, debounce);
            }   
              else{
                realPlay();
              }
        
        
        
    }
    public void onFail(){};
    public void onStart(){}
    private void realPlay(){
        if (service!=null && !service.isRunning()) {
            
            service.reset();
            
             service.start();
        }
    }
    private int debounce=0;
    public void setDebounce(int debounce){
        this.debounce=debounce;
    }
    public abstract T onAction();
    private T resulted;
    public abstract void onSucceed(T valor);
    
    private void init(){
        service = new Service<T>() {
        
        @Override
        protected Task<T> createTask() {
            return new Task<T>() {
                @Override
                protected T call() throws InterruptedException, URISyntaxException, IOException {
                    
                    // some time consuming task here
                    // use the input arguments and perform some action on it
                    // then set the process result to a Boolean and return after the task is completed
                    // also keep hold back any other process to from executing on UI 
                       resulted=onAction(); 
                       
                    return resulted;
                }
            };
        }

    };
        progressIndicator=new ProgressIndicator(0);
        progressIndicator.setProgress(0);
               

                progressIndicator.progressProperty().unbind();
        progressIndicator.progressProperty().bind(service.progressProperty());
        service.setOnReady(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        onStart();
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        service.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Failed task: "+event.getEventType().getName());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        onSucceed(resulted);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
