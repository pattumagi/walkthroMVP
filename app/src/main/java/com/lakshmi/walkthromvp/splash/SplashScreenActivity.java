package com.lakshmi.walkthromvp.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lakshmi.walkthromvp.base.BaseActivity;
import com.lakshmi.walkthromvp.login.LoginActivity;
import com.lakshmi.walkthromvp.R;

import butterknife.BindView;

/**
 * Created by mgs1899 on 4/12/2017.
 */

public class SplashScreenActivity extends BaseActivity implements SplashContract{


    SplashPresenter presenter;
    @BindView(R.id.actualview)
    RelativeLayout actualview;
    @BindView(R.id.btn_error)
    Button btn_retry;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        presenter = new SplashPresenter(this);
        presenter.callView();




        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callView();
            }
        });


    }

    @Override
    public void gotoMain() {

        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void callMain() {

        if(hasConnectivity()) {
            actualview.setVisibility(View.VISIBLE);
            presenter.callMain();
        }
        else
        {
            actualview.setVisibility(View.GONE);
        }


    }


}
