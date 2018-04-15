package com.my.xxx.endan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.xxx.endan.R;
import com.my.xxx.endan.view.ColorPickerPopupWindowView1;
import com.my.xxx.endan.view.ColorPickerPopupWindowView2;
import com.my.xxx.endan.view.SelectImageDialog;
import com.yanzhenjie.album.Album;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sjh on 2018/4/8.
 * 250135506@qq.com
 */

public class NormalShowSetActivity extends AppCompatActivity {

    @BindView(R.id.head_show)
    HorizontalScrollView head_show;
    @BindView(R.id.up)
    View up;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.down)
    View down;
    @BindView(R.id.choice_iamge)
    ImageView choice_iamge;
    @BindView(R.id.input_text)
    EditText input_text;
    @BindView(R.id.show)
    Button show;
    @BindView(R.id.text_color)
    Button text_color;
    @BindView(R.id.backgroud_color)
    Button backgroud_color;

    Activity context;
    ColorPickerPopupWindowView1 colorPickerPopupWindowView1;
    ColorPickerPopupWindowView2 colorPickerPopupWindowView2;
    private SelectImageDialog selectImageDialog;

    int scrollX = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_normal_display_set);
        ButterKnife.bind(this);
        context = this;

    }


    @OnClick({R.id.show, R.id.choice_iamge,R.id.text_color, R.id
            .backgroud_color})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.choice_iamge:
                selectImageDialog.show();
                break;
            case R.id.show:
                //展示
                disPlay();
                break;
            case R.id.text_color:
                showColorPickerPopupWindow1();
                //文字颜色
                break;
            case R.id.backgroud_color:
                //背景颜色
                showColorPickerPopupWindow2();
                break;
        }
    }

    //展示
    private void disPlay() {
        timer.cancel();
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
        text.setText(input_text.getText().toString());
        //开始循环
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 15) {
        @Override
        public void onTick(long millisUntilFinished) {
            head_show.scrollTo(scrollX, 0);
            scrollX += 5;
            if (scrollX >= (text.getWidth() + image.getWidth() + up.getWidth() +
                    down.getWidth()) - head_show.getWidth()) {
                scrollX = 5;
            }
        }

        @Override
        public void onFinish() {

        }
    };

    //显示 颜色选择器
    private void showColorPickerPopupWindow1() {
        if (colorPickerPopupWindowView1 == null) {
            colorPickerPopupWindowView1 = new ColorPickerPopupWindowView1(context, up);
            colorPickerPopupWindowView1.addPickerColorListener(new ColorPickerPopupWindowView1
                    .SelecteColorListener1() {
                @Override
                public void onSelectingColor(int i) {
                    text.setTextColor(i);
                }

                @Override
                public void onSelectedColor(int i) {
                    text.setTextColor(i);
                }
            });
        } else {
            colorPickerPopupWindowView1.show();
        }
    }

    private void showColorPickerPopupWindow2() {
        if (colorPickerPopupWindowView2 == null) {
            colorPickerPopupWindowView2 = new ColorPickerPopupWindowView2(context, up);
            colorPickerPopupWindowView2.addPickerColorListener(new ColorPickerPopupWindowView2
                    .SelecteColorListener2() {
                @Override
                public void onSelectingColor(int i) {
                    head_show.setBackgroundColor(i);
                }

                @Override
                public void onSelectedColor(int i) {
                    head_show.setBackgroundColor(i);
                }
            });
        } else {
            colorPickerPopupWindowView2.show();
        }
    }

   /* public static void startIntent(Context context) {
        Intent intent = new Intent(context, LedShowSetActivity.class);
        context.startActivity(intent);
    }*/

    /**
     * 选择图片弹窗
     */
    public void initChoiceImageDialog() {
        selectImageDialog = new SelectImageDialog(this);
        selectImageDialog.setSelect(new SelectImageDialog.OnSelect() {
            @Override
            public void openCamera() {
                Album.camera(context) // 相机功能。
                        .image() // 拍照。
                        .requestCode(2)
                        // .onResult((requestCode, result) -> upLoadImg(result))
                        .start();
            }

            @Override
            public void openGallery() {
                Album.image(context)
                        .singleChoice()
                        .requestCode(200)
                        .camera(true)
                        .columnCount(3)
                        //.onResult((requestCode, result) -> upLoadImg(result.get(0).getPath()))
                        .start();
            }
        });

    }


    public static void startIntent(Context context) {
        Intent intent = new Intent(context, NormalShowSetActivity.class);
        context.startActivity(intent);
    }
}
