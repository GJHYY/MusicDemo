package com.example.ft.musicdemo.activitys;

import android.content.Intent;
import android.os.Bundle;

import com.example.ft.musicdemo.activitys.BaseActivity;
import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.activitys.LoginActivity;
import com.example.ft.musicdemo.activitys.MainActivity;
import com.example.ft.musicdemo.utils.UserUtiles;

import java.util.Timer;
import java.util.TimerTask;

/*
* 1.延迟三秒
* 2.跳转页面
* */
public class WelcomeActivity extends BaseActivity {

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    /*
    * 初始化定时器（3秒跳转）
    * */
    private void init() {
        final boolean isLogin=UserUtiles.validateUserLogin(this);

        mTimer=new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //toMain();
                //toLoin();
                if (isLogin){
                    toMain();
                }else {
                    toLoin();
                }
            }
        },3*1000);
    }

    /*
     * 跳转到 MainActivity
     * */
    private void toMain(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /*
    * 跳转到 LoginActivity
    * */
    private void toLoin(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

}
