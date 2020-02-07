/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jmsairplaneclient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class myFlightListener implements MessageListener {
    
    Connection connection = null;
    Statement statement = null;
    int i = 0;
    
    public myFlightListener() throws ClassNotFoundException, SQLException{
        Class.forName ("org.sqlite.JDBC");
        
        connection = DriverManager.getConnection
        ("jdbc:sqlite:C:/Users/Giulio Garzia/Documents/NetBeansProjects/Esame2019.09/database.db");

        statement = connection.createStatement();
        statement.setQueryTimeout(30);
        statement.executeUpdate("DROP TABLE IF EXISTS FLIGHTS;");
        statement.executeUpdate("CREATE TABLE FLIGHTS (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "flight STRING, "
                + "status STRING);");
        statement.executeUpdate("INSERT INTO FLIGHTS(flight, status) VALUES('TestFlight', 'FOOBAR');");
    }

    public void printDB() throws SQLException{
        System.out.println("////////////////////////////////////////////"
                + "///////////////////////////////////////////////\n"
                + "PRINTING DB\n"
                + "/////////////////////////////////////////////////\n"
                + "//////////////////////////////////////////////");
        ResultSet rs = statement.executeQuery("SELECT * FROM FLIGHTS");
        while (rs.next()){
            System.out.println("FLIGHT:"+rs.getString("flight")+" STATUS:"+rs.getBoolean("status"));
        }
    }

    @Override
    public void onMessage(Message msg) {
        
        TextMessage message;
        String flight = "NOT RECEIVED";
        boolean status = false;
        
        if(msg instanceof TextMessage){
            try {
                i++;
                message = (TextMessage) msg;
                flight = message.getStringProperty("number");
                status = message.getBooleanProperty("status");
                //System.out.println("///////////////////////\n"+ message.getText());
                //System.out.println("NUMBER="+flight);
                //System.out.println("STATUS="+status+"\n///////////////////////");
                System.out.println("["+i+"]");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO FLIGHTS(flight, status) VALUES(?, ?);");
                statement.setString(1, flight);
                statement.setBoolean(2, status);
                statement.executeUpdate();
                
                if(i%5==0)printDB();
                
            } catch (JMSException ex) {
                Logger.getLogger(myFlightListener.class.getName()).log(Level.SEVERE, null, ex);
            //} catch (SQLException ex) {
            //    Logger.getLogger(myFlightListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(myFlightListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
}
