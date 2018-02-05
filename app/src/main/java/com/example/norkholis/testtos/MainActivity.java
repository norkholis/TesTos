package com.example.norkholis.testtos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    TextView txt_username, txt_token;
    Button btn_logout;
    String result_username, token;
    SharedPrefManager sharedPrefManager;

    BaseApiService mBaseApiService;
    private RecyclerView recyclerview;
    private KendaraanAdapter mKendaraanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_logout = (Button)findViewById(R.id.btn_logout);
        txt_username = (TextView)findViewById(R.id.txt_username);
        txt_token = (TextView)findViewById(R.id.txt_token);
        sharedPrefManager = new SharedPrefManager(this);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            result_username = extras.getString("result_username");
            txt_username.setText(result_username);
            token = extras.getString("token");
            txt_token.setText(token);
        }
        if (sharedPrefManager.getSpIsLogin()){
            String nama = sharedPrefManager.getSpName();
            txt_username.setText(nama);
            String token = sharedPrefManager.getSpToken();
            txt_token.setText(token);

            mBaseApiService = UtilApi.getApiService();
            mBaseApiService.dataKendaraan(token).enqueue(new Callback<List<Kendaraan>>() {
                @Override
                public void onResponse(Call<List<Kendaraan>> call, Response<List<Kendaraan>> response) {
                    if (response.isSuccessful()){
                        List<Kendaraan> listKendaraan = response.body();
                        recyclerview = (RecyclerView)findViewById(R.id.listKendaraan);
                        mKendaraanAdapter = new KendaraanAdapter(listKendaraan);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerview.setLayoutManager(mLayoutManager);
                        recyclerview.setItemAnimator(new DefaultItemAnimator());
                        recyclerview.setAdapter(mKendaraanAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Kendaraan>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }

        mBaseApiService = UtilApi.getApiService();
        mBaseApiService.dataKendaraan(token).enqueue(new Callback<List<Kendaraan>>() {
            @Override
            public void onResponse(Call<List<Kendaraan>> call, Response<List<Kendaraan>> response) {
                if (response.isSuccessful()){
                    List<Kendaraan> listKendaraan = response.body();
                    recyclerview = (RecyclerView)findViewById(R.id.listKendaraan);
                    mKendaraanAdapter = new KendaraanAdapter(listKendaraan);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerview.setLayoutManager(mLayoutManager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mKendaraanAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Kendaraan>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
            }
        });

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
