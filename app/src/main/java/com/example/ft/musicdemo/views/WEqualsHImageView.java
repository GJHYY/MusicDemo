package com.example.ft.musicdemo.views;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

//AppCompatOmageView
public class WEqualsHImageView extends AppCompatImageView {

    public WEqualsHImageView(Context context) {
        super(context);
    }

    public WEqualsHImageView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHImageView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        /*//获取View宽度
        int width=MeasureSpec.getSize(widthMeasureSpec);
        //获取View模式
        //match_parent、warp_content、具体dp
        int mode=MeasureSpec.getMode(widthMeasureSpec);
        */
    }
}
