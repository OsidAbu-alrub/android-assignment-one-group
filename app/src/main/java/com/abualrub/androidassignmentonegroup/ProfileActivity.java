package com.abualrub.androidassignmentonegroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.abualrub.androidassignmentonegroup.utils.Validator;

public class ProfileActivity extends AppCompatActivity {
    private EditText edtFName,edtLName,edtPass,edtEmail,edtPhone;
    private Button btnSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        edtFName = findViewById(R.id.edtFName);
        edtLName = findViewById(R.id.edtLName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtPhone = findViewById(R.id.edtPhone);
        btnSaveProfile =findViewById(R.id.btnSaveProfile);

    }


    public void btnSave_onClick(View view) {

        Validator val = new Validator();
        String fname = edtFName.getText().toString().trim();
        String lname = edtLName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        if(fname.length()==0){
            edtFName.requestFocus();
            edtFName.setError("FIELD CANNOT BE EMPTY");
            //Toast.makeText(this, "FIELD CANNOT BE EMPTY", Toast.LENGTH_SHORT).show();
        }
        else if(!val.isValidName(fname)){
            edtFName.requestFocus();
            edtFName.setError("NOT VALID FIRST NAME");
        }
        if(lname.length()==0){
            edtLName.requestFocus();
            edtLName.setError("FIELD CANNOT BE EMPTY");
        }
        else if(!val.isValidName(lname)){
            edtLName.requestFocus();
            edtLName.setError("NOT VALID LAST NAME");
        }

        if(email.length()==0){
            edtEmail.requestFocus();
            edtEmail.setError("FIELD CANNOT BE EMPTY");
        }
        else if(!val.isValidEmail(email)){
            edtEmail.requestFocus();
            edtEmail.setError("NOT VALID EMAIL");
        }
        if(pass.length()==0){
            edtPass.requestFocus();
            edtPass.setError("FIELD CANNOT BE EMPTY");
        }
        else if(!val.isValidPassword(pass)){
            edtPass.requestFocus();
            edtPass.setError("NOT VALID PASSWORD");
        }
        if(phone.length()==0){
            edtPhone.requestFocus();
            edtPhone.setError("FIELD CANNOT BE EMPTY");
        }
        else if(!val.isValidPhoneNumber(phone)){
            edtPhone.requestFocus();
            edtPhone.setError("NOT VALID PHONE NUMBER");
        }

    }
}