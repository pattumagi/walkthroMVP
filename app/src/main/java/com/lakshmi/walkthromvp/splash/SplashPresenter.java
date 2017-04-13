package com.lakshmi.walkthromvp.splash;


/**
 * Created by mgs1899 on 4/12/2017.
 */

public class SplashPresenter   {

    SplashContract view;

    public SplashPresenter(SplashContract view) {
        this.view = view;


    }


    void callMain(){
        view.gotoMain();
    }


}
