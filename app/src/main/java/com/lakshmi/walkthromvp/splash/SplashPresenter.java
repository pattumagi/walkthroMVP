package com.lakshmi.walkthromvp.splash;


import android.os.Handler;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.lakshmi.walkthromvp.R;
import com.lakshmi.walkthromvp.base.BaseActivity;
import com.lakshmi.walkthromvp.common.AppConstants;
import com.lakshmi.walkthromvp.common.DeviceDetailsSingleton;
import com.lakshmi.walkthromvp.common.LogUtil;
import com.lakshmi.walkthromvp.common.NativeInteractor;
import com.lakshmi.walkthromvp.common.SafetyNetResponse;

import java.util.Random;

/**
 * Created by mgs1899 on 4/12/2017.
 */

public class SplashPresenter extends BaseActivity implements SplashContract.Presenter{

    SplashContract.View view;
    private String TAG=getClass().getSimpleName();

    public SplashPresenter(SplashContract.View view) {
        this.view = view;


    }

   void callView()
   {
       view.callMain();
   }



    void callMain(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    view.gotoMain();

            }
        },1500);

    }


    @Override
    public void triggerSafetyNet(GoogleApiClient googleApiClient) {
        view.showProgressDialog();

        String nonceData = DeviceDetailsSingleton.getInstance().generateUUID() + "-" + DeviceDetailsSingleton.getInstance().getAndroidId();
        long nonce = getNonce();
        byte[] nonceByte = (nonce + "").getBytes();
        LogUtil.info(TAG, "Nonce String : " + nonceData + " Nonce : " + nonce);
      //  NativeInteractor.getInstance().setNonce(nonce + "");
        SafetyNet.SafetyNetApi.attest(googleApiClient, nonceByte)
                .setResultCallback(new ResultCallback<SafetyNetApi.AttestationResult>() {
                    @Override
                    public void onResult(SafetyNetApi.AttestationResult result) {
                     //   LogUtil.info(TAG, "Nonce : " + NativeInteractor.getInstance().getNonce());
                        Status status = result.getStatus();
                        if (status.isSuccess()) {
                            String googleResponse = result.getJwsResult();
                            LogUtil.info(TAG, "Success! SafetyNet result:\n" + googleResponse + "\n");
                            onSafetyNetResponse(result.getJwsResult());
                        } else {
                            LogUtil.info(TAG, "ERROR! " + status.getStatusCode() + " " + status
                                    .getStatusMessage());
                            view.showErrorScreen("Error");
                            view.dismissProgressDialog();
                        }
                    }
                });

    }

    public static long getNonce() {

        long min = 10000000000000L;
        long max = 99999999999999L;
        Random random = new Random();
        long number = min + ((long) (random.nextDouble() * (max - min)));
        return number;

    }

    public void onSafetyNetResponse(String response) {
//        NetworkCallSingleton.getInstance().getCallApi().validateDeviceIntegrity(response, new Response.Listener<SafetyNetResponse>() {
//            @Override
//            public void onResponse(SafetyNetResponse response) {
//                view.dismissProgressDialog();
//                if (response == null) {
//                    view.onUnStableConnection(AppConstants.SERVER_DOWN);
//                    return;
//                }
//                try {
//                    if (response.nonce.equals(NativeInteractor.getInstance().getNonce())) {
//                        if (response.basicIntegrity && response.ctsProfileMatch) {
//                            beginCommunication();
//                        } else {
//                            view.dismissProgressDialog();
//                            view.onUnReliableDevice();
//                        }
//                    } else
//                        view.onUnStableConnection(AppConstants.SSL_PINNING_FALIED);
//                } catch (Exception e) {
//                    LogUtil.printObject(e);
//                    view.onUnStableConnection(AppConstants.SERVER_DOWN);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//               // LogUtil.printVolleyError(error);
//                view.dismissProgressDialog();
//                if (error.getLocalizedMessage() != null && error.getLocalizedMessage().contains("CertificateException")) {
//                    view.onUnStableConnection(AppConstants.SSL_PINNING_FALIED);
//                } else
//                    view.showErrorScreen(R.string.something_went_wrong);
//            }
//        });
    }
}
