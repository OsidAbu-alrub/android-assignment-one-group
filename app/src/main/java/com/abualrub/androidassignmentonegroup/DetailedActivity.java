package com.abualrub.androidassignmentonegroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toolbar;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
// *********************************
// MADE BY Banan Soliman (1170686)
// ALSO OSID ABU-ALRUB (1183096)
// *********************************
public class DetailedActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    private ArrayList<String> ArrayList;
    private Button buttonAdd;
    private EditText editTextWriteNotes;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        init();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notes = editTextWriteNotes.getText().toString();

                ArrayList.add(notes);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtubePlayView);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "lDosBozB9jw";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    private void init(){
        listView = findViewById(R.id.ListViewNotes);
        buttonAdd = findViewById(R.id.buttonAdd);
        editTextWriteNotes = findViewById(R.id.editTextNotes);
        ArrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,ArrayList);
    }
}