package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Doctor_dash extends AppCompatActivity {
private String PMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dash);
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        findViewById(R.id.imgHistory1).setOnClickListener(Doctor_dash.this::click);
        findViewById(R.id.imgVitals1).setOnClickListener(Doctor_dash.this::click);
        findViewById(R.id.imgReports1).setOnClickListener(Doctor_dash.this::click);
        findViewById(R.id.imgMeds1).setOnClickListener(Doctor_dash.this::click);
        findViewById(R.id.imgNewCase).setOnClickListener(Doctor_dash.this::click);
        findViewById(R.id.imgLogout).setOnClickListener(Doctor_dash.this::click);
        Toast.makeText(Doctor_dash.this,PMobile+" Logged in",
                Toast.LENGTH_LONG).show();
    }

    private void click(View view) {

        if(view.getId()== R.id.imgHistory1) {
            Intent intent = new Intent(this, DHistory.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }
        else if(view.getId()== R.id.imgVitals1) {
            Intent intent = new Intent(this, DVitals.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgReports1) {
            Intent intent = new Intent(this, DReports.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgMeds1) {
            Intent intent = new Intent(this, DMeds.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }

        else if(view.getId()== R.id.imgNewCase) {
            Intent intent = new Intent(this, DNewcase.class);
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
