package com.example.aditya.recyclerviewjsonparse;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class ViewHolder extends RecyclerView.ViewHolder {

    TextView text1,text2;
    ViewHolder(View itemView) {
        super(itemView);
        text1=itemView.findViewById(R.id.text1);
        text2=itemView.findViewById(R.id.text2);
    }
}
