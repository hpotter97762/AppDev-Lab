package com.example.aditya.sqlite;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> str=new ArrayList<>();
    private ListView lv;
    private static MySQLiteAdapter sqliteAdapter;
    private Button but1,but2;
    public static String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteAdapter=new MySQLiteAdapter(this);
        lv=findViewById(R.id.list);
        but1=findViewById(R.id.add);
        final EditText text=findViewById(R.id.roll),text2=findViewById(R.id.name);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqliteAdapter.AddDetails(Integer.parseInt(text.getText().toString()),text2.getText().toString());
            }
        });
        but2=findViewById(R.id.refresh);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<items>st=sqliteAdapter.List();
                str.clear();
                for(int i=0;i<st.size();++i){
                    str.add(st.get(i).roll+":"+st.get(i).name);
                }
                lv.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,str));
                Intent intent=new Intent(MainActivity.this,NewAppWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] s=str.get(i).split(":");
                MainActivity.s+=s[0]+'\n';
                text.setText(s[0]);
                text2.setText(s[1]);
            }
        });
    }
}
