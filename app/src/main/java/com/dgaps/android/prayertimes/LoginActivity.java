package com.dgaps.android.prayertimes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLog;
    EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences = getSharedPreferences("myPref", MODE_PRIVATE);
        final String getUserName = preferences.getString("userName", "null");
        final String getPassword = preferences.getString("password", "null");
        if (getUserName.equals("null") && getPassword.equals("null")) {
            Intent intent = new Intent(LoginActivity.this, RegistrationActvity.class);
            startActivity(intent);
            finish();
        }
        btnLog = (Button) findViewById(R.id.btnLog);

        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etUser.getText().toString();
                String password = etPass.getText().toString();
                if (getUserName.equals(userName)&&getPassword.equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Sorry Invalid Username OR Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
