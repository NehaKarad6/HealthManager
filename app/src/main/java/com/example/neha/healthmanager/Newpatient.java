package com.example.neha.healthmanager;


public class Newpatient {

    public String mobile;
    public String name;
    public String Dmobile;
    public Newpatient() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Newpatient(String name, String mobile) {
        this.name=name;
        this.mobile = mobile;

    }

    public Newpatient(String mobile) {
        this.mobile = mobile;

    }
}
