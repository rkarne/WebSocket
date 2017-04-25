/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Product;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0689497
 */

@Path("/product")
public class ProductREST {
    @PersistenceContext(unitName = "CSD-JPAPU")
    EntityManager em;
    List<Product> productlist;
    
    @Inject
    UserTransaction transaction;
    
    @GET
    @Produces
    public void getALL(){
        JsonArrayBuilder json = Json.createArrayBuilder();
     Query a = em.createNamedQuery("SELECT p from Product p");
     productlist = a.getResultList();
     for(Product p: productlist){
         json.add(p.toJSON());
     }
     
}
}
