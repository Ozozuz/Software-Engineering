/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniroma1.msecs.server;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author biar
 */
@XmlRootElement
public class Movie {
    private int ID;
    private int directorID;
    private String title;
    private String year;

    public Movie(int ID, int directorID, String title, String year) {
        this.ID = ID;
        this.directorID = directorID;
        this.title = title;
        this.year = year;
    }

    public Movie() {
    }

    public int getID() {
        return ID;
    }

    public int getDirectorID() {
        return directorID;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
