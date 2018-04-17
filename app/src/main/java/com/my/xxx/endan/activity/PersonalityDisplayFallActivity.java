package com.my.xxx.endan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.my.xxx.endan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import falltextview.FallTextView;

/**
 * Created by sjh on 2018/4/17.
 * 250135506@qq.com
 */


public class PersonalityDisplayFallActivity extends AppCompatActivity {

    @BindView(R.id.fall1)
    FallTextView fall1;
    @BindView(R.id.fall2)
    FallTextView fall2;

    String[] sentences = {"What is design?",
            "Design is how it works. - Steve Jobs",
            "你好",
            "今天是星期二，明天是星期三，昨天是星期一",
            "明天是星期三，后天是星期四",
            "Mac Pro"};

    int index;
    int input_index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_personaltity_fall_display);
        ButterKnife.bind(this);
        timer.start();
    }


    //循环展示
    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 3000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (index + 1 >= sentences.length) {
                index = 0;
            }
            input_index = index++;

            if (sentences[input_index].length() >= 28) {
                String substring1 = sentences[input_index].substring(0, 28);
                String substring2 = sentences[input_index].substring(28, sentences[input_index].length());
                fall1.animateText(substring1.trim());
                fall2.animateText(substring2.trim());
            } else {
                fall1.animateText(sentences[input_index]);
                fall2.animateText("");
            }
        }

        @Override
        public void onFinish() {

        }
    };


    public static void startIntent(Context context) {
        Intent intent = new Intent(context, PersonalityDisplayFallActivity.class);
        context.startActivity(intent);
    }
}
