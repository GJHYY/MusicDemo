package com.example.ft.musicdemo.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.helps.MediaPlayerHelp;

public class PlayMusicView extends FrameLayout {
    private MediaPlayerHelp mMediaPlayerHelp;
    private boolean isPlaying;
    private Context mContext;
    private View mView;
    private String mPath;
    private ImageView mIvIcon,nIvNeedle,mIvPlay;
    private Animation mPlayMusicAnim,mPlayNeedleAnim,mStopNeedleAnim;
    private FrameLayout mFlPlayMusic;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        //MediaPlayer

        mContext=context;
        mView=LayoutInflater.from(mContext).inflate(R.layout.play_music,this,false);

        mFlPlayMusic=mView.findViewById(R.id.fl_play_music);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });
        nIvNeedle=mView.findViewById(R.id.iv_needle);
        mIvIcon=mView.findViewById(R.id.iv_icon);
        mIvPlay=mView.findViewById(R.id.iv_play);
       /*
       * 1.定义所要执行的动画
       *    1.1光盘转动的动画
       *    1.2指针指向光盘动画
       *    1.3指针离开光盘的动画
       * 2.startAnimation实现动画
       *
       * */
        mPlayMusicAnim=AnimationUtils.loadAnimation(mContext,R.anim.play_music_anim);
        mPlayNeedleAnim=AnimationUtils.loadAnimation(mContext,R.anim.play_needle_anim);
        mStopNeedleAnim=AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);

        addView(mView);
        mMediaPlayerHelp=MediaPlayerHelp.getInstance(mContext);

    }

    /*
    * 切换播放状态
    * */
    private void trigger(){
        if (isPlaying){
            stopMusic();
        }else {
            playMusic(mPath);
        }

    }


    /*
    * 播放音乐
    * */
    public void playMusic(String path){
        mPath=path;
        isPlaying=true;
        mIvPlay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        nIvNeedle.startAnimation(mPlayNeedleAnim);

        /*
        * 1.判断当前音乐是是否已经在播放
        * 2.如果当前音乐已经在播放的话，那么就直接执行start方法
        * 3.如果当前音乐不是需要播放的音乐的话，那么就直接执行setPath方法
        * */
        if (mMediaPlayerHelp.getPath()!=null && mMediaPlayerHelp.getPath().equals(path)){
            mMediaPlayerHelp.start();
        }else {
            mMediaPlayerHelp.setPath(path);
            mMediaPlayerHelp.setOnMeidaPlayerHelperListener(new MediaPlayerHelp.OnMeidaPlayerHelperListener() {
                @Override
                public void onPepared(MediaPlayer mp) {

                    mMediaPlayerHelp.start();
                }
            });
        }


    }
    /*
    * 停止播放
    * */
    public void stopMusic(){
        isPlaying=false;
        mIvPlay.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        nIvNeedle.startAnimation(mStopNeedleAnim);
        mMediaPlayerHelp.pause();
    }


    /*
    * 设置光盘中显示音乐封面图片
    * */
    public void setMusicIcon(String icon){
        Glide.with(mContext)
                .load(icon)
                .into(mIvIcon);
    }
}
