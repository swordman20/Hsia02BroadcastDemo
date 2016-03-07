package com.example.xwf.hsia02broadcastdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * 这个工程是用来，模拟app的欢迎功能，写了个定时器。
 */
public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        final boolean islogin = config.getBoolean("islogin", false);
        //3000后开启一个界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (islogin) {
                startActivity(new Intent(getApplicationContext(),NextActivity.class));
                }else{
                startActivity(new Intent(getApplicationContext(),TwoActivity.class));
                }
            }
        },3000);
    }
}
