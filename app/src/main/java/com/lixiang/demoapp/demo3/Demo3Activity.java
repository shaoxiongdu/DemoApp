package com.lixiang.demoapp.demo3;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
=======
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
>>>>>>> 122f98f (Initial commit)

import com.lixiang.demoapp.R;

public class Demo3Activity extends AppCompatActivity {

<<<<<<< HEAD

=======
    private View rectangle;
>>>>>>> 122f98f (Initial commit)

    /**
     * 显示一个圆角矩形，和三角形，颜色为红色填充黄色边框
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        MyView myView = new MyView(this);
        setContentView(myView);
    }
=======
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


>>>>>>> 122f98f (Initial commit)
}