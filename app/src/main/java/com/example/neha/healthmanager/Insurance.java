package com.example.neha.healthmanager;

/**
 * Created by neha on 3/5/17.
 */

public class Insurance {

    public String cname;
    public String pno;
    public String name;
    public String plan;
    public String coverage;
    public String cov_end;
    public String cov_start;

    public Insurance() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Insurance(String cname,String pno,String name,String plan,String coverage,String cov_end,String cov_start) {


        this.cname=cname;
        this.pno=pno;
        this.name=name;
        this.plan=plan;
        this.coverage=coverage;
        this.cov_end=cov_end;
        this.cov_start=cov_start;


    }



}
