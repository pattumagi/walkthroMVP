package com.lakshmi.walkthromvp.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


import com.lakshmi.walkthromvp.R;
import com.lakshmi.walkthromvp.common.DeviceDetailsSingleton;
import com.lakshmi.walkthromvp.common.LogUtil;
import com.lakshmi.walkthromvp.splash.SplashScreenActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mgs1899 on 4/15/2017.
 */

public class BaseActivity extends AppCompatActivity {


    private String TAG = getClass().getSimpleName();
    @BindView(R.id.btn_error)
    Button btn_error;

    @BindView(R.id.noConnectionLayout)
    RelativeLayout noConnectionLayout;

    ConnectivityManager connectivityManager;
    ProgressDialog mProgressDialog;

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

        if (!(this instanceof SplashScreenActivity) && DeviceDetailsSingleton.getInstance() == null) {
            DeviceDetailsSingleton.init(getApplicationContext());
        }

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

    public boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }


    public void showProgressDialog(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.show();
            mProgressDialog.setMessage(message);
        }
    }


    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


}
