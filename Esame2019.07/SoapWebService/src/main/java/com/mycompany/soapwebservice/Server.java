/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapwebservice;

import java.sql.SQLException;
import javax.xml.ws.Endpoint;

/**
 *
 * @author elelo
 */
public class Server {
    public static void main(String[] args) throws SQLException{
        CinemaImpl impl = new CinemaImpl();
        String addr = "http://localhost:8080/CINEMA";
        Endpoint.publish(addr, impl);
    }
}
