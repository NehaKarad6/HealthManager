package com.example.neha.healthmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DMedications extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String med;
    private String dosage;
    private String sideeff;
    private String reason;
    private String start;
    private String stop;
    private String PMobile;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmedications);
        Bundle bundle = getIntent().getExtras();
        date = bundle.getString("date");
        PMobile=bundle.getString("PMobile");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Patients").child(PMobile).child("Medication").child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Medication m= dataSnapshot.getValue(Medication.class);
                med=m.med;
                dosage=m.dosage;
                sideeff=m.sideeff;
                reason=m.reason;
                start=m.start;
                stop=m.stop;

                EditText edtmed = (EditText)findViewById(R.id.EdtMedication);
                edtmed.setText(med);

                EditText edtdosage = (EditText)findViewById(R.id.EdtDosage);
                edtdosage.setText(dosage);

                EditText edtsideeff = (EditText)findViewById(R.id.EdtSideEffects);
                edtsideeff.setText(sideeff);

                EditText edtreason= (EditText)findViewById(R.id.EdtReasonTaking);
                edtreason.setText(reason);

                EditText edtstart = (EditText)findViewById(R.id.EdtBeginDate);
                edtstart.setText(start);

                EditText edtstop = (EditText)findViewById(R.id.EdtStopDate);
                edtstop.setText(stop);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });






    }

}
