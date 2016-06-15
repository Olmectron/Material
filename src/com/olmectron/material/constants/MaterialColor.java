/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.constants;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.files.InnerTextFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 *
 * @author Édgar
 */
public class MaterialColor {
    public static final MaterialColor AMBER=new MaterialColor("amber");
    public static final MaterialColor BLUE=new MaterialColor("blue");
    public static final MaterialColor BLACK=new MaterialColor("black");
    public static final MaterialColor RED=new MaterialColor("red");
    public static final MaterialColor ORANGE=new MaterialColor("orange");
    public static final MaterialColor WHITE=new MaterialColor("white");
    public static final MaterialColor BLACK_87=new MaterialColor("black87");
    public static final MaterialColor BLACK_38=new MaterialColor("black38");
    public static final MaterialColor TRANSPARENT=new MaterialColor("transparent");
    public static final MaterialColor BLACK_54=new MaterialColor("black54");
    public static final MaterialColor WHITE_70=new MaterialColor("white70");
    public static final MaterialColor WHITE_100=new MaterialColor("white100");
    
    /*static{
        InnerTextFile colorFile=new InnerTextFile(MaterialDesign.resourcesPath+"/colors/material_colors.txt");
        ArrayList<String> colorLines=colorFile.getLines();
        for(int i=0;i<colorLines.size();i++){
            StringTokenizer colorTokenizer=new StringTokenizer(colorLines.get(i));
            String name=colorTokenizer.nextToken().trim().toLowerCase();
            MaterialColor color=(MaterialColor)material.colorMap().get(name);
            if(color!=null){
                while(colorTokenizer.hasMoreTokens()){
                    StringTokenizer typeTokenizer=new StringTokenizer(colorTokenizer.nextToken().trim(),".");
                    color.addColorType(typeTokenizer.nextToken().trim(), typeTokenizer.nextToken().trim());
                }
            }
        }
    }*/
    /*public static double getPerceivedBrightness(String webColor){
        Color perceivedColor=Color.web(webColor);
        return perceivedColor.getBrightness();
        
    }*/
    public static class material{
        public final static MaterialColor BLUE=new MaterialColor("blue",true);
        public final static MaterialColor LIGHT_BLUE=new MaterialColor("light_blue",true);
        public final static MaterialColor INDIGO=new MaterialColor("indigo",true);
        public final static MaterialColor TEAL=new MaterialColor("teal",true);
        public final static MaterialColor RED=new MaterialColor("red",true);
        public final static MaterialColor PINK=new MaterialColor("pink",true);
        public final static MaterialColor PURPLE=new MaterialColor("purple",true);
        public final static MaterialColor DEEP_PURPLE=new MaterialColor("deep_purple",true);
        public final static MaterialColor CYAN=new MaterialColor("cyan",true);
        public final static MaterialColor GREEN=new MaterialColor("green",true);
        public final static MaterialColor LIGHT_GREEN=new MaterialColor("light_green",true);
        public final static MaterialColor LIME=new MaterialColor("lime",true);
        public final static MaterialColor YELLOW=new MaterialColor("yellow",true);
        public final static MaterialColor AMBER=new MaterialColor("amber",true);
        public final static MaterialColor ORANGE=new  MaterialColor("orange",true);
        public final static MaterialColor DEEP_ORANGE=new MaterialColor("deep_orange",true);
        public final static MaterialColor BROWN=new MaterialColor("brown",true);
        public final static MaterialColor GREY=new MaterialColor("grey",true);
        public final static MaterialColor BLUE_GREY=new MaterialColor("blue_grey",true);
        public final static MaterialColor BLACK=new MaterialColor("black",true);
        public final static MaterialColor WHITE=new MaterialColor("white",true);
        public final static MaterialColor BLACK_54=new MaterialColor("black_54",true);
        public final static MaterialColor BLACK_87=new MaterialColor("black_87",true);
        
        public static final MaterialColor[] COLORS=new MaterialColor[]{
            
        RED,PINK,PURPLE,DEEP_PURPLE,INDIGO,BLUE,LIGHT_BLUE,CYAN,TEAL,GREEN,
            LIGHT_GREEN,LIME,YELLOW,AMBER,ORANGE,DEEP_ORANGE,BROWN,GREY,BLUE_GREY,WHITE,BLACK,BLACK_54, BLACK_87
        };
        private static HashMap COLOR_MAP;
        public static HashMap colorMap(){
            if(COLOR_MAP==null){
                COLOR_MAP=new HashMap();
                for (MaterialColor COLOR : COLORS) {
                    COLOR_MAP.put(COLOR.getColorName(), COLOR);
                }
            }
            
            
        
            
            
            return COLOR_MAP;
        }
        
    }
    public static MaterialColor getMaterialColorForName(String name){
        return (MaterialColor)material.colorMap().get(name);
    }
    
    private HashMap colorTypeMap;
    private String color=null;
    public MaterialColor(String color){
        this(color,false);
    }
    private String BLANCO="blanco";
    private String NEGRO="negro";
    
    public boolean isBlackForeground(String colorField){
        return foregroundTypeMap().get(colorField).toString().equals(NEGRO);
    }
    public MaterialColor(String color, boolean fullColor){
        
        this.color=color;
        if(fullColor){
            
            for(int i=0;i<MaterialDesign.colorLines.size();i++){
                
                StringTokenizer colorTokenizer=new StringTokenizer(MaterialDesign.colorLines.get(i),"&");
                String name=colorTokenizer.nextToken().trim().toLowerCase();
                
                if(name.equals(getColorName())){
                    while(colorTokenizer.hasMoreTokens()){
                        StringTokenizer typeTokenizer=new StringTokenizer(colorTokenizer.nextToken().trim(),"$");
                        String foreground=BLANCO;
                        String colorField=typeTokenizer.nextToken().trim();
                        String colorWeb=typeTokenizer.nextToken().trim();
                        try{
                            if(typeTokenizer.nextToken().equals("black_font")){
                               foreground=NEGRO;
                            }
                        }
                        catch(NoSuchElementException ex){
                            foreground=BLANCO;
                        }
                        
                        addColorType(colorField,colorWeb,foreground);
                        
                    }
                    break;
                }
                    
                
            }
        }
    }
    public String getColorName(){
        return color.toLowerCase();
    }
    private HashMap colorTypeMap(){
        if(colorTypeMap==null){
            colorTypeMap=new HashMap();
        }
        return colorTypeMap;
    }
    private HashMap foregroundTypeMap;
    private HashMap foregroundTypeMap(){
        if(foregroundTypeMap==null){
            foregroundTypeMap=new HashMap();
        }
        return foregroundTypeMap;
    }
    private void addColorType(String colorField, String webCode, String foregroundColor){
        
        colorTypeMap().put(colorField, webCode);
        foregroundTypeMap().put(colorField,foregroundColor);
        //System.out.println(colorField+", "+webCode);
    }
    public String getStandardWebCode(){
        return getWebCodeType("500");
    }
    public String getSmallestWebCode(){
        return getWebCodeType("50");
    }
    public String getWebCodeType(String type){

        return colorTypeMap().get(type).toString();
    }
    @Override
    public String toString(){
        return color;
    }
}
