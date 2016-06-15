/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.layouts.MaterialPane;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Edgar
 */
public abstract class MaterialTransparentPane extends MaterialPane {

    public MaterialTransparentPane(){
        super();

        this.getCoreSmartCard().getChildren().get(0).getStyleClass().add("full-transparent-container");        
        this.getCoreSmartCard().getStyleClass().add("full-transparent-container");
    }
    
}
