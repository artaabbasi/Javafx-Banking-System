package com.bank.bank;

public class UserBank {
    public User user;
    public Bank bank;
    public boolean is_admin;

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
        db.user_banks.add(this);
        fm.write_object(db);
    }
}
