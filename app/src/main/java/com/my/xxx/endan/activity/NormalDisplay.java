package com.my.xxx.endan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.xxx.endan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sjh on 2018/4/13.
 * 250135506@qq.com
 */

public class NormalDisplay extends AppCompatActivity {

    @BindView(R.id.up)
    View up;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.down)
    View down;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_display);
        ButterKnife.bind(this);




    }
}
