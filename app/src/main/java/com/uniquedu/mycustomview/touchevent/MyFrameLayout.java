package com.uniquedu.mycustomview.touchevent;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by ZhongHang on 2016/5/10.
 */
public class MyFrameLayout extends RelativeLayout {
    public MyFrameLayout(Context context) {
        super(context);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouch", "MyFramelayout当中的onInterceptTouchEvent按下事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onTouch", "MyFramelayout当中的onInterceptTouchEvent移动事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "MyFramelayout当中的onInterceptTouchEvent抬起事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("onTouch", "MyFramelayout当中的onInterceptTouchEvent取消事件");
                break;
        }
        boolean b = super.onInterceptTouchEvent(ev);
        Log.d("onTouch", "MyFramelayout当中的onInterceptTouchEvent的默认返回值" + b);
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouch", "MyFramelayout当中的dispatchTouchEvent按下事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onTouch", "MyFramelayout当中的dispatchTouchEvent移动事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "MyFramelayout当中的dispatchTouchEventt抬起事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("onTouch", "MyFramelayout当中的dispatchTouchEvent取消事件");
                break;
        }
        boolean b = super.dispatchTouchEvent(ev);
        Log.d("onTouch", "MyFramelayout当中的dispatchTouchEvent默认返回值" + b);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouch", "MyFramelayout当中的onTouchEvent按下事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onTouch", "MyFramelayout当中的onTouchEvent移动事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "MyFramelayout当中的onTouchEvent抬起事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("onTouch", "MyFramelayout当中的onTouchEvent取消事件");
                break;
        }
        boolean b = super.onTouchEvent(event);
        Log.d("onTouch", "MyFramelayout当中的onTouchEvent默认返回值" + b);
        return b;
    }
}
