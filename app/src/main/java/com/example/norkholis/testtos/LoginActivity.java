package com.example.norkholis.testtos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btn_login;
    private ArrayList<User> userList;

    SharedPrefManager sharedPrefManager;

    Context mContext;
    BaseApiService mBaseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userList = new ArrayList<>();

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSpIsLogin()){
            Intent i = new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }

        mContext = this;
        mBaseApiService = UtilApi.getApiService();
        initComponents();
    }

    private void initComponents() {
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRequest();
            }
        });
    }

    private void loginRequest() {
        mBaseApiService.loginRequest(username.getText().toString(), password.getText().toString())
                .enqueue(new Callback<ListUser>() {
                    @Override
                    public void onResponse(Call<ListUser> call, Response<ListUser> response) {

                        if(response.isSuccessful()){
                            userList = response.body().getData();

                            if (userList!=null) {
                                User user = userList.get(0);

                                String username = user.getUsername().toString();
                                String token = user.getToken().toString();
                                String nama = user.getNamaLengkap().toString();
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, username);
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, token);
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_NAME, nama);

                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_IS_LOGIN, true);
                                Intent i = new Intent(LoginActivity.this, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                Bundle extras = new Bundle();
                                extras.putString("result_username", nama);
                                extras.putString("token", token);
                                i.putExtras(extras);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this,"Udah capek, masak ya harus salah lagi", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"Something Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ListUser> call, Throwable t) {

                    }
                });
    }
}
