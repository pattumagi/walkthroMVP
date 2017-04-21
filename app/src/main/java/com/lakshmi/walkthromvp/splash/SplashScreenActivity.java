package com.lakshmi.walkthromvp.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.safetynet.SafetyNet;
import com.lakshmi.walkthromvp.base.BaseActivity;
import com.lakshmi.walkthromvp.common.DeviceDetailsSingleton;
import com.lakshmi.walkthromvp.common.LogUtil;
import com.lakshmi.walkthromvp.login.LoginActivity;
import com.lakshmi.walkthromvp.R;

import butterknife.BindView;

/**
 * Created by mgs1899 on 4/12/2017.
 */

public class SplashScreenActivity extends BaseActivity implements SplashContract.View, GoogleApiClient.OnConnectionFailedListener {


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
        Toast.makeText(this,messageRes,Toast.LENGTH_LONG).show();


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
