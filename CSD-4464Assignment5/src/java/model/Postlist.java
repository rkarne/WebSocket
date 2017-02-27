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
public  class Postlist {
    private List<Postthread> posts;
    private Postthread currentPost;
    
      public Postlist() {
        currentPost = new Postthread(-1, -1, "", null, "");
        getPostsFromDB();
    }
      
      private void getPostsFromDB(){
           try (Connection conn = DBconnection.getConnection()) {
            posts = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
            while (rs.next()) {
                Postthread p = new Postthread(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getTimestamp("created_time"),
                        rs.getString("contents")
                );
                posts.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Postlist.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets Post List as Empty
            posts = new ArrayList<>();
        }

      }
       public List<Postthread> getPosts() {
        return posts;
    }        
          
        public Postthread getCurrentPost() {
        return currentPost;
    }
        
          public Postthread getPostById(int id) {
        for (Postthread p : posts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
          
        public Postthread getPostByTitle(String title) {
        for (Postthread p : posts) {
            if (p.getTitle().equals(title)) {
                return p;
            }
        }
        return null;
    }
        
       public String view(Postthread post) {
        currentPost = post;
        return "display";
    }
       
        public String addPost() {
        currentPost = new Postthread(-1, -1, "", null, "");
        return "edit";
    }
       public String editPost() {
        return "edit";
    }
         public String cancelPost() {
        // currentPost can be corrupted -- reset it based on the DB
        int id = currentPost.getId();
        getPostsFromDB();
        currentPost = getPostById(id);
        return "display";
    }
          public String savePost(Userdetails user) {
        try (Connection conn = DBconnection.getConnection()) {
          
            if (currentPost.getId() >= 0) {
                String sql = "UPDATE posts SET title = ?, contents = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, currentPost.getTitle());
                pstmt.setString(2, currentPost.getContents());
                pstmt.setInt(3, currentPost.getId());
                pstmt.executeUpdate();
            } else {
                String sql = "INSERT INTO posts (user_id, title, created_time, contents) VALUES (?,?,NOW(),?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, user.getId());
                pstmt.setString(2, currentPost.getTitle());
                pstmt.setString(3, currentPost.getContents());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Postlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        getPostsFromDB();
        // Update the currentPost so that its details appear after navigation
        currentPost = getPostByTitle(currentPost.getTitle());
        return "display";
    }
}
