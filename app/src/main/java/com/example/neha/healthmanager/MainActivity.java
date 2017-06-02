package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnPatientLogin).setOnClickListener(this::click);
        findViewById(R.id.btnDoctorLogin).setOnClickListener(this::click);
        findViewById(R.id.btnRegister).setOnClickListener(this::click);

    }

    private void click(View view) {
        if(view.getId()== R.id.btnPatientLogin) {
            Intent intent = new Intent(this, PLogin.class);

            startActivity(intent);
        }
        else if(view.getId()== R.id.btnDoctorLogin) {
            Intent intent = new Intent(this, DLogin.class);
            startActivity(intent);
        }

        else if(view.getId()== R.id.btnRegister) {
            Intent intent = new Intent(this,PSignup.class);
            startActivity(intent);
        }
    }
}

