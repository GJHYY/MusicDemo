package com.example.ft.musicdemo.activitys;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ft.musicdemo.R;

public class BaseActivity extends Activity {
    /*
    * 作为所有Activity的父类
    * */

    private ImageView mIvBack, mIvMe;
    private TextView mTvTitle;

    /*
    * findViewById
    * @param id
    * @param <T>
    * @return
    * */
    protected <T extends View>T fd(@IdRes int id){
        return findViewById(id);
    }

    /*
    *初始化NavigationBar
    * @param isShowBack
    * @param ShowTitle
    * @param isShowMe
    * */
    protected void initNavBar(boolean isShowBack,String ShowTitle,boolean isShowMe){
        mIvBack=fd(R.id.iv_back);
        mTvTitle=fd(R.id.tv_title);
        mIvMe=fd(R.id.iv_me);
        mIvBack.setVisibility(isShowBack ? View.VISIBLE:View.GONE);
        mIvMe.setVisibility(isShowMe ? View.VISIBLE:View.GONE);
        mTvTitle.setText(ShowTitle);
        mIvBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();//回退操作
            }
        });
        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BaseActivity.this,MeActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
    * 取消系统ActionBar
    * */
    protected void noShowAcBar(){
        ActionBar actionBar=getActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }

}
