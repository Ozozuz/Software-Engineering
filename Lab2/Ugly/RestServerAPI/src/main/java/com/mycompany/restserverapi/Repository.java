/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restserverapi;


import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Nodes
 */
@Path("risorse")
@Produces({MediaType.APPLICATION_XML}) 
public class Repository {
    private List<Risorsa> data = new LinkedList<>();

    public Repository() {
        Risorsa r = new Risorsa();
        r.setId("uno");
        r.setName("risorsa normale");
        data.add(r);
        
        Risorsa r1 = new Risorsa();
        r1.setId("due");
        r1.setName("risorsa speciale");
        data.add(r1);
        
        Risorsa r2 = new Risorsa();
        r2.setId("tre");
        r2.setName("tua madre a pecora");
        data.add(r2);      
    }
    
    @GET
    @Path("{rid}")
    public Risorsa getRisorsa(@PathParam("rid") String id){
        for(Risorsa r: this.data){
            System.out.print("Argument="+id+" -- Checked Id="+r.getId());
            if(r.getId().equals(id) ){
                System.out.print("return the result");
                 return r;
            }           
        }
        System.out.print("returning null.\nId passed:\t"+id);
        return new Risorsa();
    }

    
}
