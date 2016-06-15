/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import javafx.scene.control.Label;

/**
 *
 * @author Édgar
 */
public class MaterialLabel extends Label implements MaterialComponent{
    public static final int TOOLBAR_TITLE=0;
    public static final int NORMAL_TITLE=1;
    public MaterialLabel(String texto, int tipo){
        super(texto);
        initAll(tipo);
    }
    public MaterialLabel(String texto){
        this(texto,1);
    }
    
    private void initAll(int tipo){
        getStyleClass().addAll(colorActual.toString());
        switch(tipo){
            case TOOLBAR_TITLE:
                getStyleClass().removeAll("title-label");
                getStyleClass().addAll("title-label");
                setColor(MaterialColor.WHITE);
                
                break;
            case NORMAL_TITLE:
                getStyleClass().removeAll("material-label");
                getStyleClass().addAll("material-label"/*"light-font"*/);
        
                break;
            default:
                getStyleClass().remove("material-label");
                getStyleClass().add("material-label");
                break;
        }
        
    }

     private MaterialColor colorActual=MaterialDesign.primaryColor;
    @Override
    public void setColor(MaterialColor color) {
        getStyleClass().remove(colorActual.toString());
        getStyleClass().add(color.toString());
        colorActual=color;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeColor() {
        getStyleClass().remove(colorActual.toString());
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
