package com.my.xxx.endan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.my.xxx.endan.R;

/**
 * Created by sjh on 2018/4/13.
 * 250135506@qq.com
 */

public class PersonalityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaltity_set);
    }


    public static void startIntent(Context context) {
        Intent intent = new Intent(context, PersonalityActivity.class);
        context.startActivity(intent);
    }
}
