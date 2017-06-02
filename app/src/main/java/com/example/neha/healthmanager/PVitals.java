package com.example.neha.healthmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PVitals extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String bp;
    private String pulse;
    private String sugar;
    private String temp;
    private String weight;
    private String complaints;
    private String PMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvitals);
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.btnSubmitVitals).setOnClickListener(this::click);
    }

    private void click(View view) {

        bp=((EditText) findViewById(R.id.EdtBP)).getText().toString();
        pulse=((EditText) findViewById(R.id.EdtPulseRate)).getText().toString();
        sugar=((EditText) findViewById(R.id.EdtSugar)).getText().toString();
        temp=((EditText) findViewById(R.id.EdtTemperature)).getText().toString();
        weight=((EditText) findViewById(R.id.EdtTemperature)).getText().toString();
        complaints=((EditText) findViewById(R.id.EdtComplaints)).getText().toString();

        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int minutes = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);
        String time = hour+":"+minutes+":"+seconds;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        String date =day+"-"+month+"-"+year+"-TIME-"+hour+"-"+minutes+"-"+seconds;


        Vitals v=new Vitals(date,time,bp,pulse,sugar,temp,weight,complaints);

        mDatabase.child("Patients").child(PMobile).child("Vitals").child(date).setValue(v);
        Toast.makeText(PVitals.this, "Data Saved!",
                Toast.LENGTH_LONG).show();
    }
}

