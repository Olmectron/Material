/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Édgar
 */
public class RaisedButton extends MaterialButton{

    public RaisedButton(String text) {
        super(text,MaterialButton.RAISED);
    }

    public void setOnActionEvent(EventHandler<ActionEvent> eventHandler) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
