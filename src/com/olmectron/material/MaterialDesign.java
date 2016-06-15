/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material;

import com.olmectron.material.components.MaterialConfirmDialog;
import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.files.InnerTextFile;
import com.olmectron.material.utils.LanguageRegion;
import java.io.File;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Édgar
 */
public class MaterialDesign {
    private static Scene s;
     public static Point2D windowCoord;

               public static Point2D sceneCoord;

    public static Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
    
    public final static String resourcesPath="/com/olmectron/material/resources";
    public final static String backgroundsPath=resourcesPath+"/images/backgrounds";
    
    public final static String thumbnailsPath=backgroundsPath+"/thumbnails";
    public static String customPath="";
    public static MaterialColor primaryColor=MaterialColor.BLUE;
    private static ObjectProperty<MaterialColor> primaryColorCode=null;
    public static final int ANIMATION_TRANSLATE=10;
    public static final int ANIMATION_FADE=20;
    
    public static ObjectProperty<MaterialColor> primaryColorCodeProperty(){
        if(primaryColorCode==null){
            primaryColorCode=new SimpleObjectProperty<MaterialColor>(MaterialDesign.class,"primaryColorCode");
            primaryColorCode.set(MaterialColor.material.BLUE);
        }
        return primaryColorCode;
    }
    
    public static MaterialColor getPrimaryColorCode(){
        return primaryColorCodeProperty().get();
    }
    public static void setPrimaryColorCode(MaterialColor color){
        primaryColorCodeProperty().set(color);
        
    }
    public static MaterialColor secondaryColor=MaterialColor.AMBER;
    public static Stage primary;
    public static ArrayList<String> colorLines=new InnerTextFile(MaterialDesign.resourcesPath+"/colors/material_colors.txt").getLines();
        
    private static boolean animated=true;

