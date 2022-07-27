package cn.shaoxiongdu.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    public TextView mTitleTv;
    public TextView mContentTv;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitleTv = itemView.findViewById(R.id.title);
        mContentTv = itemView.findViewById(R.id.content);
    }

}
