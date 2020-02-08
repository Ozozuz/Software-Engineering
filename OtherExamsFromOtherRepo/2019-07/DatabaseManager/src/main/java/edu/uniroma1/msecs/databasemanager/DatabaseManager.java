/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniroma1.msecs.databasemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author biar
 */
public class DatabaseManager {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/biar/msecs-1-se-2019_07.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("DROP TABLE IF EXISTS DIRECTORS;");
            statement.executeUpdate("DROP TABLE IF EXISTS MOVIES;");
            statement.executeUpdate("CREATE TABLE DIRECTORS (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                                    + "name STRING, "
                                    + "yearOfBirth STRING);");
            statement.executeUpdate("CREATE TABLE MOVIES(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                                    + "directorID STRING, "
                                    + "title STRING, "
                                    + "year STRING, "
                                    + "FOREIGN KEY(directorID) REFERENCES DIRECTORS(ID));");
            
            statement.executeUpdate("INSERT INTO DIRECTORS VALUES(0, 'Kathryn Bigelow', '1951');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'Point Break', '1991');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'K-19: The Widowmaker', '2002');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'The Hurt Locker', '2008');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'Zero Dark Thirty', '2012');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'Detroit', '2017');");
            
            statement.executeUpdate("INSERT INTO DIRECTORS VALUES(1, 'Neill Blomkamp', '1979');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(1, 'District 9', '2009');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(1, 'Elysium', '2013');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(1, 'Chappie', '2015');");
            
            statement.executeUpdate("INSERT INTO DIRECTORS VALUES(2, 'Jason Reitman', '1977');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(2, 'Up in the Air', '2009');");
            
            statement.executeUpdate("INSERT INTO DIRECTORS VALUES(3, 'Quentin Tarantino', '1963');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(3, 'Reservoir Dogs', '1992');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(3, 'Pulp Fiction', '1994');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(3, 'Kill Bill: Volume 1', '2003');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(3, 'Kill Bill: Volume 2', '2004');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(3, 'Inglourious Basterds', '2009');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(3, 'Django Unchained', '2012');");
            
            
            ResultSet rs1 = statement.executeQuery("SELECT * FROM DIRECTORS");
            while (rs1.next()) {
                System.out.println("Director " + rs1.getInt("ID")
                                   + " is " + rs1.getString("name")
                                   + " and was born in " + rs1.getString("yearOfBirth"));
            }
            
            ResultSet rs2 = statement.executeQuery("SELECT * FROM MOVIES");
            while (rs2.next()) {
                System.out.println("Movie " + rs1.getInt("ID")
                                   + " is " + rs1.getString("title") + " (" + rs1.getString("year") + ")"
                                   + " and was directed by " + rs2.getInt("directorID"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
