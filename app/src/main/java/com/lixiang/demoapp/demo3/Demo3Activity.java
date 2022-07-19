package com.lixiang.demoapp.demo3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;

import com.lixiang.demoapp.R;

public class Demo3Activity extends AppCompatActivity {



    /**
     * 显示一个圆角矩形，和三角形，颜色为红色填充黄色边框
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView myView = new MyView(this);
        setContentView(myView);
    }
}