package com.example.neha.healthmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PInsurance extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String cname;
    private String pno;
    private String name;
    private String plan;
    private String coverage;
    private String cov_end;
    private String cov_start;
    private String PMobile;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinsurance);
        Bundle bundle = getIntent().getExtras();
        PMobile = bundle.getString("mobile");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);



        awesomeValidation.addValidation(this, R.id.EdtCompanyName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.companynameerror);
        //awesomeValidation.addValidation(this, R.id.EdtPolicyNumber, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.addresserror);
        awesomeValidation.addValidation(this, R.id.EdtNameOnCard, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.EdtPlanName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.planerror);
        //awesomeValidation.addValidation(this, R.id.EdtCoverage, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.relationerror);
        awesomeValidation.addValidation(this, R.id.EdtCoverageEndDate, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);
        awesomeValidation.addValidation(this, R.id.EdtCoverageStartDate, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);


        findViewById(R.id.btnSubmitIns).setOnClickListener(this::click);
    }

    private void click(View view) {
        if (awesomeValidation.validate()) {
            cname = ((EditText) findViewById(R.id.EdtCompanyName)).getText().toString();
            pno = ((EditText) findViewById(R.id.EdtPolicyNumber)).getText().toString();
            name = ((EditText) findViewById(R.id.EdtNameOnCard)).getText().toString();
            plan = ((EditText) findViewById(R.id.EdtPlanName)).getText().toString();
            coverage = ((EditText) findViewById(R.id.EdtCoverage)).getText().toString();
            cov_end = ((EditText) findViewById(R.id.EdtCoverageEndDate)).getText().toString();
            cov_start = ((EditText) findViewById(R.id.EdtCoverageStartDate)).getText().toString();
            Insurance i = new Insurance(cname, pno, name, plan, coverage, cov_end, cov_start);
            mDatabase.child("Patients").child(PMobile).child("Insurance").setValue(i);
        }
    }
}
