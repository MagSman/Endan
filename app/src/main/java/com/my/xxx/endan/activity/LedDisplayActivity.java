package com.my.xxx.endan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import com.my.xxx.endan.R;
import com.my.xxx.mylibrary.EZLedView;

import butterknife.BindView;
import butterknife.ButterKnife;


//展示LED效果
public class LedDisplayActivity extends AppCompatActivity {

    @BindView(R.id.scrollView)
    HorizontalScrollView scrollView;
    @BindView(R.id.ledViewText)
    EZLedView ledViewText;
    @BindView(R.id.up)
    View up;
    @BindView(R.id.down)
    View down;

    Handler handler = new Handler();
    int scrollX = 0;
    int direct = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_display);
        ButterKnife.bind(this);
        //隐藏状态栏
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        final int windowWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams layoutParamsUp = up.getLayoutParams();
        layoutParamsUp.width = windowWidth;
        up.setLayoutParams(layoutParamsUp);
        ViewGroup.LayoutParams layoutParamsDown = down.getLayoutParams();
        layoutParamsDown.width = windowWidth;
        down.setLayoutParams(layoutParamsDown);
        ledViewText.setText("你好4896");
        handler.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(scrollX, 0);
                scrollX += (ledViewText.getLedRadius() + ledViewText.getLedSpace()) * direct;
                if (scrollX >= (ledViewText.getWidth() + up.getWidth() + down.getWidth()) - scrollView.getWidth()) {
                    scrollX = (ledViewText.getLedRadius() + ledViewText.getLedSpace()) * direct;
                }
                handler.postDelayed(this, 30);
            }
        });
    }

    public static void startIntent(Context context) {
        Intent intent = new Intent(context, LedDisplayActivity.class);
        context.startActivity(intent);
    }
}
