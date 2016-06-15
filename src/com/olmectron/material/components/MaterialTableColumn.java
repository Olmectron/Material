/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialRippleable;
import com.olmectron.material.constants.MaterialColor;
import javafx.scene.control.Skin;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Édgar
 */
public class MaterialTableColumn extends TableColumn implements MaterialComponent {
    public MaterialTableColumn(){
        super();
        initAll();
    }
    public MaterialTableColumn(String texto){
        super(texto);
        initAll();
    }
    private void initAll(){
        getStyleClass().add("material-table-column");
    }
    @Override
    public void setColor(MaterialColor color) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeColor() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unhide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
