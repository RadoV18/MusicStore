package com.example.musicstore.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User {
    private long id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long insert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long rowId = db.insert("User", null, values);
        setId(rowId);
        return rowId;
    }

    public boolean login(SQLiteDatabase db) {
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("User", null, selection, selectionArgs, null, null, null);
        if(cursor.moveToFirst()) {
            setId(cursor.getInt(0));
            return true;
        }
        return false;
    }

    public void showAll(SQLiteDatabase db) {
        System.out.println("All users");
        Cursor cursor = db.query("User", null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            System.out.println(String.valueOf(cursor.getInt(0)));
        }
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE User (\n" +
                "    id integer NOT NULL CONSTRAINT User_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    username varchar(20) NOT NULL,\n" +
                "    password varchar(40) NOT NULL\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS User";
    }
}
