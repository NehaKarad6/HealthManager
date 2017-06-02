package com.example.neha.healthmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dregister extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String mobile;
    private String address;
    private String dob;
    private String qualification;
    private String specialization;
    private String regno;
    private String password;
    private String name;
    private String email;


    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dregister);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        email = bundle.getString("email");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this, R.id.edtMob, "^[2-9]{2}[0-9]{8}$", R.string.mobilenumbererror);
        awesomeValidation.addValidation(this, R.id.EdtAdd, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.addresserror);
        awesomeValidation.addValidation(this, R.id.Edtdob, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);
        awesomeValidation.addValidation(this, R.id.EdtQualification , "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.qualificationerror);
        awesomeValidation.addValidation(this, R.id.EdtSpecialization , "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.specializationerror);
        awesomeValidation.addValidation(this, R.id.EdtRegno, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.emergencycontacterror);


        findViewById(R.id.btnSub).setOnClickListener(this::click);
    }

    private void click(View view) {
        if (awesomeValidation.validate()) {

            mobile = ((EditText) findViewById(R.id.edtMob)).getText().toString();
            address = ((EditText) findViewById(R.id.EdtAdd)).getText().toString();
            dob = ((EditText) findViewById(R.id.Edtdob)).getText().toString();
            qualification = ((EditText) findViewById(R.id.EdtQualification)).getText().toString();
            specialization = ((EditText) findViewById(R.id.EdtSpecialization)).getText().toString();
            regno = ((EditText) findViewById(R.id.EdtRegno)).getText().toString();
            password = ((EditText) findViewById(R.id.Edtpassword)).getText().toString();


            Doctor d = new Doctor(name, email, mobile, address, dob, qualification, specialization, regno, password);

            mDatabase.child("Doctors").child(mobile).setValue(d);

            Toast.makeText(Dregister.this, "Registration Successful",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Doctor_PatientLogin.class);
            intent.putExtra("mobile", mobile);
            startActivity(intent);
        }
    }


}
