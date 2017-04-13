package com.lakshmi.walkthromvp.login;

import android.widget.EditText;

/**
 * Created by mgs1899 on 4/13/2017.
 */

public interface LoginContract {

    interface View{

        String getUsername();
        String getPassword();


        void showToast();
        void gotoNextView();

        void setUsernameError();
        void setPasswordError();

    }

    interface Presenter{

        void validiateLoginValues();

    }
}
