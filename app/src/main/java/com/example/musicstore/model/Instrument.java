package com.example.musicstore.model;

public class Instrument {
    private int id;
    private int instrumentTypeId;
    private String name;
    private String description;
    private double price;
    private int brandId;
    private Byte[] image;
    private boolean isAvailable;
    private int stock;
    private int administratorId;

    public Instrument(int instrumentTypeId, String name, String description,
                      double price, int brandId, Byte[] image, boolean isAvailable,
                      int stock, int administratorId) {
        this.instrumentTypeId = instrumentTypeId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brandId = brandId;
        this.image = image;
        this.isAvailable = isAvailable;
        this.stock = stock;
        this.administratorId = administratorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInstrumentTypeId() {
        return instrumentTypeId;
    }

    public void setInstrumentTypeId(int instrumentTypeId) {
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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
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

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
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
                "    Administrator_id integer NOT NULL,\n" +
                "    CONSTRAINT Instrument_Administrator FOREIGN KEY (Administrator_id)\n" +
                "    REFERENCES Administrator (id),\n" +
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
