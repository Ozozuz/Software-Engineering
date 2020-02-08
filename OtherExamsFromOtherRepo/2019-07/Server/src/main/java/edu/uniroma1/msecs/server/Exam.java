/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniroma1.msecs.server;

import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author biar
 */
@WebService
public interface Exam {
    public Director getDirector(int ID);
    public Movie getMovie(int ID);
    public List<Integer> getMovies();
}
