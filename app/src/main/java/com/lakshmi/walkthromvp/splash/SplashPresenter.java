package com.lakshmi.walkthromvp.splash;


import android.os.Handler;

import com.lakshmi.walkthromvp.base.BaseActivity;

/**
 * Created by mgs1899 on 4/12/2017.
 */

public class SplashPresenter extends BaseActivity {

    SplashContract view;

    public SplashPresenter(SplashContract view) {
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


}
