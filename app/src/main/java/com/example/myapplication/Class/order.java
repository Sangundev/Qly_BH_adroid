package com.example.myapplication.Class;

public class order {
    private String id;
    private String date;
    private String amount;

    public order(String id, String date, String amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }
}
