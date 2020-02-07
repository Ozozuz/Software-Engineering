/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jmsserver;

import org.apache.activemq.broker.BrokerService;

/**
 *
 * @author Giulio Garzia
 */
public class JMSServer {
    public static void main(String[] args) throws Exception{
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://127.0.0.1:61616");
        broker.start();
        
        rankingProducer producer = new rankingProducer();
        producer.start();
    }
}
