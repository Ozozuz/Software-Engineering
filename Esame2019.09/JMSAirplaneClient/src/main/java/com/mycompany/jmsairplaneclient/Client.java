/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jmsairplaneclient;


import java.sql.SQLException;
import java.util.*;
import javax.jms.*;
import javax.naming.*;

/**
 *
 * @author Giulio Garzia
 */
public class Client {
    public static void main(String[] args) throws NamingException, JMSException, ClassNotFoundException, SQLException{
        ///////////////////////////////////////////////
        /////////////////VARIABLES////////////////////
        //////////////////////////////////////////////
        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection;
        Session session = null;
        
        Destination destination = null;
        String destinationName = "dynamicTopics/flights";
        
        /////////////////////////////////////////////////
        ///////////////INIZIALIZATION////////////////////
        /////////////////////////////////////////////////
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
        jndiContext = new InitialContext(props);
        connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
        connection = connectionFactory.createConnection();
        session    = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        
        /////////////////////////////////////////////////
        ////////////////CONSUMATION///////////////////////
        /////////////////////////////////////////////////
        destination = (Destination) jndiContext.lookup(destinationName);
        MessageConsumer consumer = session.createConsumer(destination);
        MessageListener listener = new myFlightListener();
        
        consumer.setMessageListener(listener);
        
    }
}
