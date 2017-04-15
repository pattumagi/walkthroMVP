package com.lakshmi.walkthromvp.list;

import android.os.AsyncTask;
import android.util.Log;

import com.lakshmi.walkthromvp.login.LoginContract;

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
import java.util.ArrayList;

/**
 * Created by mgs1899 on 4/15/2017.
 */

public class CallAsyncTask extends AsyncTask<Void, String, String>  {
    ListViewContractor.Presenter view;


    public CallAsyncTask(ListViewContractor.Presenter view) {
        this.view=view;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL("https://api.learn2crack.com/android/json/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream is = con.getInputStream();
            return readStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);

        view.callPostExecute(str);

    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }



}
