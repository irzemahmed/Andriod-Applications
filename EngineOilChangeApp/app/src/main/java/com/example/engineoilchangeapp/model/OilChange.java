package com.example.engineoilchangeapp.model;

public class OilChange {
    String mileage, date;

    public OilChange(String mileage, String date) {
        this.mileage = mileage;
        this.date = date;
    }

    public OilChange() {
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
