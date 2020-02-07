/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.airplaneserver;

import java.util.*;
import javax.jms.*;
import javax.naming.*;

/**
 *
 * @author Giulio Garzia
 */
class JMSFlightProducer {
    
    private Random rnd = new Random();
    private final String alpabhet = "qwertyuiopasdfghjklzxcvbnm";
    
    private String pickRandomLetter(){
        return ""+this.alpabhet.charAt(rnd.nextInt(this.alpabhet.length()));
    }
    
    private String generateRandomFlight(){
        return pickRandomLetter()+pickRandomLetter()+rnd.nextInt(9)+rnd.nextInt(9)+rnd.nextInt(9);
    }
    
    void start() throws NamingException, JMSException, InterruptedException {
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
        ////////////////GENERATION///////////////////////
        /////////////////////////////////////////////////
        destination = (Destination) jndiContext.lookup(destinationName);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage();
        int i = 0;
        while(true){
            i++;
            String flight = generateRandomFlight().toUpperCase();
            boolean landed = rnd.nextBoolean();
            message.setText("["+i+"]"+"Flight with ID: "+flight+", LANDED: "+landed);
            message.setStringProperty("number", flight);
            message.setBooleanProperty("status", landed);
            producer.send(message);
            
            System.out.println("["+i+"]"+"Flight with ID: "+flight+", LANDED: "+landed);
            
            
            Thread.sleep(2500);
        }
    }
    
}
