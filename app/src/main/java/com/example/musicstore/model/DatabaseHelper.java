package com.example.musicstore.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance = null;

    private static final String DATABASE_NAME = "musicstore";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        db.execSQL(Administrator.getCreateQueryString());
        db.execSQL(Brand.getCreateQueryString());
        db.execSQL(Customer.getCreateQueryString());
        db.execSQL(Instrument.getCreateQueryString());
        db.execSQL(InstrumentType.getCreateQueryString());
        db.execSQL(Purchase.getCreateQueryString());
        db.execSQL(PurchaseInstrument.getCreateQueryString());
        db.execSQL(User.getCreateQueryString());

        fillUser();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        db.execSQL(Administrator.getDropQueryString());
        db.execSQL(Brand.getDropQueryString());
        db.execSQL(Customer.getDropQueryString());
        db.execSQL(Instrument.getDropQueryString());
        db.execSQL(InstrumentType.getDropQueryString());
        db.execSQL(Purchase.getDropQueryString());
        db.execSQL(PurchaseInstrument.getDropQueryString());
        db.execSQL(User.getDropQueryString());
        onCreate(sqLiteDatabase);
    }

    public void fillUser() {
        User u1 = new User("admin", "password");
        u1.insert(db);
        Administrator admin1 = new Administrator(u1.getId(), "Test admin");
        admin1.insert(db);

        User u2 = new User("user", "password");
        u2.insert(db);
        Customer c1 = new Customer(u2.getId());
        c1.insert(db);
    }
}
