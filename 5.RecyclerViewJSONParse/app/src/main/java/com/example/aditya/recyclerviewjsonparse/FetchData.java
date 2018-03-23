package com.example.aditya.recyclerviewjsonparse;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.aditya.recyclerviewjsonparse.MainActivity.student;

public class FetchData extends AsyncTask<String,Void,Void > {
    @Override
    protected Void doInBackground(String... strings) {
        try {
            URL url=new URL(strings[0]);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            InputStream inputStream=conn.getInputStream();
            conn.connect();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line=bufferedReader.readLine();
            StringBuilder json=new StringBuilder();
            while(line!=null){
                json.append(line);
                line=bufferedReader.readLine();
            }
            bufferedReader.close();
            JSONArray jsonArray=new JSONArray(json.toString());
            for(int i=0;i<jsonArray.length();++i){
                JSONObject js= (JSONObject) jsonArray.get(i);
                Log.i("adi",js.toString());
                Student st=new Student(js.get("name"),js.get("roll_no"),js.get("contact"));
                student.add(st);
            }
            conn.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
