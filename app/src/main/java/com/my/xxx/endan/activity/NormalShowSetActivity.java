package com.my.xxx.endan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.my.xxx.endan.R;
import com.my.xxx.mylibrary.EZLedView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sjh on 2018/4/8.
 * 250135506@qq.com
 */

public class NormalShowSetActivity extends AppCompatActivity {

    @BindView(R.id.led)
    EZLedView led;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_normal_display_set);
        ButterKnife.bind(this);

        led.setLedRadius(4);
        led.setLedSpace(0);
        led.setDrawable(getResources().getDrawable(R.drawable.qaoba));
    }





    public static void startIntent(Context context) {
        Intent intent = new Intent(context, NormalShowSetActivity.class);
        context.startActivity(intent);
    }
}
