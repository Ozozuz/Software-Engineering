/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.msecs.ay2019.e3;

import java.sql.*;

/**
 *
 * @author biar
 */
public class DatabaseManager {
    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("Pass 'create' to initialize the database, 'run' to print the content of the database");
            System.exit(1);
        }

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/biar/se-2019_09.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            if (args[0].equals("create")) {
                statement.executeUpdate("DROP TABLE IF EXISTS flights;");
                statement.executeUpdate("CREATE TABLE flights (flight STRING, status STRING);");
                statement.executeUpdate("INSERT INTO flights VALUES('AA123', 'landed');");
            } else{
                ResultSet rs1 = statement.executeQuery("SELECT * FROM flights");
                while (rs1.next()) {
                    System.out.println(String.format("%s : %s", rs1.getString("flight"), rs1.getString("status")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}