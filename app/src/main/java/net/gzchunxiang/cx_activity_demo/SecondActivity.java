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
