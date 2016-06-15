/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.files;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class FieldsFile extends File {
    private BufferedReader reader;
    private BufferedWriter writer;
    private FilesList fields;
    
    private BufferedWriter openWriter(boolean append) throws FileNotFoundException, IOException{
        
        BufferedWriter escritor=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this,append),"UTF8"));
        return escritor;
    }
    private BufferedWriter openWriter() throws FileNotFoundException, IOException{
        return openWriter(false);
    }
    
    private BufferedReader openReader() throws FileNotFoundException{
        BufferedReader lector=null;
        try {
            lector = new BufferedReader(new InputStreamReader(new FileInputStream(this),"UTF8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FieldsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lector;
    }
    private void closeReader() throws IOException{
        getReader().close();
        
        
    }private void closeReader(BufferedReader lector) throws IOException{
        lector.close();
        
        
    }
    private void closeWriter(BufferedWriter escritor) throws IOException{
        escritor.close();
        
        
    }
    private void initList(){
        fields=new FilesList();
    }
    
    public FieldsFile(String path) throws FileNotFoundException{
        super(path);
        initList();
        
        if(this.exists()){
             readExistingFile();
        }
       
        
    }
    public String getMySQLCompatibleAbsolutePath(){
        
        return this.getAbsolutePath().replace("C:", "").replace("\\","/");
        
    }
    public FieldsFile(String path, FilesList lista){
        this(path,lista,false);
    }
    public FieldsFile(String path, FilesList lista, boolean writeKeys){
        super(path);
        setList(lista);
        rewriteData(writeKeys);
    }
    
    public FieldsFile(String path, boolean overwrite) throws FileNotFoundException{
        super(path);
        initList();
        
        if(this.exists()){
            
            if(overwrite){
                this.delete();
            }
            else{
                readExistingFile();   
            }
             
        }
       
        
    }
    public String getText(){
        String texto="";
        
        try{
            
            
            BufferedReader lector=openReader();
            
            String linea=lector.readLine();
            
            while(linea!=null){
                texto=texto+linea+"\n";
                linea=lector.readLine();
            }
            
            closeReader(lector);
        }
        catch(FileNotFoundException ex){
            
        } 
        catch (IOException ex) {
           
        }
        
        
        return texto;
    }
    public void copyHere(FieldsFile file, boolean overwrite){
        try {
            BufferedWriter escritor=openWriter(!overwrite);
            
            escritor.append(file.getText());
            
            closeWriter(escritor);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FieldsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FieldsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void copy(FieldsFile file){
        
        
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(file);
            fw = new FileWriter(this);
            int c = fr.read();
            while(c!=-1) {
                fw.write(c);
                c = fr.read();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fr);
            closeStream(fw);
        }
    
    
        
        
        
    }
    private void closeStream(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch(IOException e) {
            //...
        }
    }
    
    
    //Reescribe todos los datos en un nuevo archivo.
    private void rewriteData(){
        rewriteData(true);
    }
    private void rewriteData(boolean writeKeys){
         try{
            
            this.delete();
            this.createNewFile();
            for(int i=0;i<getList().size();i++){
                String toWrite;
                if(writeKeys){
                    toWrite=getList().getKeyAt(i)+"="+getList().getValueAt(i);
                
                }
                else{
                    toWrite=getList().getValueAt(i);
                }
                
                this.writeLine(toWrite);
                
                
            }
            
            
        }
        catch(FileNotFoundException ex){
            
        } 
        catch (IOException ex) {
           
        }
    }
    //Remueve las llaves del inicio, dejando sólo los valores
    public void removeIdentifiers(){
        try{
            
            this.delete();
            this.createNewFile();
            for(int i=0;i<getList().size();i++){
                this.writeLine(getList().get(i)[1]);
                
                
            }
            
            
        }
        catch(FileNotFoundException ex){
            
        } 
        catch (IOException ex) {
           
        }
    }
    //Remueve un campo con la llave especifica
    public void removeField(String key){
        removeFileField(key);
        removeClassField(key);
    }
    public void writeLine(String text){
        try {
            BufferedWriter escritor=openWriter(true);
            escritor.append(text);
            escritor.newLine();
            closeWriter(escritor);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FieldsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FieldsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void removeFileField(String key){
       try{
            
            //OlmectronFile auxiliar=new OlmectronFile("auxiliar");
            
           //this.delete();
           PrintWriter writer = new PrintWriter(this);
writer.print("");
writer.close();
           //this.createNewFile();
            //BufferedReader lector=openReader();
            
            int eliminar=getList().getIndexOfKey(key);
            //System.out.println(eliminar+" - por eliminar");
            //String linea=lector.readLine();
            
            for (int i=0;i<getList().size();i++){
                if(i!=eliminar){
                    this.writeLine(getList().get(i)[0]+"="+getList().get(i)[1]);
                }
                
            }
            
            //closeReader(lector);
            
            //copy(auxiliar);
            //auxiliar.delete();
        }
        catch(FileNotFoundException ex){
            
        } 
        
    }
    private void removeClassField(String key){
        getList().removeValue(key);
    }
    
    public void printSystemList(){
        
        for(int i=0;i<getList().size();i++)
        {
            System.out.println(getList().get(i)[0]+" = "+getList().get(i)[1]);
            
            
        }
        System.out.println("Terminado");
        
    }
    public FilesList getFieldList(){
        
        return fields;
    }
    private FilesList getList(){
        return fields;
    }
    public void setList(FilesList list){
        this.fields=list;
    }
    private final  String separador="=";
    public String getSeparador(){
        return separador;
    }
    public boolean isEmpty(){
        return getList().isEmpty();
    }
    private void readExistingFile(){
        try{
            
            getList().clear();
            BufferedReader lector=openReader();
            String linea=lector.readLine();
            
            while(linea!=null){
                if(!linea.trim().equals("")){
                    StringTokenizer tokenizer=new StringTokenizer(linea,getSeparador());
                    
                    if(tokenizer.countTokens()==2){
                        String[] element=new String[]{tokenizer.nextToken(),tokenizer.nextToken()};
                        getList().add(element);
                    }
                }
                linea=lector.readLine();
            }
            
            closeReader(lector);
        }
        catch(FileNotFoundException ex){
            
        } 
        catch (IOException ex) {
           
        }
        
    }
    
    private void addClassField(String field, String value){
         getList().addValue(field,value);
    }
    public String getValue(String field){
        return getList().getValueAt(field);
    }
    public String getValue(String field, String value){
        if(getValue(field)!=null){
            return getValue(field);
        }
        else{
            addField(field,value);
            //System.out.println("FIELDS: "+value);
            return value;
        }
    }
    public void setValue(String field, String value){
        if(!value.equals(getValue(field))){
            try{
                removeField(field);
            
            }
            catch(ArrayIndexOutOfBoundsException ex){
                System.out.println("The field didn't exist in the file");
            }
            addField(field,value);
        }
        
    }
    
    private void addFileField(String field, String value){
        try {
            BufferedWriter escritor=openWriter(true);
            escritor.append(field+"="+value);
            escritor.newLine();
            closeWriter(escritor);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no existe");
            try {
                if(!this.getParentFile().exists()){
                    this.getParentFile().mkdir();
                
                }
                this.createNewFile();
                
                addFileField(field,value);
                
            } catch (IOException ex1) {
                ex1.printStackTrace();
                //System.out.println("Hubo un ENI, Error No Identificado");
                //Logger.getLogger(FieldsFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
           //System.out.println("Hubo un ENI, Error No Identificado");
        }
    }
    public void addField(String field, String value){
        addClassField(field,value);
        addFileField(field,value);
    }
    
    protected BufferedReader getReader(){
        
        return reader;
    }
    
        
    
}
