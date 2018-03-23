package com.example.aditya.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static int chance = 0;
    private Button Button[][] = new Button[3][3];
    private TextView Text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Text1 = findViewById(R.id.Text1);
        Button[0][0] = findViewById(R.id.a00);
        Button[0][1] = findViewById(R.id.a01);
        Button[0][2] = findViewById(R.id.a02);
        Button[1][0] = findViewById(R.id.a10);
        Button[1][1] = findViewById(R.id.a11);
        Button[1][2] = findViewById(R.id.a12);
        Button[2][0] = findViewById(R.id.a20);
        Button[2][1] = findViewById(R.id.a21);
        Button[2][2] = findViewById(R.id.a22);
        reset(Text1);
    }

    public void reset(View view) {
        chance = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                StringBuilder st = new StringBuilder();
                for (int k = 0; k < i + j; ++k)
                    st.append(" ");
                Button[i][j].setText(st);
                Button[i][j].setEnabled(true);
            }
        }
        String str = "Game commencing";
        Text1.setText(str);
    }

    boolean check() {
        for (int i = 0; i < 3; ++i) {
            if (!Button[i][0].getText().toString().equals("0") && !Button[i][0].getText().toString().equals("X"))
                continue;
            int j;
            for (j = 1; j < 3; ++j) {
                if (!Button[i][j].getText().toString().equals(Button[i][j - 1].getText().toString()))
                    break;
            }
            if (j == 3)
                return true;
        }
        for (int j = 0; j < 3; ++j) {
            if (!Button[j][0].getText().toString().equals("0") && !Button[j][0].getText().toString().equals("X"))
                continue;
            int i;
            for (i = 1; i < 3; ++i) {
                if (!Button[i][j].getText().toString().equals(Button[i - 1][j].getText().toString()))
                    break;
            }
            if (i == 3)
                return true;
        }
        if (Button[1][1].getText().toString().equals(""))
            return false;
        if ((Button[0][0].getText() == Button[1][1].getText()) && (Button[1][1].getText() == Button[2][2].getText()))
            return true;
        return (Button[2][0].getText() == Button[1][1].getText()) && (Button[1][1].getText() == Button[0][2].getText());
    }

    public void fun(View view) {
        Button But1 = (Button) view;
        if (chance % 2 == 0)
            But1.setText("0");
        else
            But1.setText("X");
        But1.setEnabled(false);
        int Player = chance % 2 + 1;
        ++chance;
        String str = "Player " + Player + " wins", str2 = "Tie Game";
        if (check()) {
            Text1.setText(str);
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (Button[i][j].isEnabled())
                        Button[i][j].setEnabled(false);
                }
            }
        } else if (chance == 9) {
            Text1.setText(str2);
        }
    }
}
