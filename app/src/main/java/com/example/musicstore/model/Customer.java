package com.example.musicstore.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Customer {
    private long id;
    private long userId;

    public Customer(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long insert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("User_id", userId);
        long rowId = db.insert("Customer", null, values);
        setId(rowId);
        return rowId;
    }

    public boolean findByUserId(SQLiteDatabase db) {
        String selection = "user_id = " + userId;
        Cursor cursor = db.query("Customer", null, selection, null, null, null, null);
        if (cursor.moveToFirst()) {
            setId(cursor.getInt(0));
            return true;
        }
        return false;
    }

    public void showAll(SQLiteDatabase db) {
        System.out.println("All customers");
        Cursor cursor = db.query("Customer", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            System.out.println(String.valueOf(cursor.getInt(0)));
        }
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE Customer (\n" +
                "    id integer NOT NULL CONSTRAINT Customer_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    User_id integer NOT NULL,\n" +
                "    CONSTRAINT Client_User FOREIGN KEY (User_id)\n" +
                "    REFERENCES User (id)\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS Customer";
    }
}
