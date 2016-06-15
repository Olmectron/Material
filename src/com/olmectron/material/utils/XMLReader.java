/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

import com.olmectron.material.MaterialDesign;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Edgar
 */
public abstract class XMLReader<T> {
    public abstract T parseObject(String value, XMLAttributeList attr,ArrayList<String> tags,ArrayList<String> values,ArrayList<XMLAttributeList> attrs);
    private ObservableList<T> list;
   
    private String getSearchField(){
        return searchName;
    }
    private String internalFile;
    private String elementTag;
    public XMLReader(String internalFile, String elementTag){
       
       this.internalFile=internalFile;
       this.elementTag=elementTag;
       this.list=FXCollections.observableArrayList();
       
       initSaxReader();
    }
    //public abstract void onStringFound(String item);
    private String searchName=null;
    private String result=null;
    
   
    private boolean wait=true;
    public ObservableList<T> readElementList(){
        list.clear();
        read();
        return list;
    }
    private void read(){
        this.wait=true;
        //this.searchName=name;
        try {
            //File xmlFile=new File(file);
            String path;
            InputStream inputStream;
                
                path=internalFile;
               
            
            inputStream=XMLString.class.getResourceAsStream(path);
            if(inputStream==null){
                System.err.println("File not found");
            }
            //new FileInputStream(xmlFile);
            Reader reader=new  InputStreamReader(inputStream,"UTF-8");
            InputSource inputSource=new InputSource(reader);
            inputSource.setEncoding("UTF-8");
            parser.parse(inputSource,handler);
            
        } catch (SAXException ex) {
            Logger.getLogger(XMLString.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLString.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        catch(NullPointerException ex){
           ex.printStackTrace();
        }
    }
    private SAXParser parser;
    private DefaultHandler handler;
    
    private void initSaxReader(){
        try {
            
            SAXParserFactory factory=SAXParserFactory.newInstance();
            parser=factory.newSAXParser();
            
            handler=new DefaultHandler(){
                boolean boolElement=false;
                private XMLAttributeList lastAttributes;
                private String lastString;
                private String lastValue;
                private boolean boolValue;
                private boolean boolValues;
                 private ArrayList<String> tags;
                 private ArrayList<String> values;
                private ArrayList<XMLAttributeList> attrs;
                public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException{
                    if(qName.equalsIgnoreCase(elementTag)){
                        
                        lastAttributes=new XMLAttributeList(attributes);
                        tags=new ArrayList<String>();
                        attrs=new ArrayList<XMLAttributeList>();
                        values=new ArrayList<String>();
                        //lastString=attributes.getValue("name");
                        
                        boolElement=true;
                        boolValue=true;
                        //if(attributes.getValue("name").equals(getSearchField())){
                            //boolString=true;
                            
                        //}
                        
                        //lastName=attributes.getValue("name");
                    }
                    if(!qName.equalsIgnoreCase(elementTag) && boolElement){
                        boolValues=true;
                        tags.add(qName);
                        attrs.add(new XMLAttributeList(attributes));
                    }
                    //System.out.println("Start element: "+qName);
                    /*if(qName.equalsIgnoreCase("NAME")){
                        boolName=true;
                    }*/
                    
                    
                    
                }
                public void endElement(String uri, String localName, String qName) throws SAXException{
                    if(qName.equalsIgnoreCase(elementTag)){
                        //map.put(lastString, lastValue);
                        //result=lastString;
                        //wait=false;
                       
                        T object=parseObject(lastValue,lastAttributes,tags,values,attrs);
                        list.add(object);
                        lastValue=null;
                        lastAttributes=null;
                        tags=null;
                        attrs=null;
                        values=null;
                        boolElement=false;
                        
                    }
                    //System.out.println("End element: "+qName);
                }
                public void characters(char[] ch, int start, int length) throws SAXException{
                    //String name, region, serial, language, titleId;
                    /*if(boolName){
                        lastName=new String(ch,start,length);
                        //System.out.println("Name: "+name);
                        boolName=false;
                    }*/
                    if(boolValues){
                        values.add(new String(ch,start,length).trim());
                        boolValues=false;
                    }
                    if(boolValue){
                        lastValue=new String(ch,start,length).trim();
                        boolValue=false;
                         //System.out.println("Title ID: "+new String(ch,start,length));
                        
                    }
                }
            
            
            };
            
           
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLString.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLString.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void initDomReader(){
        
        try {
            File xmlFile = new File("3dsreleases.xml");
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList releasesList = document.getElementsByTagName("release");
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLString.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLString.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLString.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

