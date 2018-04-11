package com.my.xxx.endan.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.my.xxx.endan.R;


public class ColorPickerPopupWindowView {
    private final int USER_GET_RENT_DATA = 1;

    private Activity context;
    private View container;
    private RelativeLayout rootView;
    private PopupWindow popupWindow;

    public ColorPickerPopupWindowView(Activity context, View container) {
        this.context = context;
        this.container = container;
        show();
    }

    public void initPopupWindow() {
        rootView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.popupwindow_color_picker, null, false);

        RelativeLayout pickerBox = rootView.findViewById(R.id.picker_box);
        ColorPickerView colorPicker = rootView.findViewById(R.id.color_picker_view);
        final CircleImageView colorShow = rootView.findViewById(R.id.color_show);
        colorPicker.addOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int i) {
                colorShow.setBackgroundColor(i);
            }
        });
        colorPicker.addOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int i) {
                colorShow.setBackgroundColor(i);
            }
        });
        popupWindow = new PopupWindow(rootView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        popupWindow.setFocusable(true);
        //设置点击其他地方popwindow消失
        popupWindow.setOutsideTouchable(true);

        //弹出软键盘时将popupwindow向上推
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.setBackgroundDrawable(new PaintDrawable(Color.parseColor("#30000000")));
        // 在中央显示
        popupWindow.showAtLocation(container, Gravity.RIGHT, 0, 0);
    }

    public void show() {
        if (popupWindow == null) {
            initPopupWindow();
        } else {
            try {
                popupWindow.showAtLocation(container, Gravity.RIGHT, 0, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public interface onDay {
        void onDay(String day);
    }

}