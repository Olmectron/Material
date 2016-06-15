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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class XMLString {
   
    public HashMap map;
    private String getSearchField(){
        return searchName;
    }
    public XMLString(){
       initSaxReader();
    }
    //public abstract void onStringFound(String item);
    private String searchName=null;
    private String result=null;
    
    public HashMap readAll(){
        return readAll(null);
    }
    public HashMap readAll(LanguageRegion regionCode){
        if(map==null){
            map=new HashMap();
            read(regionCode);
        }
        return map;
    }
    private boolean wait=true;
    public void read(LanguageRegion region){
        this.wait=true;
        //this.searchName=name;
        try {
            //File xmlFile=new File(file);
            String path;
            InputStream inputStream;
            try{
                
                path=MaterialDesign.getLanguagePath()+"/strings_"+region.getCode()+".xml";
               
            }
            catch(NullPointerException ex){
                path=MaterialDesign.getLanguagePath()+"/strings.xml";
                
            
            }
            inputStream=XMLString.class.getResourceAsStream(path);
            if(inputStream==null){
                path=MaterialDesign.getLanguagePath()+"/strings.xml";
                inputStream=XMLString.class.getResourceAsStream(path);
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
            System.err.println("Language path isn't defined in the MaterialDesign class");
        }
    }
    private SAXParser parser;
    private DefaultHandler handler;
    
    private void initSaxReader(){
        try {
            
            SAXParserFactory factory=SAXParserFactory.newInstance();
            parser=factory.newSAXParser();
            
            handler=new DefaultHandler(){
                boolean boolString=false;
               
                private String lastString;
                private String lastValue;
                public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException{
                    if(qName.equalsIgnoreCase("STRING")){
                        lastValue=null;
                        lastString=attributes.getValue("name");
                        boolString=true;
                        //if(attributes.getValue("name").equals(getSearchField())){
                            //boolString=true;
                            
                        //}
                        
                        //lastName=attributes.getValue("name");
                    }
                    //System.out.println("Start element: "+qName);
                    /*if(qName.equalsIgnoreCase("NAME")){
                        boolName=true;
                    }*/
                    
                    
                    
                }
                public void endElement(String uri, String localName, String qName) throws SAXException{
                    if(qName.equalsIgnoreCase("STRING")){
                        map.put(lastString, lastValue);
                        //result=lastString;
                        //wait=false;
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
                    
                    if(boolString){
                        lastValue=new String(ch,start,length);
                         //System.out.println("Title ID: "+new String(ch,start,length));
                        boolString=false;
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
