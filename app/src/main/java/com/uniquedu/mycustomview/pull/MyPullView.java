package com.uniquedu.mycustomview.pull;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.uniquedu.mycustomview.R;

/**
 * Created by ZhongHang on 2016/5/12.
 */
public class MyPullView extends FrameLayout {
    private View header;
    private View content;
    private OnPull onPullListener;

    public void setOnPullListener(OnPull onPullListener) {
        this.onPullListener = onPullListener;
    }

    public interface OnPull {
        void onPull(View view);
    }

    public MyPullView(Context context) {
        super(context);
        init(context);
    }

    public MyPullView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyPullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyPullView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = inflater.inflate(R.layout.pull_header, this, false);
        content = inflater.inflate(R.layout.pull_content, this, false);
        addView(header);
        addView(content);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //事件分发不需要重写
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //事件拦截的时机
        return true;
    }

    float oldY;
    float newY;
    float offset;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理事件的时机
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                newY = event.getY();
                offset += newY - oldY;
                content.setY(offset);//设置偏移
                oldY = newY;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (offset > 200 && onPullListener != null) {
                    //添加事件
                    onPullListener.onPull(content);
                }
                //启用动画返回
                ObjectAnimator animator = new ObjectAnimator().ofFloat(content, "Y", offset, 0).setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        content.setY((float) animation.getAnimatedValue());
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        offset = 0;
                        super.onAnimationEnd(animation);
                    }
                });
                animator.start();
                break;
        }
        return true;
    }

}
