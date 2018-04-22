package com.my.xxx.endan.view;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.my.xxx.endan.R;

//颜色选择器1
public class InputNamePopupWindow {

    private Activity context;
    private View container;
    private RelativeLayout rootView;
    private PopupWindow popupWindow;


    private ReturnInputTextListener returninputtextlistener;

    public void addReturnInputTextListener(ReturnInputTextListener returninputtextlistener) {
        this.returninputtextlistener = returninputtextlistener;
    }

    public InputNamePopupWindow(Activity context, View container) {
        this.context = context;
        this.container = container;
        show();
    }

    public void initPopupWindow() {

        rootView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout
                .popupwindow_input_name, null, false);

        RelativeLayout dismissView = rootView.findViewById(R.id.white_block);
        dismissView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        final EditText input_name = rootView.findViewById(R.id.input_text);
        Button search = rootView.findViewById(R.id.serch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returninputtextlistener.getText(input_name.getText().toString());
                popupWindow.dismiss();
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
        //showSoftInputFromWindow(context,input_name);
    }
   /* public static void showSoftInputFromWindow(Context context, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        context.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }*/

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

    public interface ReturnInputTextListener {
        void getText(String str);
    }

}
