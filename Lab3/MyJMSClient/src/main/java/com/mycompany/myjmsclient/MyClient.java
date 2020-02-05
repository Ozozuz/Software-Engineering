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
    public static void main(String[] args) throws NamingException, JMSException{
        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        
        //For topic 1, the one in which we listen
        Destination destination = null; 
        String destinationName = "dynamicTopics/Quotazioni";
        
        //For topic 2, the one in which we write
        Destination destination2 = null;
        String destinationName2= "dynamicTopics/Ordini";

        /////////////////////////////////////////////////////////
        /////////////////////INIZIALIZATION//////////////////////
        ///////////STANDARD EVERY TIME FOR EVERYONE//////////////
        /////////////////////////////////////////////////////////
        Properties props = new Properties();
        props.setProperty (Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        jndiContext = new InitialContext(props);
        connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
        connection = (Connection) connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        
        /////////////////////////////////////////////////////////////
        ///////////////////FOR TOPIC 1 - LISTEN ////////////////////
        ////////////////////////////////////////////////////////////
        //Here we have a CONSUMER, aka a SUBSCRIBER to a given topic
        //The consumer will  have to have a LISTENER, a class needed to manage the reception of
        //messages from the topic
        destination = (Destination) jndiContext.lookup(destinationName);
        MessageConsumer consumer = session.createConsumer(destination);
        MessageListener listener = new myListenerQuotazioni();
        consumer.setMessageListener(listener);
        
        /////////////////////////////////////////////////////////////
        ///////////////////FOR TOPIC 2 - WRITE// ////////////////////
        /////////////////////////////////////////////////////////////
        //Here we have a CONSUMER, aka a SUBSCRIBER to another given topic, DESTINATION, like above
        //We will also have a PUBLISHER associated to that consumer in order to send messages
        //But we will also have a LISTENER2, to receive the answer
        //Thus, we will use the same DESTINATION for both a CONSUMER and a PRODUCER
        destination2 = (Destination) jndiContext.lookup(destinationName2);
        MessageConsumer consumer2 = session.createConsumer(destination2);
        MessageProducer producer = session.createProducer(destination2);
        TextMessage m = session.createTextMessage();
        m.setText("user");
        m.setStringProperty("Utente", "Ozozuz");
        m.setStringProperty("Nome", "Telecom");
        m.setFloatProperty("Prezzo", 1);
        m.setIntProperty("Quantita", 3);
        producer.send(m);
        System.out.println("[CLIENT]ORDER_REQUEST\nUSER: Ozozuz - NOME: Telecom -PREZZO: 1€ - QUANTITA: 3\n"
                + " ATTACHED TEXT: user\n/////////////////////////////");        

        MessageListener listener2 = new myListenerOrdini();
        consumer2.setMessageListener(listener2);
        
    }

}