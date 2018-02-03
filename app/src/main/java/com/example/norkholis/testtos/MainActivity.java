package com.example.norkholis.testtos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_username;
    String result_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_username = (TextView)findViewById(R.id.txt_username);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            result_username = extras.getString("result_username");
            txt_username.setText(result_username);
        }
    }
}
