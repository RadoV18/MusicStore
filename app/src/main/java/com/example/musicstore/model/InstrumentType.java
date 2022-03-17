package com.example.musicstore.model;

public class InstrumentType {
    private int id;
    private String typeName;

    public InstrumentType(String typeName) {
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE InstrumentType (\n" +
                "    id integer NOT NULL CONSTRAINT InstrumentType_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    typeName varchar(50) NOT NULL\n" +
                ");";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS InstrumentType";
    }
}
