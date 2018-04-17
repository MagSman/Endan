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
import scaletxetview.ScaleTextView;

/**
 * Created by sjh on 2018/4/17.
 * 250135506@qq.com
 */


public class PersonalityDisplayScaleActivity extends AppCompatActivity {

    @BindView(R.id.fall1)
    ScaleTextView fall1;
    @BindView(R.id.fall2)
    ScaleTextView fall2;

    String[] sentences = {"What is design?",
            "Design is not just",
            "what it looks like and feels like.",
            "Design is how it works. - Steve Jobs",
            "Older people",
            "sit down and ask,",
            "'What is it?'",
            "but the boy asks,",
            "'What can I do with it?'. - Steve Jobs",
            "Swift",
            "Objective-C",
            "iPhone",
            "iPad",
            "Mac Mini", "MacBook Pro", "Mac Pro"};

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
    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 4000) {
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
        Intent intent = new Intent(context, PersonalityDisplayScaleActivity.class);
        context.startActivity(intent);
    }
}
