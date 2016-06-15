/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.layouts;

/**
 *
 * @author olmec
 */
public abstract class MaterialFullPane extends MaterialPane {
    public  MaterialFullPane(){
        super();
        setSize();
    }
    public MaterialFullPane(int shadow){
        super(shadow);
        setSize();
    }
    private void setSize(){
        getContentCard().setCardHeight(2000);
        getContentCard().setCardWidth(2000);
        getContentCard().getCoreStackCard().getChildren().get(0).getStyleClass().removeAll("material-card","material-plain-card");
        getContentCard().getCoreStackCard().getChildren().get(0).getStyleClass().add("material-plain-card");
    }
}
