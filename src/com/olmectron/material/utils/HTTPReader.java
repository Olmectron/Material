/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

import com.olmectron.material.components.MaterialProgressBar;
import com.olmectron.material.components.MaterialToast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 *
 * @author Edgar
 */
public class HTTPReader {
    private String address;
    public HTTPReader(String address){
        super();
        this.address=address;
    }
    private static final int BUFFER_SIZE = 4096;
 
    /**
     * Downloads a file from a URL
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public void onFileDownloaded(){};
    public void downloadFile(String saveDir) throws IOException{
        this.downloadFile(saveDir,null);
    }
    public void downloadFile(String saveDir, MaterialProgressBar bar)
            throws IOException {
        URL url = new URL(address);
        if(bar!=null){
            bar.setProgress(0);
           
        }
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
 
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();
 
            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = address.substring(address.lastIndexOf("/") + 1,
                        address.length());
            }
 
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);
 
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;
             
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                
                
                                // Left this in just incase
                               
                                    if(bar!=null){
                                        
                counterProperty().set(counterProperty().get() + bytesRead);
                                        bar.setProgress((double)getCounter()/(double)contentLength);
                                    
                                    }
                
                
                
                
                
            }
 
            outputStream.close();
            inputStream.close();
            onFileDownloaded();
            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
    private String networkFailedMessage;
    public void setNetworkFailedMessage(String message){
        this.networkFailedMessage=message;
    }
    private boolean showNetwork=false;
    public void setShowNetworkFailedToast(boolean showNetwork){
        this.showNetwork=showNetwork;
    }
    private URL url;
    public String readFile(){
        try {
            url = new URL(address);
            URLConnection conn = url.openConnection();
            
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String fullText="";
            String inputLine;
            
            
          
            while ((inputLine = br.readLine()) != null) {
                fullText=fullText+inputLine+System.lineSeparator();
                //bw.write(inputLine);
            }
            
            //bw.close();
            br.close();
            return fullText;
            //System.out.println("Done");
        } catch (MalformedURLException ex) {
            System.out.println("URL not reachable");
            //Logger.getLogger(HTTPReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            if(networkFailedMessage==null){
                System.out.println("Couldn't connect to file stream");
            }
            else{
                System.out.println(networkFailedMessage);
                if(showNetwork){
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {

                    new MaterialToast(networkFailedMessage).unhide();
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                }
            }
            
            //Logger.getLogger(HTTPReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private final IntegerProperty length=new SimpleIntegerProperty(this,"length");
    public IntegerProperty lengthProperty(){
        return length;
    }
    private final LongProperty counter=new SimpleLongProperty(this,"counter");
    private LongProperty counterProperty(){
     
        return counter;
    }
    private final LongProperty cost = new SimpleLongProperty(this,"cost");
    private LongProperty costProperty(){
        return cost;
    }
    public long getCounter(){
        return counterProperty().get();
    }
    public void setCounter(long val){
        counterProperty().set(val);
    }
    
    
}
