package com.example.xwf.broadcast05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadcast(View view){
        Intent intent = new Intent();
        /**
         * receiverPermission:  广播权限
         * resultReceiver:  广播返回结果
         */
        intent.setAction("HsiaH");
        sendOrderedBroadcast(intent, null, new FinalAcceptBroadcast(), null,1,"有序广播1+1=2",null);
    }

}
