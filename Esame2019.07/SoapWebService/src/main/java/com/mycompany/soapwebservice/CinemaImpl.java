/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapwebservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CinemaImpl implements CinemaInterface {
    private Connection connection = null;
    
    public CinemaImpl() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/elelo/Desktop/KidusOdiaMarven/moviesDB.db");
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
            if (rs.next()) {
                return new Director(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getString("yearOfBirth"));
            }
        } catch (SQLException e) {
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
            if(rs.next()){
                int id = rs.getInt("ID");
                int dirID = rs.getInt("directorID");
                String title = rs.getString("title");
                String year = rs.getString("year");
                return new Movie(id,dirID, title, year);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CinemaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Integer> getMovies() {
        List<Integer> movies = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM MOVIES;");
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                movies.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CinemaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movies;
    }

    @Override
    public void addDirector() {
            try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("INSERT INTO DIRECTORS VALUES(10, 'Francesco Totti', '1927');");
            } catch (SQLException ex) {
            Logger.getLogger(CinemaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
