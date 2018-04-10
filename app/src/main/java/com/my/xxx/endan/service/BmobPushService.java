package com.my.xxx.endan.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.my.xxx.endan.endan.Endan;

import cn.bmob.push.PushConstants;

/**
 * Created by sjh on 2018/4/10.
 * 250135506@qq.com
 */

public class BmobPushService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            Log.d("bmob", "客户端收到推送内容：" + intent.getStringExtra("msg"));
            Toast.makeText(context,intent.getStringExtra("msg"),Toast.LENGTH_SHORT).show();
        }
    }
}
