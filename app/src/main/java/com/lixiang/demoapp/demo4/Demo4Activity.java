package com.lixiang.demoapp.demo4;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.lixiang.demoapp.R;

import java.util.ArrayList;
import java.util.List;

public class Demo4Activity extends AppCompatActivity {

    List<Item> itemList;
    int[] resId = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};
    private ItemAdapter itemAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4);

        init();
        listView.setAdapter(itemAdapter);
        initMonitor();
    }

    /**
     * 多选监听
     */
    private void initMonitor() {
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int num = 0;
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // 调整选定条目
                itemList.get(position).checked = checked;
                itemAdapter.notifyDataSetChanged();
                num += checked ? 1 : -1;
                // 用TextView显示
                mode.setTitle("  " + num + " Selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 设置长按后所要显示的标题栏的内容
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.problem04_action_mode, menu);
                num = 0;
                itemAdapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                itemAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {}
        });
    }

    /**
     * 初始化
     */
    private void init() {
        // init data
        itemList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            itemList.add(new Item("图片" + i, resId[i % resId.length]));
        }

        // init adapter
        itemAdapter = new ItemAdapter(itemList, this);

        // init view
        listView = findViewById(R.id.imageList);
    }
}
