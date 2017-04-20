package com.lakshmi.walkthromvp.common;

import android.util.Log;


/**
 * Created by mgs1850 on 1/4/2017.
 */

public class LogUtil {
    public static void info(String Tag, String message) {
        if (BuildConfig.BUILD_TYPE.equals("release"))
            return;
        Log.d(Tag, message);
    }

//    public static void printObject(Object object) {
//        if (BuildConfig.BUILD_TYPE.equals("release"))
//            return;
//        String string = ((new Gson()).toJson(object));
//        LogUtil.info(object.getClass().getSimpleName(), string);
//    }

//    public static void printVolleyError(VolleyError error) {
//        if (BuildConfig.BUILD_TYPE.equals("release"))
//            return;
//        error.printStackTrace();
//
//    }
}
