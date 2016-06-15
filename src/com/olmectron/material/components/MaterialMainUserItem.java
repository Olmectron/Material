/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.constants.MaterialColor;
import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 *
 * @author olmec
 */
public class MaterialMainUserItem extends HBox{
    
    public void setUserImage(String userPath){
        
            this.userImage.setMySQLImage(userPath);
        
    }
    public void setUserName(String userName){
        nombreUsuario.setText(userName);
    }
    public void setUserType(String userType){
        tipoUsuario.setText(userType);
    }
    private MaterialCircleImageView userImage;
    private MaterialDisplayText nombreUsuario, tipoUsuario;
    public MaterialMainUserItem(){
        super();
        userImage=new MaterialCircleImageView(MaterialDesign.images.avatar,62,true);
        getChildren().add(userImage);
        VBox userImageContainer=new VBox();
        
        //userImageContainer.getStyleClass().remove("usuario-image");
        //userImageContainer.getStyleClass().add("usuario-image");
        nombreUsuario=new MaterialDisplayText("Ejemplo",MaterialDisplayText.USER_SIZE);
        nombreUsuario.setColor(MaterialColor.BLACK_87);
        nombreUsuario.setPadding(new Insets(0,0,0,0));
        
        
        tipoUsuario=new MaterialDisplayText("Administrador",MaterialDisplayText.USER_SIZE_REGULAR);
        tipoUsuario.setColor(MaterialColor.BLACK_54);
        tipoUsuario.setPadding(new Insets(0,0,0,0));
        
        
        
        userImageContainer.getChildren().addAll(nombreUsuario,tipoUsuario);
        this.setPadding(new Insets(8,16,8,16));
        
        userImageContainer.setPadding(new Insets(0,0,0,8));
        userImageContainer.setAlignment(Pos.CENTER_LEFT);
        setAlignment(Pos.CENTER_LEFT);
//userImageContainer.setPadding(new Insets(0,0));
        getChildren().add(userImageContainer);
    }
}
