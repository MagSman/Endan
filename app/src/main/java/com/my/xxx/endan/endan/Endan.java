package com.my.xxx.endan.endan;

import android.app.Application;
import android.content.Context;

/**
 * Created by sjh on 2018/4/9.
 * 250135506@qq.com
 */

public class Endan extends Application {

    private static Context endancontext;

    @Override
    public void onCreate() {
        super.onCreate();
        endancontext = getApplicationContext();
        initBmob();
    }

    private void initBmob() {


    }
}
