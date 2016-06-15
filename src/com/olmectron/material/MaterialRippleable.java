/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material;

import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

/**
 *
 * @author Édgar
 */
public interface MaterialRippleable {
    public abstract Skin<?> createDefaultSkin();
    public abstract void onRippleEffectFinished();
    public abstract void setRippleActive(boolean active);
    
    public abstract Color getRippleColor();
    public abstract void setRippleColor(Color color);
    
}
