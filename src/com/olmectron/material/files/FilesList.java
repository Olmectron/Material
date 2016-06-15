 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.files;


import java.util.ArrayList;

/**
 *
 * @author Édgar
 */
public class FilesList extends ArrayList<String[]>{
    
    public FilesList(){
        super();
        id=null;
    }
    private String id;
    public FilesList(String id){
        super();
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public FieldsFile generateFile(String path){
        return new FieldsFile(path,this);
    }
    
    public void removeValue(String key){
        remove(getIndexOfKey(key));
    }
    public void addValue(String key, String value){
        add(new String[]{key,value});
    }
    public String getKeyAt(int index){
        return get(index)[0];
    }
    public int getIndexOfKey(String key){
        for(int i=0;i<size();i++){
            if(getKeyAt(i).equalsIgnoreCase(key)){
                return i;
            }
        }
        return -1;
    }
    public String getValueAt(String key){
        if(getIndexOfKey(key)!=-1){
            return getValueAt(getIndexOfKey(key));
        }
        return null;
        
    }
    public void setValueAt(int index, String newValue){
        get(index)[1]=newValue;
    }
    public String getValueAt(int index){
        return get(index)[1];
    }
    
    public void replaceValue(String key, String toReplace, String toPut){
        int index=getIndexOfKey(key);
        replaceValue(index,toReplace,toPut);
    }
    public void replaceValue(int index, String toReplace, String toPut){
        
        setValueAt(index,getValueAt(index).replace(toReplace, toPut));
        
        
    }
    public void appendToValue(int index, String string){
      setValueAt(index,getValueAt(index)+string);
    }
    public void appendToValue(String key, String string){
        appendToValue(this.getIndexOfKey(key),string);
    }
    public void appendToAllValues(String string) {
        for(int i=0;i<size();i++){
            appendToValue(i,string);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void replaceAllValues(String toReplace, String toPut){
        for(int i=0;i<size();i++){
            replaceValue(i,toReplace,toPut);
        }
        
    }

    
    
    
}
