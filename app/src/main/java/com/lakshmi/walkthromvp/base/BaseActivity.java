package com.lakshmi.walkthromvp.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


import com.lakshmi.walkthromvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mgs1899 on 4/15/2017.
 */

public class BaseActivity extends Activity {


    @BindView(R.id.btn_error)
    Button btn_error;

    @BindView(R.id.noConnectionLayout)
    RelativeLayout noConnectionLayout;

    ConnectivityManager connectivityManager;

    private BroadcastReceiver networkDetectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkInternetConnection();
        }
    };

    private void checkInternetConnection() {


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!hasConnectivity()) {
                    noConnectionLayout.setVisibility(View.VISIBLE);
                } else {
                  noConnectionLayout.setVisibility(View.GONE);
                }
            }
        }, 100);
    }

    public boolean hasConnectivity() {

            connectivityManager.getActiveNetworkInfo();
            return connectivityManager.getActiveNetworkInfo() != null;


    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        checkInternetConnection();


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkDetectReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onPause() {
        super.onPause();
       unregisterReceiver(networkDetectReceiver);

    }

}
