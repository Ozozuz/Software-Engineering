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
public class Director {
    private int ID;
    private String name;
    private String yearOfBirth;

    public Director() {
    }

    public Director(int ID, String name, String yearOfBirth) {
        this.ID = ID;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
