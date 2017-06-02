package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PLogin extends AppCompatActivity {
    private String Mobile;
    private String Password;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plogin);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.btnPatientLogin1).setOnClickListener(PLogin.this::click);

    }

    private void click(View view) {


        Mobile = ((EditText) findViewById(R.id.PMobile)).getText().toString();
        Password = ((EditText) findViewById(R.id.PatientPassword)).getText().toString();

        mDatabase.child("Patients").child(Mobile).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Patient p= dataSnapshot.getValue(Patient.class);

                if (Password.equals(p.password)) {
                    Next();
                } else {
                    Toast.makeText(PLogin.this,  "Incorrect Password",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }

    private void Next()
    {
        Intent intent = new Intent(this, Patient_dash.class);
        intent.putExtra("mobile", Mobile);
        startActivity(intent);
    }
}
