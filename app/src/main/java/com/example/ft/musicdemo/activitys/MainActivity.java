package com.example.ft.musicdemo.activitys;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ft.musicdemo.activitys.BaseActivity;
import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.adapters.MusicGridAdapter;
import com.example.ft.musicdemo.adapters.MusicListAdapter;
import com.example.ft.musicdemo.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {
/*
 * 1.项目 project
 * 2.模块 module
 * */
    private RecyclerView mRvGrid ,mRvList;
    private MusicGridAdapter mAdapter;
    private MusicListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        noShowAcBar();
        initNavBar(false,"星音乐",true);
        mRvGrid=fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this,3));
        // mRvGrid.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));//系统设置分割线方法
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize),mRvGrid));//自定义设置分割线方法
        //解决滑动冲突
        mRvGrid.setNestedScrollingEnabled(false);
        mAdapter=new MusicGridAdapter(this);
        mRvGrid.setAdapter(mAdapter);

        /*
        * 1.假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义
        * 2.在不知道列表高度的情况下，需要手动计算RecyclerView的高度
        * */
        mRvList=fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //解决滑动冲突
        mRvList.setNestedScrollingEnabled(false);
        mListAdapter=new MusicListAdapter(this,mRvList);
        mRvList.setAdapter(mListAdapter);
    }
}
