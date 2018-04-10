package com.my.xxx.endan.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;


import com.my.xxx.endan.MainActivity;
import com.my.xxx.endan.R;


import cn.bmob.push.PushConstants;

/**
 * Created by sjh on 2018/4/10.
 * 250135506@qq.com
 */

public class BmobPushService extends BroadcastReceiver {

    private static String msg;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            //Log.d("bmob", "客户端收到推送内容：" + intent.getStringExtra("msg"));
            Toast.makeText(context, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
            msg = intent.getStringExtra("msg");
            showNotifictionIcon(context);
        }
    }

    public static void showNotifictionIcon(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent intent = new Intent(context, MainActivity.class);//将要跳转的界面
        //Intent intent = new Intent();//只显示通知，无页面跳转
        builder.setAutoCancel(true);//点击后消失
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知栏消息标题的头像
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
       /* builder.setTicker("状态栏显示的文字");
        builder.setContentTitle("标题");
        builder.setContentText("通知内容");*/
        builder.setTicker(msg);
        builder.setContentTitle(msg);
        builder.setContentText(msg);
        //利用PendingIntent来包装我们的intent对象,使其延迟跳转
        PendingIntent intentPend = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(intentPend);
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
