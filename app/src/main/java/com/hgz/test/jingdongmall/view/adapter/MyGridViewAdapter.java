package com.hgz.test.jingdongmall.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgz.test.jingdongmall.R;

/**
 * Created by Administrator on 2017/9/9.
 */

public class MyGridViewAdapter extends BaseAdapter{
    private Context context;
    public MyGridViewAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = convertView.inflate(context, R.layout.gridview_items, null);
        }
        TextView text= (TextView) convertView.findViewById(R.id.gridview_item_text);
        ImageView image = (ImageView) convertView.findViewById(R.id.gridview_item_image);
        text.setText("新品T恤");
        image.setImageResource(R.drawable.nanzhuang);
        return convertView;
    }
}
