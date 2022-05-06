package com.bank.DB;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill  implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public String name;
    public int amount;
    public int ID;
    public boolean payed ;
    public UserBank userBank;
    public Bill(){
        this.payed = false;
    }
    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        ArrayList<Bill> bills = new ArrayList<Bill>();
        bills.add(this);
        db.bills.remove(bills);
        db.bills.add(this);

        fm.write_object(db);
    }
    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.bills.add(this);
        fm.write_object(db);
    }
}
