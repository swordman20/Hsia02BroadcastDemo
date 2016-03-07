package com.example.xwf.broadcast04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sendbroadcast(View v){
        Intent intent = new Intent();
        intent.setAction("Hsia");
        intent.putExtra("name","Hsia");
        sendBroadcast(intent);
    }
}
