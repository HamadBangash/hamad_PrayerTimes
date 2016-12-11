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

public class RegistrationActvity extends AppCompatActivity {
    Button btnRegister;
    EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_actvity);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = etUser.getText().toString();
                String password = etPass.getText().toString();

                if (userName.equals("") || password.equals("")) {
                    Toast.makeText(RegistrationActvity.this, "Enter Value", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences preferences = getSharedPreferences("myPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("userName", userName);
                    editor.putString("password", password);
                    editor.apply();

                    Intent intent = new Intent(RegistrationActvity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}
