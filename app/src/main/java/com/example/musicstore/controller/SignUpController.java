package com.example.musicstore.controller;

import android.provider.ContactsContract;

import com.example.musicstore.model.Customer;
import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.User;

public class SignUpController {
    private User newUser;
    private Customer newCustomer;
    private DatabaseHelper dbHelper;

    public SignUpController(String name, String lastName, String email, String username,
                            String password, DatabaseHelper dbHelper) {
        newUser = new User(username, password);
        this.dbHelper = dbHelper;
    }

    public void signUp() {
        newUser.insert(dbHelper.getWritableDatabase());
        newCustomer = new Customer(newUser.getId());
        newCustomer.insert(dbHelper.getWritableDatabase());
    }
}
