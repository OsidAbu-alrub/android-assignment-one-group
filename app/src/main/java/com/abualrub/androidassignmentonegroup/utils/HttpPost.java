package com.abualrub.androidassignmentonegroup.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

// *********************************
// MADE BY OSID ABU-ALRUB (1183096)
// *********************************
public class HttpPost {
    public String post(String URL,HashMap<String,String> params){
        String body = encode(params);
        String res = "";
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL(URL);

            // Send POST data request
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(body);
            wr.flush();

            // Get the server response
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            res = sb.toString();
            reader.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        // Show response on activity
        return res;
    }
    private String encode(HashMap<String,String> params){
        try {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            return result.toString();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
