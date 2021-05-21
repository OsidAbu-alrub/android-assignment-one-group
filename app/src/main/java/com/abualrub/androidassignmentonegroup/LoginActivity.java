package com.abualrub.androidassignmentonegroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.abualrub.androidassignmentonegroup.domain.Root;
import com.abualrub.androidassignmentonegroup.domain.Student;
import com.abualrub.androidassignmentonegroup.utils.HttpPost;
import com.abualrub.androidassignmentonegroup.utils.ITags;
import com.abualrub.androidassignmentonegroup.utils.IURLs;
import com.abualrub.androidassignmentonegroup.utils.Utils;
import com.abualrub.androidassignmentonegroup.utils.Validator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

// *********************************
// MADE BY OSID ABU-ALRUB (1183096)
// *********************************
public class LoginActivity extends AppCompatActivity implements ITags, IURLs {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        boolean isCreated = prefs.getBoolean(IS_CREATED,false);
        if(!isCreated) return;
        Student student = new Gson().fromJson(prefs.getString(STUDENT,null),Student.class);
        editTextUserName.setText(student.getUserName());
        editTextPassword.setText(student.getPassword());
    }

    public void buttonLoginHandleClick(View view) {
        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        Validator validator = new Validator(this);
        if(!validator.isValidUserName(userName)) return;
        if(!validator.isValidPassword(password)) return;
        HashMap<String,String> params = new HashMap<String,String>();
        params.put(USERNAME,userName);
        params.put(PASSWORD,password);
        LoginTask runner = new LoginTask();
        runner.execute(URL_LOGIN,params);
    }

    public void textViewCreateAccountHandleClick(View view) {
        Intent i = new Intent(this,RegisterActivity.class);
        finish();
        startActivity(i);
    }

    private void init(){
        new Utils().requestPermission(this);
        editTextUserName = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void startMainActivity(Student student){
        String jsonStudent = new Gson().toJson(student);

        // save to shared prefs
        editor.putString(STUDENT,jsonStudent);
        editor.putBoolean(IS_CREATED,true);
        editor.commit();

        // start main activity
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra(STUDENT,jsonStudent);
        startActivity(i);
    }

    private class LoginTask extends AsyncTask<Object,Void,String> {
        @Override
        protected String doInBackground(Object ...args){
            String URL = args[0]+"";
            HashMap<String,String> params = (HashMap<String,String>) args[1];
            HttpPost req = new HttpPost();
            return req.post(URL,params);
        }

        @Override
        protected void onPostExecute(String result) {
            Gson gson = new Gson();
            Type type = new TypeToken<Root<Student>>(){}.getType();
            Root<Student> res = gson.fromJson(result,type);
            if(res.isError()){
                Toast.makeText(LoginActivity.this, res.getError(), Toast.LENGTH_LONG).show();
                return;
            }
            startMainActivity(res.getData());
        }
    }
}