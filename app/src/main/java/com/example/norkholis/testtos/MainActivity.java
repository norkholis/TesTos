package com.example.norkholis.testtos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_username;
    Button btn_logout;
    String result_username;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_logout = (Button)findViewById(R.id.btn_logout);
        txt_username = (TextView)findViewById(R.id.txt_username);
        sharedPrefManager = new SharedPrefManager(this);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            result_username = extras.getString("result_username");
            txt_username.setText(result_username);
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_IS_LOGIN, false);
                Intent i = new Intent(MainActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}
