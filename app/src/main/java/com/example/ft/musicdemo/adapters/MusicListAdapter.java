package com.example.ft.musicdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.activitys.PlayMusicActivity;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalcaulationRvHeight;

    public MusicListAdapter(Context context,RecyclerView recyclerView){
        mContext=context;
        mRv=recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       mItemView=LayoutInflater.from(mContext).inflate(R.layout.item_list_music,viewGroup,false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        setRecyclerViewHeight();
        Glide.with(mContext)
                .load("https://f10.baidu.com/it/u=3047353025,3129502346&fm=72")
                .into(viewHolder.ivIcon);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,PlayMusicActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    /*
    * 1.获取 ItemView 的高度
    * 2.获取 ItemView 的数量
    * 3.使用 ItemViewHeight * itemViewNum =RecyclerView的高度
    * */
    private void setRecyclerViewHeight() {
        if (isCalcaulationRvHeight||mRv==null) return;
        isCalcaulationRvHeight=true;
        //获取ItemView的高度
        RecyclerView.LayoutParams itemViewLp= (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        //获取 ItemView 的数量
        int itemCount=getItemCount();
        //使用 ItemViewHeight * itemViewNum =RecyclerView的高度
        int recyclerViewHeight=itemViewLp.height*itemCount;
        //设置RecyclerView的高度
        LinearLayout.LayoutParams rvLp= (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvLp.height=recyclerViewHeight;
        mRv.setLayoutParams(rvLp);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            ivIcon=itemView.findViewById(R.id.iv_icon);
        }
    }

}