    public static void setExitOnClose(boolean b) {
        try{
        if(b){
        primary.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        
        });
        
        }
        else{
        primary.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                new MaterialConfirmDialog("Salir","¿Está seguro de que desea salir del sistema?","Salir","Cancelar"){
                    @Override
                    public void onPositiveButton(){
                        System.exit(0);
                    }

                    @Override
                    public void onDialogShown() {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onDialogHidden() {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onDialogKeyReleased(KeyEvent event) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onDialogKeyPressed(KeyEvent event) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                
                }.unhide();
                
                //primary.toFront();
                primary.requestFocus();
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        }
        }
        catch(NullPointerException ex){
            System.err.println("Primary stage isn't set. Set it by using setContentStage() method");
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private static int animationType=ANIMATION_TRANSLATE;
    public static int getAnimationType() {
        return animationType;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void setAnimationType(int anim){
        animationType=anim;
    }
    
    public static class audio{
        
public static AudioClip notification = new AudioClip(MaterialDesign.class.getResource(MaterialDesign.resourcesPath+"/audio/notification_toast.mp3").toExternalForm());
public static AudioClip error = new AudioClip(MaterialDesign.class.getResource(MaterialDesign.resourcesPath+"/audio/error_toast.mp3").toExternalForm());
        
    }
    public static class images{
        public static Image avatar=new Image(resourcesPath+"/images/avatar.png");
        public static Image menu=new Image(resourcesPath+"/images/menu.png");
        public static String backgrounds_array[]=new String[]{"00.jpg",
"000.png",
"0000.png",
"00000.png",
"000000.jpg",
"00001.jpg",
"000a.jpg",
"1.jpg",
"12.png",
"13.jpg",
"14.png",
"2.png",
"4.jpg",
"6.png",
"7.png",
"8.jpg",
"9.jpg",
"fka.jpg",
"wall1.jpg",
"wall3.jpg",
"wall4.jpg",
"wall5.jpg"};
    
    }
    public static void setCustomPath(String path){
        customPath=path;
    }
    private static String languagePath;
    private static LanguageRegion languageRegion;
    public static String getLanguagePath(){
        return languagePath;
    }
   
    public static LanguageRegion getLanguageRegion(){
        return languageRegion;
    }
    public static void setLanguage(String path, LanguageRegion region){
        languagePath=path;
        languageRegion=region;
    }
    public static void setContentStage(Stage stg){
        System.setProperty("prism.text","t2k");
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("javafx.animation.fullspeed","true");
        System.setProperty("prism.vsync","false");
        
        primary=stg;
        primary.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    MaterialDropdownMenu m=MaterialDropdownMenu.getLastMenu();
                            if(m!=null){
                                m.hideMenu();
                            }
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    public static void setWindowOwner(Stage stg){
        
        primary=stg;
    }
    public static void setAnimated(boolean anim){
       animated=anim;
    }
    public static boolean isAnimated(){
        return animated;
    }
    public void setWindowCoords(Scene stage){
        windowCoord= new Point2D(stage.getWindow().getX(), stage.getWindow().getY());
        sceneCoord= new Point2D(stage.getX(), stage.getY());
    }
    private void setOutsideListeners(Scene scene){
            
                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                            //System.out.println("juerngviansgvijasndsv");
                           
                            MaterialDropdownMenu m=MaterialDropdownMenu.getLastMenu();
                            if(m!=null){
                                m.hideMenu();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
               
            
        }
    public static Scene getMaterialStandardScene(int width, int height){
        Scene scene=new Scene(new StackPane(),width,height);
        s=scene;
        s.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                            //System.out.println("juerngviansgvijasndsv");
                           
                            MaterialDropdownMenu m=MaterialDropdownMenu.getLastMenu();
                            if(m!=null){
                                m.hideMenu();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        
        //ClassLoader classLoader = getClass().getClassLoader();
        
        //System.out.println(getClass().getResource("/com/olmectron/material/resources/css/principal.css").toExternalForm());
        
        s.getStylesheets().add(MaterialDesign.class.getResource(resourcesPath+"/css/principal.css").toExternalForm());
        s.getStylesheets().add(MaterialDesign.class.getResource(resourcesPath+"/fonts/custom-font-styles.css").toExternalForm());
        
        return s;
    }
    public static Scene getMaterialScene(Stage st, Pane root, int width, int height){
        setContentStage(st);
        Scene scene=getMaterialScene(root,width,height);
        st.setScene(scene);
        return scene;
    }
    public static Scene getMaterialScene(Pane root, int width, int height){
        Scene scene=new Scene(root,width,height);
        s=scene;
        s.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                            //System.out.println("juerngviansgvijasndsv");
                           
                            MaterialDropdownMenu m=MaterialDropdownMenu.getLastMenu();
                            if(m!=null){
                                m.hideMenu();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        
        //ClassLoader classLoader = getClass().getClassLoader();
        
        //System.out.println(getClass().getResource("/com/olmectron/material/resources/css/principal.css").toExternalForm());
        
        s.getStylesheets().add(MaterialDesign.class.getResource(resourcesPath+"/css/principal.css").toExternalForm());
        s.getStylesheets().add(MaterialDesign.class.getResource(resourcesPath+"/fonts/custom-font-styles.css").toExternalForm());
        return s;
    }
    public void setScene(Scene stage){
        
        s=stage;
        setOutsideListeners(s);
        //ClassLoader classLoader = getClass().getClassLoader();
        
        //System.out.println(getClass().getResource("/com/olmectron/material/resources/css/principal.css").toExternalForm());
        
        s.getStylesheets().add(getClass().getResource(resourcesPath+"/css/principal.css").toExternalForm());
        s.getStylesheets().add(getClass().getResource(resourcesPath+"/fonts/custom-font-styles.css").toExternalForm());
        //s.getStylesheets().add("file:///C:/fonts/custom-font-styles.css");
        //s.getStylesheets().add(new File("./sources/fonts/custom-font-styles.css").toURI().toString());
    }
    public static Scene getScene(){
        return s;
    }
    
}
