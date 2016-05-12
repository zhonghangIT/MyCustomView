package com.uniquedu.mycustomview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.uniquedu.mycustomview.widget.MyViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button_myedit)
    Button buttonMyedit;
    @BindView(R.id.button_myviewgroup)
    Button buttonMyviewgroup;
    @BindView(R.id.button_myview)
    Button buttonMyview;
    @BindView(R.id.linear_gen)
    LinearLayout linearGen;
    @BindView(R.id.button_touch)
    Button buttonTouch;
    @BindView(R.id.button_pull)
    Button buttonPull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_myedit, R.id.button_myviewgroup, R.id.button_myview, R.id.button_touch, R.id.button_pull})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_myedit:
                startActivity(new Intent(getApplicationContext(), MyEditActivity.class));
                break;
            case R.id.button_myviewgroup:
                startActivity(new Intent(getApplicationContext(), MyViewGroupActivity.class));
                break;
            case R.id.button_myview:
                startActivity(new Intent(getApplicationContext(), MyViewActivity.class));
                break;
            case R.id.button_touch:
                startActivity(new Intent(getApplicationContext(), TouchEventActivity.class));
                break;
            case R.id.button_pull:
                startActivity(new Intent(getApplicationContext(), MyPullActivity.class));
                break;
        }
    }
}
