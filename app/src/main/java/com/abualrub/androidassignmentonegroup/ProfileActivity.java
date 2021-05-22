package com.abualrub.androidassignmentonegroup;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abualrub.androidassignmentonegroup.domain.Root;
import com.abualrub.androidassignmentonegroup.domain.Student;
import com.abualrub.androidassignmentonegroup.utils.HttpGet;
import com.abualrub.androidassignmentonegroup.utils.HttpPost;
import com.abualrub.androidassignmentonegroup.utils.ITags;
import com.abualrub.androidassignmentonegroup.utils.IURLs;
import com.abualrub.androidassignmentonegroup.utils.Validator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

// *********************************
// MADE BY Suzan Altawil (1162347)
// ALSO OSID ABU-ALRUB (1183096)
// *********************************
public class ProfileActivity extends AppCompatActivity implements IURLs, ITags {
    private EditText editTextUserName, editTextPassword,editTextEmail,editTextPhoneNumber,
            editTextFirstName,editTextLastName;
    private SharedPreferences.Editor editor;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        getIntentData();
    }

    private void init(){
        editTextUserName = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editor =  PreferenceManager.getDefaultSharedPreferences(this).edit();
        getSupportActionBar().setTitle(R.string.osid_textViewToolBarMyProfile);
    }

    private void getIntentData(){
        Gson gson = new Gson();
        student = gson.fromJson(getIntent().getStringExtra(STUDENT),Student.class);
        if(student == null) {
            Toast.makeText(this, "Fatal error. Account not found!", Toast.LENGTH_SHORT).show();
            findViewById(R.id.buttonSave).setEnabled(false);
            return;
        }
        HashMap<String,String> params = new HashMap<>();
        params.put(STUDENT_ID,student.getStudentId()+"");
        GetStudentTask runner = new GetStudentTask();
        runner.execute(URL_GET_STUDENT,params);
    }

    public void buttonSaveHandleClick(View view) {
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
        params.put(STUDENT_ID,student.getStudentId()+"");
        params.put(USERNAME,userName);
        params.put(PASSWORD,password);
        params.put(EMAIL,email);
        params.put(PHONE_NUMBER,phoneNumber);
        params.put(FIRST_NAME,firstName);
        params.put(LAST_NAME,lastName);

        // send POST request
        ProfileUpdateTask runner = new ProfileUpdateTask();
        runner.execute(URL_UPDATE_STUDENT,params);
    }

    private void backToMainActivity(Student student){
        String jsonStudent = new Gson().toJson(student);

        // save to shared prefs
        editor.putString(STUDENT,jsonStudent);
        editor.putBoolean(IS_CREATED,true);
        editor.commit();

        // start main activity
        finish();
    }

    private class ProfileUpdateTask extends AsyncTask<Object,Void,String> {
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
                Toast.makeText(ProfileActivity.this, res.getError(), Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(ProfileActivity.this, "Save successful", Toast.LENGTH_SHORT).show();
            backToMainActivity(res.getData());
        }
    }

    private class GetStudentTask extends AsyncTask<Object,Void,String> {
        @Override
        protected String doInBackground(Object ...args){
            String URL = args[0]+"";
            HashMap<String,String> params = (HashMap<String,String>)args[1];
            HttpGet req = new HttpGet();
            return req.get(URL,params);
        }

        @Override
        protected void onPostExecute(String result) {
            Gson gson = new Gson();
            Type type = new TypeToken<Root<Student>>(){}.getType();
            Root<Student> res = gson.fromJson(result,type);
            if(res.isError()){
                Toast.makeText(ProfileActivity.this, res.getError(), Toast.LENGTH_LONG).show();
                return;
            }
            student = res.getData();
            editTextUserName.setText(student.getUserName());
            editTextEmail.setText(student.getEmail());
            editTextFirstName.setText(student.getFirstName());
            editTextLastName.setText(student.getLastName());
            editTextPassword.setText(student.getPassword());
            editTextPhoneNumber.setText(student.getPhoneNumber());
        }
    }
}