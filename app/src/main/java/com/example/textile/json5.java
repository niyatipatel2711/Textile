package com.example.textile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.textile.json4.EXTRA_IMG;
import static com.example.textile.json4.EXTRA_NAME;
import static com.example.textile.json4.EXTRA_URL;

public class json5 extends AppCompatActivity {
    private TextView tvFullName, tvFollower, tvFollowing, loginName;
    private ImageView imageView;
    RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json5);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);
//        Log.e("URl--->",url);

        imageView = findViewById(R.id.img);
        loginName = findViewById(R.id.username);
        tvFullName = findViewById(R.id.name);
        tvFollower = findViewById(R.id.follower);
        tvFollowing = findViewById(R.id.following);

        mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                        Log.e("Response Data-->",response.toString());
                    String name = response.getString("login");
                    String fullName= response.getString("name");
                    String fol1 = response.getString("followers");
                    String fol2 = response.getString("following");
                    String profileURL = response.getString("avatar_url");

                    loginName.setText(name);
                    tvFullName.setText(fullName);
                    tvFollower.setText(fol1);
                    tvFollowing.setText(fol2);
                    Picasso.get().load(profileURL).fit().centerInside().into(imageView);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
            }
        });
        mQueue.add(jsonObjectRequest);

    }
}