package com.dgaps.android.prayertimes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText country, city;
    Button btn;
    Spinner spinner;
    public static TextView tvFajr, tvSunrise, tvDhuhr, tvAsar, tvSunset, tvMaghrib, tvIsha, tvImsak, tvMidnight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("myPref", MODE_PRIVATE);
        String getUserName = preferences.getString("userName", "null");

        Toast.makeText(this, "Welcome "+getUserName, Toast.LENGTH_SHORT).show();
        initializeAll();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String City, Country, StringUrl;
                int SpinnerVal;
                View v = MainActivity.this.getCurrentFocus();
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                City = city.getText().toString().trim();
                Country = country.getText().toString().trim();
                SpinnerVal = spinner.getSelectedItemPosition();
                if (SpinnerVal == 6) {
                    SpinnerVal = 7;
                }
                StringUrl = "http://api.aladhan.com/timingsByCity?city=" + City + "&country=" + Country + "&method=" + SpinnerVal;
                BackgroundTask backgroundTask2 = new BackgroundTask(MainActivity.this);
                backgroundTask2.execute(StringUrl);
            }
        });
    }

    private void initializeAll() {
        country = (EditText) findViewById(R.id.ETcountry);
        city = (EditText) findViewById(R.id.ETcity);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.method, R.layout.custom_text_spinner);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_text_spinner);
        spinner.setAdapter(spinnerArrayAdapter);

        btn = (Button) findViewById(R.id.button);

        tvFajr = (TextView) findViewById(R.id.Fajr);
        tvSunrise = (TextView) findViewById(R.id.Sunrise);
        tvDhuhr = (TextView) findViewById(R.id.Dhuhr);
        tvAsar = (TextView) findViewById(R.id.Asr);
        tvSunset = (TextView) findViewById(R.id.Sunset);
        tvMaghrib = (TextView) findViewById(R.id.Maghrib);
        tvIsha = (TextView) findViewById(R.id.Isha);
        tvImsak = (TextView) findViewById(R.id.Imsak);
        tvMidnight = (TextView) findViewById(R.id.Midnight);

        MainActivity.tvFajr.setText("");
        MainActivity.tvSunrise.setText("");
        MainActivity.tvDhuhr.setText("");
        MainActivity.tvAsar.setText("");
        MainActivity.tvSunset.setText("");
        MainActivity.tvMaghrib.setText("");
        MainActivity.tvIsha.setText("");
        MainActivity.tvImsak.setText("");
        MainActivity.tvMidnight.setText("");

    }


}
