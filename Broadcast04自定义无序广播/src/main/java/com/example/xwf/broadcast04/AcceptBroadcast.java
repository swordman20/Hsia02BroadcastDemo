package com.example.xwf.broadcast04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created:         Hsia on 16/3/6.
 * Email:           xiaweifeng@live.cn
 * Description:     {TODO}(用一句话描述该文件做什么)
 */
public class AcceptBroadcast extends BroadcastReceiver {
    private static final String TAG = "Hsia";

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = (String) intent.getExtras().get("name");

        Log.d(TAG,"你发送了一个自定义广播"+name);
    }
}
