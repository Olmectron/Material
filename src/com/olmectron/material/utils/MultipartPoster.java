/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author olmec
 */
public class MultipartPoster {
    public MultipartPoster(int nada){
        //codigo tonto
    }
    /*private HttpClient httpClient;
    private MultipartEntityBuilder builder;
    private HttpPost poster;
    public MultipartPoster(String url){
       
            httpClient = HttpClientBuilder.create().build();
            poster = new HttpPost(url);
            
            builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("UTF-8"));
            //builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
            //builder.addBinaryBody("file", new File("..."), ContentType.APPLICATION_OCTET_STREAM, "file.ext");
            
            
        
    }
    public void addTextPost(String field, String value){
        builder.addTextBody(field, value, ContentType.TEXT_PLAIN);
            
    }
    public InputStream post(){
        try {
            HttpEntity multipart = builder.build();
            
            poster.setEntity(multipart);
            HttpResponse response = httpClient.execute(poster);
            HttpEntity responseEntity = response.getEntity();
            return responseEntity.getContent();
        } catch (IOException ex) {
            Logger.getLogger(MultipartPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String postGetString(){
        
        InputStream is=post();
        String result = getStringFromInputStream(is);

		return result;
    }
    
    private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}*/
}
