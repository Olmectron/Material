/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.helpers;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author olmec
 */
public class SuperPane extends Pane {
    public SuperPane(){
        super();
        
    }
    public void addNode(Node node){
        getChildren().add(node);
    }
}
