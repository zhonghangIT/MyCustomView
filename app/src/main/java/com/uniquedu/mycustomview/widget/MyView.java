package com.uniquedu.mycustomview.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.uniquedu.mycustomview.utils.DpUtils;

/**
 * Created by ZhongHang on 2016/5/9.
 * 模仿360加速球的控件
 */
public class MyView extends View {
    private static final int NEED_INVALIDATE = 0x23;
    private Paint paintCircle;
    private Paint paintText;
    private Paint paintPath;
    private int width;
    private int height;
    private float radius;
    private Path path;
    private float persent;
    private int wave_swing = 5;
    private int wave_long = 20;

    public void setOffset(float offset) {
        this.offset = offset;
    }

    private float offset;

    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    //操作UI主线程
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NEED_INVALIDATE:
                    //更新时间
                    offset += 5;
                    if (offset > wave_long * 2) {
                        offset = 0;
                    }
                    invalidate();
                    sendEmptyMessageDelayed(NEED_INVALIDATE, 50);
                    break;
            }

        }
    };

    private void init(Context context) {
        paintCircle = new Paint();
        paintText = new Paint();
        paintPath = new Paint();
        paintCircle.setColor(Color.argb(0xff, 0x2b, 0x84, 0x62));
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setAntiAlias(true);
        paintPath.setColor(Color.argb(0xff, 0x4E, 0XC8, 0X63));
        paintPath.setStyle(Paint.Style.FILL);
        paintPath.setStrokeWidth(1);
        paintPath.setAntiAlias(true);
        paintPath.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paintText.setColor(Color.WHITE);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextSize(DpUtils.dip2px(context, 20));
        startWaveAnimation();
    }


    //属性动画要求必须有set方法
    public void setPersent(float persent) {
        this.persent = persent;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth();
        height = getHeight();
        radius = Math.min(width, height) / 2 - 10;
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制底图
        canvas.drawCircle(width / 2, height / 2, radius, paintCircle);
        //保存图层
        int layerId = canvas.saveLayer(0, 0, width, height, paintText, Canvas.ALL_SAVE_FLAG);
        //在保存的图层上绘制圆
        canvas.drawCircle(width / 2, height / 2, radius, paintCircle);
        //绘制波浪线的位图
        //绘制路径,设置路径
        //重置路径
        path.reset();
        //移动到
        path.moveTo(0, height);
        path.lineTo(width, height);
        path.lineTo(width + offset, height - height * persent / 100);
        Log.d("value", "绘制时的偏移量为" + offset);
        for (int i = 0; i < width / wave_long / 4 + 2; i++) {
            //从右向左绘制的，所以用的负数
            path.rQuadTo(-wave_long, -wave_swing, -wave_long * 2, 0);
            path.rQuadTo(-wave_long, wave_swing, -wave_long * 2, 0);
        }
        //path.lineTo(0, height - height * persent / 100);这个是直线闭合
        //将路径闭合
        path.close();
        canvas.drawPath(path, paintPath);
        //恢复图层
        canvas.restoreToCount(layerId);
        //绘制文本
        canvas.drawText((int) persent + "%", width / 2, height / 2, paintText);
    }


    /**
     * 启动动画
     */
    public void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "persent", 0, 100);
        animator.setDuration(10000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("value", "动画插值为" + animation.getAnimatedValue());
                invalidate();
            }
        });
    }

    /**
     * 启动波浪的动画
     */
    public void startWaveAnimation() {
        //清空所有的消息
        handler.removeMessages(NEED_INVALIDATE);
        //发送一个消息
        handler.sendEmptyMessage(NEED_INVALIDATE);
    }
}
