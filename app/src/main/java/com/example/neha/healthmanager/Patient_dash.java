package com.example.neha.healthmanager;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Patient_dash extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    // GPSTracker class
    GPSTracker gps;
    private String PMobile;
    private String EmergencyMob;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dash);
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("Patients").child(PMobile).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Patient p= dataSnapshot.getValue(Patient.class);
                EmergencyMob=p.emerg_mob;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        findViewById(R.id.imgHistory).setOnClickListener(Patient_dash.this::click);
        findViewById(R.id.imgVitals).setOnClickListener(Patient_dash.this::click);
        findViewById(R.id.imgDoc).setOnClickListener(Patient_dash.this::click);
        findViewById(R.id.imgMeds).setOnClickListener(Patient_dash.this::click);
        findViewById(R.id.imgEmergency).setOnClickListener(Patient_dash.this::click);
        findViewById(R.id.imgReports).setOnClickListener(Patient_dash.this::click);
        findViewById(R.id.imgInsurance).setOnClickListener(Patient_dash.this::click);
        findViewById(R.id.imgEmergencyContact).setOnClickListener(Patient_dash.this::click);
        findViewById(R.id.imgLogout).setOnClickListener(Patient_dash.this::click);



    }

    private void click(View view) {

        if(view.getId()== R.id.imgHistory) {
            Intent intent = new Intent(this, DHistory.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }
        else if(view.getId()== R.id.imgVitals) {
            Intent intent = new Intent(this, PVitals.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgDoc) {
            Intent intent = new Intent(this, PDoctors.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgMeds) {
            Intent intent = new Intent(this, PMedications.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgEmergency) {



            try {
                if (ActivityCompat.checkSelfPermission(this, mPermission)
                        != MockPackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this, new String[]{mPermission},
                            REQUEST_CODE_PERMISSION);

                    // If any permission above not allowed by user, this condition will
                    // execute every time, else your else part will work
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            gps = new GPSTracker(Patient_dash.this);

            // check if GPS enabled
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                        + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            }else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }
            String[] TO = {"swaraj.kardile@gmail.com"};
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();


            String messageToSend = "https://www.google.com/maps/place/"+latitude+","+longitude;
            String number = EmergencyMob;

            SmsManager.getDefault().sendTextMessage(number , null , messageToSend , null, null);

            Intent intent = new Intent(this, PEmergency.class);
            intent.putExtra("mobile", EmergencyMob);
            startActivity(intent);
        }




        else if(view.getId()== R.id.imgReports) {
            Intent intent = new Intent(this, PReports.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgInsurance) {
            Intent intent = new Intent(this, PInsurance.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgEmergencyContact) {
            Intent intent = new Intent(this, PEmergencyContacts.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgLogout) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }
}
