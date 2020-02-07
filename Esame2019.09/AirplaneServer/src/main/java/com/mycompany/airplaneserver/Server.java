/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.airplaneserver;

import org.apache.activemq.broker.BrokerService;

/**
 *
 * @author Giulio Garzia
 */
public class Server {
    public static void main(String[] args) throws Exception{
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.start();
        JMSFlightProducer prod = new JMSFlightProducer();
        prod.start();
    }
}
