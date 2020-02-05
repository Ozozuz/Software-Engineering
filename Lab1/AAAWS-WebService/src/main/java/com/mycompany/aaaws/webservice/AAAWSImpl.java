/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aaaws.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

//home the endpointInterface is correct, very important tho
@WebService(endpointInterface = "com.mycompany.aaaws.webservice.AAAWSInterface")
public class AAAWSImpl implements AAAWSInterface {

    //A public ArrayList of String to store all the clients
    public List<String> allClients = new ArrayList<>();
    
    
    //the allClients arraylist is used to populate an array of the same size
    //to finally return an array of string.
    @Override
    public String[] getClients() {
        String[] result = new String[this.allClients.size()];
        for(int i=0; i < this.allClients.size(); i++){
            result[i]= this.allClients.get(i);
        }
        return result;        
    }
    
    @Override
    public void addClient(String s){
        this.allClients.add(s);
    }
}
