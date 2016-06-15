/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author olmec
 */
public class FileDirectory {
    private ArrayList<File> files;
    private ArrayList<File> directories;
    private File folder;
    public ArrayList<File> getFiles(){
        return files;
    }
    public FileDirectory(String path){
        System.out.println(path);
        files=new ArrayList<File>();
        directories=new ArrayList<File>();
        
        folder = new File(path);
        if(folder.isDirectory()){
            initAll();
        }
        else{
            System.out.println("ERROR INMINENTE: The path specify a simple file and not a directory.");
        }
        
    }
    private void initAll(){
File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
          files.add(listOfFiles[i]);
//System.out.println("File " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
          directories.add(listOfFiles[i]);
        //System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
        try {
            FieldsFile archivos=new FieldsFile(folder.getAbsolutePath()+"/archivos.txt");
            for(int i=0;i<files.size();i++){
                archivos.addField("a"+i,files.get(i).getName());
                
            }
            } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDirectory.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}

