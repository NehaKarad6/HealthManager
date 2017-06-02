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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class PDoctors extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String Dmobile;
    private String pname;
    private String PMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdoctors);
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.btnaddDoctor).setOnClickListener(this::click);
    }

    private void click(View view) {
        mDatabase.child("Patients").child(PMobile).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Patient p= dataSnapshot.getValue(Patient.class);
                pname=p.name;
                Next(pname);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
   public void Next(String patname) {
        Dmobile = ((EditText) findViewById(R.id.Doctornumber)).getText().toString();



       mDatabase.child("Doctors").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                   GenericTypeIndicator<Map<String, Object>> m = new GenericTypeIndicator<Map<String, Object>>(){};
                   Map<String, Object> map = snapshot.getValue(m);
                   if(Dmobile.equals(snapshot.getKey()))
                   {
                       Newpatient n = new Newpatient(patname, PMobile);
                       mDatabase.child("Doctors").child(Dmobile).child("Patients").child(PMobile).setValue(n);
                       Toast.makeText(PDoctors.this,"Doctor Added Successfully",
                               Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(PDoctors.this,Patient_dash.class);
                       intent.putExtra("mobile", PMobile);
                       startActivity(intent);
                   }
               }
               Toast.makeText(PDoctors.this,"Doctor not found",
                       Toast.LENGTH_LONG).show();
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });





    }
}
