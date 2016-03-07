package com.example.xwf.broadcast01ip;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        if (config != null) {

            et.setText( config.getString("number", ""));
        }
    }
    public void save(View view){
        String trim = et.getText().toString().trim();
        getSharedPreferences("config",MODE_PRIVATE).edit().putString("number",trim).commit();
    }
}
