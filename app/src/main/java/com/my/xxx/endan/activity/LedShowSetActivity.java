package com.my.xxx.endan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.my.xxx.endan.R;
import com.my.xxx.endan.view.ColorPickerPopupWindowView;
import com.my.xxx.endan.view.SelectImageDialog;
import com.my.xxx.mylibrary.EZLedView;
import com.yanzhenjie.album.Album;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LedShowSetActivity extends AppCompatActivity {
    @BindView(R.id.activity)
    RelativeLayout activity;
    @BindView(R.id.display_head_led)
    HorizontalScrollView scrollViewLed;//展示控件
    @BindView(R.id.choice_iamge)
    ImageView choice_iamge;
    @BindView(R.id.input_text)
    EditText input_text;//文字输入框
    @BindView(R.id.up)
    View up;
    @BindView(R.id.down)
    View down;
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
    private SelectImageDialog selectImageDialog;

    int scrollX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_show_set);
        ButterKnife.bind(this);
        context = this;
        initChoiceImageDialog();
    }

    @OnClick({R.id.show, R.id.choice_iamge, R.id.changeStyle, R.id.text_color, R.id.backgroud_color})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.choice_iamge:
                selectImageDialog.show();
                break;
            case R.id.show:
                //展示
                showColorPickerPopupWindow();
                break;
            case R.id.changeStyle:
                disPlay();
                break;
            case R.id.text_color:
                //文字颜色
                break;
            case R.id.backgroud_color:
                //背景颜色
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
        ledViewText.setText("123456789");
        ledViewText.setText(input_text.getText().toString());
        //开始循环
        timer.start();
    }


    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 5) {
        @Override
        public void onTick(long millisUntilFinished) {
            scrollViewLed.scrollTo(scrollX, 0);
            scrollX += 5;
            if (scrollX >= (ledViewText.getWidth() + ledViewImage.getWidth() + up.getWidth() + down.getWidth()) - scrollViewLed.getWidth()) {
                scrollX = 5;
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
                    scrollViewLed.setBackgroundColor(i);
                }

                @Override
                public void onSelectedColor(int i) {
                }
            });
        } else {
            colorPickerPopupWindowView.show();
        }
    }

    public static void startIntent(Context context) {
        Intent intent = new Intent(context, LedShowSetActivity.class);
        context.startActivity(intent);
    }

    /**
     * 选择图片弹窗
     */
    public void initChoiceImageDialog() {
        if (selectImageDialog == null) {
            new SelectImageDialog(context);
        } else {
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
    }
}
