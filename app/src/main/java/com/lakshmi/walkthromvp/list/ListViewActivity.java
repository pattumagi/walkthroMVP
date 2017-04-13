package com.lakshmi.walkthromvp.list;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lakshmi.walkthromvp.R;

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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mgs1899 on 4/13/2017.
 */

public class ListViewActivity extends Activity {

    @BindView(R.id.listview)
    ListView listview;

    ArrayList<String> arname=new ArrayList<>();

    ArrayAdapter aa;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);
        ButterKnife.bind(this);




        new CallAsyncTask().execute();
    }

    private class CallAsyncTask extends AsyncTask<Void, String, String> {


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
                aa=new ArrayAdapter(ListViewActivity.this,android.R.layout.simple_list_item_1,arname);
                listview.setAdapter(aa);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
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
