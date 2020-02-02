/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myjmsserver;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Giulio Garzia
 */
public class orderReceiver {
    Context jndiContext = null;
    ConnectionFactory connectionFactory = null;
    Connection connection = null;
    Session session = null;

    Destination destination = null;
    String destinationName = "dynamicTopics/Ordini";
   
    
    public void start() throws NamingException, JMSException, InterruptedException{
        /////////////////////////////////////////////////////////
        /////////////////////INIZIALIZATION//////////////////////
        ///////////STANDARD EVERY TIME FOR EVERYONE//////////////
        /////////////////////////////////////////////////////////
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        jndiContext = new InitialContext(props);
        connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
        connection = (Connection) connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        
        /////////////////////////////////////////////////////////////
        ///////////////////FOR TOPIC 1 - LISTEN+PRODUCER////////////
        ////////////////////////////////////////////////////////////
        //We have a CONSUMER, aka a subscriber and it's listener, HOWEVER
        //every time we receive a message we also need to send back an answer,
        //Thus we also need a PRODUCER
        destination = (Destination) jndiContext.lookup(destinationName);
        MessageProducer producer = session.createProducer(destination);
        MessageConsumer consumer = session.createConsumer(destination);
        TextMessage msg          = session.createTextMessage();
   
        orderListener tmp = new orderListener();
        tmp.setSetStuff(session,destination);
        
        MessageListener listener = tmp;     
        consumer.setMessageListener(listener);
    }
}