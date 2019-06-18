package com.example.ft.musicdemo.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayerHelp {

    private  static  MediaPlayerHelp instance;
    private String mPath;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private OnMeidaPlayerHelperListener onMeidaPlayerHelperListener;

    public static MediaPlayerHelp getInstance(Context context){
        if (instance==null){
            synchronized (MediaPlayerHelp.class){
                if (instance==null){
                    instance=new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerHelp (Context context){
        mContext=context;
        mMediaPlayer=new MediaPlayer();

    }
    /*
    * 1.setPath:当前需要播放的音乐
    * 2.start:播放音乐
    * 3.pause:暂停播放
    *
    * */

    //1.setPath:当前需要播放的音乐
    public void setPath(String path)  {
        /*
        * 1.音乐正在播放，重置音乐播放状态
        * 2.设置音乐播放路径
        * 3.准备播放
        * */
        //1.音乐正在播放，重置音乐播放状态
        mPath=path;
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.reset();
        }

        //2.设置音乐播放路径
        try {
            mMediaPlayer.setDataSource(mContext,Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.准备播放
        mMediaPlayer.prepareAsync();//异步加载音乐
        /*try {
            mMediaPlayer.prepare();//同步加载
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                if (onMeidaPlayerHelperListener!=null){
                    onMeidaPlayerHelperListener.onPepared(mp);
                }
            }
        });
    }

    /*
     * 返回正在播放的路径
     * */
    public String getPath(){
        return mPath;
    }

    //2.start:播放音乐
    public void start(){
        if (mMediaPlayer.isPlaying())return;
        mMediaPlayer.start();
    }

    //3.pause:暂停播放
    public void pause(){
        mMediaPlayer.pause();
    }
    public void setOnMeidaPlayerHelperListener(OnMeidaPlayerHelperListener onMeidaPlayerHelperListener) {
        this.onMeidaPlayerHelperListener = onMeidaPlayerHelperListener;
    }

    public  interface OnMeidaPlayerHelperListener{
        void onPepared(MediaPlayer mp);
    }

}
