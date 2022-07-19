package com.lixiang.demoapp.demo3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Triangle extends View {

    private static final String TAG = Triangle.class.getName();
    Paint fillRedPaint = new Paint();
    Paint strokeYellowPaint = new Paint();

    public Triangle(Context context) {
        super(context);
        initPaint();
    }

    public Triangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initPaint() {
        fillRedPaint.setColor(Color.RED);
        fillRedPaint.setStyle(Paint.Style.FILL);

        strokeYellowPaint.setColor(Color.YELLOW);
        strokeYellowPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawThree(canvas);
    }

    private void drawThree(Canvas canvas) {

        Path path = new Path();
        path.moveTo(10, 340);//将路径点设置到10,340位置
        path.lineTo(70, 340);//将起始路径点连接都70,340位置
        path.lineTo(40, 290);//将第二连接点连接到40,290位置
        path.close();//关闭路径的绘制
        //根据Path进行绘制，绘制三角形
        canvas.drawPath(path, fillRedPaint);
        canvas.drawPath(path, strokeYellowPaint);
    }
}
