package com.example.musicstore.model;

import android.content.ContentValues;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InstrumentType {
    private long id;
    private String typeName;

    public InstrumentType(String typeName) {
        this.typeName = typeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public long insert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("typeName", typeName);
        long rowId = db.insert("InstrumentType", null, values);
        setId(rowId);
        return rowId;
    }

    public boolean findByName(SQLiteDatabase db) {
        String selection = "typeName = ?";
        String[] selectionArgs = { typeName };
        Cursor cursor = db.query("InstrumentType", null, selection, selectionArgs, null, null, null);
        if(cursor.moveToFirst()) {
            setId(cursor.getInt(0));
            return true;
        }
        return false;
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE InstrumentType (\n" +
                "    id integer NOT NULL CONSTRAINT InstrumentType_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    typeName varchar(50) NOT NULL\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS InstrumentType";
    }
}
