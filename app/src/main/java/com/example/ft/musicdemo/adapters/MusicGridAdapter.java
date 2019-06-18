package com.example.ft.musicdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.activitys.AIbumListActivity;

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {
    private Context mContext;
    public MusicGridAdapter (Context context){
        mContext=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_music,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder musicGridAdapter, int i) {

        Glide.with(mContext)
                .load("https://f10.baidu.com/it/u=3047353025,3129502346&fm=72")
                .into(musicGridAdapter.ivIcon);
        musicGridAdapter.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,AIbumListActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        View itemView;
        public ViewHolder(View itemView){
            super(itemView);
            this.itemView=itemView;
            ivIcon=itemView.findViewById(R.id.iv_icon);
        }
    }

}
