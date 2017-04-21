package com.lakshmi.walkthromvp.common.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.lakshmi.walkthromvp.common.AppConstants;


/**
 * Created by mgs1850 on 1/4/2017.
 */

public class SharedPreferenceHelper {
    public static SharedPreferenceHelper SHARED_PREFERENCE_INSTANCE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void initSharedPreferenceHelper(Context context) {
        SHARED_PREFERENCE_INSTANCE = new SharedPreferenceHelper(context);
    }

    public static SharedPreferenceHelper getInstance() {
//        return SHARED_PREFERENCE_INSTANCE != null ? SHARED_PREFERENCE_INSTANCE : (SHARED_PREFERENCE_INSTANCE = new SharedPreferenceHelper(context));
        return SHARED_PREFERENCE_INSTANCE;
    }

//      public void setAppPassCode(String passCode) {
//        editor.putString(AppConstants.APP_PASS_CODE, passCode);
//        editor.commit();
//    }
//
//    public String getAppPassCode() {
//        return sharedPreferences.getString(AppConstants.APP_PASS_CODE, "");
//    }

    public String getSimStatePair() {
        return sharedPreferences.getString(AppConstants.SIM_STATE_PAIR, "");
    }

    public void setSimStatePair(String simStatePair) {
        editor.putString(AppConstants.SIM_STATE_PAIR, simStatePair);
        editor.commit();
    }

    public void setSimStatePair(int simSerialNoLength) {
        editor.putInt(AppConstants.SIM_SERIALNO_LENGTH, simSerialNoLength);
        editor.commit();
    }

    public int getSimSerialNoLength() {
        return (sharedPreferences.getInt(AppConstants.SIM_SERIALNO_LENGTH, 19));
    }
//
//    public String getListKeyToken() {
//        return (sharedPreferences.getString(AppConstants.LIST_KEY_TOKEN, ""));
//    }
//
//    public String getChallangeType() {
//        return (sharedPreferences.getString(AppConstants.CHALLANGE_TYPE, ""));
//    }
//
//    public String getChallangeSubtype() {
//        return (sharedPreferences.getString(AppConstants.CHALLANGE_SUB_TYPE, ""));
//    }
//
    public String getMobileNumber() {
        return (sharedPreferences.getString(AppConstants.MOBILE_NUMBER, ""));
    }
//
//
//
//
//    public void setListKeyToken(String listKeyToken) {
//        editor.putString(AppConstants.LIST_KEY_TOKEN, listKeyToken);
//        editor.commit();
//    }
//
//    public void setChallangeType(String challangeType) {
//        editor.putString(AppConstants.CHALLANGE_TYPE, challangeType);
//        editor.commit();
//    }
//
//    public void setChallangeSubtype(String challangeSubtype) {
//        editor.putString(AppConstants.CHALLANGE_SUB_TYPE, challangeSubtype);
//        editor.commit();
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        editor.putString(AppConstants.MOBILE_NUMBER, mobileNumber);
//        editor.commit();
//    }
//
//
//
//    public void setHandShake(boolean status) {
//        editor.putBoolean(AppConstants.HANDSHAKE_WITH_SERVER, status);
//        editor.commit();
//    }
//
//    public void setClientSecret(String clientSecret) {
//        editor.putString(AppConstants.CLIENT_SECRET, clientSecret);
//        editor.commit();
//    }
//
//    public String getClientSecret() {
//        return sharedPreferences.getString(AppConstants.CLIENT_SECRET, "");
//    }
//
//    public boolean hasEstablishedHandShake() {
//        return sharedPreferences.getBoolean(AppConstants.HANDSHAKE_WITH_SERVER, false);
//    }
//
//    public void setRateusFlag(boolean rateusFlag) {
//        editor.putBoolean(AppConstants.RATE_US, rateusFlag);
//        editor.commit();
//    }
//    public boolean hasRateusFlag() {
//        return sharedPreferences.getBoolean(AppConstants.RATE_US, false);
//    }
}
