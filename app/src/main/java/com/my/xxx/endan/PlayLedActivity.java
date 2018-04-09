package com.my.xxx.endan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.my.xxx.mylibrary.EZLedView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sjh on 2018/4/8.
 * 250135506@qq.com
 */

public class PlayLedActivity extends AppCompatActivity{

    @BindView(R.id.led)
    EZLedView led;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play_led);
        ButterKnife.bind(this);

        //led.setDrawable(getResources().getDrawable(R.drawable.simpson));
        led.invalidate();
        led.setText("实验");
    }
}
