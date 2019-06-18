package com.example.ft.musicdemo.views;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    public GridSpaceItemDecoration(int space,RecyclerView parent){
        mSpace=space;
        getRecyclerViewOffsets(parent);
    }

    /*
    * @param outRect item的矩形边界
    * @param view ItemView
    * @param parent RecyclerView
    * @param state RecyclerView的状态
    *
    * 此方法设置分割线
    * */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=mSpace;
        //判断item是不是每一行第一个Item
        /*if (parent.getChildAdapterPosition(view)%3==0){
            outRect.left=0;
        }*/

        //View margin
        //margin 为 正，则View 会距离边界产生一个距离
        //margin 为 负，则View 会超出边界产生一个距离
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin=-mSpace;
        parent.setLayoutParams(layoutParams);
        //如果上述方法写到这，此方法会被多次调用，但我们的方法只需要调用一次就从

    }
    //解决上述方法多次调用问题
    private void getRecyclerViewOffsets(RecyclerView parent){
        //View margin
        //margin 为 正，则View 会距离边界产生一个距离
        //margin 为 负，则View 会超出边界产生一个距离
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin=-mSpace;
        parent.setLayoutParams(layoutParams);

    }


}
