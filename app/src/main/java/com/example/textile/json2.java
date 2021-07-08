package com.example.textile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Locale;
import java.util.Queue;

public class json2 extends AppCompatActivity {

    public static TextView tv;
    Button buttonfetch;
    String data = "";
    RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json2);

        tv = findViewById(R.id.text);
        buttonfetch = findViewById(R.id.fetch);
        mQueue = Volley.newRequestQueue(this);

        buttonfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parse process = new parse();
                process.execute();
            }
        });
    }
}