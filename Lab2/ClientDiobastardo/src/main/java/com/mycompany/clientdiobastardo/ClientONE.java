/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientdiobastardo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ClientONE {
    private static String BASE_URL = "http://localhost:8080/GenericResources/";
    
    //Standard way to open and close connection to a web-service
    private static CloseableHttpClient client;
    public static void createClient(){
        client = HttpClients.createDefault();
    }
    public static void closeClient() throws IOException{
        client.close();
    }
    
    public static void getOperation(String resID) throws IOException{
        //////////////////////////////////////////////////////////
        ////////////////////GET operation/////////////////////////
        //////////////////////////////////////////////////////////Used to retreive (if existing) a GenericResource
        //The URI of the resource will be:
        //Base URL + PathOfRepository + resourceID
        //because we associated the GET call to getGenericResources that require the Gen.Res id!
        HttpGet httpGet = new HttpGet(BASE_URL + resID);
        httpGet.setHeader("Content-Type","text/xml");
        
        HttpResponse response = client.execute(httpGet);
        
        GenericResource received = JAXB.unmarshal(response.getEntity().getContent(), GenericResource.class);
        System.out.println("Resource with [ID]= "+ received.getId()+"///and [NAME]= "+ received.getName());
    }
    
    public static void getCapitano() throws IOException{
        HttpGet httpGet = new HttpGet(BASE_URL + "capitani/10");
        httpGet.setHeader("Content-Type","text/xml");
        
        HttpResponse response = client.execute(httpGet);
        GenericResource received = JAXB.unmarshal(response.getEntity().getContent(), GenericResource.class);
        System.out.println("Resource with [ID]= "+ received.getId()+"///and [NAME]= "+ received.getName());
    }
    
    public static void postOperation(String id, String name) throws JAXBException, FileNotFoundException, IOException{
        //////////////////////////////////////////////////////////
        ////////////////////POST operation////////////////////////
        ////////////////////////////////////////////////////////// Used to add a new GenericResource 
        GenericResource toAdd = new GenericResource();
        toAdd.setId(id);
        toAdd.setName(name);
        
        HttpPost httpPost = new HttpPost(BASE_URL);
        
        JAXBContext jaxbContext = JAXBContext.newInstance(GenericResource.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(toAdd, new File("res.xml"));
        File file = new File("res.xml");
        
        InputStream targetStream = new FileInputStream(file);
        
        httpPost.setEntity(new InputStreamEntity(targetStream));
        httpPost.setHeader("Content-Type","text/xml");
        HttpResponse response=client.execute(httpPost);
        System.out.println("POST Request of GenRes with ID="+ toAdd.getId()+"///executed with RESPONSE= "+ response.getStatusLine());
    }
    
    public static void putOperation(String oldID, String id, String name) throws JAXBException, FileNotFoundException, IOException{
        //////////////////////////////////////////////////////////
        ////////////////////PUT operation/////////////////////////
        //////////////////////////////////////////////////////////Used to update the value of a GenericResource
        GenericResource replacement = new GenericResource();
        replacement.setId(id);
        replacement.setName(name);
       
        
        HttpPut httpPut = new HttpPut(BASE_URL+oldID);
        
        JAXBContext jaxbContext = JAXBContext.newInstance(GenericResource.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(replacement, new File("res.xml"));
        File file = new File("res.xml");
        InputStream targetStream = new FileInputStream(file);
        
        httpPut.setEntity(new InputStreamEntity(targetStream));
        httpPut.setHeader("Content-Type","text/xml");
        
        HttpResponse response=client.execute(httpPut);
         
        System.out.println("POST Request of GenRes with ID="+ replacement.getId()+"///executed with RESPONSE= "+ response.getStatusLine());
    }
    
    public static void deleteOperation(String id) throws IOException{
        HttpDelete httpDelete = new HttpDelete(BASE_URL+id);
        httpDelete.setHeader("Content-Type","text/xml");
        
        HttpResponse response = client.execute(httpDelete);
        System.out.println("DELETE Request of ID=" + id + "///executed with RESPONSE= " + response.getStatusLine());
    }
    public static void main(String[] args) throws IOException, JAXBException {
        createClient();
       
        postOperation("cinque","[Name]Cazzo alla fragola");
        getOperation("cinque");
        //deleteOperation("cinque");
        //getOperation("cinque");
        //System.out.println("////////////////");
        //getCapitano();
        
        
    }
}