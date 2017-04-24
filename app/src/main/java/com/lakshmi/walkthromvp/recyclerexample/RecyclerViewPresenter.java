package com.lakshmi.walkthromvp.recyclerexample;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lakshmi.walkthromvp.common.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgs1899 on 4/24/2017.
 */

public class RecyclerViewPresenter  implements RecyclerContract.Presenter{

RecyclerContract.View view;
    recyclerPOJO item;
    ArrayList<recyclerPOJO> arname = new ArrayList<recyclerPOJO>();
    public RecyclerViewPresenter(RecyclerContract.View view) {
        this.view=view;
    }

    public void callRecyclerAsync() {
        new CallRecyclerAsyncTask(this, AppConstants.recycle_url).execute();
    }


    @Override
    public void callPostExecute(String str) {


        Log.d("Lakshmi", "This is str  " + str);
        try {
            JSONObject jobj = new JSONObject(str);
            JSONArray jarray = jobj.getJSONArray("contacts");
            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jsonObject = jarray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String email = jsonObject.getString("email");


                 item = new recyclerPOJO(id,name,email);

                for(i=0;i<jarray.length();i++)
                {
                    arname.add(item);
                }

            }
            view.setadapterRecycler(arname);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void callOnClickPosition(int position) {

        Toast.makeText((Context) view,arname.get(position).name.toString()+" this is position "+position,Toast.LENGTH_LONG).show();
    }
}
