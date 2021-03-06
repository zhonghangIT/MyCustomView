package com.uniquedu.mycustomview.touchevent;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by ZhongHang on 2016/5/10.
 */
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouch", "MyButton当中的onTouchEvent按下事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onTouch", "MyButton当中的onTouchEvent移动事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "MyButton当中的onTouchEvent抬起事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("onTouch", "MyButton当中的onTouchEvent取消事件");
                break;
        }
        boolean b = super.onTouchEvent(event);
        Log.d("onTouch", "myButton的onTouchEvent的默认返回值" + b);
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouch", "MyButton当中的dispatchTouchEvent按下事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onTouch", "MyButton当中的dispatchTouchEvent移动事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "MyButton当中的dispatchTouchEvent抬起事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("onTouch", "MyButton当中的dispatchTouchEvent取消事件");
                break;
        }
        boolean b = super.dispatchTouchEvent(event);
        Log.d("onTouch", "myButton的dispatchTouchEvent的默认返回值" + b);
        return b;
    }

}
