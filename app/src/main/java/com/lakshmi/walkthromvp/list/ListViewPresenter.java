package com.lakshmi.walkthromvp.list;

import android.util.Log;

import com.lakshmi.walkthromvp.common.AppConstants;
import com.lakshmi.walkthromvp.recyclerexample.recyclerPOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import butterknife.internal.Utils;

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


       new CallAsyncTask(this, AppConstants.list_url).execute();

    }

    @Override
    public void callPostExecute(String str) {
        ArrayList arname = new ArrayList<>();
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

    @Override
    public void callNextView() {
        view.callNextView();
    }
}

