package com.my.xxx.endan.activity;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.xxx.endan.R;
import com.my.xxx.endan.utils.OperationalDataBase;
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
    @BindView(R.id.image)
    ImageView image;//正常图片
    @BindView(R.id.text)
    TextView text;//正常文字
    @BindView(R.id.ledViewImage)
    EZLedView ledViewImage;//led图片
    @BindView(R.id.ledViewText)
    EZLedView ledViewText;//led文字
    @BindView(R.id.up)
    View up;
    @BindView(R.id.down)
    View down;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.color)
    View color;
    Activity context;
    ColorPickerPopupWindowView colorPickerPopupWindowView;

    Handler handler = new Handler();
    int scrollX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

    }


    @OnClick({R.id.start, R.id.display_head})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.start:
                //LedDisplayActivity.startIntent(this, 2);
                showColorPickerPopupWindow();
                disPlay();
                break;
            case R.id.display_head:
                //跳转展示
                LedDisplayActivity.startIntent(this, 2);
                Log.i("跳转", "-----------");
                break;
        }
    }


    //展示
    private void disPlay() {

        switch (2) {
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
        ledViewText.setText("你好4896");
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

    //显示 颜色选择器
    private void showColorPickerPopupWindow() {
        if (colorPickerPopupWindowView == null) {
            colorPickerPopupWindowView = new ColorPickerPopupWindowView(context, color);
            colorPickerPopupWindowView.addPickerColorListener(new ColorPickerPopupWindowView.SelecteColorListener() {
                @Override
                public void onSelectingColor(int i) {
                    color.setBackgroundColor(i);
                    text.setTextColor(i);
                    scrollView.setBackgroundColor(i);
                }

                @Override
                public void onSelectedColor(int i) {
                    color.setBackgroundColor(i);
                }
            });
        } else {
            colorPickerPopupWindowView.show();
        }
    }

}
