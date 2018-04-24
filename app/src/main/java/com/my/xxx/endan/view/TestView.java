package com.my.xxx.endan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sjh on 2018/4/20.
 * 250135506@qq.com
 */

public class TestView extends View {

    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = widthMeasureSpec;
        this.mHeight = heightMeasureSpec;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
      /*  // 描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,200,100,mPaint);

        // 填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200,500,100,mPaint);

        // 描边加填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 100, mPaint);*/
       /* canvas.drawCircle(400, 500, 200, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawArc(200, 300, 600, 700, 0, 90, true, mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawArc(200, 300, 600, 700, 90, 120, true, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(200, 300, 600, 700, 120, 150, true, mPaint);
        mPaint.setColor(Color.DKGRAY);
        canvas.drawArc(200, 300, 600, 700, 150, 240, true, mPaint);*/


      /*  mPaint.setColor(Color.RED);
        canvas.drawArc(200, 300, 600, 700, 240, 300, true, mPaint);
        mPaint.setColor(Color.CYAN);
        canvas.drawArc(200, 300, 600, 700, 300, 320, true, mPaint);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawArc(200, 300, 600, 700, 320, 350, true, mPaint);
*/

        canvas.translate(360, 500);

        RectF rect = new RectF(-400, -400, 400, 400);   // 矩形区域

        for (int i = 0; i <= 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rect, mPaint);
        }


    }


    private void initPaint() {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
    }


}
