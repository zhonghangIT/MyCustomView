package com.uniquedu.mycustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.uniquedu.mycustomview.pull.MyPullView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPullActivity extends AppCompatActivity {

    @BindView(R.id.mypull)
    MyPullView mypull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pull);
        ButterKnife.bind(this);
        mypull.setOnPullListener(new MyPullView.OnPull() {
            @Override
            public void onPull(View view) {
                //事件
                Toast.makeText(MyPullActivity.this, "下拉刷新事件相应", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
