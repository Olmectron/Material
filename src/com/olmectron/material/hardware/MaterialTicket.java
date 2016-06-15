/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.hardware;

import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

/**
 *
 * @author Édgar
 */
public class MaterialTicket extends VBox {
        public MaterialTicket(){
            super();
            this.setMinWidth(300);
            this.setMaxWidth(300);
            this.setMinHeight(700);
            this.setStyle(this.getStyle()+" -fx-background-color: blue;");
            initAll();
        }
        private void initAll(){
        
        }
}
