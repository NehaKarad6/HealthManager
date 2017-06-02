package com.example.neha.healthmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DVital_Details extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String date;
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
        setContentView(R.layout.activity_dvital__details);
        Bundle bundle = getIntent().getExtras();
        date = bundle.getString("date");
        PMobile=bundle.getString("PMobile");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Patients").child(PMobile).child("Vitals").child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Vitals v= dataSnapshot.getValue(Vitals.class);
                bp = v.bp;
                pulse=v.pulse;
                sugar=v.sugar;
                temp=v.temp;
                weight=v.weight;
                complaints=v.complaints;

                EditText edtbp = (EditText)findViewById(R.id.EdtBP);
                edtbp.setText(bp);

                EditText edtpulse = (EditText)findViewById(R.id.EdtPulseRate);
                edtpulse.setText(pulse);

                EditText edtsugar = (EditText)findViewById(R.id.EdtSugar);
                edtsugar.setText(sugar);

                EditText edttemp = (EditText)findViewById(R.id.EdtTemperature);
                edttemp.setText(temp);

                EditText edtweight = (EditText)findViewById(R.id.EdtWeight);
                edtweight.setText(weight);

                EditText edtcomplains = (EditText)findViewById(R.id.EdtComplaints);
                edtcomplains.setText(complaints);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



    }
}
