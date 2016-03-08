#Android学习从零单排之BroadCastReceiver

**读了那么多年的书让我明白一个道理。人要稳重，不要想到啥就做啥。做一行越久即使你不会，几年之后慢慢的你也会了，加上一点努力你或许你能成为别人眼中的专家。**

	注：本blog一部分是Android广播内容（因为广播内容太少了），一部分是AndroidUI内容。

##BroadCastReceiver是什么
Android应用程序，会把一系列事件（电量不足、电量充满、开机启动完成）作为一个广播消息发送其他的接收者；当然自己也可以定义一些广播事件发送出去，然后我们需要自己写一个广播接受者，用来接受系统发出的广播或者自己定义的广播，来处理某些数据。

	广播的特点：
即使广播接收者没有运行，当广播消息到达的时候，系统会自动启动广播接收者的进程，调用onReceive方法，接收消息。

##ip拨号器开发步骤
	1、写一个类继承自BroadcastReceiver，并在清单文件中，配置拨打电话的action,和添加权限。


```
<uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"/>

<receiver android:name=".AcceptBroadcast">
            <intent-filter>
                <!--当拨打电话出去的时候，发送此广播-->
                <action android:name="android.intent.action.NEW_OUTGOING_CALL"/>
            </intent-filter>
        </receiver>
```
	2、在AcceptBroadcast类中重写onReceive方法，监听拨打电话的状态

```
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
```

##短信监听器开发步骤
	1、写一个类继承自BroadcastReceiver，并在清单文件中，配置拨打电话的action,和添加权限。

```
<uses-permission android:name="android.permission.RECEIVE_SMS"/>

<receiver android:name=".SMSBroadcast">
            <intent-filter>
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
            </intent-filter>
        </receiver>
```
	2、在SMSBroadcast类中重写onReceive方法，监听短信内容和短信号码

```
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
```

##监听开机状态开发步骤
	1、写一个类继承自BroadcastReceiver，并在清单文件中，配置拨打电话的action,和添加权限。
```

<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>

<receiver android:name=".AutoBootBroadcast">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
            </intent-filter>
        </receiver>
```
	2、在AutoBootBroadcast类中重写onReceive方法，监听监听开机状态，并开启主界面。

```
@Override
    public void onReceive(Context context, Intent intent) {
        //开机启动的广播
        Log.d(TAG,"您的手机开机了");

//        Intent intent1 = intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Intent intent1 = new Intent(context, MainActivity.class);
        //必须配置
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent1);
        Log.d(TAG,"链式写法");
        context.startActivity(new Intent(context,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
```
##发送自定义无序广播
	1、发送自定义广播
```
public void sendbroadcast(View v){
        Intent intent = new Intent();
        //设置Action动作
        intent.setAction("Hsia");
        intent.putExtra("name","Hsia");
        sendBroadcast(intent);
    }
```
	2、写一个类，接受自定义广播
```
 @Override
    public void onReceive(Context context, Intent intent) {
        String name = (String) intent.getExtras().get("name");
        Log.d(TAG,"你发送了一个自定义广播"+name);
    }
```

##发送自定义有序广播（可被拦截）
	1、发送一条自定义有序广播

```

public void sendBroadcast(View view){
        Intent intent = new Intent();
        intent.setAction("HsiaH");
        //发送一个有序的广播
        //intent 意图
        //permission 指定接收者需要添加了权限
        //resultReceiver 指定哪个广播接收者最后接到消息
        //scheduler 消息处理器 
        //initialCode 给消息指定初始代码
        //initialData 指定消息的数据
        //initialExtras 指定额外的参数
        sendOrderedBroadcast(intent, null, new FinalAcceptBroadcast(), null,1,"有序广播1+1=2",null);
    }
```
	2、写多个广播接受者，用来接受广播，并在清单文件中配置层级关系。

```
<receiver android:name=".FirstAcceptBroadcast">
                <!--代表的层级关系-->
            <intent-filter
                android:priority="1000">
                <action android:name="HsiaH"></action>
            </intent-filter>
        </receiver>
        <receiver android:name=".SecondAcceptBroadcast">
            <intent-filter
                android:priority="800">
                <action android:name="HsiaH"></action>
            </intent-filter>
        </receiver>
        <receiver android:name=".ThirdAcceptBroadcast">
            <intent-filter
                android:priority="500">
                <action android:name="HsiaH"></action>
            </intent-filter>
        </receiver>
        <receiver android:name=".FinalAcceptBroadcast">
            <intent-filter
                android:priority="300">
                <action android:name="HsiaH"></action>
            </intent-filter>
        </receiver>

```

	3、随便看一个广播接受者

```
@Override
    public void onReceive(Context context, Intent intent) {
        //终止广播往下传递
        abortBroadcast();
        String resultData = getResultData();
        setResultData("有序广播2+2=4");
        Toast.makeText(context,resultData,Toast.LENGTH_SHORT).show();
    }
```
	4、最后还可以定义一个最终的广播接受者，用来监视广播下发的数据变化。

```
@Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        Toast.makeText(context, "最终接受广播："+resultData, Toast.LENGTH_SHORT).show();
    }
```

#以下是AndroidUI部分内容

##Android自定义样式
	1、在style中定义样式

```
<!--我的主题,继承自AppTheme-->
    <style name="MyTheme" parent="AppTheme">
        <item name="android:background">#ff0</item>
        <item name="colorPrimaryDark">#ff0</item>
    </style>

    <!--定义自定义样式-->
<style name="MyStyle">
    <item name="android:layout_width">wrap_content</item>
    <item name="android:layout_height">wrap_content</item>
    <item name="android:textSize">23sp</item>
    <item name="android:textColor">#ff0000</item>
    <item name="android:layout_centerInParent">true</item>
</style>
```
	2、在layout文件中引用

```
 <activity android:name=".MainActivity"
            android:theme="@style/MyTheme">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>


<!--引用自定义样式-->
    <TextView
        style="@style/MyStyle"
        android:text="Hello World!" />
```
##Android中国际化
	1、res目录下创建values—(国家编码）
	2、在自己创建的vaules—(国家编码)文件中，复制strings.xml文件，并更改里面的字符串。

##Android中定义帧动画
步骤
	1、在drawable目录下新建layout文件，根节点为animation-list，item为你的资源文件。

```

<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="true">
    <item android:drawable="@drawable/rocket_thrust1" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust2" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust3" android:duration="200" />

</animation-list>
```
	2、在Activity中找到imageview控件，并设置背景资源，强制转为AnimationDrawable，最后start动画。

```

rocketImage.setBackgroundResource(R.drawable.rocket_drawable);
        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
        rocketAnimation.start();
```

##Android补间动画

```

//渐变动画
AlphaAnimation(1.0f,0.1f);
//旋转动画
RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//缩放动画
ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//位移动画
new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.5f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, -0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);

//动画集合
AnimationSet(false);

```

##Android中常用的对话框
	因对话框基于AlertDialog这个类，所以只写了多选对话框代码，普通对话框和单选对话框以此类推。

```
//单选对话框
 builder.setSingleChoiceItems(item, 1, new DialogInterface.OnClickListener() {

//多选对话框
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

```

**关于作者**
	- Email：[xiaweifeng@live.cn](https://login.live.com)
	- 项目地址:[https://github.com/swordman20/Hsia02BroadcastDemo](https://github.com/swordman20/Hsia02BroadcastDemo)