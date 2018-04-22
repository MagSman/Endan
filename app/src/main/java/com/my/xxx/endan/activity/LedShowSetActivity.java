package com.my.xxx.endan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.my.xxx.endan.R;
import com.my.xxx.endan.bean.Star;
import com.my.xxx.endan.utils.OperationalDataBase;
import com.my.xxx.endan.utils.StringUtils;
import com.my.xxx.endan.utils.ToastUtils;
import com.my.xxx.endan.view.ColorPickerPopupWindowView1;
import com.my.xxx.endan.view.ColorPickerPopupWindowView2;
import com.my.xxx.endan.view.EZLedView;
import com.my.xxx.endan.view.InputNamePopupWindow;
import com.my.xxx.endan.view.SelectImageDialog;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LedShowSetActivity extends AppCompatActivity {

    @BindView(R.id.activity)
    RelativeLayout activity;
    @BindView(R.id.display_head_led)
    HorizontalScrollView scrollViewLed;//展示控件
    @BindView(R.id.choice_iamge)
    RelativeLayout choice_iamge;
    @BindView(R.id.input_text)
    EditText input_text;//文字输入框
    @BindView(R.id.up)
    View up;
    @BindView(R.id.down)
    View down;
    @BindView(R.id.ledViewText)
    EZLedView ledViewText;//led文字
    @BindView(R.id.show)
    Button show;//效果预览
    @BindView(R.id.text_color)
    Button text_color;//文字颜色
    @BindView(R.id.backgroud_color)
    Button changeStyle;//切换风格
    @BindView(R.id.text_size)
    Button text_size;//字号
    @BindView(R.id.text_size_seekbar)
    SeekBar text_size_seekbar;
    @BindView(R.id.speed_seekbar)
    SeekBar speed_seekbar;
    @BindView(R.id.to_led_show)
    Button to_led_show;
    @BindView(R.id.image)
    ImageView image;//图片
    @BindView(R.id.choice_net_iamge)
    RelativeLayout choice_net_iamge;//选择网络图片


    Activity context;
    ColorPickerPopupWindowView1 colorPickerPopupWindowView1;
    ColorPickerPopupWindowView2 colorPickerPopupWindowView2;
    private SelectImageDialog selectImageDialog;

    int scrollX = 0;
    int speedNumber = 6;
    int textColor;
    int backgroudColor;
    int textSize;
    String textInfo;
    String imagePath;
    private InputNamePopupWindow inputNamePopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_show_set);
        ButterKnife.bind(this);
        context = this;
        initView();
        //初始化选择图片dialog
        initChoiceImageDialog();

    }

    private void initView() {
        ledViewText.setLedRadius(4);
        ledViewText.setLedSpace(2);
        text_size_seekbar.setProgress(ledViewText.getLedTextSize());
        //字号大小
        text_size_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textSize = i;
                ledViewText.setLedTextSize(i);
                ledViewText.requestLayout();
                ledViewText.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //速度大小
        speed_seekbar.setProgress(10);
        speed_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                speedNumber = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        input_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                textInfo = input_text.getText().toString();
                ledViewText.setText(input_text.getText().toString());

            }
        });

    }

    @OnClick({R.id.show, R.id.choice_iamge, R.id.text_size, R.id.text_color, R.id
            .backgroud_color, R.id.to_led_show, R.id.choice_net_iamge})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.choice_iamge:
                //选择本地图片
                selectImageDialog.show();
                break;
            case R.id.choice_net_iamge:
                //选择网络图片
                showInputStarNamePopupWindow();
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
            case R.id.to_led_show:
                //去应援
                if (backgroudColor == 0) {
                    backgroudColor = 7649793;
                }
                LedDisplayActivity.startIntent(context, textColor, backgroudColor, textSize,
                speedNumber, imagePath, textInfo);

                //Log.i("显示的图片地址", imagepath);
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


    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 10) {
        @Override
        public void onTick(long millisUntilFinished) {
            scrollViewLed.scrollTo(scrollX, 0);
            scrollX += speedNumber;
            if (scrollX >= (ledViewText.getWidth() + image.getWidth() + up.getWidth() +
                    down.getWidth()) - scrollViewLed.getWidth()) {
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
                    ledViewText.setLedColor(i);
                    ledViewText.requestLayout();
                    ledViewText.invalidate();
                }

                @Override
                public void onSelectedColor(int i) {
                    textColor = i;
                    ledViewText.setLedColor(i);
                    ledViewText.requestLayout();
                    ledViewText.invalidate();
                }
            });
        } else {
            colorPickerPopupWindowView1.show();
        }
    }

    //选择背景颜色
    private void showColorPickerPopupWindow2() {
        if (colorPickerPopupWindowView2 == null) {
            colorPickerPopupWindowView2 = new ColorPickerPopupWindowView2(context, up);
            colorPickerPopupWindowView2.addPickerColorListener(new ColorPickerPopupWindowView2
                    .SelecteColorListener2() {
                @Override
                public void onSelectingColor(int i) {
                    scrollViewLed.setBackgroundColor(i);
                }

                @Override
                public void onSelectedColor(int i) {
                    backgroudColor = i;
                    scrollViewLed.setBackgroundColor(i);
                }
            });
        } else {
            colorPickerPopupWindowView2.show();
        }
    }

    //显示 输入框  输入明星姓名
    private void showInputStarNamePopupWindow() {
        if (inputNamePopupWindow == null) {
            inputNamePopupWindow = new InputNamePopupWindow(context, up);
            inputNamePopupWindow.addReturnInputTextListener(new InputNamePopupWindow
                    .ReturnInputTextListener() {
                @Override
                public void getText(String str) {
                    //TODO 搜索的人名
                    QueryStarImage(str);
                }
            });

        } else {
            inputNamePopupWindow.show();
        }
    }

    public static void startIntent(Context context) {
        Intent intent = new Intent(context, LedShowSetActivity.class);
        context.startActivity(intent);
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (colorPickerPopupWindowView1 != null) {
                colorPickerPopupWindowView1.dismiss();
            }
            if (colorPickerPopupWindowView2 != null) {
                colorPickerPopupWindowView2.dismiss();
            }
            if (inputNamePopupWindow != null) {
                inputNamePopupWindow.dismiss();
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }*/


    /**
     * 选择图片弹窗
     */
    public void initChoiceImageDialog() {
        selectImageDialog = new SelectImageDialog(this);
        selectImageDialog.setSelect(new SelectImageDialog.OnSelect() {
            //手机拍照
            @Override
            public void openCamera() {
                Album.camera(context) // 相机功能。
                        .image() // 拍照。
                        .requestCode(2)
                        .onResult(new Action<String>() {
                            @Override
                            public void onAction(int requestCode, @NonNull String result) {
                                imagePath = result;
                                image.setVisibility(View.VISIBLE);
                                Glide.with(context).load(result).into(image);
                                disPlay();
                            }
                        })
                        .start();
            }

            //选择相册
            @Override
            public void openGallery() {
                Album.image(context)
                        .singleChoice()
                        .requestCode(200)
                        .camera(true)
                        .columnCount(3)
                        .onResult(new Action<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAction(int requestCode, @NonNull ArrayList<AlbumFile>
                                    result) {
                                imagePath = result.get(0).getPath();
                                image.setVisibility(View.VISIBLE);
                                Glide.with(context).load(result.get(0).getPath()).into(image);
                                Log.i("保存", result.get(0).getPath());
                                OperationalDataBase.SaveStarImage("第五人称", result.get(0).getPath());
                                disPlay();
                            }
                        })
                        .start();
            }
        });
    }

    //查询图片
    public String queryImage() {
        String starImage = OperationalDataBase.QueryStarImage("第五人称");
        return starImage;
    }

    //查询图片
    public void QueryStarImage(String starname) {
        BmobQuery<Star> query = new BmobQuery<Star>();
        query.addWhereEqualTo("starName", starname);
        //执行查询方法
        query.findObjects(new FindListener<Star>() {
            @Override
            public void done(List<Star> object, BmobException e) {
                if (e == null) {
                    Log.i("查询成功：共", object.size() + "条数据。");
                    if (!StringUtils.isEmpty(object.get(0).getImagepath())) {
                        image.setVisibility(View.VISIBLE);
                        Glide.with(context).load(object.get(0).getImagepath()).into(image);
                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    ToastUtils.showToast(context, "对不起还没有相关图片!!!");
                }
            }
        });
    }
}
