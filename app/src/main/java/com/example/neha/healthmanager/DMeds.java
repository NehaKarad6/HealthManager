package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DMeds extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String med;
    private String date;
    private String dosage;
    private String sideeff;
    private String reason;
    private String start;
    private String stop;
    private String PMobile;
    private List<String> PList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmeds);
        PList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        ListView lv = (ListView) findViewById(R.id.Medlist);
        //lv.setOnItemClickListener(this::listItemClick);
        mDatabase.child("Patients").child(PMobile).child("Medication").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Fetch image data from firebase database
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    GenericTypeIndicator<Map<String, Object>> m = new GenericTypeIndicator<Map<String, Object>>(){};
                    Map<String, Object> map = snapshot.getValue(m);
                    //Log.i("sk", "User Data Value is: " + snapshot.getKey());
                    Medication a= snapshot.getValue(Medication.class);
                    date = a.date;
                    med=a.med;
                    dosage=a.dosage;
                    sideeff=a.sideeff;
                    reason=a.reason;
                    start=a.start;
                    stop=a.stop;

                    PList.add(date+" "+med);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(DMeds.this, R.layout.list_item, PList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DMeds.this, DMedications.class);
               // intent.putExtra("date", lv.getItemAtPosition(position).toString());

                String[] arr = lv.getItemAtPosition(position).toString().split("\\s");
                String date = arr[0];
                Toast.makeText(DMeds.this, date,
                        Toast.LENGTH_LONG).show();
                intent.putExtra("date", date);
                intent.putExtra("PMobile", PMobile);
                startActivity(intent);
            }
        });

    }
}
