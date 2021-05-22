package com.abualrub.androidassignmentonegroup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abualrub.androidassignmentonegroup.domain.Course;
import com.abualrub.androidassignmentonegroup.domain.Note;
import com.abualrub.androidassignmentonegroup.domain.Root;
import com.abualrub.androidassignmentonegroup.domain.Student;
import com.abualrub.androidassignmentonegroup.utils.HttpGet;
import com.abualrub.androidassignmentonegroup.utils.HttpPost;
import com.abualrub.androidassignmentonegroup.utils.ITags;
import com.abualrub.androidassignmentonegroup.utils.IURLs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

// *********************************
// MADE BY Banan Soliman (1170686)
// ALSO OSID ABU-ALRUB (1183096)
// *********************************
public class DetailedActivity extends AppCompatActivity implements IURLs, ITags {
    private ListView listView;
    private EditText editTextWriteNotes;
    private TextView textViewDescription;
    private ArrayList<String> notes;
    private ArrayList<String> newNotes;
    private ArrayAdapter<String> arrayAdapter;
    private Student student;
    private Course course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        init();
        getIntentData();
        initActivityData();
    }

    public void buttonAddHandleClick(View view) {
        String note = editTextWriteNotes.getText().toString().trim();
        if(note.isEmpty()) return;
        newNotes.add(note);
        notes.add(note);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        editTextWriteNotes.setText("");
    }

    private void init(){
        notes = new ArrayList<String>();
        newNotes = new ArrayList<String>();
        listView = findViewById(R.id.ListViewNotes);
        editTextWriteNotes = findViewById(R.id.editTextNotes);
        textViewDescription = findViewById(R.id.textViewDescription);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                notes);
    }

    private void getIntentData(){
        Gson gson = new Gson();
        student = gson.fromJson(getIntent().getStringExtra(STUDENT),Student.class);
        course = gson.fromJson(getIntent().getStringExtra(COURSE),Course.class);
        if(student == null){
            Toast.makeText(this, "Fatal error. Account not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        if(course == null){
            Toast.makeText(this, "Fatal error. Course not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initActivityData(){
        getSupportActionBar().setTitle(course.getTitle());
        textViewDescription.setText(course.getLongDesc());

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtubePlayView);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = course.getUrl().split("=")[1].trim();
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        GetNotesTask runner = new GetNotesTask();
        HashMap<String,String> params = new HashMap<>();
        params.put(STUDENT_ID,student.getStudentId()+"");
        params.put(COURSE_ID,course.getCourseId()+"");
        runner.execute(URL_NOTES,params);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(newNotes.size() == 0) return;
        Gson gson = new Gson();
        String jsonNotes = gson.toJson(newNotes.toArray());
        HashMap<String,String> params = new HashMap<>();
        params.put(STUDENT_ID,student.getStudentId()+"");
        params.put(COURSE_ID,course.getCourseId()+"");
        params.put(NOTES,jsonNotes);
        AddNotesTask runner = new AddNotesTask();
        runner.execute(URL_ADD_NEW_NOTES,params);
    }

    private class GetNotesTask extends AsyncTask<Object,Void,String> {
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
            Type type = new TypeToken<Root<ArrayList<Note>>>(){}.getType();
            Root<ArrayList<Note>> res = gson.fromJson(result,type);
            if(res.isError()){
                Toast.makeText(DetailedActivity.this, res.getError(), Toast.LENGTH_LONG).show();
                return;
            }
            ArrayList<Note> notes = res.getData();
            for(Note note : notes){
                DetailedActivity.this
                        .notes.add(note.getNote());
            }
            listView.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            editTextWriteNotes.setText("");
        }
    }

    private class AddNotesTask extends AsyncTask<Object,Void,String> {
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
            Type type = new TypeToken<Root<String>>(){}.getType();
            Root<String> res = gson.fromJson(result,type);
        }
    }
}