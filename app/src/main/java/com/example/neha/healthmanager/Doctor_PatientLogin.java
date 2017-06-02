package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Doctor_PatientLogin extends AppCompatActivity {
private String DMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__patient_login);
        Bundle bundle = getIntent().getExtras();
        DMobile = bundle.getString("mobile");
        findViewById(R.id.btnExistingPatient).setOnClickListener(this::click);
        findViewById(R.id.btnAddPatient).setOnClickListener(this::click);

    }

    private void click(View view) {
        if(view.getId()== R.id.btnExistingPatient) {
            Intent intent = new Intent(this, ExistingPatients.class);
            intent.putExtra("mobile", DMobile);
            startActivity(intent);
        }
        else if(view.getId()== R.id.btnAddPatient) {
            Intent intent = new Intent(this, AddNewPatient.class);
            intent.putExtra("mobile", DMobile);
            startActivity(intent);
        }



    }
}
