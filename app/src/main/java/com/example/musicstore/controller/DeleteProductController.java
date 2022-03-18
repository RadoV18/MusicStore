package com.example.musicstore.controller;

import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.Instrument;

public class DeleteProductController {

    private String id;
    private DatabaseHelper dbHelper;

    private Instrument found;

    public DeleteProductController(String id, DatabaseHelper dbHelper) {
        this.id = id;
        this.dbHelper = dbHelper;
    }

    public boolean findProduct() {
        Instrument instrument = new Instrument(Long.parseLong(id));
        if(instrument.findById(dbHelper.getReadableDatabase())) {
            found = instrument;
            return true;
        }
        return false;
    }

    public void deleteProduct() {
        found.delete(dbHelper.getWritableDatabase());
    }

}
