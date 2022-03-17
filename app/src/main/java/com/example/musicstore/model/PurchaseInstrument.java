package com.example.musicstore.model;

public class PurchaseInstrument {
    private int purchaseId;
    private int instrumentId;
    private int quantity;

    public PurchaseInstrument(int purchaseId, int instrumentId, int quantity) {
        this.purchaseId = purchaseId;
        this.instrumentId = instrumentId;
        this.quantity = quantity;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static String getCreateQueryString() {
        return "CREATE TABLE Purchase_Instrument (\n" +
                "    Purchase_id integer NOT NULL,\n" +
                "    Instrument_id integer NOT NULL CONSTRAINT Purchase_Instrument_pk PRIMARY KEY,\n" +
                "    quantity integer NOT NULL,\n" +
                "    CONSTRAINT Purchase_Instrument_Instrument FOREIGN KEY (Instrument_id)\n" +
                "    REFERENCES Instrument (id),\n" +
                "    CONSTRAINT Purchase_Instrument_Purchase FOREIGN KEY (Purchase_id)\n" +
                "    REFERENCES Purchase (id)\n" +
                ");\n";
    }

    public static String getDropQueryString() {
        return "DROP TABLE IF EXISTS Purchase_Instrument";
    }
}
