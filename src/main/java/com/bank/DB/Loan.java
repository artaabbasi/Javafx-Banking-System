package com.bank.DB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Loan   implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public UserBank userBank;
    public User user;
    public Bank bank;
    public int amount;
    public boolean approved = false;

    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        ArrayList<Loan> loans = new ArrayList<Loan>();
        loans.add(this);
        db.loans.remove(loans);
        db.loans.add(this);

        fm.write_object(db);
    }
    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.loans.add(this);
        fm.write_object(db);
    }
}
