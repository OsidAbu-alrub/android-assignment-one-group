package com.abualrub.androidassignmentonegroup.utils;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

// *********************************
// MADE BY OSID ABU-ALRUB (1183096)
// *********************************
public class Utils  {
    public void requestPermission(AppCompatActivity activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.INTERNET},123);
        }
    }
}
