package com.example.aditya.test;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

    private int y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if(savedInstanceState!=null){
//            y=savedInstanceState.getInt("y");
//            savedInstanceState=null;
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BlankFragment frag=BlankFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.menu,frag).commit();
        if(y!=0){
            onFragmentInteraction(y);
        }
        int requestGranted=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS);
        if (requestGranted!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},requestGranted);
        }
    }

    @Override
    public void onFragmentInteraction(int a) {
        char start,end;
        switch(a){
            case 0:{start='A';end='H';y=0;
                    break;}
            case 1:{start='I';end='R';y=1;
                    break;}
            default:{start='S';end='Z';y=2;}
        }
        List frag= List.newInstance(start,end);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu,frag).addToBackStack(null).commit();
    }
}
