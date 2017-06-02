package com.example.neha.healthmanager;


public class Medication {
    public String med;
    public String dosage;
    public String sideeff;
    public String reason;
    public String start;
    public String stop;
    public String date;
    public Medication() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Medication(String date,String med,String dosage,String sideeff,String reason,String start,String stop) {
        this.date=date;
        this.med=med;
        this.dosage=dosage;
        this.sideeff = sideeff;
        this.reason = reason;
        this.start=start;
        this.stop=stop;

    }



}
