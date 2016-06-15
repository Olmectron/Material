/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.helpers;

import com.olmectron.material.components.FontWeight;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.constants.MaterialColor;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Édgar
 */
public class IconButtonContainer extends StackPane{
            private VBox c;
            private StackPane p;
            public void setNumberNotification(int number){
                //this.setAlignment(Pos.BOTTOM_RIGHT);
                if(p==null){
                    p=new StackPane();
                    p.setAlignment(Pos.BOTTOM_RIGHT);
                    p.setPickOnBounds(false);
                }
                if(c==null){
                    c=new VBox();
                    c.setStyle(c.getStyle()+"-fx-background-color: red; -fx-background-radius:4px;");
                    c.setMaxHeight(13);
                    c.setAlignment(Pos.CENTER);
                    c.setOnMouseClicked(new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event) {
                            event.consume();
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
c.setMaxWidth(13);                    
//getChildren().add(c);
                }
                if(number>0){
                    if(number>=100){
                        c.setMaxWidth(25);
                    }
                    else if(number>=10){
                        c.setMaxWidth(20);
                        
                    }
                    else{
                        c.setMaxWidth(13);
                    }
                    MaterialDisplayText numberText=new MaterialDisplayText(number+"");
                    numberText.setFontSize(13);
                    numberText.setFontWeight(FontWeight.MEDIUM);
                    numberText.setColorCode(MaterialColor.material.WHITE);
                    c.getChildren().clear();
                    c.getChildren().add(numberText);
                    if(!p.getChildren().contains(c)){
                        p.getChildren().add(c);
                    }
                    if(!getChildren().contains(p)){
                        getChildren().add(p);
                    }
                    
                }
                else{
                    c.setMaxWidth(13);
                    //MaterialDisplayText numberText=new MaterialDisplayText(number+"");
                    //numberText.setFontSize(7);
                    //c.getChildren().add(numberText);
                    
                    p.getChildren().remove(c);
                    c.getChildren().clear();
                }
                
            }
            public IconButtonContainer(MaterialIconButton iconB){
                super(iconB);
                this.getStyleClass().add("button-container");
            }
            public IconButtonContainer(String materialButton){
                super(new MaterialIconButton(materialButton));
                this.getStyleClass().add("button-container");
            }
            public IconButtonContainer(String materialButton, MaterialColor color){
                super(new MaterialIconButton(materialButton));
                MaterialIconButton b=(MaterialIconButton)getChildren().get(0);
                b.setColor(color);
                this.getStyleClass().add("button-container");
            }
            public MaterialIconButton getIconButton(){
                return (MaterialIconButton)getChildren().get(0);
            }
            
    }