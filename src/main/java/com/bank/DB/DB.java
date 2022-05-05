package com.bank.DB;

import java.io.Serializable;
import java.util.ArrayList;

public class DB implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<Bank> banks = new ArrayList<Bank>();
    public ArrayList<UserBank> user_banks = new ArrayList<UserBank>();
    public ArrayList<Service> services = new ArrayList<Service>();
    public ArrayList<Bill> bills = new ArrayList<Bill>();

    public DB(){

    }
    public DB(ArrayList<User> users, ArrayList<Bank> banks, ArrayList<UserBank> user_banks, ArrayList<Service> services){
        this.banks = banks;
        this.users = users;
        this.user_banks = user_banks;
        this.services = services;
    }
}
