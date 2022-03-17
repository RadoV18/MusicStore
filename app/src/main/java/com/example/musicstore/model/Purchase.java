package com.example.musicstore.model;

import java.util.Date;

public class Purchase {
    private int id;
    private int customerId;
    private Date purchaseDate;

    public Purchase(int customerId, Date purchaseDate) {
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE Purchase (\n" +
                "    id integer NOT NULL CONSTRAINT Purchase_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    Customer_id integer NOT NULL,\n" +
                "    purchaseDate datetime NOT NULL,\n" +
                "    CONSTRAINT Purchase_Client FOREIGN KEY (Customer_id)\n" +
                "    REFERENCES Customer (id)\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS Purchase";
    }
}
