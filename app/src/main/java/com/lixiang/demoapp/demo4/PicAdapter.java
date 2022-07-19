package com.lixiang.demoapp.demo4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiang.demoapp.R;

import java.util.List;

public class PicAdapter extends BaseAdapter {

    private List<Photo> photoList;
    private Context context;

    public PicAdapter(List<Photo> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = photoList.get(position);
        View item = LayoutInflater.from(context).inflate(R.layout.demo4_item, parent, false);
        ((ImageView) item.findViewById(R.id.image)).setImageResource(photo.getRes());
        ((TextView) item.findViewById(R.id.title)).setText(photo.getTitle());
        return item;
    }
}
