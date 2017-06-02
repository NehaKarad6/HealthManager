package com.example.neha.healthmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PMedications extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private AwesomeValidation awesomeValidation;
    private String med;
    private String dosage;
    private String sideeff;
    private String reason;
    private String start;
    private String stop;
    private String PMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmedications);
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this, R.id.EdtBeginDate, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);
        awesomeValidation.addValidation(this, R.id.EdtStopDate, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);







        findViewById(R.id.SubmitMed).setOnClickListener(this::click);
    }


        private void click(View view) {
            med=((EditText) findViewById(R.id.EdtMedication)).getText().toString();
            dosage=((EditText) findViewById(R.id.EdtDosage)).getText().toString();
            sideeff=((EditText) findViewById(R.id.EdtSideEffects)).getText().toString();
            reason=((EditText) findViewById(R.id.EdtReasonTaking)).getText().toString();
            start=((EditText) findViewById(R.id.EdtBeginDate)).getText().toString();
            stop=((EditText) findViewById(R.id.EdtStopDate)).getText().toString();


            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);
            int minutes = c.get(Calendar.MINUTE);
            int hour = c.get(Calendar.HOUR);
            //String time = hour+":"+minutes+":"+seconds;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);
            String date = day+"-"+month+"-"+year+"-TIME-"+hour+"-"+minutes+"-"+seconds;

            Medication m=new Medication(date,med,dosage,sideeff,reason,start,stop);

            mDatabase.child("Patients").child(PMobile).child("Medication").child(date).setValue(m);
            Toast.makeText(PMedications.this, "Data Saved!",
                    Toast.LENGTH_LONG).show();
        }
    }

