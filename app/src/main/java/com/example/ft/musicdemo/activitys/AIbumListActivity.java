package com.example.ft.musicdemo.activitys;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.adapters.MusicListAdapter;

public class AIbumListActivity extends BaseActivity {

    private RecyclerView mRvList;
    private MusicListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aibum_list);
        initView();
    }

    private void initView() {
        noShowAcBar();;
        initNavBar(true,"专辑列表",false);

        mRvList=fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter=new MusicListAdapter(this,null);
        mRvList.setAdapter(mAdapter);
    }
}
