/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.components;

import com.olmectron.material.MaterialComponent;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.MaterialRippleable;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.ripple.CircleRipple;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import com.sun.javafx.scene.control.skin.LabelSkin;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 *
 * @author Édgar
 */
public class MaterialDisplayText extends Label implements MaterialComponent,  MaterialRippleable{
    public static final int MEDIUM=1;
    public static final int BIG=2;
    public static final int HOUR_SIZE=3;
    public static final int USER_SIZE=4;
    public static final int USER_SIZE_REGULAR=5;
    public static final int MENU_ITEM=6;
    public static final int MENU_DROPDOWN_ITEM=7;
    private CircleRipple ripple=new CircleRipple(){
        @Override
        public void onRippleFinished(){
            onRippleEffectFinished();
        }
    
    };
    
    public MaterialDisplayText(String texto, int tipo){
        super(texto);
        initAll(tipo);
        ripple.setActive(false);
        
    }
    public void setFillContainer(Pane pane){
        this.prefWidthProperty().bind(pane.widthProperty());
        this.prefHeightProperty().bind(pane.heightProperty());
        
    }
    public MaterialDisplayText(String texto){
        this(texto,1);
    }
    @Override
    public Skin<?> createDefaultSkin() {
       
        final LabelSkin buttonSkin = new LabelSkin(this);
        // Adding circleRipple as fist node of button nodes to be on the bottom
        this.getChildren().add(0, ripple.getShape());
        return buttonSkin;
    }
    private String actualSize;
    private void initAll(int tipo){
         setCache(true);
        setCacheShape(true);
        setCacheHint(CacheHint.SPEED);
        ripple.setNode(this);
        getStyleClass().addAll(colorActual.toString());
        switch(tipo){
            case 1:
                actualSize="medium";
                    getStyleClass().removeAll("material-text","medium");
                getStyleClass().addAll("material-text","medium");
                break;
            case 2:
                actualSize="big";
                    getStyleClass().removeAll("material-text","big");
                getStyleClass().addAll("material-text","big");
            break;
            case 3:
                actualSize="hour-size";
                getStyleClass().removeAll("material-text","hour-size","medium-font");
                getStyleClass().addAll("material-text","hour-size","medium-font");
                break;
            case 4: 
                actualSize="user-size";
                getStyleClass().removeAll("material-text","user-size","medium-font");
                getStyleClass().addAll("material-text","user-size","medium-font");
                break;
                case 5: 
                    actualSize="user-size";
                getStyleClass().removeAll("material-text","user-size");
                getStyleClass().addAll("material-text","user-size");
                break;
                case 6:
                    actualSize="menu-item-size";
                    getStyleClass().removeAll("material-text","menu-item-size","medium-font");
                getStyleClass().addAll("material-text","menu-item-size","medium-font");
                setColor(MaterialColor.BLACK_87);
                    break;
                case 7:
                    actualSize="menu-dropdown-item-size";
                     getStyleClass().removeAll("material-text","menu-dropdown-item-size","medium-font");
                getStyleClass().addAll("material-text","menu-dropdown-item-size","medium-font");
                setColor(MaterialColor.BLACK_87);
                    break;
            default:
                actualSize="medium";
                    getStyleClass().removeAll("material-text","medium");
                getStyleClass().addAll("material-text","medium");
            break;
            
        }
              
        
        
    }

     private MaterialColor colorActual=MaterialDesign.primaryColor;
    @Override
    public void setColor(MaterialColor color) {
        setStyle(getStyle().replace(colorCode, ""));
        getStyleClass().remove(colorActual.toString());
        getStyleClass().add(color.toString());
        colorActual=color;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String colorCode="";
    public void setColorCode(MaterialColor color){
        getStyleClass().remove(colorActual.toString());
        setStyle(getStyle().replace(colorCode, ""));
        colorCode="-fx-effect: innershadow( gaussian , "+color.getStandardWebCode()+" , 7 , 1 , 1 , 1 );";
        setStyle(this.getStyle()+colorCode);
    }
    private String soloStyle=null;
    public void setFontSize(int number){
        
        if(actualSize.equals("number")){
            setStyle(getStyle().replace(soloStyle, ""));
            
        }
        else{
            getStyleClass().remove(actualSize);
            
        
        }
        soloStyle="-fx-font-size: "+number+" !important;";
            setStyle(getStyle()+soloStyle);
        
        
        actualSize="number";
    }
    public void setFontWeight(FontWeight weight){
        getStyleClass().remove(weight.getWeightClass());
        getStyleClass().add(weight.getWeightClass());
        
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

    @Override
    public void onRippleEffectFinished() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public CircleRipple getRipple(){
        return ripple;
    }
    @Override
    public void setRippleActive(boolean active) {
        ripple.setActive(active);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void setRippleColor(Color color){
         ripple.setMainColor(color);
        
    }

    @Override
    public Color getRippleColor() {
        return ripple.getMainColor();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getRippleColorString(){
        String color=getRippleColor().toString();
       // System.out.println("Ripple: "+color);
        
        String webColor="#"+color.substring(2, 8);
        return webColor;
    }
}
