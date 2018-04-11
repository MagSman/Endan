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
    @BindView(R.id.ledView)
    EZLedView ledView;
    /* @BindView(R.id.ledViewUp)
     EZLedView ledViewUp;
     @BindView(R.id.ledViewDown)
     EZLedView ledViewDown;*/
    @BindView(R.id.up)
    View up;
    @BindView(R.id.down)
    View down;

    Handler handler = new Handler();
    int scrollX = 0;
    int direct = 1;
    private StringBuilder builder = new StringBuilder();

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
        ledView.setText("你好48964561");

        handler.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(scrollX, 0);
                scrollX += (ledView.getLedRadius() + ledView.getLedSpace()) * direct;
                if (scrollX >= (ledView.getWidth() + up.getWidth() + down.getWidth()) - scrollView.getWidth()) {
                    scrollX = (ledView.getLedRadius() + ledView.getLedSpace()) * direct;
                }
                Log.i("ledView宽度", "---" + ledView.getWidth());
                Log.i("scrollView宽度", "---" + scrollView.getWidth());
                Log.i("屏幕宽度", "---" + windowWidth);
                Log.i("滚动距离", "---" + scrollX);
                handler.postDelayed(this, 30);
            }
        });
       /* handler.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(scrollX, 0);
                scrollX += (ledView.getLedRadius() + ledView.getLedSpace()) * direct;
                if (scrollX <= 0 || scrollX >= ledView.getWidth() - scrollView.getWidth()) {
                    scrollX = (ledView.getLedRadius() + ledView.getLedSpace()) * direct;
                    // direct = -direct;
                    Log.i("ledView宽度", "---" + ledView.getWidth());
                    Log.i("scrollView", "---" + scrollView.getWidth());
                    Log.i("滚动距离", "---" + scrollX);
                    Log.i("屏幕宽度", "---" + windowWidth);
                }
                Log.i("ledView宽度", "---" + ledView.getWidth());
                Log.i("scrollView宽度", "---" + scrollView.getWidth());
                Log.i("屏幕宽度", "---" + windowWidth);
                Log.i("滚动距离", "---" + scrollX);
                handler.postDelayed(this, 30);
            }
        });*/
    }

    public static void startIntent(Context context) {
        Intent intent = new Intent(context, LedDisplayActivity.class);
        context.startActivity(intent);
    }
}
