前面说过Android应用中，一个Activity可视作为应用的一个介面，实际上Activity是安卓应用的四大组件之一，也是最常用的组件，因为做安卓应用基本上离不开Activity。

本节就针对Activity做一些基础练习。

## 1.1 新建项目
新建一个有Empty Activity的项目。

![image](http://youdao.mikezz.cn/QQ20190321-093415.png-wardo)

![image](http://youdao.mikezz.cn/QQ20190321-093530.png-wardo)

项目已有一个Activity。

![image](http://youdao.mikezz.cn/QQ20190321-093813.png-wardo)


## 1.2 Activity的生命周期

随着应用的打开关闭以及界面跳转等操作，Activity进行着它的生命周期，并在不同阶段执行相应的生命周期方法。具体如下图。

![image](http://www.runoob.com/wp-content/uploads/2015/08/18364230.jpg)

这些生命周期方法都是自动被调用的，可以重写这些方法达到我们的目的。
参考[http://www.runoob.com/w3cnote/android-tutorial-activity.html](http://www.runoob.com/w3cnote/android-tutorial-activity.html)

```
package net.gzchunxiang.cx_activity_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到TextView
        textView = findViewById(R.id.textView);
        textView.setText("activity onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flag++;
        if (flag == 1) {
            textView.setText("activity onCreate-- onStart -- onResume");
        } else {
            textView.setText("onResume " + flag);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


```

运行项目，应用打开后，按home键回到桌面，再点击应用图标打开应用，观察TextView内容变化。

![image](http://youdao.mikezz.cn/QQ20190321-101238.png-wardo)
![image](http://youdao.mikezz.cn/QQ20190321-101353.png-wardo)
![image](http://youdao.mikezz.cn/QQ20190321-101453.png-wardo)


## 1.3 Activity跳转
之前的练习都是在一个Activity中进行，一个安卓应用往往都会有多个界面，那么就会有多个Activity。

新建一个Activity。
![image](http://youdao.mikezz.cn/QQ20190321-102025.png-wardo)
![image](http://youdao.mikezz.cn/QQ20190321-102206.png-wardo)

编辑这个类。
```
package net.gzchunxiang.cx_activity_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //一般在Activiy创建时指定它的布局文件,所以要在res/layout目录下新建布局文件
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(SecondActivity.this,"我要销毁了！！",Toast.LENGTH_LONG).show();
    }
}

```

在res/layout目录下新建activity_second.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_horizontal"
    android:orientation="vertical">

    <TextView
        android:id="@+id/tv"
        android:layout_margin="20dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这里是第二个Activity"/>

    <EditText
        android:id="@+id/et"
        android:layout_margin="20dp"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:gravity="center"
        android:background="#dddddd"
        android:hint="请输入"/>

    <Button
        android:id="@+id/btn"
        android:layout_margin="20dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="关闭Activity"/>

</LinearLayout>
```

编辑AndroidManifest.xml，添加SecondActiviy的声明配置。
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="net.gzchunxiang.cx_activity_demo">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".SecondActivity"/>
    </application>

</manifest>
```

要在第一个Activity中跳转到SecondActivity。

编辑layout_main.xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center_horizontal"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
    
    <Button
        android:id="@+id/button"
        android:layout_margin="20dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="跳转Activity"/>
    
    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="第一个Activity"/>

</LinearLayout>
```

编辑MainActivity

```
package net.gzchunxiang.cx_activity_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int flag;

    TextView textView2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到TextView
        textView = findViewById(R.id.textView);
        textView.setText("activity onCreate");

        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里打开SecondActivity
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flag++;
        if (flag == 1) {
            textView.setText("activity onCreate-- onStart -- onResume");
        } else {
            textView.setText("onResume " + flag);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

```

重新运行项目。点击按钮跳转到第二个Activity。SecondActivity的按钮还没绑定事件，点击没反应。可以按手机的返回键，回退到上一个Activity。

![image](http://youdao.mikezz.cn/QQ20190321-104429.png-wardo)
![image](http://youdao.mikezz.cn/QQ20190321-104725.png-wardo)
![image](http://youdao.mikezz.cn/QQ20190321-105425.png-wardo)

看到Toast内容『我要销毁了』，说明SecondActivity已被销毁，执行onDestroy方法，它的生命周期结束了。
对于MainActivity，它的onRestart，onStart，onResume方法顺序被执行。

## 1.4 Activity间的数据传递

分别编辑MainActivity和SecondActivity如下。

```
package net.gzchunxiang.cx_activity_demo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int flag;

    TextView textView2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到TextView
        textView = findViewById(R.id.textView);
        textView.setText("activity onCreate");

        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里打开SecondActivity
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//                startActivity(intent);
                //在intent中添加要传递的数据 key-value，支持多种数据类型。
                intent.putExtra("msg","hello!!");
                //跳转Activity，同时要求返回时提供结果数据
                startActivityForResult(intent,234);
            }
        });
    }

    /*startActivityForResult与onActivityResult配对使用，进行结果处理*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断是那个请求的返回
        if (requestCode == 234){
            if (data.getStringExtra("result")!=null){
                textView2.setText("SecondActivity返回结果："+data.getStringExtra("result"));
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flag++;
        if (flag == 1) {
            textView.setText("activity onCreate-- onStart -- onResume");
        } else {
            textView.setText("onResume " + flag);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

```

SecondActivity.java
```
package net.gzchunxiang.cx_activity_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //一般在Activiy创建时指定它的布局文件,所以要在res/layout目录下新建布局文件
        setContentView(R.layout.activity_second);

        //读取MainActivity传递过来的数据
        textView = findViewById(R.id.tv);
        String msg = getIntent().getStringExtra("msg");
        textView.setText(msg);


        editText = findViewById(R.id.et);
        button = findViewById(R.id.btn);

        button.setOnClickListener(this);//SeconActivity实现OnClickListener
    }


    //实现OnClickListener的onClick方法
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:

                String result = editText.getText().toString().trim();
                if(result.equals("")){
                    result = "无话可说";
                }
                //设置结果并结束当前Activity
                Intent intent = new Intent();
                intent.putExtra("result",result);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(SecondActivity.this,"我要销毁了！！",Toast.LENGTH_LONG).show();
    }


}

```

运行项目，点击跳转到SecondActivity看到已接收到传过来的msg。

![image](http://youdao.mikezz.cn/QQ20190321-112832.png-wardo)


在输入框输入内容并返回给MainActivity。

![image](http://youdao.mikezz.cn/QQ20190321-113303.png-wardo)
![image](http://youdao.mikezz.cn/QQ20190321-113527.png-wardo)



---
*以上是Activity的基本使用，深入了解Activity，参考[http://www.runoob.com/w3cnote/android-tutorial-activity.html](http://www.runoob.com/w3cnote/android-tutorial-activity.html)*
