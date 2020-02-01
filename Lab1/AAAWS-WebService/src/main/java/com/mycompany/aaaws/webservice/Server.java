/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aaaws.webservice;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        AAAWSImpl implementor = new AAAWSImpl();
        String address = "http://localhost:8081/AAAWS";
        
        Endpoint.publish(address, implementor);
        
        implementor.addClient("1, Massimo Mecella");
        implementor.addClient("2, Miguel Ceriani");
        implementor.addClient("3, Giulio Garzia");
        implementor.addClient("4, Romano Prodi");
        implementor.addClient("10, Francesco Totti");
        
        System.out.println("FragolameVario");
    }
}
