package com.example.xwf.broadcast01ip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created:         Hsia on 16/3/6.
 * Email:           xiaweifeng@pingshow.net
 * Description:     {TODO}(用一句话描述该文件做什么)
 */
public class AcceptBroadcast extends BroadcastReceiver {
    //当接受广播的时候调用
    @Override
    public void onReceive(Context context, Intent intent) {
        String string = context.getSharedPreferences("config", Context.MODE_PRIVATE).getString("number", "");
        System.out.println("你打电话了……");
        //获取data数据
        String resultData = getResultData();
        Log.d("Hsia",resultData);
        if (resultData.startsWith("0")){
            setResultData(string+resultData);
        }
    }
}
