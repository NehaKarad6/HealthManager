package com.example.neha.healthmanager;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PEmergency extends AppCompatActivity {
private String EMobile;
    Ringtone ringTone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemergency);
        Bundle bundle = getIntent().getExtras();
        EMobile = bundle.getString("mobile");
        Uri uriAlarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
       ringTone = RingtoneManager
                .getRingtone(getApplicationContext(), uriAlarm);
        ringTone.play();


        findViewById(R.id.btnStop).setOnClickListener(PEmergency.this::click);
        findViewById(R.id.btnCall).setOnClickListener(PEmergency.this::click1);
    }

    private void click1(View view) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + EMobile));
        startActivity(intent);
    }

    private void click(View view) {
        ringTone.stop();
    }
}
