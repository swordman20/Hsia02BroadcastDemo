package com.example.xwf.hsia07androidanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created:         Hsia on 16/3/7.
 * Email:           xiaweifeng@live.cn
 * Description:     {TODO}(用一句话描述该文件做什么)
 */
public class second extends Activity implements View.OnClickListener {

    private ImageView image;
    private AlphaAnimation aa;
    private RotateAnimation ra;
    private ScaleAnimation sa;
    private TranslateAnimation ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        image = (ImageView) findViewById(R.id.image);
        Button mBtn1 = (Button) findViewById(R.id.btn_1);
        Button mBtn2 = (Button) findViewById(R.id.btn_2);
        Button mBtn3 = (Button) findViewById(R.id.btn_3);
        Button mBtn4 = (Button) findViewById(R.id.btn_4);
        Button mBtn5 = (Button) findViewById(R.id.btn_5);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);

        //渐变动画
        aa = new AlphaAnimation(1.0f,0.1f);
        //旋转动画
        ra = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //缩放动画
        sa = new ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //位移动画
        ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.5f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, -0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //渐变动画
            case R.id.btn_1:

                aa.setDuration(3000);//设置动画时间
                aa.setRepeatMode(Animation.RESTART);//设置动画播放模式
                aa.setRepeatCount(1);//设置动画显示次数
                image.startAnimation(aa);
                break;
            //旋转动画
            case R.id.btn_2:

                ra.setDuration(3000);
                ra.setRepeatMode(Animation.RESTART);
                ra.setRepeatCount(1);
                image.startAnimation(ra);
                break;
            //缩放动画
            case R.id.btn_3:
                sa.setDuration(3000);
                sa.setRepeatMode(Animation.RESTART);
                sa.setRepeatCount(1);
                image.startAnimation(sa);
                break;
            //位移动画
            case R.id.btn_4:
                ta.setDuration(3000);
                ta.setRepeatMode(Animation.RESTART);
                ta.setRepeatCount(1);
                image.startAnimation(ta);
                break;
            //动画集合
            case R.id.btn_5:
                AnimationSet as = new AnimationSet(false);
                as.addAnimation(aa);
                as.addAnimation(ra);
                as.addAnimation(sa);
                as.addAnimation(ta);
                as.setFillAfter(true);
                image.startAnimation(as);
                break;
            default:
                break;
        }
    }
}
