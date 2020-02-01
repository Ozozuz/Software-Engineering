/*
The server will run and launch the REST web-services interface
 */
package com.mycompany.restserver;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;


public class Server {
    public static void main(String[] args){
        
        //Standard Stuff
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(Repository.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new Repository()));
        
        //This will be the addres that a client has to reach in order to use the methods offere
        //with the addition of the XmlRootElement specified in the repository.
        factoryBean.setAddress("http://localhost:8080/");
        
        org.apache.cxf.endpoint.Server server = factoryBean.create();
        System.out.println("[SERVER]Server up and running");
    }
}
