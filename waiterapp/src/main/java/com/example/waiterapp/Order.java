package com.example.waiterapp;

public class Order {
    private String table;
    private String items;
    private String notes;

    public Order(String table, String items, String notes) {
        this.table = table;
        this.items = items;
        this.notes = notes;
    }

    public String getTable() { return table; }
    public String getItems() { return items; }
    public String getNotes() { return notes; }
}
