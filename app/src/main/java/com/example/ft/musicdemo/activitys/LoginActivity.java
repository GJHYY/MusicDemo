package com.example.ft.musicdemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ft.musicdemo.activitys.BaseActivity;
import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.utils.UserUtiles;
import com.example.ft.musicdemo.views.InputView;

/*
* NavigationBar
* */
public class LoginActivity extends BaseActivity {

    private InputView mInputPhone,mInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    /*
    * 初始化View
    * */
    private void initView(){
        noShowAcBar();
        initNavBar(false,"登陆",false);
        mInputPhone=fd(R.id.input_phone);
        mInputPassword=fd(R.id.input_password);
    }

    /*
    * 跳转注册页面点击事件
    * */
    public void onRegClick1(View view){
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    /*
    * 登录
    * */
    public void onCommitClick(View view){
        String phone=mInputPhone.getInputStr();
        String password=mInputPassword.getInputStr();
        //验证用户输入是否合法
        if (!UserUtiles.validateLogin(this,phone,password)){
            return;
        }
        //跳转应用主页
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
