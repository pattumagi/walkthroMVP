package com.lakshmi.walkthromvp.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.lakshmi.walkthromvp.login.LoginActivity;
import com.lakshmi.walkthromvp.R;

/**
 * Created by mgs1899 on 4/12/2017.
 */

public class SplashScreenActivity extends Activity  implements SplashContract{


    SplashPresenter presenter;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        presenter = new SplashPresenter(this);
        presenter.callMain();


    }

    @Override
    public void gotoMain() {

        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);

    }
}
