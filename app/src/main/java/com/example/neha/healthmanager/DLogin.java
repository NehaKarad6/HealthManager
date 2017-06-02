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

public class DLogin extends AppCompatActivity {
    private String Mobile;
    private String Password;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlogin);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.btnDoctorLogin1).setOnClickListener(DLogin.this::click);
        findViewById(R.id.btnDocReg).setOnClickListener(DLogin.this::click);
    }

    private void click(View view) {

        if(view.getId()== R.id.btnDoctorLogin1) {
            Mobile=((EditText)findViewById(R.id.DoctorMob)).getText().toString();
            Password=((EditText)findViewById(R.id.DoctorPassword)).getText().toString();

            mDatabase.child("Doctors").child(Mobile).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Doctor d = dataSnapshot.getValue(Doctor.class);

                    if(Password.equals(d.password)) {
                        Next();
                    }

                else
                    {Toast.makeText(DLogin.this, Password+"Incorrect Password"+d.password,
                            Toast.LENGTH_LONG).show();}

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(view.getId()== R.id.btnDocReg) {
            Intent intent = new Intent(this, DSignup.class);
            startActivity(intent);
        }

    }
    private void Next()
    {
        Intent intent = new Intent(this, Doctor_PatientLogin.class);
        intent.putExtra("mobile", Mobile);
        startActivity(intent);
    }
}
