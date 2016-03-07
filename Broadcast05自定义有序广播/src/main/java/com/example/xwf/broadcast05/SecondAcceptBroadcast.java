package com.example.xwf.broadcast05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created:         Hsia on 16/3/7.
 * Email:           xiaweifeng@live.cn
 * Description:     {TODO}(用一句话描述该文件做什么)
 */
public class SecondAcceptBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        setResultData("有序广播4+4=8");
        Toast.makeText(context, resultData, Toast.LENGTH_SHORT).show();
    }
}
