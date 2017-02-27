/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author c0689497
 */

@Named
@SessionScoped
public class Logindetails   implements Serializable{
    private String username;
    private String password;
    private boolean status;
    private Userdetails user;
    
     public Logindetails() {
        username = null;
        password = null;
        status = false;
        user = null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStatus() {
        return status;
    }

    public Userdetails getUser() {
        return user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String dologin() {
        String pass = HashCredentials.hashPassword(password);
        for (Userdetails use : Userlist.getInstance().getUsers()) {
            if (username.equals(use.getUsername())
                    && pass.equals(use.getPassword())) {
                status = true;
                user = use;
                return "index";
            }
        }
     
        user = null;
        status = false;
        return "index";
    }
   
}
