package com.my.xxx.endan.activity;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.my.xxx.endan.R;
import com.my.xxx.endan.view.ColorPickerPopupWindowView;
import com.my.xxx.mylibrary.EZLedView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity)
    RelativeLayout activity;
    @BindView(R.id.display_head)
    HorizontalScrollView scrollView;//展示控件
    @BindView(R.id.up)
    View up;
    @BindView(R.id.down)
    View down;
    @BindView(R.id.image)
    ImageView image;//正常图片
    @BindView(R.id.text)
    TextView text;//正常文字
    @BindView(R.id.ledViewImage)
    EZLedView ledViewImage;//led图片
    @BindView(R.id.ledViewText)
    EZLedView ledViewText;//led文字
    @BindView(R.id.show)
    Button show;//展示
    @BindView(R.id.changeStyle)
    Button changeStyle;//切换风格
    @BindView(R.id.text_color)
    Button text_color;//文字颜色
    @BindView(R.id.backgroud_color)
    Button backgroud_color;//背景颜色


    Activity context;
    ColorPickerPopupWindowView colorPickerPopupWindowView;

    int scrollX = 0;
    int styleNunber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

    }

    @OnClick({R.id.show, R.id.changeStyle, R.id.text_color, R.id.backgroud_color, R.id.display_head})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.show:
                //展示
                //LedDisplayActivity.startIntent(this, 2);
                showColorPickerPopupWindow();
                disPlay();
                break;
            case R.id.changeStyle:
                //切换风格
                if (styleNunber == 1) {
                    styleNunber = 2;
                } else {
                    styleNunber = 1;
                }
                disPlay();
                break;
            case R.id.text_color:
                //文字颜色
                break;
            case R.id.backgroud_color:
                //背景颜色
                break;
            case R.id.display_head:
                //跳转展示
                LedDisplayActivity.startIntent(this, 2);
                break;
        }
    }


    //展示
    private void disPlay() {
        timer.cancel();
        scrollX = 0;
        switch (styleNunber) {
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
        //开始循环
        timer.start();
    }

    //显示 颜色选择器
    private void showColorPickerPopupWindow() {
        if (colorPickerPopupWindowView == null) {
            colorPickerPopupWindowView = new ColorPickerPopupWindowView(context, up);
            colorPickerPopupWindowView.addPickerColorListener(new ColorPickerPopupWindowView.SelecteColorListener() {
                @Override
                public void onSelectingColor(int i) {
                    scrollView.setBackgroundColor(i);
                }

                @Override
                public void onSelectedColor(int i) {
                }
            });
        } else {
            colorPickerPopupWindowView.show();
        }
    }

    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 30) {

        @Override
        public void onTick(long millisUntilFinished) {
            scrollView.scrollTo(scrollX, 0);
            scrollX += (ledViewText.getLedRadius() + ledViewText.getLedSpace());
            if (scrollX >= (ledViewText.getWidth() + ledViewImage.getWidth() + text.getWidth()
                    + image.getWidth() + up.getWidth() + down.getWidth()) - scrollView.getWidth()) {
                scrollX = (ledViewText.getLedRadius() + ledViewText.getLedSpace());
            }
        }

        @Override
        public void onFinish() {

        }
    };


}
