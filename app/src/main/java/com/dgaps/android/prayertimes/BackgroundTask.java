package com.dgaps.android.prayertimes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by waqas on 25/05/2016.
 */
public class BackgroundTask extends AsyncTask<String ,Void,String> {
    public SharedPreferences settings;
    private String TAG="mytag";
    Context ctx;
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    ProgressDialog dialog ;

    public static String method="";
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected void onPreExecute() {
        dialog  = ProgressDialog.show(ctx, "",
                "Loading. Please wait...", true);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params){
        String stringUrl = params[0];
        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        }
        catch(IOException e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.hide();
    if(result!=null) {

        Log.d("magic","not null");
        String timings, timings1, Fajr, Sunrise,Dhuhr,Asar,Sunset,Maghrib,Isha,Imsak,Midnight;
        try {
            Log.d("magic",result);

            JSONObject jsonObject=new JSONObject(result);
            timings=jsonObject.getString("data");
            Log.d("magicF",timings);
            JSONObject jsonObject2=new JSONObject(timings);
            timings1=jsonObject2.getString("timings");
            Log.d("magic1",timings1);

            JSONObject jsonObject3=new JSONObject(timings1);

            Fajr=jsonObject3.getString("Fajr");
            Sunrise=jsonObject3.getString("Sunrise");
            Dhuhr=jsonObject3.getString("Dhuhr");
            Asar=jsonObject3.getString("Asr");
            Sunset=jsonObject3.getString("Sunset");
            Maghrib=jsonObject3.getString("Maghrib");
            Isha=jsonObject3.getString("Isha");
            Imsak=jsonObject3.getString("Imsak");
            Midnight=jsonObject3.getString("Midnight");

            MainActivity.tvFajr.setText(Fajr);
            MainActivity.tvSunrise.setText(Sunrise);
            MainActivity.tvDhuhr.setText(Dhuhr);
            MainActivity.tvAsar.setText(Asar);
            MainActivity.tvSunset.setText(Sunset);
            MainActivity.tvMaghrib.setText(Maghrib);
            MainActivity.tvIsha.setText(Isha);
            MainActivity.tvImsak.setText(Imsak);
            MainActivity.tvMidnight.setText(Midnight);
    /*
            Log.d("magicx",Fajr);
            Log.d("magicx",Sunrise);
            Log.d("magicx",Dhuhr);
            Log.d("magicx",Asar);
            Log.d("magicx",Sunset);
            Log.d("magicx",Maghrib);
            Log.d("magicx",Isha);
            Log.d("magicx",Imsak);
            Log.d("magicx",Midnight);*/

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    else{
        Toast.makeText(ctx,"Result Not Found",Toast.LENGTH_LONG).show();
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

}