package com.lixiang.demoapp.demo3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyView extends View {

    private static final String TAG = MyView.class.getName();
    Paint fillPaint = new Paint();
    Paint strokePaint = new Paint();
    private Canvas canvas;

    public MyView(Context context) {
        super(context);

        initPaint();
    }

    private void initPaint() {
        fillPaint.setColor(Color.RED);
        fillPaint.setStyle(Paint.Style.FILL);

        strokePaint.setColor(Color.YELLOW);
        strokePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        super.onDraw(canvas);
        drawRect();
        drawThree();
    }

    private void drawRect(){
        strokePaint.setStrokeWidth(14.5f);
        canvas.drawRoundRect(300f,300f,600f,600f,50f,50f,fillPaint);
        canvas.drawRoundRect(300f,300f,600f,600f,50f,50f,strokePaint);
    }

    private void drawDownRect(){
        strokePaint.setStrokeWidth(14.5f);
        canvas.drawRoundRect(300f,300f,600f,600f,50f,50f,strokePaint);
        canvas.drawRoundRect(300f,300f,600f,600f,50f,50f,fillPaint);
    }

    private void drawThree(){

        Path path = new Path ();
        path.moveTo(10,340);//将路径点设置到10,340位置
        path.lineTo(70,340);//将起始路径点连接都70,340位置
        path.lineTo(40,290);//将第二连接点连接到40,290位置
        path.close();//关闭路径的绘制
        //根据Path进行绘制，绘制三角形
        canvas.drawPath(path,fillPaint);
        canvas.drawPath(path,strokePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        float x = event.getX();
        float y = event.getY();
        if (x < 300 || x > 600 || y < 300 || y > 600) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawDownRect();
                break;
            case MotionEvent.ACTION_UP:
                drawRect();
                break;
            default:
        }

        return super.onTouchEvent(event);
    }
}
