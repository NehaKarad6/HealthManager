package com.example.neha.healthmanager;


public class Patient {



    public String name;
    public String email;
    public String mobile;
    public String address;
    public String dob;
    public String weight;
    public String bg;
    public String emerg_contact;
    public String relation;
    public String emerg_mob;
    public String password;

    public Patient() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Patient(String name,String email,String mobile,String address,String dob,String weight,String bg,String emerg_contact,String relation,String emerg_mob,String password) {
        this.name=name;
        this.email=email;
        this.mobile = mobile;
        this.address = address;
        this.dob=dob;
        this.weight=weight;
        this.bg=bg;
        this.emerg_contact=emerg_contact;
        this.relation=relation;
        this.emerg_mob=emerg_mob;
        this.password=password;
    }





}
