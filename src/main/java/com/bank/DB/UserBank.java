package com.bank.DB;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.Serializable;

public class UserBank implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public User user;
    public Bank bank;
    public boolean is_admin;
    public String shaba_code;
    public String transfer_code;
    public boolean is_approved;
    public int balance;

    public UserBank() {

    }

    public Bank getBank() {
        return bank;
    }

    public User getUser() {
        return user;
    }

    public UserBank(User user, Bank bank, boolean is_admin){
        this.bank = bank;
        this.user = user;
        this.is_admin = is_admin;
    }

    public UserBank(User user, Bank bank){
        this.bank = bank;
        this.user = user;
        this.is_admin = false;
    }

    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        for(UserBank userBank:db.user_banks){
            if(userBank.user.username.equals(this.user.username) && userBank.bank.name.equals(this.bank.name) ){
                db.user_banks.remove(userBank);
                db.user_banks.add(this);
            }
        }
        fm.write_object(db);
    }
    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.user_banks.add(this);
        fm.write_object(db);
    }
}
