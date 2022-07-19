package com.lixiang.demoapp;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

=======
>>>>>>> 122f98f (Initial commit)
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
<<<<<<< HEAD

import com.lixiang.demoapp.demo1.OtherActivity;
import com.lixiang.demoapp.demo3.Demo3Activity;

/**
 * 2.有outton,点击可弹出Dialog
 * a.Dialog内有edittext,可以输入文字，文字输入完成后会显示在activity内，且activity销毁后再次
 * 进入也要显示到activity内
 * b.Dialog有确定和取消button
 * <p>
 * 3.在activity内画一个显示一个圆角矩形，和三角形，颜色为红色填充黄色边框
 * <p>
 * 4.设置圆角矩形支持可按，按下时为黄色填充、红色边框
 * 5.有图片列表显示，可滚动，可长按后角标显示选中状态
=======
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lixiang.demoapp.demo1.Demo1Activity;
import com.lixiang.demoapp.demo3.Demo3Activity;
import com.lixiang.demoapp.demo4.Demo4Activity;

/**
 * 5.
>>>>>>> 122f98f (Initial commit)
 * 6.监听主体改变后，将activity内字体改变颜色
 */
public class MainActivity extends AppCompatActivity {

    private TextView resText;
    SharedPreferences sp;

    private void init() {
        resText = findViewById(R.id.dialogText);
        sp = getSharedPreferences("inputData", Context.MODE_PRIVATE);
        resText.setText(sp.getString("input",""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * 有button,点击可弹出Dialog
     * a.Dialog内有edittext,可以输入文字，文字输入完成后会显示在activity内，且activity销毁后再次
     * 进入也要显示到activity内
     * *      b.Dialog有确定和取消button
     *
     * @param view
     */
    public void showDialog(View view) {
        EditText inputEditText = new EditText(this);

        new AlertDialog.Builder(this)
                .setView(inputEditText)
<<<<<<< HEAD
                .setPositiveButton("yes", (dialogInterface, i) -> {
                    resText.setText(inputEditText.getText());
                })
                .setNegativeButton("no", (dialogInterface, i) -> {
=======
                .setTitle("输入框")
                .setMessage("请在下方输入数据:")
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    resText.setText(inputEditText.getText());
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", (dialogInterface, i) -> {
                    Toast.makeText(this, "您取消了保存", Toast.LENGTH_SHORT).show();
>>>>>>> 122f98f (Initial commit)
                })
                .show();
    }

    /**
     * 显示一个圆角矩形，和三角形，颜色为红色填充黄色边框
     *
     * 设置圆角矩形支持可按，按下时为黄色填充、红色边框
     *
     * @param view
     */
    public void showGraph(View view) {
        Intent intent = new Intent(this, Demo3Activity.class);
        startActivity(intent);
    }

<<<<<<< HEAD
    public void showImageList(View view) {
=======
    /**
     * 有图片列表显示，可滚动，可长按后角标显示选中状态
     *
     * @param view
     */
    public void showImageList(View view) {
        Intent intent = new Intent(this, Demo4Activity.class);
        startActivity(intent);
>>>>>>> 122f98f (Initial commit)
    }

    public void changeColor(View view) {
    }

<<<<<<< HEAD


    @Override
    protected void onDestroy() {
        super.onDestroy();
=======
    @Override
    protected void onPause() {
        super.onPause();
>>>>>>> 122f98f (Initial commit)
        saveDialogInput();
    }

    private void saveDialogInput(){
        sp.edit().putString("input",resText.getText().toString()).commit();
    }

    /**
     * 跳转二级activity
     *
     * @param view
     */
    public void toOtherActivity(View view) {
<<<<<<< HEAD
        Intent intent = new Intent(this, OtherActivity.class);
=======
        Intent intent = new Intent(this, Demo1Activity.class);
>>>>>>> 122f98f (Initial commit)
        startActivity(intent);
    }


}