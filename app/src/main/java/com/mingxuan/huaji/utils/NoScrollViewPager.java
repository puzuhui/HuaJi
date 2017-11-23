package com.mingxuan.huaji.utils;

import android.content.Context;
import android.support.annotation.Px;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = true;
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll){
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        super.scrollTo(x, y);
    }

    //禁止滑动
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if(noScroll){
//            return super.dispatchTouchEvent(ev);
//        }
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(noScroll){
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if(noScroll){
            return super.onTouchEvent(event);
        }
        return false;
    }


}
