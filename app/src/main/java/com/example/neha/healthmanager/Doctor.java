package com.example.neha.healthmanager;



public class Doctor {
    public String mobile;
    public String address;
    public String dob;
    public String qualification;
    public String specialization;
    public String regno;
    public String name;
    public String email;
    public String password;

    public Doctor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Doctor(String name,String email,String mobile,String address,String dob,String qualification,String specialization,String regno,String password) {
        this.name=name;
        this.email=email;
        this.mobile = mobile;
        this.address = address;
        this.dob=dob;
        this.qualification=qualification;
        this.specialization=specialization;
        this.regno=regno;
        this.password=password;
    }

}
