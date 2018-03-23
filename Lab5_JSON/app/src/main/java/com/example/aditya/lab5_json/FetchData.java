package com.example.aditya.lab5_json;


import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FetchData extends AsyncTask<Void, Void,Void>{
    private String printParsed="";
    private String[] DataSet=new String[50];
    private int j=0;
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.myDataSet=DataSet;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder print=new StringBuilder("");
        URL url;
        try {
            url = new URL("https://api.myjson.com/bins/12rudd");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream inputStreamReader=conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamReader));
            String line=reader.readLine();
            while(line!=null){
                print.append(line);
                line =reader.readLine();
            }
            JSONArray js=new JSONArray(print.toString());
            for(int i=0;i<js.length();++i){
                JSONObject jo= (JSONObject) js.get(i);
                String str="Name: " + jo.get("name")+"\n"+
                        "Roll No. "+jo.get("roll_no")+"\n\n";
                DataSet[j++]=str;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
