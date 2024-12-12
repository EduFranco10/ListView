package com.example.listviewpersonalisado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class GenericAdapter<T> extends BaseAdapter {

    private final List<T> items;
    private final int layoutResourceId;
    private final Context context;

    public GenericAdapter(Context context, int layoutResourceId, List<T> items) {
        this.context = context;
        this.items = items;
        this.layoutResourceId = layoutResourceId;
    }

    public abstract void bindView(T item, View view);

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResourceId, parent, false);
        }
        bindView(getItem(position), view);
        return view;
    }
}
