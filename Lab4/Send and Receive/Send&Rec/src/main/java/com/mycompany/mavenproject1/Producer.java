/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


//Aka the SENDER, PUBLISHER
public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            int i = 0;
            while(true){
                i++;
                String message = "["+i+"]Message";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
                Thread.sleep(2000);
            }
        }catch(Exception e){}
    }
}

