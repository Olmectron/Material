/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

import com.olmectron.material.MaterialDesign;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 *
 * @author olmec
 */
public class LayoutMath {
    public static double getAbsoluteX(Node node){
        return getAbsoluteParentsX(node);
    }
    public static double getAbsoluteY(Node node){
        return getAbsoluteParentsY(node);
    }
    
    public static double getAbsoluteParentsX(Node node){
        double absoluteX=node.getLayoutX();
        Parent pariente=node.getParent();
        while(pariente!=null){
            absoluteX=pariente.getLayoutX()+absoluteX;
            pariente=pariente.getParent();
        }
      
        
        //System.out.println(absoluteX+" layout X");
        if(MaterialDesign.windowCoord!=null && MaterialDesign.sceneCoord!=null){
            return absoluteX+MaterialDesign.windowCoord.getX()+MaterialDesign.sceneCoord.getX();
        }
        else{
            return absoluteX;
        }
    }
   
    public static double getAbsoluteParentsY(Node node){
        double absoluteY=node.getLayoutY();
        Parent pariente=node.getParent();
        while(pariente!=null){
            
            absoluteY=pariente.getLayoutY()+absoluteY;
            pariente=pariente.getParent();
        }
        if(MaterialDesign.windowCoord!=null && MaterialDesign.sceneCoord!=null){
        
        //System.out.println(absoluteY+" layout Y");
        return absoluteY+MaterialDesign.windowCoord.getY()+MaterialDesign.sceneCoord.getY();
        }
        else{
            return absoluteY;
        }
            
    }
}
