package com.bank.bank;

import java.io.Serializable;

public class Bank implements Serializable {
    private static final long serialVersionUID = 1234567L;
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
