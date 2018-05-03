package com.my.xxx.endan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.my.xxx.endan.R;
import com.my.xxx.endan.utils.StringUtils;
import com.my.xxx.endan.view.EZLedView;


import butterknife.BindView;
import butterknife.ButterKnife;


//展示LED效果
public class LedDisplayActivity extends AppCompatActivity {
    @BindView(R.id.scrollView)
    HorizontalScrollView scrollView;
    @BindView(R.id.ledViewText)
    EZLedView ledViewText;//Led文字
    @BindView(R.id.image)
    ImageView image;//普通图片
    @BindView(R.id.up)
    View up;
    @BindView(R.id.down)
    View down;

    Activity context;
    int scrollX = 0;
    private int speedNumber;
    private int textColor;
    private int backgroudColor;
    private int textSize;
    private String imagePath;
    private String textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_led_display);
        ButterKnife.bind(this);
        context = this;
        getDescId();
    }

    //获取类型
    public void getDescId() {
        textColor = getIntent().getIntExtra("TextColor", 0);
        backgroudColor = getIntent().getIntExtra("BackgroudColor", 0);
        textSize = getIntent().getIntExtra("TextSize", 0);
        speedNumber = getIntent().getIntExtra("SpeedNumber", 0);
        imagePath = getIntent().getStringExtra("ImagePath");
        textInfo = getIntent().getStringExtra("TextInfo");
        disPlay();
    }

    //展示
    private void disPlay() {
        ledViewText.setLedRadius(4);
        ledViewText.setLedSpace(2);
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

        //设置文字
        if (StringUtils.isEmpty(textInfo)) {
            ledViewText.setText("输入内容");
        } else {
            ledViewText.setText(textInfo+"   ");
        }
        //文字颜色
        if (textColor == 0) {
            ledViewText.setLedColor(-13697129);
        } else {
            ledViewText.setLedColor(textColor);
        }
        //背景颜色
        if (backgroudColor == 0) {
            scrollView.setBackgroundColor(getResources().getColor(R.color.tab_text));
        } else {
            scrollView.setBackgroundColor(backgroudColor);
        }
        //设置字号
        if (textSize == 0) {
            ledViewText.setLedTextSize(300);
        } else {
            ledViewText.setLedTextSize(textSize);
        }
        //设置图片
        if (!StringUtils.isEmpty(imagePath)) {
            image.setVisibility(View.VISIBLE);
            Glide.with(context).load(imagePath).into(image);
        }
        Log.i("信息", "文字颜色" + textColor + "---背景颜色" + backgroudColor + "---文字大小" + textSize + "---速度" + speedNumber + "---文字内容" + textInfo + "---图片路径" + imagePath);
        timer.start();
    }

    public static void startIntent(Context context, int textColor, int backgroudColor, int textSize, int speednumber, String imagePath, String textInfo) {
        Intent intent = new Intent(context, LedDisplayActivity.class);
        intent.putExtra("TextColor", textColor);
        intent.putExtra("BackgroudColor", backgroudColor);
        intent.putExtra("TextSize", textSize);
        intent.putExtra("SpeedNumber", speednumber);
        intent.putExtra("ImagePath", imagePath);
        intent.putExtra("TextInfo", textInfo);
        context.startActivity(intent);
    }


    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 10) {
        @Override
        public void onTick(long millisUntilFinished) {
            scrollView.scrollTo(scrollX, 0);
            scrollX += speedNumber;
            if (scrollX >= (ledViewText.getWidth() + image.getWidth() + up.getWidth() +
                    down.getWidth()) - scrollView.getWidth()) {
                scrollX = 5;
            }
        }

        @Override
        public void onFinish() {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
