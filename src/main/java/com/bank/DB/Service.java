package com.bank.DB;

import java.io.Serializable;

public class Service implements Serializable {
    private static final long serialVersionUID = 1234567L;

    public Bank bank;
    public String name;
    public String first_lable;
    public String secend_lable;
    public String thired_label;
    public String description;
    public boolean has_price;
    public boolean is_cash;
    public Service(String name){
        this.name = name;
    }
    public Service(String name, String first_lable, String secend_lable,String thired_label, String description){
        this.name = name;
        this.first_lable = first_lable;
        this.secend_lable = secend_lable;
        this.thired_label = thired_label;
        this.description = description;
    }

    public Service() {

    }

    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        for(Service service:db.services){
            if(service.name.equals(this.name) && service.bank.name.equals(this.bank.name) ){
                db.services.remove(service);
                db.services.add(this);
            }
        }
        fm.write_object(db);
    }
    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.services.add(this);
        fm.write_object(db);
    }
}
