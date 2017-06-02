package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExistingPatients extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String DMobile;
    private String pmobile;
    private String pname;
    private String pm;

    private List<String> PList;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_patients);
        PList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        DMobile = bundle.getString("mobile");
        ListView lv = (ListView) findViewById(R.id.patientlist);
        mDatabase.child("Doctors").child(DMobile).child("Patients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Newpatient p = snapshot.getValue(Newpatient.class);
                    pmobile = p.mobile;
                    pname=p.name;
                    PList.add(pmobile+" "+pname);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ExistingPatients.this, R.layout.list_item, PList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExistingPatients.this, Doctor_dash.class);
                String[] arr = lv.getItemAtPosition(position).toString().split("\\s");
                String pm = arr[0];
                intent.putExtra("mobile", pm);
                startActivity(intent);
            }
        });



    }
}
