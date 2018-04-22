package com.my.xxx.endan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.my.xxx.endan.R;
import com.my.xxx.endan.utils.OperationalDataBase;
import com.my.xxx.endan.utils.StringUtils;
import com.my.xxx.endan.utils.ToastUtils;
import com.my.xxx.endan.view.SelectImageDialog;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shijianghan on 2018/4/22.
 */

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.save_image)
    Button save_iamge;
    @BindView(R.id.input_text)
    EditText input_text;


    private SelectImageDialog selectImageDialog;
    Activity context;
    String imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        context = this;
        initChoiceImageDialog();
    }


    @OnClick({R.id.image, R.id.save_image})
    public void choiceImage(View view) {
        switch (view.getId()) {
            case R.id.image:
                selectImageDialog.show();
                break;
            case R.id.save_image:
                if (!StringUtils.isEmpty(input_text.getText().toString())) {
                    OperationalDataBase.SaveStarImage(input_text.getText().toString(), imagePath);
                } else {
                    ToastUtils.showToast(context, "没有写名字");
                }
                break;
        }
    }


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
                                //Log.i("保存", result.get(0).getPath());
                                //OperationalDataBase.SaveStarImage("第五人称", result.get(0).getPath
                                // ());
                            }
                        })
                        .start();
            }
        });
    }


    public static void startIntent(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
