package com.lakshmi.walkthromvp.base;

/**
 * Created by mgs1899 on 4/20/2017.
 */


    public interface BaseView {
        void onUnStableConnection(int reasonFlag);

        void showAlert(int stringResId);

        void showAlert(String message);

        void showToast(int stringResId);

        void showToast(String message);

        void showProgressDialog(int resId);

        void showProgressDialog();

        void dismissProgressDialog();


    }


