/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;
import com.olmectron.material.components.MaterialToast;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by olmec on 16/08/2015.
 */
public class MultipartUtility {
    //private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;

    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     * @param requestURL
     * @param charset
     * @throws IOException
     */
    String as;
    private String requestURL;
    public MultipartUtility(String requestURL/*, String charset*/)
            throws IOException {
        this.charset = "UTF-8";
        this.requestURL=requestURL;
        parameters=new ArrayList<MultipartParameter>();
    
        //URLEncoder.encode("", charset);
        
    }
/*public MultipartUtility(String requestURL, String charset)
            throws IOException {
        this.charset = "UTF-8";
        parameters=new ArrayList<MultipartParameter>();
        // creates a unique boundary based on time stamp
        //boundary = "===" + System.currentTimeMillis() + "===";

        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        //httpConn.addRequestProperty("codigo_cliente", "amoag.12314garo");
        httpConn.setDoOutput(true); // indicates POST method
        httpConn.setDoInput(true);
        //httpConn.setRequestProperty("Content-Type",
        //       "multipart/form-data; boundary=" + boundary);
        //httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
        //httpConn.setRequestProperty("Test", "Bonjour");
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                true);
    }*/
    /**
     * Adds a form field to the request
     * @param name field name
     * @param value field value
     */
    private ArrayList<MultipartParameter> parameters;
    public void addRequestField(String name, String value){
        
        parameters.add(new MultipartParameter(name,value));
    }
    public JSONArray sendPostJSONRequest(){
        try{
        String respuesta=sendPostRequest();
        //JSONObject jObject = new JSONObject(respuesta);
        JSONArray jArray;
        if(!respuesta.startsWith("[")){
            respuesta="[]";
        }    
        jArray = new JSONArray(respuesta);
        
        
        return jArray;
          
        }
        catch (JSONException ex) {
            Logger.getLogger(MultipartUtility.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
        
    }
    public String sendPostRequest(){
              
        String respuesta="";
     
            
            
            
            
            String query="";
            try {
                for(int i=0;i<parameters.size();i++){
                    MultipartParameter param=parameters.get(i);
                    String queryPart = String.format(param.getFieldName()+"=%s",
                            URLEncoder.encode(param.getValue(), charset));
                    
                    query=query+queryPart;
                    
                    if(i<parameters.size()-1){
                        query=query+"&";
                    }
                    
                }
                
// ...
                
                
                //System.out.println(query+" --- "+as);
           
            
            
            HttpURLConnection connection = (HttpURLConnection) new URL(requestURL).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            
            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8")) {
                //output.write(query.getBytes(charset));
                writer.write(query);
            }
            catch(ConnectException ex){
                System.err.println(ex.getMessage());
                new MaterialToast("Error al conectar al servidor").unhide();
            }
//List<String> response = new ArrayList<String>();
            
            // checks server's status code first
            //int status = httpConn.getResponseCode();
            //if (status == HttpURLConnection.HTTP_OK) {
           try{ 
           BufferedReader reader = new BufferedReader(new InputStreamReader(
            connection.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                respuesta=respuesta+line;
            }
            reader.close();
            connection.disconnect();
            
           }
catch(SocketException ex){
System.err.println(ex.getMessage());
}//} else {
            //    throw new IOException("Server returned non-OK status: " + status);
            //}
            
            
            
        } 
            catch(UnknownHostException ex){
                return "Imposible conectar con el servidor";
            }
            catch(NoRouteToHostException ex){
                return "Imposible conectar con el servidor";
            }
            
            catch (IOException ex) {
            Logger.getLogger(MultipartUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    /*private void addFormField(String name, String value) {
        //writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=" + charset).append(
                LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED);
        writer.flush();
    }*/

    /**
     * Adds a upload file section to the request
     * @param fieldName name attribute in <input type="file" name="..." />
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    /*private void addFilePart(String fieldName, File uploadFile)
            throws IOException {
        String fileName = uploadFile.getName();
        //writer.append("--" + boundary).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"")
                .append(LINE_FEED);
        writer.append(
                "Content-Type: "
                        + URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.append(LINE_FEED);
        writer.flush();
    }*/

    /**
     * Adds a header field to the request.
     * @param name - name of the header field
     * @param value - value of the header field
     */
   /* private void addHeaderField(String name, String value) {
        writer.append(name + ": " + value).append(LINE_FEED);
        writer.flush();
    }
*/
    /**
     * Completes the request and receives response from the server.
     * @return a list of Strings as response in case the server returned
     * status OK, otherwise an exception is thrown.
     * @throws IOException
     */
   /* private List<String> finish() throws IOException {
        List<String> response = new ArrayList<String>();

        writer.append(LINE_FEED).flush();
        //writer.append("--" + boundary + "--").append(LINE_FEED);
        writer.close();

        // checks server's status code first
        int status = httpConn.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            httpConn.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }

        return response;
    }*/
}