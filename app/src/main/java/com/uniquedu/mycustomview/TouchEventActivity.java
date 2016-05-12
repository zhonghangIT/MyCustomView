package com.uniquedu.mycustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.uniquedu.mycustomview.touchevent.MyButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TouchEventActivity extends AppCompatActivity {

    @BindView(R.id.button_click)
    MyButton buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        ButterKnife.bind(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouch", "Activity当中的dispatchTouchEvent按下事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onTouch", "Activity当中的dispatchTouchEvent移动事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "Activity当中的dispatchTouchEvent抬起事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("onTouch", "Activity当中的dispatchTouchEvent取消事件");
                break;
        }
        boolean b = super.dispatchTouchEvent(ev);
        Log.d("onTouch", "Activity当中的dispatchTouchEvent默认返回值" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouch", "Activity当中的onTouchEvent按下事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onTouch", "Activity当中的onTouchEvent移动事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "Activity当中的onTouchEvent抬起事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("onTouch", "Activity当中的onTouchEvent取消事件");
                break;
        }
        boolean b = super.onTouchEvent(event);
        Log.d("onTouch", "Activity当中的onTouchEvent默认返回值" + b);
        return b;
    }

    @OnClick(R.id.button_click)
    public void onClick() {
        Log.d("onTouch", "按钮的点击事件");
    }
}
