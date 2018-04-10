package com.my.xxx.endan.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.my.xxx.endan.R;
import com.my.xxx.mylibrary.EZLedView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sjh on 2018/4/8.
 * 250135506@qq.com
 */

public class PlayLedActivity extends AppCompatActivity {

    @BindView(R.id.led)
    EZLedView led;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play_led);
        ButterKnife.bind(this);
        led.setLedRadius(4);
        led.setLedSpace(0);
        led.setDrawable(getResources().getDrawable(R.drawable.qaoba));
      /*  led.invalidate();
        led.setText("实验");*/
    }

/*
    *//**控制点的大小*//*
    SeekBar circleRadius = (SeekBar) findViewById(R.id.seekbarCircle);
        circleRadius.setProgress(ledView.getLedRadius());
        circleRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (progress < 2)
                return;
            ledView.setLedRadius(progress);
            ledView.invalidate();
            Log.i("点的大小", "-----------" + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });

    *//**控制点的空隙*//*
    SeekBar ledSpace = (SeekBar) findViewById(R.id.seekbarSpace);
        ledSpace.setProgress(ledView.getLedSpace());
        ledSpace.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            ledView.setLedSpace(progress);
            ledView.invalidate();
            Log.i("点的空隙", "-----------" + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });

    *//**文字大小*//*
    SeekBar textSize = (SeekBar) findViewById(R.id.seekbarTextSize);
        textSize.setProgress(ledView.getLedTextSize());
        textSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            ledView.setLedTextSize(progress);
            ledView.requestLayout();
            ledView.invalidate();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });

    *//**显示文字还是图片*//*
    setRadioCheckListener(R.id.led_type, new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if (checkedId == R.id.rb_text) {
                ledView.setText("123456789");
            } else {
                ledView.setDrawable(getResources().getDrawable(R.drawable.simpson));
            }
        }
    });
    *//**切换led点的类型*//*
    setRadioCheckListener(R.id.point_type, new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if (checkedId == R.id.rb_circle) {
                ledView.setLedType(EZLedView.LED_TYPE_CIRCLE);
            } else if (checkedId == R.id.rb_square) {
                ledView.setLedType(EZLedView.LED_TYPE_SQUARE);
            } else {
                ledView.setLedLightDrawable(getResources().getDrawable(R.drawable.ic_star_black_24dp));
                ledView.setLedType(EZLedView.LED_TYPE_DRAWABLE);
            }
            ledView.invalidate();
        }
    });*/
}
