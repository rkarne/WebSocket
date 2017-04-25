/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;


/**
 *
 * @author c0689497
 */

@ServerEndpoint("/sample")
@ApplicationScoped
public class Socket {
    
    private List<Session> people = new ArrayList<>();
    private List<JsonObject> messages = new ArrayList<>();
    
  //  @OnOpen
  //  public void connected (Session session) throws IOException {
      //  JsonArrayBuilder arr = Json.createArrayBuilder();
       // for(JsonObject m : messages) arr.add(m);
       // String output = arr.build().toString();
      //  Basic basic = session.getBasicRemote();
        
   // }
    
@OnMessage
    public void receiveMessge(String message, Session session) throws IOException{
        /* step 2 
        //Add the person to the lis of people
        if(!people.contains(session)){
            people.add(session);
        }
        //converts the list into json object
        //save the message to the list of messages
        JsonObject json = Json.createReader(new StringReader(message)).readObject();
        messages.add(json);
        
        //Boradcast the message out to all connected peopl
        for(Session person : people){
            Basic basic = person.getBasicRemote();
            String output = Json.createArrayBuilder().add(json).build().toString();
           // basic.sendText(new Date() + ": "+message);
           basic.sendText(output);
        }  */
        /* step 1*/
        System.out.println(message);
        Basic basic = session.getBasicRemote();
        basic.sendText(new Date() + ": "+message);
    }
}

