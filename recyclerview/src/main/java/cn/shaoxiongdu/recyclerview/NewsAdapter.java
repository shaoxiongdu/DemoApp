package cn.shaoxiongdu.recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    public Context context;
    public ArrayList<News> mNewsList;

    public NewsAdapter(Context context, ArrayList<News> mNewsList) {
        this.context = context;
        this.mNewsList = mNewsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_list, null);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.mTitleTv.setText(news.title);
        holder.mContentTv.setText(news.content);
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }


}
