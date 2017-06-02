package com.example.neha.healthmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DNewcase extends AppCompatActivity {
    private String PMobile;
    private DatabaseReference mDatabase;
    private String chief;
    private String other;
    private String med;
    private String invest;
    private String instruct;
    private String type;
    private Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnewcase);
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.btnSubmitcase).setOnClickListener(this::click);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }


    //process the data further
    public void addListenerOnSpinnerItemSelection() {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);


    }




    private void click(View view) {

        chief = ((EditText) findViewById(R.id.EdtChiefComplaint)).getText().toString();
        other = ((EditText) findViewById(R.id.EdtOtherComplaints)).getText().toString();
        invest = ((EditText) findViewById(R.id.EdtInvestigations)).getText().toString();
        med= ((EditText) findViewById(R.id.EdtMedication)).getText().toString();
        instruct= ((EditText) findViewById(R.id.EdtInstructions)).getText().toString();
        type=String.valueOf(spinner2.getSelectedItem());
        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int minutes = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);
        //String time = hour+":"+minutes+":"+seconds;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        String date = day+"-"+month+"-"+year+"-TIME-"+hour+"-"+minutes+"-"+seconds;

        if(type.equalsIgnoreCase("major")) {
            newcase n = new newcase(date,chief, other, invest, med, instruct);

            //mDatabase.child("Patients").child(PMobile).child("History").child(date).setValue(System.currentTimeMillis());
            mDatabase.child("Patients").child(PMobile).child("History").child("Major").child(date).setValue(n);
            Toast.makeText(DNewcase.this, "Data Saved!",
                    Toast.LENGTH_LONG).show();
        }

        if(type.equalsIgnoreCase("minor")) {
            newcase n = new newcase(date,chief, other, invest, med, instruct);

            //mDatabase.child("Patients").child(PMobile).child("History").child(date).setValue(System.currentTimeMillis());
            mDatabase.child("Patients").child(PMobile).child("History").child("Minor").child(date).setValue(n);
            Toast.makeText(DNewcase.this, "Data Saved!",
                    Toast.LENGTH_LONG).show();
        }

    }
}