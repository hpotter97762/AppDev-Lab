package com.example.aditya.recyclerviewjsonparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    String url;
    public static List<Student> student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.list);
        EditText et = findViewById(R.id.text2);
        url= et.getText().toString();
        rv.setLayoutManager(new LinearLayoutManager(this));
        Button but=findViewById(R.id.fetch);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);
                FetchData fetchData=new FetchData();
                student=new ArrayList<>();
                try {
                    fetchData.execute(url).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                Log.i("adi",String.valueOf(student.size()));
                Adapter adapter=new Adapter(MainActivity.this);
                rv.setAdapter(adapter);
            }
        });
    }
}
//https://api.myjson.com/bins/12rudd