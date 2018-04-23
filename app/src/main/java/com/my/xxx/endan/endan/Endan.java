package com.my.xxx.endan.endan;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.logging.Logger;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by sjh on 2018/4/9.
 * 250135506@qq.com
 */

public class Endan extends Application {

    private static Endan instance;

    public static Endan getEndanContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initBmob();
    }

    private void initBmob() {
        //初始化Bmob
        Bmob.initialize(this, "33c30558c016b8b8cef910cefafd3aca");
        //使用推送服务时的初始化操作
        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    Log.i("sss", bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
                } else {
                    Log.e("sss", e.getMessage());
                }
            }
        });
        // 启动推送服务
        BmobPush.startWork(this);
        //AdManager.getInstance(Context context).init(String appId, String appSecret, boolean isEnableYoumiLog);


    }
}
