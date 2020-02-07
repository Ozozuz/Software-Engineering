/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jmsserver;

import java.util.*;
import javax.jms.*;
import javax.naming.*;

/**
 *
 * @author Giulio Garzia
 */


public class rankingProducer {
    
    private String[] nomi = {"Giulio", "Massimo", "Giuseppe", "Andrea", "Lorenzo",
         "Michele", "Riccardo", "Maurizio", "Francesco"};
    private String[] cognomi = {"Garzia", "Mecella", "De Giacomo", "Baldoni", "Rosati",
        "Santucci", "Spaccamela"};
    
    private Random rnd = new Random();

            

    void start() throws NamingException, JMSException, InterruptedException {
        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;  
        Destination destination = null;
        String destinationName = "dynamicTopics/professors";
        
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
        jndiContext = new InitialContext(props);
        connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
        connection = (Connection) connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        
        destination = (Destination) jndiContext.lookup(destinationName);
        MessageProducer producer = session.createProducer(destination);
        
        TextMessage message = session.createTextMessage();
        String id;
        float ranking;
        
        while(true){
            id = ""+rnd.nextInt(6);
            ranking = rnd.nextFloat()*10;
            
            message.setStringProperty("id", id);
            message.setFloatProperty("ranking", ranking);
            
            producer.send(message);
            
            Thread.sleep(3500);
            
        }
    }
    
}
