package com.lakshmi.walkthromvp.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.safetynet.SafetyNet;
import com.lakshmi.walkthromvp.base.BaseActivity;
import com.lakshmi.walkthromvp.common.AppConstants;
import com.lakshmi.walkthromvp.common.DeviceDetailsSingleton;
import com.lakshmi.walkthromvp.common.LogUtil;
import com.lakshmi.walkthromvp.login.LoginActivity;
import com.lakshmi.walkthromvp.R;

import butterknife.BindView;

/**
 * Created by mgs1899 on 4/12/2017.
 */

public class SplashScreenActivity extends BaseActivity implements SplashContract.View, GoogleApiClient.OnConnectionFailedListener {


    private static final int REQUEST_READ_PHONE_STATE = 101;
    SplashPresenter presenter;
    @BindView(R.id.actualview)
    RelativeLayout actualview;
    @BindView(R.id.btn_error)
    Button btn_retry;
    GoogleApiClient googleApiClient;
    private String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
      //  checkForPermission();
        presenter = new SplashPresenter(this);
        presenter.callView();
        buildGoogleApiClient();
        DeviceDetailsSingleton.init(getApplicationContext());
        presenter.triggerSafetyNet(googleApiClient);
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callView();
            }
        });


    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .enableAutoManage(this, this)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        LogUtil.info(TAG,
                "Error connecting to Google Play Services." + connectionResult.getErrorMessage());
    }

    @Override
    public void gotoMain() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void callMain() {

        if (hasConnectivity()) {
            actualview.setVisibility(View.VISIBLE);
            presenter.callMain();
        } else {
            actualview.setVisibility(View.GONE);
        }


    }

    @Override
    public void showErrorScreen(String messageRes) {
        Toast.makeText(this, messageRes, Toast.LENGTH_LONG).show();


    }

    @Override
    public void checkForPermission() {
//        String[] permissions = new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE};
//
//        if (hasPermissions(permissions, AppConstants.SPLASH_PERMISSION)) {
//            //proceedProcess();
//            callMain();
//
//        }

//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
//
//        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
//        } else {
//            //TODO
//            callMain();
//        }

        //First checking if the app is already having the permission
        if (isReadStorageAllowed()) {
            //If permission is  there already
            callMain();
            //Existing the method with return
            return;
        }

        //If the app has not the permission then asking for the permission
        requestStoragePermission();
    }

    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission() {

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_PHONE_STATE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode == REQUEST_READ_PHONE_STATE) {
            callMain();
        }

    }

    @Override
    public void onUnStableConnection(int reasonFlag) {

    }

    @Override
    public void showAlert(int stringResId) {

    }

    @Override
    public void showAlert(String message) {

    }

    @Override
    public void showToast(int stringResId) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showProgressDialog(int resId) {

    }

    @Override
    public void showProgressDialog() {

    }
}
