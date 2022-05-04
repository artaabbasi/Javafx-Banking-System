package com.bank.bank;

public class User {
    public String username;
    public String password;
    public String IDcard;
    public boolean is_approved;
    public User(String username, String password, String IDcard){
        this.password = password;
        this.username = username;
        this.IDcard = IDcard;
    }

    public void save(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.users.add(this);
        fm.write_object(db);
    }
}
