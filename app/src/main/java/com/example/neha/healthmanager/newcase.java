package com.example.neha.healthmanager;


public class newcase {


    public String date;
    public String chief;
    public String other;
    public String invest;
    public String med;
    public String instruct;

    public newcase() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public newcase(String date,String chief,String other,String invest,String med,String instruct){
        this.date=date;
        this.chief=chief;
        this.other = other;
        this.invest = invest;
        this.med=med;
        this.instruct=instruct;

    }
}
