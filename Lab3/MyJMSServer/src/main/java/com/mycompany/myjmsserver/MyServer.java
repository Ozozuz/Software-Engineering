/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myjmsserver;

import org.apache.activemq.broker.BrokerService;


public class MyServer {
    public static void main(String[] args) throws Exception{
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://127.0.0.1:61616");
        
        broker.start();

        //ORDER MATTER!!!!!!
        orderReceiver rec2 = new orderReceiver();
        rec2.start();
        
        genericProducer1 prod1 = new genericProducer1();
        prod1.start();
        

        
    }
}
