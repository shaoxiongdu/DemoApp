package com.lixiang.demoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lixiang.demoapp.demo1.Demo1Activity;
import com.lixiang.demoapp.demo3.Demo3Activity;
import com.lixiang.demoapp.demo4.Demo4Activity;

public class MainActivity extends AppCompatActivity {

    private static SharedPreferences sp;
    private static TextView resText;
    private static TextView currentTheme;
    private static final String SAVE_FILE_NAME = "input_data";

    /**
     * 初始化
     */
    private void init() {
        resText = findViewById(R.id.dialogText);
        sp = getSharedPreferences(SAVE_FILE_NAME, Context.MODE_PRIVATE);
        currentTheme = findViewById(R.id.currentTheme);

        // 读取历史记录
        resText.setText(sp.getString("input", ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * 监听配置文件改变
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int currentNightMode = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // 关闭
                currentTheme.setText("当前主题:亮");
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // 开启
                currentTheme.setText("当前主题:黑");
                currentTheme.setTextColor(Color.RED);
                break;
            default:
                break;
        }
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
                .setTitle("输入框")
                .setMessage("请在下方输入数据:")
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    resText.setText(inputEditText.getText());
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", (dialogInterface, i) -> {
                    Toast.makeText(this, "您取消了保存", Toast.LENGTH_SHORT).show();
                })
                .show();
    }


    public void toGraphActivity(View view) {
        Intent intent = new Intent(this, Demo3Activity.class);
        startActivity(intent);
    }

    /**
     * 有图片列表显示，可滚动，可长按后角标显示选中状态
     *
     * @param view
     */
    public void toImageListActivity(View view) {
        Intent intent = new Intent(this, Demo4Activity.class);
        startActivity(intent);
    }

    /**
     * 暂停时 持久化输入
     */
    @Override
    protected void onPause() {
        super.onPause();
        sp.edit().putString("input", resText.getText().toString()).commit();
    }

    /**
     * 跳转二级activity
     *
     * @param view
     */
    public void toOtherActivity(View view) {
        Intent intent = new Intent(this, Demo1Activity.class);
        startActivity(intent);
    }


}