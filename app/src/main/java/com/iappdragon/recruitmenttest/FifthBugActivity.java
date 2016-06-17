package com.iappdragon.recruitmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FifthBugActivity extends Activity {
    TextView tv;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_bug);
        View v = findViewById(R.id.button3);
        bt = (Button) v;
        bt.setText("Bug fixed");
    }
}
