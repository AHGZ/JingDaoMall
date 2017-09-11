package com.hgz.test.jingdongmall.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgz.test.jingdongmall.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MyListviewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list;
    private HashMap<Integer, Boolean> hashMap;
    public MyListviewAdapter(Context context, ArrayList<String> list) {
        hashMap = new HashMap<>();
        this.context = context;
        this.list=list;
        for (int i=0;i<list.size();i++){
            hashMap.put(i,false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.shopping_listview_items, null);
        }
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.listview_checkbox);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.listview_image);
        TextView description= (TextView) convertView.findViewById(R.id.listview_description);
        TextView color = (TextView) convertView.findViewById(R.id.listview_color);
        TextView price= (TextView) convertView.findViewById(R.id.listview_price);
        ImageView cutDown = (ImageView) convertView.findViewById(R.id.listview_cut_down);
        TextView count = (TextView) convertView.findViewById(R.id.listview_count);
        ImageView add = (ImageView) convertView.findViewById(R.id.listview_add);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hashMap.put(position, !hashMap.get(position));
                notifyDataSetChanged();

            }
        });
        checkBox.setChecked(hashMap.get(position));
        return convertView;
    }
    //全选
    public void allSelect(){
        Set<Map.Entry<Integer, Boolean>> entries = hashMap.entrySet();
        boolean shouldSelectedAll = false;
        for (Map.Entry<Integer, Boolean> entry: entries) {
            Boolean value = entry.getValue();
            if (!value){
                shouldSelectedAll=true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> entry: entries) {
            entry.setValue(shouldSelectedAll);
        }
        notifyDataSetChanged();
    }
    //移除item
    public void removeItem(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
}