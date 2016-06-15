/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material;

import com.olmectron.material.constants.MaterialColor;
import javafx.scene.Node;
import javafx.scene.control.Skin;

/**
 *
 * @author Édgar
 */
public interface MaterialComponent {
    public abstract void setColor(MaterialColor color);
    public abstract void removeColor();
    public abstract void hide();
    public abstract void unhide();
    
}
