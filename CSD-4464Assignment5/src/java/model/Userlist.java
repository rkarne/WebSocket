/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0689497
 */
@Named
@ApplicationScoped
public class Userlist {
    
    private List<Userdetails> users;
    private static Userlist instance;
      public Userlist() {
        getUsersFromDB();
        instance = this;
    }
      
      private void getUsersFromDB() {
        try (Connection conn = DBconnection.getConnection()) {
            users = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                Userdetails u = new Userdetails(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("passhash")
                );
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Userdetails.class.getName()).log(Level.SEVERE, null, ex);
            users = new ArrayList<>();
        }
        }
      
       public List<Userdetails> getUsers() {
        return users;
    }
       
        public static Userlist getInstance() {
        return instance;
    }
        
       public String getUsernameById(int id) {
        for (Userdetails u : users) {
            if (u.getId() == id) {
                return u.getUsername();
            }
        }
        return null;
    }
        public int getUserIdByUsername(String username) {
        for (Userdetails u : users) {
            if (u.getUsername().equals(username)) {
                return u.getId();
            }
        }
        return -1;
    }
        
        public void addUser(String username, String password) {
        try  {
            Connection conn = DBconnection.getConnection();
            String sql = "INSERT INTO users (username, passhash) VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Userlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        getUsersFromDB();
    }
}
      
