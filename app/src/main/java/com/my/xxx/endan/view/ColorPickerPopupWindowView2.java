package com.my.xxx.endan.view;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
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


//选择器2
public class ColorPickerPopupWindowView2 {

    private Activity context;
    private View container;
    private RelativeLayout rootView;
    private PopupWindow popupWindow;

    private SelecteColorListener2 selectecolorlistener;

    public void addPickerColorListener(SelecteColorListener2 selectecolorlistener) {
        this.selectecolorlistener = selectecolorlistener;
    }

    public ColorPickerPopupWindowView2(Activity context, View container) {
        this.context = context;
        this.container = container;
        show();
    }

    public void initPopupWindow() {
        rootView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout
                .popupwindow_color_picker, null, false);

        RelativeLayout pickerBox = rootView.findViewById(R.id.picker_box);
        RelativeLayout whiteBlock = rootView.findViewById(R.id.white_block);
        whiteBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        ColorPickerView colorPicker = rootView.findViewById(R.id.color_picker_view);
        final View colorShow = rootView.findViewById(R.id.color_show);
        //Shape绘制实用圆圈，并动态改变圆点的颜色
        final GradientDrawable background = (GradientDrawable) colorShow.getBackground();
        colorPicker.addOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int i) {
                background.setColor(i);
                selectecolorlistener.onSelectingColor(i);
            }
        });
        colorPicker.addOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int i) {
                background.setColor(i);
                selectecolorlistener.onSelectedColor(i);
            }
        });
        popupWindow = new PopupWindow(rootView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        popupWindow.setFocusable(true);
        //设置进出动画
        popupWindow.setAnimationStyle(R.style.PopupWindowStyle);

        //设置点击其他地方popwindow消失
        popupWindow.setOutsideTouchable(true);

        //弹出软键盘时将popupwindow向上推
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //popupWindow.setBackgroundDrawable(new PaintDrawable(Color.parseColor("#30000000")));
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

    public interface SelecteColorListener2 {
        void onSelectingColor(int i);

        void onSelectedColor(int i);
    }

}
