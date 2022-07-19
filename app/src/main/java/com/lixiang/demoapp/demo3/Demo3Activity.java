package com.lixiang.demoapp.demo3;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lixiang.demoapp.R;

/**
 * 显示一个圆角矩形，和三角形，颜色为红色填充黄色边框
 * <p>
 * 设置圆角矩形支持可按，按下时为黄色填充、红色边框
 *
 */
public class Demo3Activity extends AppCompatActivity {

    private View rectangle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);

        initView();
        initEvent();
    }

    private void initView() {
        rectangle = findViewById(R.id.rectangle);
    }

    private void initEvent() {
        rectangle.setOnTouchListener((view, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    rectangle.setBackgroundResource(R.drawable.background);
                    break;
                case MotionEvent.ACTION_UP:
                    rectangle.setBackgroundResource(R.drawable.selecd_background);
            }
            return true;
        });
    }
}