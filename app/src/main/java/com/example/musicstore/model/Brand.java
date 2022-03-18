package com.example.musicstore.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Brand {
    private long id;
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long insert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("name", this.name);
        long rowId = db.insert("Brand", null, values);
        setId(rowId);
        return rowId;
    }

    public boolean findByName(SQLiteDatabase db) {
        String selection = "name = ?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query("Brand", null, selection, selectionArgs, null, null, null);
        if(cursor.moveToFirst()) {
            setId(cursor.getInt(0));
        }
        return false;
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE Brand (\n" +
                "    id integer NOT NULL CONSTRAINT Brand_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    name varchar(40) NOT NULL\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS Brand";
    }
}
