package com.lixiang.demoapp.demo4;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.lixiang.demoapp.R;

import java.util.ArrayList;
import java.util.List;

public class Demo4Activity extends AppCompatActivity {

    List<Photo> photoList;
    int[] resId = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};
    private PicAdapter picAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4);

        init();
        listView.setAdapter(picAdapter);
    }

    private void init() {
        // init data
        photoList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            photoList.add(new Photo("图片" + i, resId[i % resId.length]));
        }

        // init adapter
        picAdapter = new PicAdapter(photoList, this);

        // init view
        listView = findViewById(R.id.imageList);
    }
}