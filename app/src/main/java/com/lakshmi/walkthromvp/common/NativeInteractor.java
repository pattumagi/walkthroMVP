package com.lakshmi.walkthromvp.common;

/**
 * Created by mgs1887 on 10-03-2017.
 */

public class NativeInteractor {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    static NativeInteractor nativeInteractor;

    private NativeInteractor() {

    }

    public static NativeInteractor getInstance() {
        return nativeInteractor == null ? (nativeInteractor = new NativeInteractor()) : nativeInteractor;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String getRefreshToken();

    public native String getSymmetricKey();

    public native String getOAuthPasswordDev();

    public native String getOAuthPasswordQa();

    public native String getOAuthPasswordProd();

    public native String getNonce();


    public native String getAccessToken();

    public native void setRefreshToken(String refreshToken);

    public native void setAccessToken(String accessToken);

    public native void setNonce(String nonce);


    public String getOAuthPassword() {
        if (BuildConfig.FLAVOR.equalsIgnoreCase("prod")) {
            return getOAuthPasswordProd();
        } else if (BuildConfig.FLAVOR.equalsIgnoreCase("qa")) {
            return getOAuthPasswordQa();

        } else
            return getOAuthPasswordDev();

    }
}
