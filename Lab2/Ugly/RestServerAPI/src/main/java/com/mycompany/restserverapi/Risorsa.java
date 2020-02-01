/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restserverapi;

import java.util.Objects;
import javax.xml.bind.annotation.*;


@XmlRootElement(name="Risorsa")
public class Risorsa {
    private String id;
    private String name;

    public Risorsa() {
        this.id = "";
        this.name= "";
    }

    public String getId() {
        return id;
    }
    @XmlElement(name="identifier")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlElement(name="name")
    public void setName(String name) {
        this.name = name;
    }

}
