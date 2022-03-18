package com.example.musicstore.controller;

import android.graphics.Bitmap;

import com.example.musicstore.model.Brand;
import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.Instrument;
import com.example.musicstore.model.InstrumentType;

import java.io.ByteArrayOutputStream;

public class AddProductController {
    private String name;
    private String description;
    private String brand;
    private double price;
    private int stock;
    private String category;
    private Bitmap image;

    private DatabaseHelper dbHelper;

    public AddProductController(String name, String description, String brand, String price,
                                String stock, String category, Bitmap image, DatabaseHelper dbHelper) throws Exception {
        validate(name, description, brand, price, stock, category, image);
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = Double.parseDouble(price);
        this.stock = Integer.parseInt(stock);
        this.category = category;
        this.image = image;
        this.dbHelper = dbHelper;
    }

    public void insertData() {
        Brand searchBrand = new Brand(this.brand);
        if(!searchBrand.findByName(dbHelper.getReadableDatabase())) {
            // insert if not found
            searchBrand.insert(dbHelper.getWritableDatabase());
        }
        long brandId = searchBrand.getId();
        System.out.println(brandId);

        InstrumentType searchInstrumentType = new InstrumentType(this.category);
        if(!searchInstrumentType.findByName(dbHelper.getReadableDatabase())) {
            // insert if not found
            searchInstrumentType.insert(dbHelper.getWritableDatabase());
        }
        long categoryId = searchInstrumentType.getId();
        // Bitmap to Byte Array
        byte[] imgArray = asByteArray(this.image);
        // insert new Instrument
        Instrument newInstrument = new Instrument(categoryId, name, description, price, brandId,
                imgArray, true, stock);
        System.out.println(newInstrument.insert(dbHelper.getWritableDatabase()));

    }

    public static byte[] asByteArray(Bitmap image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public void validate(String name, String description, String brand, String price,
                          String stock, String category, Bitmap image) throws Exception {
        if(name == null || name.equals("")) {
            throw new Exception("Error. Nombre inválido.");
        }
        if(description == null || description.equals("")) {
            throw new Exception("Error. Descripción inválida.");
        }
        if(brand == null || brand.equals("")) {
            throw new Exception("Error. Marca inválida.");
        }
        if(price == null || price.equals("")) {
            throw new Exception("Error. Precio inválido.");
        } else {
            try {
                Double.parseDouble(price);
            } catch (Exception e) {
                throw new Exception("Error. El precio debe ser un número");
            }
        }
        if(stock == null || stock.equals("")) {
            throw new Exception("Error. Stock inválido.");
        } else {
            try {
                Integer.parseInt(stock);
            } catch (Exception e) {
                throw new Exception("Error. El stock debe ser un número.");
            }
        }
        if(category == null || category.equals("")) {
            throw new Exception("Error. Categoría inválida");
        }
        if(image == null) {
            throw new Exception("Error. Debe seleccionar una imagen.");
        }
    }
}
