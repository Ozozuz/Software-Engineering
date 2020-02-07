/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientjms_soap;

import java.util.*;
import javax.jms.*;
import javax.naming.*;

/**
 *
 * @author Giulio Garzia
 */
public class Client {
    public static void main(String[] args) throws NamingException, JMSException{
        Context jndiContext = null;
        javax.jms.ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;  
        Destination destination = null;
        String destinationName = "dynamicTopics/professors";
        
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
        jndiContext = new InitialContext(props);
        connectionFactory = (javax.jms.ConnectionFactory) jndiContext.lookup("ConnectionFactory");
        connection = (Connection) connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        destination = (Destination) jndiContext.lookup(destinationName);
        MessageConsumer consumer = session.createConsumer(destination);
        MessageListener myListener = new myListener();
        consumer.setMessageListener(myListener);
    }
}
