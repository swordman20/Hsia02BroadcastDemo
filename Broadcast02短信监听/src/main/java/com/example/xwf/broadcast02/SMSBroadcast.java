package com.example.xwf.broadcast02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created:         Hsia on 16/3/6.
 * Email:           xiaweifeng@live.cn
 * Description:     {TODO}(用一句话描述该文件做什么)
 */
public class SMSBroadcast extends BroadcastReceiver {
    private static final String TAG = "Hsia";

    @Override
    public void onReceive(Context context, Intent intent) {
        //pdus是根据开发文档提示或系统上层源码查看得知
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object obj: pdus) {
            //获取SmsMessage对象
            SmsMessage fromPdu = SmsMessage.createFromPdu((byte[]) obj);
            String address = fromPdu.getOriginatingAddress();
            String body = fromPdu.getMessageBody();
            Log.d(TAG,"短信号码："+address);
            Log.d(TAG,"短信内容："+body);
        }
    }
}
