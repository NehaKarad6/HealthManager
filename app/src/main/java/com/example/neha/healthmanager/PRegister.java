package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PRegister extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String mobile;
    private String address;
    private String dob;
    private String weight;
    private String bg;
    private String emerg_contact;
    private String relation;
    private String emerg_mob;
    private String name;
    private String email;
    private String password;

    private AwesomeValidation awesomeValidation;
    private Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        email = bundle.getString("email");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.edtMobileNumber, "^[2-9]{2}[0-9]{8}$", R.string.mobilenumbererror);
        awesomeValidation.addValidation(this, R.id.EdtAddress, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.addresserror);
        awesomeValidation.addValidation(this, R.id.EdtDOB, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);
        awesomeValidation.addValidation(this, R.id.EdtWeight ,"(?<Weight>\\d*\\.?\\d+)" , R.string.weighterror);
        awesomeValidation.addValidation(this, R.id.EdtEmergencyContact, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.emergencycontacterror);
        awesomeValidation.addValidation(this, R.id.EdtRelation, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.relationerror);
        awesomeValidation.addValidation(this, R.id.EdtEmgMob, "^[2-9]{2}[0-9]{8}$", R.string.emergencymobileerror);

        findViewById(R.id.btnSubmit).setOnClickListener(this::click);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }



    //process the data further
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);


    }




    private void click(View view) {
        if (awesomeValidation.validate()) {
            mobile = ((EditText) findViewById(R.id.edtMobileNumber)).getText().toString();
            address = ((EditText) findViewById(R.id.EdtAddress)).getText().toString();
            dob = ((EditText) findViewById(R.id.EdtDOB)).getText().toString();
            weight = ((EditText) findViewById(R.id.EdtWeight)).getText().toString();
            //bg=((EditText) findViewById(R.id.EdtBloodGroup)).getText().toString();
            bg = String.valueOf(spinner1.getSelectedItem());
            emerg_contact = ((EditText) findViewById(R.id.EdtEmergencyContact)).getText().toString();
            relation = ((EditText) findViewById(R.id.EdtRelation)).getText().toString();
            emerg_mob = ((EditText) findViewById(R.id.EdtEmgMob)).getText().toString();
            password = ((EditText) findViewById(R.id.EdtPatientPassword)).getText().toString();

            Patient p = new Patient(name, email, mobile, address, dob, weight, bg, emerg_contact, relation, emerg_mob, password);

            mDatabase.child("Patients").child(mobile).setValue(p);
            Toast.makeText(PRegister.this, "Registration Successful!",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Patient_dash.class);
            intent.putExtra("mobile", mobile);
            startActivity(intent);
        }
    }





}

