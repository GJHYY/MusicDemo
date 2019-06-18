package com.example.ft.musicdemo.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;

import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.utils.UserUtiles;
import com.example.ft.musicdemo.views.InputView;

public class ChangePasswordActivity extends BaseActivity {
    private InputView mOldPassword,mPassword,mPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        noShowAcBar();
        initNavBar(true,"修改密码",false);
        mOldPassword=fd(R.id.input_phone_change);
        mPassword=fd(R.id.input_password_change);
        mPasswordConfirm=fd(R.id.input_password_change_confirm);
    }

    public void onChangePasswordClick(View view){
        String oldPassword=mOldPassword.getInputStr();
        String password=mPassword.getInputStr();
        String passwordConfirm=mPasswordConfirm.getInputStr();

        boolean result=UserUtiles.changePassword(this,oldPassword,password,passwordConfirm);
        if (!result){
            //修改失败
            return;
        }
        UserUtiles.logout(this);

    }


}
