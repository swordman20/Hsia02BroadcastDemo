package com.example.xwf.hsia02broadcastdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created:         Hsia on 16/3/6.
 * Email:           xiaweifeng@pingshow.net
 * Description:     {TODO}(用一句话描述该文件做什么)
 */
public class TwoActivity extends AppCompatActivity{

    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        config = getSharedPreferences("config", MODE_PRIVATE);
    }
    public void next(View v){
        config.edit().putBoolean("islogin",true).commit();
        startActivity(new Intent(this,NextActivity.class));
    }
}
