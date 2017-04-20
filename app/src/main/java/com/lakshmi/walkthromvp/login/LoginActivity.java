package com.lakshmi.walkthromvp.login;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lakshmi.walkthromvp.R;
import com.lakshmi.walkthromvp.base.BaseActivity;
import com.lakshmi.walkthromvp.list.ListViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnClickListener {
    @BindView(R.id.edt_username)
    EditText edt_username;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.actualview)
    LinearLayout actualview;
    @BindView(R.id.btn_error)
    Button btn_error;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);

        btn_submit.setOnClickListener(this);
    }

    @Override
    public String getUsername() {

        return edt_username.getText().toString().trim();
    }

    @Override
    public String getPassword() {

        return edt_password.getText().toString().trim();
    }

    @Override
    public void showToast() {

        Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotoNextView() {

        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);

    }

    @Override
    public void setUsernameError() {
        edt_username.setError("Error");
    }

    @Override
    public void setPasswordError() {
        edt_password.setError("Error");
    }

    @Override
    public void onClick(android.view.View v) {
        presenter.validiateLoginValues();
    }
}
