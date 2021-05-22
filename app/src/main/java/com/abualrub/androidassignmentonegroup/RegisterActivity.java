package com.abualrub.androidassignmentonegroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.abualrub.androidassignmentonegroup.domain.Root;
import com.abualrub.androidassignmentonegroup.domain.Student;
import com.abualrub.androidassignmentonegroup.utils.HttpGet;
import com.abualrub.androidassignmentonegroup.utils.HttpPost;
import com.abualrub.androidassignmentonegroup.utils.ITags;
import com.abualrub.androidassignmentonegroup.utils.IURLs;
import com.abualrub.androidassignmentonegroup.utils.Utils;
import com.abualrub.androidassignmentonegroup.utils.Validator;
import com.google.gson.Gson;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

// *********************************
// MADE BY OSID ABU-ALRUB (1183096)
// *********************************
public class RegisterActivity extends AppCompatActivity implements IURLs, ITags {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextPhoneNumber;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    public void buttonRegisterHandleClick(View view) {
        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();

        Validator validator = new Validator(this);
        if(!validator.isValidUserName(userName)) return;
        if(!validator.isValidPassword(password)) return;
        if(!validator.isValidName(firstName)) return;
        if(!validator.isValidName(lastName)) return;
        if(!validator.isValidEmail(email)) return;
        if(!validator.isValidPhoneNumber(phoneNumber)) return;

        HashMap<String,String> params = new HashMap<String,String>();
        params.put(USERNAME,userName);
        params.put(PASSWORD,password);
        params.put(EMAIL,email);
        params.put(PHONE_NUMBER,phoneNumber);
        params.put(FIRST_NAME,firstName);
        params.put(LAST_NAME,lastName);

        // send POST request
        RegisterTask runner = new RegisterTask();
        runner.execute(URL_REGISTER,params);
    }

    public void textViewLoginHandleClick(View view) {
        Intent i = new Intent(this,LoginActivity.class);
        finish();
        startActivity(i);
    }

    private void init(){
        new Utils().requestPermission(this);
        editTextUserName = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        getSupportActionBar().setTitle(R.string.osid_textViewRegisterToolBarText);
    }

    private void startLoginActivity(Student student){
        String jsonStudent = new Gson().toJson(student);

        // save to shared prefs
        editor.putString(STUDENT,jsonStudent);
        editor.putBoolean(IS_CREATED,true);
        editor.commit();

        // start main activity
        Intent i = new Intent(this,LoginActivity.class);
        finish();
        startActivity(i);
    }

    private class RegisterTask extends AsyncTask<Object,Void,String>{
        @Override
        protected String doInBackground(Object ...args){
            String URL = args[0]+"";
            HashMap<String,String> params = (HashMap<String,String>)args[1];
            HttpPost req = new HttpPost();
            return req.post(URL,params);
        }

        @Override
        protected void onPostExecute(String result) {
            Gson gson = new Gson();
            Type type = new TypeToken<Root<Student>>(){}.getType();
            Root<Student> res = gson.fromJson(result,type);
            if(res.isError()){
                Toast.makeText(RegisterActivity.this, res.getError(), Toast.LENGTH_LONG).show();
                return;
            }
            startLoginActivity(res.getData());
        }
    }
}