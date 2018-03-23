package com.example.aditya.recyclerviewjsonparse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    Adapter(Context c){
        context=c;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.single_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text1.setText(MainActivity.student.get(position).name);
        holder.text2.setText(MainActivity.student.get(position).contact);
    }

    @Override
    public int getItemCount() {
        return MainActivity.student.size();
    }
}
