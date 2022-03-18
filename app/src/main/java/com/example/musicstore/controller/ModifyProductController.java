package com.example.musicstore.controller;

import android.graphics.Bitmap;

import com.example.musicstore.model.Brand;
import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.Instrument;

import java.io.ByteArrayOutputStream;
import java.util.function.DoubleBinaryOperator;

public class ModifyProductController {
    private String id;
    private String name;
    private String description;
    private String brand;
    private double price;
    private int stock;
    private long category;
    private Bitmap image;
    private DatabaseHelper dbHelper;

    public ModifyProductController(String id, DatabaseHelper dbHelper) {
        this.id = id;
        this.dbHelper = dbHelper;
    }

    public ModifyProductController(String id, String name, String description, String brand,
        double price, int stock, long category, Bitmap image, DatabaseHelper dbHelper) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.image = image;
        this.dbHelper = dbHelper;
    }

    public void updateProduct() {
        Brand searchBrand = new Brand(this.brand);
        if(!searchBrand.findByName(dbHelper.getReadableDatabase())) {
            searchBrand.insert(dbHelper.getWritableDatabase());
        }
        Instrument instrument = new Instrument(category, name, description, price, searchBrand.getId(),
                asByteArray(image), true, stock);
        instrument.setId(Long.parseLong(id));
        instrument.update(dbHelper.getWritableDatabase());
    }

    public Instrument findProductById() {
        Instrument found = new Instrument(Long.parseLong(id));
        if(found.findById(dbHelper.getReadableDatabase())) {
            return found;
        }
        return null;
    }

    public static byte[] asByteArray(Bitmap image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

}
