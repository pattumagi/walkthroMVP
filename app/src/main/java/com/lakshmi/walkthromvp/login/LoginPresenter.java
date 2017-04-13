package com.lakshmi.walkthromvp.login;

import android.widget.EditText;

/**
 * Created by mgs1899 on 4/13/2017.
 */

public class LoginPresenter implements LoginContract.Presenter{

   LoginActivity view;


    public LoginPresenter(LoginActivity view) {
       this.view=view;

    }


    @Override
    public void validiateLoginValues() {
        String  uname= view.getUsername();
        String  pass=view.getPassword();


        if(uname.isEmpty()) view.setUsernameError();

        if(pass.isEmpty())  view.setPasswordError();

        if(!uname.isEmpty() && ! pass.isEmpty())
        {
            view.gotoNextView();

        }


    }


}
