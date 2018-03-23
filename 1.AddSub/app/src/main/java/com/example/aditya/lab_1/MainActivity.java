package com.example.aditya.lab_1;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view){
        EditText fn = (EditText) findViewById(R.id.editText);
        EditText sn = (EditText) findViewById(R.id.editText2);
        Integer a = Integer.parseInt(fn.getText().toString());
        Integer b = Integer.parseInt(sn.getText().toString());
        RadioButton r1=(RadioButton) findViewById(R.id.button1);
        RadioButton r2=(RadioButton) findViewById(R.id.button2);
        if(r1.isChecked()){	add(a,b);}
        else{	sub(a,b);}
    }
    public void add(int a,int b) {
        String result = new Integer(a+b).toString();
        TextView ans = (TextView) findViewById(R.id.textView);
        ans.setText(result);
    }


    public void sub(int a,int b) {
        String result = new Integer(a-b).toString();
        TextView ans = (TextView) findViewById(R.id.textView);
        ans.setText(result);
    }
}

