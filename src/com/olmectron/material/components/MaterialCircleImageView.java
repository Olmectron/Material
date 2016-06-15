/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 *
 * @author olmec
 */
public class MaterialCircleImageView extends ImageView implements MaterialComponent  {
    
    public MaterialCircleImageView(Image image, double diameter, boolean isPlaceholder){
        super(image);
         
        setOnMouseClicked((MouseEvent event) -> {
         

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        });
        if(isPlaceholder){
            placeholder=image;
        } 
        setFitWidth(diameter);
         setPreserveRatio(true);
         Circle circle = new Circle();
        circle.setCenterX(getFitWidth()/2);
        circle.setCenterY(getFitWidth()/2);
        circle.setRadius(getFitWidth()/2);
        setClip(circle);
        
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
    public MaterialCircleImageView(Image image){
        this(image,150,false);
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
