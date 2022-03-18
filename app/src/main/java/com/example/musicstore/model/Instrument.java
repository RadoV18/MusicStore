package com.example.musicstore.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.musicstore.view.adapters.ProductModel;

import java.util.ArrayList;

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

    public Instrument(long id) {
        this.id = id;
    }

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

    public static ArrayList<ProductModel> selectAllAsProductModel(SQLiteDatabase db) {
        ArrayList<ProductModel> list = new ArrayList<>();
        Cursor cursor = db.query("Instrument", null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            System.out.println(cursor.getInt(0));
            long categoryId = cursor.getLong(1);
            String name = cursor.getString(2);
            String description = cursor.getString(3);
            double price = cursor.getDouble(4);
            long brandId = cursor.getLong(5);
            byte[] imgArray = cursor.getBlob(6);
            Bitmap image = BitmapFactory.decodeByteArray(imgArray, 0, imgArray.length);
            int stock = cursor.getInt(8);
            ProductModel product = new ProductModel(image, name, description, String.valueOf(price),
                    String.valueOf(stock), String.valueOf(brandId));
            list.add(product);
        }
        return list;
    }

    public void update(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("InstrumentType_id", instrumentTypeId);
        values.put("name", name);
        values.put("description", description);
        values.put("price", price);
        values.put("Brand_id", brandId);
        values.put("image", image);
        values.put("isAvailable", isAvailable);
        values.put("stock", stock);
        String[] selectionArgs = { String.valueOf(id) };
        db.update("Instrument", values, "id = ?", selectionArgs);
    }

    public boolean findById(SQLiteDatabase db) {
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = db.query("Instrument", null, selection, selectionArgs, null, null, null);
        if(cursor.moveToFirst()) {
            setInstrumentTypeId(cursor.getLong(1));
            setName(cursor.getString(2));
            setDescription(cursor.getString(3));
            setPrice(cursor.getDouble(4));
            setBrandId(cursor.getLong(5));
            setImage(cursor.getBlob(6));
            setAvailable(true);
            setStock(cursor.getInt(8));
            return true;
        }
        return false;
    }

    public void delete(SQLiteDatabase db) {
        String where = "id = ?";
        String[] args = { String.valueOf(id)};
        db.delete("Instrument", where, args);
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
