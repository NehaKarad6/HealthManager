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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History_Major extends AppCompatActivity {
    private String PMobile;
    private String date;
    private String chief;

    private DatabaseReference mDatabase;
    private List<String> PList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__major);
        PList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        ListView lv = (ListView) findViewById(R.id.Majorlist);
        //lv.setOnItemClickListener(this::listItemClick);
        mDatabase.child("Patients").child(PMobile).child("History").child("Major").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Fetch image data from firebase database
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    GenericTypeIndicator<Map<String, Object>> m = new GenericTypeIndicator<Map<String, Object>>(){};
                    Map<String, Object> map = snapshot.getValue(m);
                    newcase nc= snapshot.getValue(newcase.class);
                    date = nc.date;
                    chief=nc.chief;


                    PList.add(date+" "+chief);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(History_Major.this, R.layout.list_item, PList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(History_Major.this, History_Details.class);

                String[] arr = lv.getItemAtPosition(position).toString().split("\\s");
                String date = arr[0];
                intent.putExtra("date", date);
                intent.putExtra("PMobile", PMobile);
                intent.putExtra("Type", "Major");
                startActivity(intent);
            }
        });

    }
}
