package com.my.xxx.endan.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
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
    @BindView(R.id.color_picker_view)
    ColorPickerView color_picker_view;
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
        //showColorPicker();

    }


    @OnClick({R.id.start})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.start:
                //LedDisplayActivity.startIntent(this, 1);
                showReletDayNumberPopupWindowView();
                //showColorPicker();
                break;
        }
    }

    private void showColorPicker() {
        color_picker_view.addOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int i) {
                Log.i("颜色1", " " + i);
                color.setBackgroundColor(i);
            }
        });
        color_picker_view.addOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int i) {
                Log.i("颜色2", " " + i);
                color.setBackgroundColor(i);
            }
        });
        int selectedColor = color_picker_view.getSelectedColor();
        Log.i("颜色3", " " + selectedColor);

    }


    private void showReletDayNumberPopupWindowView() {
        if (colorPickerPopupWindowView == null) {
            colorPickerPopupWindowView = new ColorPickerPopupWindowView(context, color);
        } else {
            colorPickerPopupWindowView.show();
        }
    }

}
