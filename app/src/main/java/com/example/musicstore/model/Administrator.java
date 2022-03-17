package com.example.musicstore.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Administrator {
    private long id;
    private long userId;
    private String name;

    public Administrator(long userId, String name) {
        this.userId = userId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long insert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("User_id", userId);
        values.put("name", name);
        long rowId = db.insert("Administrator", null, values);
        setId(rowId);
        return rowId;
    }

    public boolean findByUserId(SQLiteDatabase db) {
        String selection = "user_id = " + userId;
        Cursor cursor = db.query("Administrator", null, selection, null, null, null, null);
        if (cursor.moveToFirst()) {
            setId(cursor.getInt(0));
            setName(cursor.getString(2));
            return true;
        }
        return false;
    }

    public void showAll(SQLiteDatabase db) {
        System.out.println("Show all admins");
        Cursor cursor = db.query("Administrator", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            System.out.println(cursor.getInt(0));
        }
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE Administrator (\n" +
                "    id integer NOT NULL CONSTRAINT Administrator_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    User_id integer NOT NULL,\n" +
                "    name varchar(50) NOT NULL,\n" +
                "    CONSTRAINT Administrator_User FOREIGN KEY (User_id)\n" +
                "    REFERENCES User (id)\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS Administrator";
    }
}
