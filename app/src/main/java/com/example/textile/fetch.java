package com.example.textile;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetch extends AsyncTask<Void,Void,Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.github.com/users");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }


            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length();i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                singleParsed = "Login: " + jsonObject.get("login") + "\n" +
                                "Id: " + jsonObject.get("id") + "\n" +
                                "Avatar_url: " + jsonObject.get("avatar_url") + "\n" +
                                "Type: " + jsonObject.get("type") + "\n" +
                                "Site_admin: " + jsonObject.get("site_admin") + "\n\n";

                dataParsed = singleParsed + dataParsed;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        jsonParse.textView.setText(dataParsed);
    }
}
