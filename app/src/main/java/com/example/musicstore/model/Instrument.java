package com.example.musicstore.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Instrument {
    private long id;
    private long instrumentTypeId;
    private String name;
    private String description;
    private double price;
    private long brandId;
    private byte[] image;
    private boolean isAvailable;
    private int stock;

    public Instrument(long instrumentTypeId, String name, String description,
                      double price, long brandId, byte[] image, boolean isAvailable,
                      int stock) {
        this.instrumentTypeId = instrumentTypeId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brandId = brandId;
        this.image = image;
        this.isAvailable = isAvailable;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInstrumentTypeId() {
        return instrumentTypeId;
    }

    public void setInstrumentTypeId(long instrumentTypeId) {
        this.instrumentTypeId = instrumentTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long insert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("InstrumentType_id", instrumentTypeId);
        values.put("name", name);
        values.put("description", description);
        values.put("price", price);
        values.put("Brand_id", brandId);
        values.put("image", image);
        values.put("isAvailable", isAvailable);
        values.put("stock", stock);
        long rowId = db.insert("Instrument", null, values);
        setId(rowId);
        return rowId;
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE Instrument (\n" +
                "    id integer NOT NULL CONSTRAINT Instrument_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    InstrumentType_id integer NOT NULL,\n" +
                "    name varchar(40) NOT NULL,\n" +
                "    description varchar(255) NOT NULL,\n" +
                "    price decimal(7,2) NOT NULL,\n" +
                "    Brand_id integer NOT NULL,\n" +
                "    image blob NOT NULL,\n" +
                "    isAvailable boolean NOT NULL,\n" +
                "    stock integer NOT NULL,\n" +
                "    CONSTRAINT Instrument_InstrumentType FOREIGN KEY (InstrumentType_id)\n" +
                "    REFERENCES InstrumentType (id),\n" +
                "    CONSTRAINT Instrument_Brand FOREIGN KEY (Brand_id)\n" +
                "    REFERENCES Brand (id)\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS Instrument";
    }
}
