/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.msecs.ay2019.e3;

/**
 *
 * @author biar
 */
public class Server {
    public static void main(String args[]) throws Exception {
        FlightProductor productor = new FlightProductor();
        productor.start();      
    }
}
