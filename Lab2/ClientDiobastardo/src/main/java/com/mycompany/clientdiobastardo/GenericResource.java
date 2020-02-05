/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientdiobastardo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//This will be the Name of a Res, not very important
@XmlRootElement(name="GenericResourceasd")
public class GenericResource {
    private String name;
    private String id;
    private Boolean gay;
    
    //A generic builder
    public GenericResource(){
        this.name="";
        this.id="";
    }
    
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
    //for some reason, SETTER will require an XmlElement field
    
    @XmlElement(name="name")
    public void setName(String n){
        this.name=n;
    }
    
    @XmlElement(name="id")
    public void setId(String i){
        this.id = i;
    }
}
