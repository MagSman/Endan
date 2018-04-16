package com.my.xxx.endan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.my.xxx.endan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import evaporatetextview.EvaporateTextView;
import falltextview.FallTextView;
import scaletxetview.ScaleTextView;
import typertextview.TyperTextView;

/**
 * Created by sjh on 2018/4/13.
 * 250135506@qq.com
 */

public class PersonalityActivity extends AppCompatActivity {
    @BindView(R.id.fall)
    FallTextView fall;
    @BindView(R.id.fall_button)
    Button fall_button;
    @BindView(R.id.scale)
    ScaleTextView scale;
    @BindView(R.id.scale_button)
    Button scale_button;
    @BindView(R.id.evaporate)
    EvaporateTextView evaporate;
    @BindView(R.id.evaporate_button)
    Button evaporate_button;
    @BindView(R.id.typer)
    TyperTextView typer;
    @BindView(R.id.typer_button)
    Button typer_button;

    String[] sentences = {"What is design?",
            "Design is not just",
            "what it looks like and feels like.",
            "Design is how it works. \n- Steve Jobs",
            "Older people",
            "sit down and ask,",
            "'What is it?'",
            "but the boy asks,",
            "'What can I do with it?'. \n- Steve Jobs",
            "Swift",
            "Objective-C",
            "iPhone",
            "iPad",
            "Mac Mini", "MacBook Pro", "Mac Pro", "爱老婆", "老婆和女儿"};

    int index;
    int input_index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaltity_set);
        ButterKnife.bind(this);
        timer.start();
    }


    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 2000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (index + 1 >= sentences.length) {
                index = 0;
            }
            input_index = index++;
            fall.animateText(sentences[input_index]);
            scale.animateText(sentences[input_index]);
            evaporate.animateText(sentences[input_index]);
            typer.animateText(sentences[input_index]);

        }

        @Override
        public void onFinish() {

        }
    };


    public static void startIntent(Context context) {
        Intent intent = new Intent(context, PersonalityActivity.class);
        context.startActivity(intent);
    }
}
