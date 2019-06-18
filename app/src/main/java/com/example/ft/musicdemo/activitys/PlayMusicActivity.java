package com.example.ft.musicdemo.activitys;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ft.musicdemo.R;
import com.example.ft.musicdemo.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    private ImageView mIvBg;
    private PlayMusicView mPlayMusicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        //隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView(){
        mIvBg=findViewById(R.id.iv_bg);
        //glide-transformations
        Glide.with(this)
                .load("https://f10.baidu.com/it/u=3047353025,3129502346&fm=72")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(mIvBg);

        mPlayMusicView=fd(R.id.play_music_view);
        mPlayMusicView.setMusicIcon("https://f10.baidu.com/it/u=3047353025,3129502346&fm=72");
        mPlayMusicView.playMusic("http://dl.stream.qqmusic.qq.com/M50000494QYI4HYTst.mp3?vkey=5751C5B58BF4D7FBCE3DB56C26AFFC6A70B0E8C955BBB04989CA94DE2D85BDEFD2BD611A176A5C83C29D0F550F2CE64812C3C7C37FD76E0A&guid=5150825362&fromtag=1");
        /*
        * http://www.170hi.com/kw/ss-sycdn.kuwo.cn/resource/n2/65/28/587993702.mp3
        * http://www.170hi.com/kw/sg-sycdn.kuwo.cn/resource/n2/46/51/1865764998.mp3
        * /storage/emulated/0/netease/cloudmusic/Music/花粥 胜娚 - 出山.mp3
        * [url=https://1.itzmx.com/file-1763.html]GARNiDELiA - 极乐净土.mp3[/url]
        * */

    }

    /*
    * 回退按钮点击事件
    * */
    public void onBackClick(View view){
        onBackPressed();
    }
}
