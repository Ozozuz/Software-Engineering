/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restserverapi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;


/**
 *
 * @author Giulio Garzia
 */
public class Server {
    public static void main(String[] args){
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(Repository.class);
        factoryBean.setResourceProvider(
                new SingletonResourceProvider(new Repository())
        );
        
       


        
        factoryBean.setAddress("http://localhost:8080/");
        org.apache.cxf.endpoint.Server server = factoryBean.create();
        System.out.println("Server up and running");
    }
}
