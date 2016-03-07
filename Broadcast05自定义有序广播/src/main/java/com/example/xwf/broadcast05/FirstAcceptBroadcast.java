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
public class FirstAcceptBroadcast   extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        //终止广播往下传递
        abortBroadcast();
        String resultData = getResultData();
        setResultData("有序广播2+2=4");
        Toast.makeText(context,resultData,Toast.LENGTH_SHORT).show();
    }
}
