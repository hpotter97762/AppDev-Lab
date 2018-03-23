package com.example.aditya.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter extends ArrayAdapter<String> {
    private Context context;
    Adapter(Context c) {
        super(c,R.layout.single_item,R.id.name,List.arrayName);
        context=c;
    }

    @Override
    @NonNull
    public View getView(int pos,View view,@Nullable ViewGroup parent){
        LayoutInflater l= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v=l.inflate(R.layout.single_item,parent,false);
        TextView tv=v.findViewById(R.id.name);
        tv.setText(List.arrayName.get(pos));
        TextView tv2=v.findViewById(R.id.contact);
        tv2.setText(List.arrayContact.get(pos));
        return v;
    }
}
