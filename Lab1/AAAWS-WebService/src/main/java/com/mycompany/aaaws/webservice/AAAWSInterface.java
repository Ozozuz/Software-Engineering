/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aaaws.webservice;

import javax.jws.WebService;

@WebService
public interface AAAWSInterface {
    public String[] getClients();
    public void addClient(String s);
}
