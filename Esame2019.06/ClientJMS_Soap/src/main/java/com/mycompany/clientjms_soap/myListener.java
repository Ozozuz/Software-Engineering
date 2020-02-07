/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientjms_soap;

import edu.uniroma1.msecs.soapserver.Exam;
import edu.uniroma1.msecs.soapserver.ExamImplService;
import edu.uniroma1.msecs.soapserver.Professor;
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
public class myListener implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        TextMessage message;
        if(msg instanceof TextMessage){
            try {
                message = (TextMessage) msg;
                String id =message.getStringProperty("id");
                float ranking = message.getFloatProperty("ranking");
                
                ExamImplService server = new ExamImplService();
                Exam port = server.getExamImplPort();
                Professor detail = port.getDetails(id);
                System.out.println("////////////////////////////////\n"
                        + "[JMS]Professor ID:"+id+
                        " and RANKING:"+ranking);
                System.out.println("[SOAP]Name:"+detail.getName()+"_"+detail.getSurname());
                
                
                
            } catch (JMSException ex) {
                Logger.getLogger(myListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
