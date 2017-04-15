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

public class ListViewActivity extends Activity implements ListViewContractor.View {

    @BindView(R.id.listview)
    ListView listview;

    ListViewPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);
        ButterKnife.bind(this);

         presenter=new ListViewPresenter(this);
         presenter.callAsyncTask();

    }

    @Override
    public void setadapterListView( ArrayList<String> arname) {

        ArrayAdapter aa=new ArrayAdapter(ListViewActivity.this,android.R.layout.simple_list_item_1,arname);
        listview.setAdapter(aa);

    }


}
