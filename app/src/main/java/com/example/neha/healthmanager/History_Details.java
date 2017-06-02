package com.example.neha.healthmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class History_Details extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String date;
    private String chief;
    private String other;
    private String med;
    private String invest;
    private String instruct;
    private String PMobile;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__details);
        Bundle bundle = getIntent().getExtras();
        date = bundle.getString("date");
        PMobile=bundle.getString("PMobile");
        type=bundle.getString("Type");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Patients").child(PMobile).child("History").child(type).child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                newcase nc= dataSnapshot.getValue(newcase.class);
                date = nc.date;
                chief=nc.chief;
                other=nc.other;
                med=nc.med;
                instruct=nc.instruct;
                invest=nc.invest;

        EditText edtchief = (EditText)findViewById(R.id.EdtChiefComplaint);
        edtchief.setText(chief);

        EditText edtother = (EditText)findViewById(R.id.EdtOtherComplaints);
        edtother.setText(other);

        EditText edtmed = (EditText)findViewById(R.id.EdtMedication);
        edtmed.setText(med);

        EditText edtinvest = (EditText)findViewById(R.id.EdtInvestigations);
        edtinvest.setText(invest);

        EditText edtinstruct = (EditText)findViewById(R.id.EdtInstructions);
        edtinstruct.setText(instruct);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
