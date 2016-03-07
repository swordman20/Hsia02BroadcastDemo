package com.example.xwf.hsia02broadcastdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created:         Hsia on 16/3/6.
 * Email:           xiaweifeng@pingshow.net
 * Description:     {TODO}(用一句话描述该文件做什么)
 */
public class NextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        TextView textView = new TextView(NextActivity.this);
        textView.setText("欢迎进入主界面");
        textView.setTextSize(23);
        textView.setGravity(Gravity.CENTER);
    }
}
