/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myjmsserver;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Giulio Garzia
 */
public class orderListener implements MessageListener {
    
    private Session session;
    private Destination destination;
    
    public void setSetStuff(Session p, Destination d){
        this.session=p;
        this.destination=d;
    }

    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        String text = "";
        String user = "";
        String name = "";
        float price = 0;
        int quantita = 0;
        
        if(message instanceof TextMessage){
            try {
                msg = (TextMessage) message;
                text = msg.getText();
                user = msg.getStringProperty("Utente");
                name = msg.getStringProperty("Nome");
                price = msg.getFloatProperty("Prezzo");
                quantita = msg.getIntProperty("Quantita");
                System.out.println("////////////////\n[ORDER_LISTENER]ORDER_REQUEST\nUSER: Ozozuz - NOME: Telecom -PREZZO: 1€ - QUANTITA: 3\n"
                        + "ATTACHED TEXT: user\n/////////////////////////////");

                
                MessageProducer producer = session.createProducer(destination);
                TextMessage toSend = session.createTextMessage();
                toSend.setStringProperty("Status", "Daje");
                producer.send(toSend);
            } catch (JMSException ex) { }
        }
    }
    
}
