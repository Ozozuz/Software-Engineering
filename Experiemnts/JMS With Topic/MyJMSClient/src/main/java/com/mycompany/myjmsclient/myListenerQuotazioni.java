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
public class myListenerQuotazioni implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String final_name = "";
        String final_text = "";
        float final_value = 0;
        TextMessage msg = null;
        
        if(message instanceof TextMessage ){
            try {
                msg = (TextMessage) message;
                final_name  = msg.getStringProperty("Nome");
                if(!final_name.equals("Mondadori")) return;
                final_value = msg.getFloatProperty("Valore");
                final_text  = msg.getText();
                
            } catch (JMSException ex) {
                Logger.getLogger(myListenerQuotazioni.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("[CLIENT LISTENER QUOTAZIONI]\nNAME: "+final_name + " VALUE: "+final_value+"\n"
                + "TEXT: "+final_text);
    }
    
}
