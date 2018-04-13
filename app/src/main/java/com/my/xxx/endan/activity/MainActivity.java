package com.my.xxx.endan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.my.xxx.endan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sjh on 2018/4/13.
 * 250135506@qq.com
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.led_style)
    Button led_style;
    @BindView(R.id.normal_style)
    Button normal_style;
    @BindView(R.id.personality_style)
    Button personality_style;

    private Activity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

    }

    @OnClick({R.id.led_style, R.id.normal_style, R.id.personality_style})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.led_style:
                LedShowSetActivity.startIntent(context);
                break;
            case R.id.normal_style:
                NormalShowSetActivity.startIntent(context);
                break;
            case R.id.personality_style:
                PersonalityActivity.startIntent(context);
                break;
        }
    }


}
