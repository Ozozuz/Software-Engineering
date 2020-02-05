/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myjmsclient;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Giulio Garzia
 */
public class myListenerOrdini implements MessageListener {


    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                Enumeration p = message.getPropertyNames();
                if(p!=null){
                    while(p.hasMoreElements()){
                        try {
                            String key = (String)p.nextElement();
                            Object value = message.getObjectProperty(key);
                            if(key.equals("Status")){
                                System.out.println("[CLIENT LISTENER]OPERATION RESULT:" + value + "\n/////////////");
                            }
                        } catch (JMSException ex) {
                            Logger.getLogger(myListenerOrdini.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } catch (JMSException ex) {
                Logger.getLogger(myListenerOrdini.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
