/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.util.Random;

/**
 *
 * @author Giulio Garzia
 */
public class Publisher {
    private static final String EXCHANGE_NAME = "go_de_totti";
    public static String routingKey = "go.belli";
    public static String routingKey2 = "go.bellissimi";
    
    public static void main(String[] args){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();) {
            
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            Random rnd = new Random();
            String message="";
            String queue="";
            int i=0;;
            while(true){
                i++;
                if (rnd.nextInt() % 2 == 0) {
                    queue = routingKey;
                    message = "["+i+"]Un bel go de Totti";
                } else {
                    queue = routingKey2;
                    message = "["+i+"]Un BELLISSIMO go de Totti";
                }
                System.out.println("[X] Sended: "+message);
                channel.basicPublish(EXCHANGE_NAME, queue, null, message.getBytes("UTF-8"));
                Thread.sleep(2000);
            }
            
        }catch(Exception e){}
    }
}
