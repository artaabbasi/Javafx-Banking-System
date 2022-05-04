package com.bank.bank;

public class Bank {
    public String name;

    public Bank (String name){
        this.name = name;
    }

    public void save(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.banks.add(this);
        fm.write_object(db);
    }
}
