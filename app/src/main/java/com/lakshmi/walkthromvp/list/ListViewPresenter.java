package com.lakshmi.walkthromvp.list;

import android.os.AsyncTask;
import android.util.Log;

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
 * Created by mgs1899 on 4/13/2017.
 */

public class ListViewPresenter implements ListViewContractor.Presenter {
    ListViewActivity view;




    public ListViewPresenter(ListViewActivity view) {

        this.view = view;
    }

    @Override
    public void callAsyncTask() {


       new CallAsyncTask(this).execute();

    }

    @Override
    public void callPostExecute(String str) {
        ArrayList arname=new ArrayList<>();
        Log.d("Lakshmi", "This is str  " + str);
        try {
            JSONObject jobj = new JSONObject(str);
            JSONArray jarray = jobj.getJSONArray("user");
            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jsonObject = jarray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String email = jsonObject.getString("email");
                Log.d("Lakshmi", "This is name " + name);


                for (int j = 0; j < 10; j++) {
                    arname.add(name);
                }
            }
            view.setadapterListView(arname);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}