package com.example.xwf.hsia08android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mBtn1 = (Button) findViewById(R.id.btn_1);
        Button mBtn2 = (Button) findViewById(R.id.btn_2);
        Button mBtn3 = (Button) findViewById(R.id.btn_3);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                //普通对话框
                myDialog();
                break;
            case R.id.btn_2:
                //单选对话框
                singleMyDialog();

                break;
            case R.id.btn_3:
                //多选对话框
                doubleMyDialog();
                break;

        }
    }

    private void doubleMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你喜欢吃那些食物？");
        final String[] foods = {"大米","白菜","牛肉","热狗","披萨","火腿","豆腐"};
        final boolean[] isCheck = {true,false,true,false,true,false,false};
        //多选对话框
        builder.setMultiChoiceItems(foods, isCheck, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //取反
               isCheck[which] = isChecked;
            }
        });
        //设置确定点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < isCheck.length; i++) {
                    if (isCheck[i]) {
                        String food = foods[i];
                        //拼接取出的水果
                        sb.append(food + " ");
                    }
                }
                Toast.makeText(getApplicationContext(), "你喜欢吃" + sb.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void singleMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你喜欢什么编程语言");
        final String[] item = {"JAVA","C","GO","PHP"};
        builder.setSingleChoiceItems(item, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String language = item[which];
                Toast.makeText(getApplication(), "你喜欢" + language + "语言", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void myDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("开始游戏");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "你点击了确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "你点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
