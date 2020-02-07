/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapwebservice;

import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author elelo
 */
@WebService
public interface CinemaInterface {
    public Director getDirector(int ID);
    public Movie getMovie(int ID);
    public List<Integer> getMovies();
    public void addDirector();
}
