/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author c0689497
 */

@Entity

public class Product implements  Serializable {
    @Id
    
    private int productid;
    private String namme;
    private String description;
    private int quantity;

    public int getProductid() {
        return productid;
    }

    public String getNamme() {
        return namme;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public void setNamme(String namme) {
        this.namme = namme;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
       public JsonObject toJSON() {
        
        return Json.createObjectBuilder()
                .add("productid", productid)
                .add("namme",  namme)
                .add("description", description)
                .add("quantity", quantity)
                .build();
    }
    
}
