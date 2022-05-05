package com.bank.DB;

import java.io.Serializable;

public class Bill  implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public String name;
    public int amount;
    public int ID;
    public boolean payed = false;
    public UserBank userBank;
    public Bill(){
    }
    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        for(Bill bill:db.bills){
            if(bill.name.equals(this.name) && bill.ID == this.ID){
                db.bills.remove(bill);
                db.bills.add(this);
            }
        }
        fm.write_object(db);
    }
    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.bills.add(this);
        fm.write_object(db);
    }
}
