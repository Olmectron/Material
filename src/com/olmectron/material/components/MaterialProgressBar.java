/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.constants.MaterialColor;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Édgar
 */
public class MaterialProgressBar extends ProgressBar implements MaterialComponent {
    public MaterialProgressBar(){
        super();
        getStyleClass().remove("material-progress-bar");
        getStyleClass().add("material-progress-bar");
    }
    public MaterialProgressBar(double d){
        super(d);
        getStyleClass().remove("material-progress-bar");
        getStyleClass().add("material-progress-bar");
    }
    private MaterialColor colorActual=null;
    @Override
    public void setColor(MaterialColor color) {
        if(colorActual!=null){
            getStyleClass().remove(colorActual.toString());
        }
        getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeColor() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        this.setVisible(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unhide() {
        this.setVisible(true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
