/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restclient;


import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;


/**
 *
 * @author Nodes
 */
public class Client {

    private static String BASE_URL = "http://localhost:8080/risorse/";
    private static CloseableHttpClient client;

    public static void createClient() {
        client = HttpClients.createDefault();
    } 

    public static void closeClient() throws IOException {
        client.close();
    }

    public static void main(String[] args) throws MalformedURLException, IOException, JAXBException {
       /*
        XML MESSAGES
       */
        
        createClient();
        
        /*
         GET
         */
        String nomeRisorsa = "uno";
        String geturi = "http://localhost:8080/risorse/"+nomeRisorsa;
        
        HttpGet httpGet = new HttpGet(geturi);
        httpGet.setHeader("Content-Type", "text/xml");
        HttpResponse response = client.execute(httpGet);
        Risorsa resFromGet = JAXB.unmarshal( response.getEntity().getContent(), Risorsa.class);
        System.out.println("Resource ID= "+ resFromGet.getId()+" and Name= "+ resFromGet.getName());
    }
}
