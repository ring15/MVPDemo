package com.example.founq.mvpdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.founq.mvpdemo.R;
import com.example.founq.mvpdemo.mvp.presenter.LoginPresenter;
import com.example.founq.mvpdemo.mvp.presenter.LoginPresenterInter;
import com.example.founq.mvpdemo.mvp.view.LoginViewInter;

public class LoginActivity extends AppCompatActivity implements LoginViewInter,View.OnClickListener {

    EditText etName,etPassword;
    Button btnLogin;
    LoginPresenterInter loginPresenterInter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        loginPresenterInter = new LoginPresenter(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void show(String string) {
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                loginPresenterInter.dologin(etName.getText().toString(),etPassword.getText().toString());
                break;
        }
    }
}
