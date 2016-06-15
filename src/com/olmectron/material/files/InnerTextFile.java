/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.files;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author Edgar
 */
public class InnerTextFile {
    private ArrayList<String> lines;
    public InnerTextFile(String innerPath){
        lines=new ArrayList<String>();
        InputStream in = InnerTextFile.class.getResourceAsStream(innerPath);
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
                input.lines().forEach(new Consumer<String>(){

                        @Override
                        public void accept(String t) {
                            lines.add(t);
                            //System.out.println(t);
                            //System.out.println("Linea "+t);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
        
    }
    public ArrayList<String> getLines(){
        return lines;
    }
}
