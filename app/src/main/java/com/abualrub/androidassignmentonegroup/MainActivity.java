package com.abualrub.androidassignmentonegroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.abualrub.androidassignmentonegroup.domain.Course;
import com.abualrub.androidassignmentonegroup.utils.ListViewAdapter;
import com.abualrub.androidassignmentonegroup.domain.Root;
import com.abualrub.androidassignmentonegroup.domain.Student;
import com.abualrub.androidassignmentonegroup.utils.HttpGet;
import com.abualrub.androidassignmentonegroup.utils.ITags;
import com.abualrub.androidassignmentonegroup.utils.IURLs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

// *********************************
// MADE BY MOHAMMAD MUTAIR (1180907)
// ALSO OSID ABU-ALRUB (1183096)
// *********************************
public class MainActivity extends AppCompatActivity  implements IURLs, ITags {

   // SearchView searchView;
    ListView listView;
    ListViewAdapter adapter;
    private ArrayList<Course> coursesArrayList =new ArrayList<Course>();
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getIntentData();
        getCourses();
    }

    private void init(){
        initListView();
        getSupportActionBar().setTitle(R.string.osid_mainActivityTitle);
    }

    private void initListView(){
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                Gson gson = new Gson();
                Course course = coursesArrayList.get(pos);

                String jsonCourse = gson.toJson(course);
                String jsonStudent = gson.toJson(student);

                Intent i = new Intent(MainActivity.this,DetailedActivity.class);
                i.putExtra(COURSE,jsonCourse);
                i.putExtra(STUDENT,jsonStudent);
                startActivity(i);
            }
        });
    }

    private void getIntentData(){
        Gson gson = new Gson();
        student = gson.fromJson(getIntent().getStringExtra(STUDENT),Student.class);
    }

    private void getCourses(){
        HashMap<String,String> params = new HashMap<String,String>();
        // send GET request
        GetCoursesTask runner = new GetCoursesTask();
        runner.execute(URL_COURSES,params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem myActionMenuItem= menu.findItem(R.id.actionSearch);
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
        MenuItem profileAction = menu.findItem(R.id.actionProfile);
        profileAction.setOnMenuItemClickListener(e ->{
            Gson gson = new Gson();
            String jsonStudent = gson.toJson(student);
            Intent intent=new Intent(this,ProfileActivity.class);
            intent.putExtra(STUDENT,jsonStudent);
            startActivity(intent);
            return true;
        });
        return true;
    }


    private class GetCoursesTask extends AsyncTask<Object,Void,String> {
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
            Type type = new TypeToken<Root<ArrayList<Course>>>(){}.getType();
            Root<ArrayList<Course>> res = gson.fromJson(result,type);
            if(res.isError()){
                Toast.makeText(MainActivity.this, res.getError(), Toast.LENGTH_LONG).show();
                return;
            }

            for(int i = 0 ; i < res.getData().size() ; i++){
                coursesArrayList.add(res.getData().get(i));
            }
            adapter=new ListViewAdapter(MainActivity.this, coursesArrayList);
            listView.setAdapter(adapter);
        }
    }
}

