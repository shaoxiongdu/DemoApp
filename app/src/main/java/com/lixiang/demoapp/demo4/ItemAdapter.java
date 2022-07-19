package com.lixiang.demoapp.demo4;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiang.demoapp.R;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private final List<Item> itemList;
    private final Context context;

    public ItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item photo = itemList.get(position);
        View item = LayoutInflater.from(context).inflate(R.layout.demo4_item, parent, false);
        ((ImageView) item.findViewById(R.id.image)).setImageResource(photo.getRes());
        ((TextView) item.findViewById(R.id.title)).setText(photo.getTitle());
        if (photo.checked) {
            item.setBackgroundColor(Color.RED);
        }else {
            item.setBackgroundColor(Color.WHITE);
        }
        return item;
    }
}
