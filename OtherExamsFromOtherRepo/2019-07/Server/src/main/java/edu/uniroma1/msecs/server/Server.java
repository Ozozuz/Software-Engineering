/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniroma1.msecs.server;

import javax.xml.ws.Endpoint;

/**
 *
 * @author biar
 */
public class Server {
    public static void main(String[] args) {
        ExamImpl implementor = new ExamImpl();
        String address = "http://localhost:8080/MovieDatabase";
        Endpoint.publish(address, implementor);
        System.out.println("Server ready...");
    }
}
