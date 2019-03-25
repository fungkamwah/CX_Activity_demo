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
