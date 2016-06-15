/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author olmec
 */
public class TextFile extends File {

    public TextFile(String pathname) {
        super(pathname);
    }
    private BufferedReader openReader() throws FileNotFoundException, IOException{
        
        BufferedReader escritor=new BufferedReader(new FileReader(this));
        return escritor;
    }
    
    private BufferedWriter openWriter(boolean append) throws FileNotFoundException, IOException{
        
        BufferedWriter escritor=new BufferedWriter(new FileWriter(this,append));
        return escritor;
    }
    private void closeRedaer(BufferedReader escritor) throws IOException{
        escritor.close();
        
        
    }
    private void closeWriter(BufferedWriter escritor) throws IOException{
        escritor.close();
        
        
    }
    public void appendText(String value){
        try {
            BufferedWriter escritor=openWriter(true);
            escritor.append(value);
            escritor.newLine();
            closeWriter(escritor);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no existe");
            try {
                if(!this.getParentFile().exists()){
                    this.getParentFile().mkdir();
                
                }
                this.createNewFile();
                
                setText(value);
                
            } catch (IOException ex1) {
                ex1.printStackTrace();
                //System.out.println("Hubo un ENI, Error No Identificado");
                //Logger.getLogger(FieldsFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
           //System.out.println("Hubo un ENI, Error No Identificado");
        }
    }
    public String getText(){
        try{
            BufferedReader lector=openReader();
            Stream<String> stream=lector.lines();
            StringProperty textoLineas=new SimpleStringProperty(this,"textoLineas");
            textoLineas.set("");
            stream.forEach(new Consumer<String>(){
                @Override
                public void accept(String t) {
                    textoLineas.set(textoLineas.get()+System.getProperty("line.separator")+t);
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            //System.out.println(textoLineas.get());
            return textoLineas.get();
        }
        catch(FileNotFoundException ex){
            System.out.println("Archivo no existe");
            return null;
        } catch (IOException ex) {
            Logger.getLogger(TextFile.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public void setText(String value){
        try {
            BufferedWriter escritor=openWriter(false);
            escritor.append(value);
            escritor.newLine();
            closeWriter(escritor);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no existe");
            try {
                if(!this.getParentFile().exists()){
                    this.getParentFile().mkdir();
                
                }
                this.createNewFile();
                
                setText(value);
                
            } catch (IOException ex1) {
                ex1.printStackTrace();
                //System.out.println("Hubo un ENI, Error No Identificado");
                //Logger.getLogger(FieldsFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
           //System.out.println("Hubo un ENI, Error No Identificado");
        }
    }
    
}
