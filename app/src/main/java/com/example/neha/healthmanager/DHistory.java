package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DHistory extends AppCompatActivity {
private String PMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhistory);
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.btnMajor).setOnClickListener(this::click);
        findViewById(R.id.btnMinor).setOnClickListener(this::click);


    }


    private void click(View view) {
        if(view.getId()== R.id.btnMajor) {
            Intent intent = new Intent(this, History_Major.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }
        else if(view.getId()== R.id.btnMinor) {
            Intent intent = new Intent(this, History_Minor.class);
            intent.putExtra("mobile", PMobile);
            startActivity(intent);
        }



    }


}

