package com.uniquedu.mycustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.uniquedu.mycustomview.widget.MyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyViewActivity extends AppCompatActivity {

    @BindView(R.id.myview)
    MyView myview;
    @BindView(R.id.button_start)
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_start)
    public void onClick() {
        myview.startAnimation();
    }
}
