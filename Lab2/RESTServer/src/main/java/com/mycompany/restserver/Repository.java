/*
A Repository is very important, it will contain the actual METHODS and
OPERTIONS that your REST WebService will offer to the outside.
 */
package com.mycompany.restserver;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//This will be the path used to LOCATE the resources by the CLIENT, very important!
@Path("GenericResources")
@Produces({MediaType.APPLICATION_XML})
public class Repository {
    private List<GenericResource> data = new ArrayList<>();
    
    //Here we will build all the data contained and offered by our WS
    public Repository(){
        GenericResource r1 = new GenericResource();
        GenericResource r2 = new GenericResource();
        GenericResource r3 = new GenericResource();
        
        r1.setId("uno");
        r2.setId("2");
        r3.setId("tre");
        
        r1.setName("[NAME]Er go de Totti");
        r2.setName("[NAME]Er go de Florenzi");
        r3.setName("[NAME]Er go de Pjanic");
               
        data.add(r1);
        data.add(r2);
        data.add(r3);
    }
    
    
    //Now we will add the various operations, since REST is a webInterface
    //the various operations will be based on HTTP request, so GET, POST, ... TODO
    
    @GET
    @Path("{rid}")
    public GenericResource getGenericResource(@PathParam("rid") String res_id){
        for(GenericResource tmp : data){
            if(tmp.getId().equals(res_id)) return tmp;
        }
        return new GenericResource();
    }
    
    @POST
    @Path("")
    public Response addGenericResource(GenericResource r){
        for(GenericResource tmp : data){
            if(tmp.getId().equals(r.getId())) return Response.status(Response.Status.CONFLICT).build();
        }
        this.data.add(r);
        return Response.ok().build();
    }
    
    @PUT
    @Path("{rid}")
    public Response updateGenericResource(@PathParam("rid")String id, GenericResource newRes){
        for (GenericResource tmp : data){
            if(tmp.getId().equals(id)){
                if(tmp.getName().equals(newRes.getName())) return Response.status(Response.Status.NOT_MODIFIED).build();
                tmp.setId(newRes.getId());
                tmp.setName(newRes.getName());
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("{rid}")
    public Response deleteGenericResource(@PathParam("rid") String res_id){
        for (GenericResource tmp : data) {
            if(tmp.getId().equals(res_id)){
                this.data.remove(tmp);
                return Response.ok().build();
            } 
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
