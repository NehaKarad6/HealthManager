package com.example.neha.healthmanager;

/**
 * Created by neha on 2/6/17.
 */

public class Vitals {
    public String bp;
    public String pulse;
    public String sugar;
    public String temp;
    public String weight;
    public String complaints;
    public String date;
    public String time;

    public Vitals() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Vitals(String date,String time,String bp,String pulse,String sugar,String temp,String weight,String complaints) {
        this.date = date;
        this.time = time;
        this.bp = bp;
        this.pulse = pulse;
        this.sugar = sugar;
        this.temp = temp;
        this.weight = weight;
        this.complaints = complaints;
    }


}
