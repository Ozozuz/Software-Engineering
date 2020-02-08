/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniroma1.msecs.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author biar
 */
@WebService(endpointInterface = "edu.uniroma1.msecs.server.Exam")
public class ExamImpl implements Exam {
    private Connection connection = null;

    public ExamImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
            
            connection = DriverManager.getConnection("jdbc:sqlite:/home/biar/msecs-1-se-2019_07.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public Director getDirector(int ID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM DIRECTORS WHERE ID = ?;");
            statement.setInt(1, ID);
            statement.setQueryTimeout(30);
            
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return new Director(rs.getInt("ID"),
                                    rs.getString("name"),
                                    rs.getString("yearOfBirth"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Movie getMovie(int ID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM MOVIES WHERE ID = ?;");
            statement.setInt(1, ID);
            statement.setQueryTimeout(30);
            
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return new Movie(rs.getInt("ID"),
                                 rs.getInt("directorID"),
                                 rs.getString("title"),
                                 rs.getString("year"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Integer> getMovies() {
        ArrayList<Integer> movies = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("SELECT ID FROM MOVIES;");
            while(rs.next()) {
                movies.add(rs.getInt("ID"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }

        return movies;
    }
    
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
                System.err.println(e);
        }
    }
}
