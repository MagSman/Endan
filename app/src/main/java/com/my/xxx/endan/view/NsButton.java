package com.my.xxx.endan.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by muzi on 2017/12/1.
 * 727784430@qq.com
 */

public class NsButton extends android.support.v7.widget.AppCompatButton {

    public NsButton(Context context) {
        super(context);
        init();
    }

    public NsButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NsButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        try {
            setTypeface(Typeface.SANS_SERIF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
