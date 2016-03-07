package com.example.xwf.broadcast03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created:         Hsia on 16/3/6.
 * Email:           xiaweifeng@live.cn
 * Description:     {TODO}(用一句话描述该文件做什么)
 */
public class AutoBootBroadcast extends BroadcastReceiver {
    private static final String TAG = "Hsia";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            Log.d(TAG,"有APP安装了");
        }else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")){
            Log.d(TAG,"有APP被卸载了");
        }


        //开机启动的广播
        Log.d(TAG,"您的手机开机了");

//        Intent intent1 = intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Intent intent1 = new Intent(context, MainActivity.class);
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent1);
        Log.d(TAG,"链式写法");
        context.startActivity(new Intent(context,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
