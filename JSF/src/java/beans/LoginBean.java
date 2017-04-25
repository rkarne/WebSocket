/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author c0689497
 */

@Named//correct one from java application 7 if u want to use java 6 then use facelets.*
@SessionScoped


public class LoginBean implements Serializable {
    
    private  String username = "";
    private  String password = "";
    private boolean loggedIn = false;

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String  dologin(){
        //instead use "roja".equals(username) to void the null pointer exception
        if(username.equals("roja") && password.equals("password")){
            //return username + " , " + password; 
            return "newjsf";
        }
        else{
            return "Please try again";
        }
 
    }
    
    
}
