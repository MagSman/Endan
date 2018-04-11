package com.my.xxx.endan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.xxx.endan.R;
import com.my.xxx.mylibrary.EZLedView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.start)
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }




    @OnClick({R.id.start})
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.start:
                LedDisplayActivity.startIntent(this, 1);
                break;
        }
    }

}
