/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myjmsclient;

//For some reason this import fuck up things!
//////////import java.sql.Connection;
import java.util.Properties;
import javax.jms.*;
import javax.naming.*;




//A JMS Client will have a LISTENER for every TOPIC he is interested into.
public class MyClient {
    private static  TopicConnection connection;
    
    public void start() {
        try {
            connection.start();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }

    public void stop() {
        try {
            connection.stop();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }
    public static void main(String[] args) throws NamingException, JMSException{
        
        TopicConnection connection;
        TopicSession session = null;
        TopicSubscriber subscriber = null;
        
        Context ctx = null;
        ConnectionFactory connectionFactory = null;
        
        //For topic 1, the one in which we listen
        Destination destination = null; 
        String destinationName = "dynamicTopics/Quotazioni";
       
        /////////////////////////////////////////////////////////
        /////////////////////INIZIALIZATION//////////////////////
        ///////////STANDARD EVERY TIME FOR EVERYONE//////////////
        /////////////////////////////////////////////////////////
        Properties props = new Properties();
        props.setProperty (Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        
        ctx = new InitialContext(props);
        connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
        connection = (TopicConnection) connectionFactory.createConnection();
        session = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
  
        destination = (Destination)ctx.lookup(destinationName);
        subscriber = session.createSubscriber((Topic) destination);
        subscriber.setMessageListener(new myListenerQuotazioni());
    }

}