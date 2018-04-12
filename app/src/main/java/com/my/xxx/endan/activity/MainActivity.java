package com.my.xxx.endan.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.my.xxx.endan.R;
import com.my.xxx.endan.view.ColorPickerPopupWindowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity)
    RelativeLayout activity;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.color)
    View color;
    Activity context;
    ColorPickerPopupWindowView colorPickerPopupWindowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

    }


    @OnClick({R.id.start})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.start:
                //LedDisplayActivity.startIntent(this, 2);
                showColorPickerPopupWindow();
                break;
        }
    }

    //显示 颜色选择器
    private void showColorPickerPopupWindow() {
        if (colorPickerPopupWindowView == null) {
            colorPickerPopupWindowView = new ColorPickerPopupWindowView(context, color);
            colorPickerPopupWindowView.addPickerColorListener(new ColorPickerPopupWindowView.SelecteColorListener() {
                @Override
                public void onSelectingColor(int i) {
                    color.setBackgroundColor(i);
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
