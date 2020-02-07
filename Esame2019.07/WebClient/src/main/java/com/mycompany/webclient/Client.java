/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webclient;

import com.mycompany.soapwebservice.CinemaImplService;
import com.mycompany.soapwebservice.CinemaInterface;
import com.mycompany.soapwebservice.Director;
import com.mycompany.soapwebservice.Movie;
import java.util.List;

/**
 *
 * @author elelo
 */
public class Client {
    public static void main(String[] args){
        CinemaImplService server = new CinemaImplService();
        CinemaInterface port = server.getCinemaImplPort();
       
        List<Integer> listofMovies = port.getMovies();
        System.out.println("[LIST OF MOVIES]");
        for (int i : listofMovies){
            Movie mov = port.getMovie(i);
            int movieID = mov.getID();
            int dirID   = mov.getDirectorID();
            String movieName = mov.getTitle();
            String year = mov.getYear();
            
            Director dir = port.getDirector(dirID);
            int dirIDclone = dir.getID();
            String dirName = dir.getName();
            String born    = dir.getYearOfBirth();
            System.out.println("///////////////////////////////////////////////");
            System.out.println("Movie["+movieID+"]Name:"+movieName+" ReleaseDate:"+year+" By dir-ID:" + dirID);
            System.out.println("Director["+dirIDclone+"]Name:"+dirName+" Born in:"+born);
            System.out.println("///////////////////////////////////////////////");
        }   
    }
    
}
