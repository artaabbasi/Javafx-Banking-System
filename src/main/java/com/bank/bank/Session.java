package com.bank.bank;

import java.io.Serializable;

public class Session implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public User user;
    public Bank bank;
    public Service service;
    public UserBank userBank;
    public Session (User user, Bank bank){
        this.user = user;
        this.bank = bank;
    }
    public Session (User user){
        this.user = user;
    }
    public Session (){
    }
    public void save(){
        SessionManager sm = new SessionManager();
        sm.write_object(this);
    }
}
