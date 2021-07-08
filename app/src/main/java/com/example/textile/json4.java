package com.example.textile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class json4 extends AppCompatActivity implements Adapter.OnItemClickListener {
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_IMG = "imgUrl";
    public static final String EXTRA_NAME = "name";

    RecyclerView recyclerView;
    List<detail> details ;
    private static String JSON_URL = "https://api.github.com/search/users?q=language:java+location:lagos";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json4);

        recyclerView = findViewById(R.id.rec);
        details = new ArrayList<>();

        extractDetail();

    }

    private void extractDetail() {
        RequestQueue mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("Data--->",response.toString());
                    JSONArray jsonArray = response.getJSONArray("items");
                    for(int i = 0; jsonArray.length() > i; i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        detail det = new detail();
                        det.setName(jsonObject.getString("login"));
                        det.setImgURL(jsonObject.getString("avatar_url"));
                        det.setUrl(jsonObject.getString("url"));
                        details.add(det);
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), details);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(json4.this);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse"+ error.getMessage());
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(json4.this, json5.class);
        detail clickedItem = details.get(position);

        intent.putExtra(EXTRA_IMG, clickedItem.getImgURL());
        intent.putExtra(EXTRA_URL, clickedItem.getUrl());
        intent.putExtra(EXTRA_NAME, clickedItem.getName());

        startActivity(intent);
    }
}
