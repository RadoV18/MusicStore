package com.example.musicstore.model;

public class Brand {
    private int id;
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE Brand (\n" +
                "    id integer NOT NULL CONSTRAINT Brand_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    name varchar(40) NOT NULL\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS Brand";
    }
}
