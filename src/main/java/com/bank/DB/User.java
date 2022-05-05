package com.bank.DB;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public String username;
    public String password;
    public String IDcard;

    public User(String username, String password, String IDcard){
        this.password = password;
        this.username = username;
        this.IDcard = IDcard;
    }

    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.users.add(this);
        fm.write_object(db);
    }
    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        for(User user:db.users){
            if(user.username.equals(this.username)){
                db.users.remove(user);
                db.users.add(this);
            }
        }
        fm.write_object(db);
    }
}
