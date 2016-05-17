package com.uniquedu.mycustomview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.uniquedu.mycustomview.R;
import com.uniquedu.mycustomview.utils.DpUtils;

/**
 * Created by ZhongHang on 2016/5/9.
 */
public class MyEditText extends EditText {
    public MyEditText(Context context) {
        super(context);
        init(context, null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        mPaint = new Paint();
        mPaint.setStrokeWidth(20);
        textSize = DpUtils.dip2px(context, 20);
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.RED);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyEditText);
            int color = array.getColor(R.styleable.MyEditText_myColor, Color.RED);
            mPaint.setColor(color);
        }
        mPaint.setTextAlign(Paint.Align.LEFT);
    }

    private int textSize;
    private Paint mPaint;
    private int width;
    private int height;
    private String textLength;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("myEditText", "开始测量" + getWidth());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("myEditText", "测量完毕" + getWidth());
        width = getWidth();//得到控件的宽高
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textLength = getText().length() + "/20";
        //查看源码可以看到EditText涉及到超出屏幕时能够滚动的情况，所以这里添加了getScrollY()
        canvas.drawText(textLength, width - mPaint.measureText(textLength), height + getScrollY() - textSize, mPaint);
    }
}
