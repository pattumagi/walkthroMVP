package com.lakshmi.walkthromvp.recyclerexample;

import android.os.AsyncTask;

import com.lakshmi.walkthromvp.list.ListViewContractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mgs1899 on 4/15/2017.
 */

public class CallRecyclerAsyncTask extends AsyncTask<Void, String, String>  {
    RecyclerContract.Presenter view;
    String str_url;


    public CallRecyclerAsyncTask(RecyclerContract.Presenter view, String url) {
        this.view=view;
       str_url=url;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {

            URL url = new URL(str_url);
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
