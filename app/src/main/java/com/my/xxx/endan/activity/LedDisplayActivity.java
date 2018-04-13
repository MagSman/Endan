package com.my.xxx.endan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.xxx.endan.R;
import com.my.xxx.mylibrary.EZLedView;

import butterknife.BindView;
import butterknife.ButterKnife;


//展示LED效果
public class LedDisplayActivity extends AppCompatActivity {
    @BindView(R.id.scrollView)
    HorizontalScrollView scrollView;
    @BindView(R.id.ledViewText)
    EZLedView ledViewText;//Led文字
    @BindView(R.id.ledViewImage)
    EZLedView ledViewImage;//Led图片
    @BindView(R.id.text)
    TextView text;//普通文字
    @BindView(R.id.image)
    ImageView image;//普通图片
    @BindView(R.id.up)
    View up;
    @BindView(R.id.down)
    View down;


    Handler handler = new Handler();
    int scrollX = 0;
    private int style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_led_display);
        ButterKnife.bind(this);
        getDescId();
    }

    //展示
    private void disPlay() {
        switch (1) {
            case 1:
                ledViewImage.setVisibility(View.VISIBLE);
                ledViewText.setVisibility(View.VISIBLE);
                text.setVisibility(View.GONE);
                image.setVisibility(View.GONE);
                break;
            case 2:
                ledViewImage.setVisibility(View.GONE);
                ledViewText.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                break;
        }
        //获取桌面宽度
        final int windowWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        //设置循环体前宽度
        ViewGroup.LayoutParams layoutParamsUp = up.getLayoutParams();
        layoutParamsUp.width = windowWidth;
        up.setLayoutParams(layoutParamsUp);
        //设置循环体后宽度
        ViewGroup.LayoutParams layoutParamsDown = down.getLayoutParams();
        layoutParamsDown.width = windowWidth;
        down.setLayoutParams(layoutParamsDown);
        ledViewText.setText("默默");
        handler.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(scrollX, 0);
                scrollX += (ledViewText.getLedRadius() + ledViewText.getLedSpace());
                if (scrollX >= (ledViewText.getWidth() + ledViewImage.getWidth() + text.getWidth()
                        + image.getWidth() + up.getWidth() + down.getWidth()) - scrollView.getWidth()) {
                    scrollX = (ledViewText.getLedRadius() + ledViewText.getLedSpace());
                }
                handler.postDelayed(this, 30);
            }
        });
    }

    //获取类型
    public void getDescId() {
        style = getIntent().getIntExtra("Style", 0);
        disPlay();
    }

    public static void startIntent(Context context, int style) {
        Intent intent = new Intent(context, LedDisplayActivity.class);
        intent.putExtra("Style", style);
        context.startActivity(intent);
    }
}
