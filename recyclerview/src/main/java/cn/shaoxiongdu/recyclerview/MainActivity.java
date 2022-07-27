package cn.shaoxiongdu.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final ArrayList<News> mNewsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // 设置适配器
        NewsAdapter newsAdapter = new NewsAdapter(this, mNewsList);
        recyclerView.setAdapter(newsAdapter);

        // 设置线性布局 orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    private void initData() {
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.title = "标题" + i;
            news.content = "内容" + i;
            if (i % 3 == 0) news.content += "内容\n" + i + "内容\n" + i + "内容\n" + i;
            mNewsList.add(news);
        }
    }

}