package com.example.waiterapp;

import com.example.common.MenuItem;

import java.util.List;

public class OrderWrapper {
    private List<MenuItem> items;
    private String status;

    public OrderWrapper(List<MenuItem> items, String status) {
        this.items = items;
        this.status = status;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public void advanceStatus() {
        switch (status) {
            case "התקבלה":
                status = "בהכנה";
                break;
            case "בהכנה":
                status = "מוכנה";
                break;
            case "מוכנה":
                status = "הוגשה";
                break;
        }
    }
}
