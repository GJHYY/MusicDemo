package com.example.ft.musicdemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.helps.UserHelp;
import com.example.ft.musicdemo.utils.UserUtiles;

public class MeActivity extends BaseActivity {
    private TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    private void initView() {
        noShowAcBar();
        initNavBar(true,"个人中心",false);
        mTvUser=fd(R.id.tv_user);
        mTvUser.setText("用户名："+UserHelp.getInstance().getPhone());

    }

    /*
    * 修改密码点击事件
    * */
    public void onChangeClick(View view){
        Intent intent=new Intent(this,ChangePasswordActivity.class);
        startActivity(intent);
    }

    /*
    * 退出登录
    * */
    public void onLogoutClick(View view){
        UserUtiles.logout(this);
    }
}

