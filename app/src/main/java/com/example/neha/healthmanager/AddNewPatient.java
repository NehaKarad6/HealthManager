package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewPatient extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String mobile;
    private String name;
    private String DMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);
        Bundle bundle = getIntent().getExtras();
        DMobile = bundle.getString("mobile");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.btnDocPatientreg).setOnClickListener(this::click);
    }

    private void click(View view) {
        name=((EditText)findViewById(R.id.PatientName)).getText().toString();
        mobile=((EditText)findViewById(R.id.Patientnumber)).getText().toString();

        Newpatient n =new Newpatient(name,mobile);
        mDatabase.child("Doctors").child(DMobile).child("Patients").child(mobile).setValue(n);
        Intent intent=new Intent(AddNewPatient.this,Doctor_dash.class);
        startActivity(intent);
    }
}
