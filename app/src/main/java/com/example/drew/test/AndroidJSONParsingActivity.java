package com.example.drew.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by RoSo on 4/7/17.
 * Source: http://stackoverflow.com/questions/18352688/parsing-json-into-android-textviews
 */

public class AndroidJSONParsingActivity extends Activity {

        // url to make request
        private static String url = "http://54.218.73.244:8084/";
        private HashMap<Integer, String> contentsMap = new HashMap<Integer, String>();


        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_next);



            TextView textView=(TextView) findViewById(R.id.textview1);


            TextView textView1=(TextView) findViewById(R.id.textview2);



            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            // Creating JSON Parser instance
            JSONParser jParser = new JSONParser();

            // getting JSON string from URL
            JSONObject json = jParser.getJSONFromUrl(url);

            JSONArray first_array = null;
            JSONArray second_array = null;

            try {
                first_array = json.getJSONArray("first");
                second_array = json.getJSONArray("second");

                for(int i=0; i < first_array.length(); i++)
                {
                    JSONObject detail_obj = first_array.getJSONObject(i);

                    Log.v("name--", detail_obj.getString("name"));

                    textView.setText(first_array.getJSONObject(2).getString("name"));
                }

                for(int i=0; i < second_array.length(); i++)
                {
                    JSONObject detail_obj = second_array.getJSONObject(i);

                    Log.v("place--", detail_obj.getString("place"));

                    textView1.setText(second_array.getJSONObject(0).getString("place"));
                }
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }



        }
        @Override
        protected void onResume() {
            super.onResume();

        }


    }
