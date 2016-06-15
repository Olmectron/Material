/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.constants.MaterialColor;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 *
 * @author Edgar
 */
public class MaterialSquareImageView extends ImageView implements MaterialComponent  {
    
    public MaterialSquareImageView(Image image, boolean isPlaceholder, Pane widthContainer){
        super(image);
        
            
        
        setOnMouseClicked((MouseEvent event) -> {
         

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        });
        if(image!=null){
        if(isPlaceholder){
            placeholder=image;
        } 
        }
        else{
            
        }
        if(widthContainer!=null){
            widthContainer.widthProperty().addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    setFitWidth(newValue.doubleValue());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        setFitWidth(widthContainer.getPrefWidth());
        //setFitWidth(diameter);
         
        setPreserveRatio(true);
        
        
         //Circle circle = new Circle();
        //circle.setCenterX(getFitWidth()/2);
        //circle.setCenterY(getFitWidth()/2);
        //circle.setRadius(getFitWidth()/2);
        //setClip(circle);
        
    }
    public void toSquareImage(){
        this.setClip(null);
    }
    private Image placeholder=null;
    public void setPlaceHolderImage(Image image){
        placeholder=image;
        if(getImage()==null){
            setImage(placeholder);
        }
    }
    public void setMySQLImage(String imagePath){
         
        if(!imagePath.trim().equals("null")){
            
            setImage(new Image(new File(imagePath).toURI().toString()));
        }
        else{
            
            setImage(placeholder);
        }
    }
    public void setDiameter(double diameter){
        setFitWidth(diameter);
    }
    public MaterialSquareImageView(Image image){
        this(image,false,null);
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

