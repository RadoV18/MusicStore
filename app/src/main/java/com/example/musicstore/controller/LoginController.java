package com.example.musicstore.controller;

import com.example.musicstore.model.Administrator;
import com.example.musicstore.model.Customer;
import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.User;

public class LoginController {
    private User loggedUser;

    private Administrator admin;
    private Customer customer;

    private DatabaseHelper dbHelper;

    public LoginController(User loggedUser, DatabaseHelper dbHelper) {
        this.loggedUser = loggedUser;
        this.dbHelper = dbHelper;
    }

    public String login() {
        if(loggedUser.login(dbHelper.getReadableDatabase())) {
            admin = new Administrator(loggedUser.getId());
            if(admin.findByUserId(dbHelper.getReadableDatabase())) {
                return admin.getName();
            } else {
                customer = new Customer(loggedUser.getId());
                if(customer.findByUserId(dbHelper.getReadableDatabase())) {
                    return "customer";
                }
            }
        }
        return "none";
    }
}
