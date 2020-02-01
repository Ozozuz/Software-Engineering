/*
This file is used to represent any particular resource that you want to offer/share
in your REST Web-service, it can be a Person, a Bank Account and so on.
*/

package com.mycompany.restserver;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//This will be the Name of a Res, not very important
@XmlRootElement(name="GenericResource")
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
    
    @XmlElement(name="id")
    public void setName(String n){
        this.name=n;
    }
    
    @XmlElement(name="name")
    public void setId(String i){
        this.id = i;
    }
}
