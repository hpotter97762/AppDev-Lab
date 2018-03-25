package com.example.aditya.lab_7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import static android.graphics.Typeface.BOLD;
import static android.graphics.Typeface.ITALIC;
import static android.graphics.Typeface.NORMAL;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    TextView tv;
    private SharedPreferences sharedPreferences;
    public static Toolbar tbar;
    private ActionMode actionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbar=findViewById(R.id.tBar);
        tbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(tbar);
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        tv=findViewById(R.id.txt1);
        registerForContextMenu(tv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String str=sharedPreferences.getString("User","User");
        LinearLayout linearLayout=findViewById(R.id.main_layout);
        tv.setText(str);
        boolean b=sharedPreferences.getBoolean("italicize",false);
        if(b)
            tv.setTypeface(null, ITALIC);
        else
            tv.setTypeface(null,NORMAL);
        str=sharedPreferences.getString("theme","water");
        switch(str){
            case "water": {
                String s=String.valueOf(R.color.blue);
                tv.setBackgroundColor(getResources().getColor(R.color.blue));
                tbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            }
            case "nature":{
                tv.setBackgroundColor(getResources().getColor(R.color.greenlight));
                tbar.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            }
            case "fire":{
                tv.setBackgroundColor(getResources().getColor(R.color.redLight));
                tbar.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            }
            default:{
                tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tbar.setBackgroundColor(Color.parseColor("#000000"));
            }
        }
        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu,menu);
        return true;
    }
    public void openContext(View view){
        actionMode=MainActivity.this.startActionMode(new ActionBarCallback());
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.float_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
    public void openPopup(View view){
        PopupMenu pm=new PopupMenu(this,view);
        pm.getMenuInflater().inflate(R.menu.float_menu,pm.getMenu());
        pm.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent();
        if(item.getItemId()==R.id.setting){
            intent.setClass(this,setting.class);
        }
        else{
            intent.setClass(this,help.class);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
    public class ActionBarCallback implements ActionMode.Callback{
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MainActivity.tbar.setVisibility(View.GONE);
            actionMode.getMenuInflater().inflate(R.menu.context_menu,menu);
            return true;
        }
        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }
        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            MainActivity.tbar.setVisibility(View.VISIBLE);
        }
    }
}
