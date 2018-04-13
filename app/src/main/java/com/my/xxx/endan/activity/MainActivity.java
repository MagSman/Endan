package com.my.xxx.endan.activity;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    int scrollViewWidthOut ;//循环体长度(外部)
    int scrollViewWidthIn ;//循环体长度(内部)

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
                ledViewText.setText("nianodiandianifweknfqewfqewffewfew");
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
        switch (1) {
            case 1:
                //led风格
                ledViewImage.setVisibility(View.VISIBLE);
                ledViewText.setVisibility(View.VISIBLE);
                text.setVisibility(View.GONE);
                image.setVisibility(View.GONE);
                //循环体长度
                scrollViewWidthOut = ledViewText.getWidth() + ledViewImage.getWidth() + up.getWidth() + down.getWidth();
                scrollViewWidthIn = ledViewText.getWidth() + ledViewImage.getWidth();
                break;
            case 2:
                //正常风格
                ledViewImage.setVisibility(View.GONE);
                ledViewText.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                //循环体长度
                scrollViewWidthOut = text.getWidth() + image.getWidth() + up.getWidth() + down.getWidth();
                scrollViewWidthIn = text.getWidth() + image.getWidth();
                break;
        }
        //开始循环
        timer.start();
    }


    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 30) {

        @Override
        public void onTick(long millisUntilFinished) {
            scrollView.scrollTo(scrollX, 0);
            //scrollX += (ledViewText.getLedRadius() + ledViewText.getLedSpace());
            scrollX += 10;
            if (scrollX > scrollViewWidthOut - scrollViewWidthIn) {
                //scrollX = (ledViewText.getLedRadius() + ledViewText.getLedSpace());
                scrollX = 0;
            }
        }

        @Override
        public void onFinish() {

        }
    };


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


}
