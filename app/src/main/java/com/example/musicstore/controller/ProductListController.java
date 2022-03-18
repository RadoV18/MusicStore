package com.example.musicstore.controller;

import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.Instrument;
import com.example.musicstore.model.InstrumentType;
import com.example.musicstore.view.adapters.ProductModel;

import java.util.ArrayList;

public class ProductListController {
    private DatabaseHelper dbHelper;

    public ProductListController(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<ProductModel> getProductList() {
        ArrayList<ProductModel> list = Instrument.selectAllAsProductModel(dbHelper.getReadableDatabase());
        System.out.println(list.size());
        for(int i = 0; i < list.size(); i++) {
            ProductModel product = list.get(i);
            // set category
            InstrumentType type = new InstrumentType(Long.parseLong(product.getCategory()));
            type.findById(dbHelper.getReadableDatabase());
            product.setCategory(type.getTypeName());
        }
        return list;
    }
}
