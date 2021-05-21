package com.abualrub.androidassignmentonegroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abualrub.androidassignmentonegroup.domain.Course;
import com.abualrub.androidassignmentonegroup.domain.ListViewAdapter;
import com.abualrub.androidassignmentonegroup.domain.Student;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

   // SearchView searchView;
    ListView listView;
    ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Course> arrayList=new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Courses List");
        title=new String[]{"Java", "Python", "Ai", "Android", "Security", "C", "C#", "ASP.Net", "JavaScript", "Html"};
        description =new String[]{"Java Description","Python Description","Ai Description","Android Description","Security Description","C Description","C# Description","ASP.Net Description","JavaScript Description","Html Description"};
        icon= new int[]{R.drawable.java,R.drawable.python,R.drawable.ai,R.drawable.android,R.drawable.security,R.drawable.c,R.drawable.csharp,R.drawable.aspnet,R.drawable.javascript,R.drawable.html};


     /*   Intent intent=getIntent();
        Student s= new Student(Integer.parseInt(intent.getStringExtra("studentId")) , intent.getStringExtra("firstName") , intent.getStringExtra("lastName") , intent.getStringExtra("userName") , intent.getStringExtra("password") , intent.getStringExtra("email") , intent.getStringExtra("phoneNumber") );

        Intent intent1=new Intent(this,MainActivity2.class);

        intent1 = intent.putExtra("studentId",s.getStudentId());
        intent1 = intent.putExtra("firstName",s.getFirstName());
        intent1 = intent.putExtra("lastName",s.getLastName());
        intent1 = intent.putExtra("userName",s.getUserName());
        intent1 = intent.putExtra("password",s.getPassword());
        intent1 = intent.putExtra("email",s.getEmail());
        intent1 = intent.putExtra("phoneNumber",s.getPhoneNumber());
        */


        listView = findViewById(R.id.listView);

        for(int i=0;i<title.length;i++){
            Course course = new Course(title[i],description[i],icon[i]);
            arrayList.add(course);
        }
        adapter=new ListViewAdapter(this, arrayList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem myActionMenuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else{
                    adapter.filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.action_setting){
       //     Intent intent=new Intent(this,MainActivity2.class);
        //    startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


