package com.example.aditya.fragments;

import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements menu_fragment.OnFragmentInteractionListener,Data.OnFragmentInteractionListener {

    private int y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            y=savedInstanceState.getInt("y");
            savedInstanceState=null;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu_fragment menu=menu_fragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.menu,menu,"menu_frag").commit();
        if(y!=0)
            onFragmentInteraction(y);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("y",y);
    }

    @Override
    public void onFragmentInteraction(int a) {
        Data dat=new Data();
        y=dat.x=a;
        FragmentTransaction ftn=getSupportFragmentManager().beginTransaction();
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
            if(getSupportFragmentManager().findFragmentById(R.id.base)!=getSupportFragmentManager().findFragmentByTag("fragment"+dat.x)||
                    getSupportFragmentManager().findFragmentById(R.id.base)==null)
                ftn.replace(R.id.base, dat,"fragment"+dat.x).addToBackStack(null).commit();
        }
        else
            ftn.replace(R.id.menu,dat).addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction2(Uri uri) {

    }
}
