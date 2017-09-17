package com.hgz.test.jingdongmall.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.model.bean.ListViewBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MyListviewAdapter extends BaseAdapter {
    private Context context;

    private List<ListViewBean> list;

    OnShopingChangeListener onShopingChangeListener;
    OnShopingSelectAllListener onShopingSelectAllListener;
    private boolean isAllSelected ;

    public void setOnShopingChangeListener(OnShopingChangeListener onShopingChangeListener) {
        this.onShopingChangeListener = onShopingChangeListener;
    }
    public void setOnShopingSelectAllListener(OnShopingSelectAllListener onShopingSelectAllListener) {
        this.onShopingSelectAllListener = onShopingSelectAllListener;
    }

    public interface OnShopingChangeListener {
        void onCheckStateChange(boolean isAllChecked);
        void onTotalPriceChange(int totalPrice);
        void onTotalCount(int totalCount);
    }
    public interface OnShopingSelectAllListener {
        void onCheckStateChange(boolean isAllChecked);
        void onTotalPriceChange(int totalPrice);
        void onTotalCount(int totalCount);
    }

    public MyListviewAdapter(Context context, List<ListViewBean> list) {
        this.context = context;
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelect(false);
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
        Viewhoder holder = null;
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.shopping_listview_items, null);
            holder = new Viewhoder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.listview_checkbox);
            holder.imageView = (ImageView) convertView.findViewById(R.id.listview_image);
            holder.description = (TextView) convertView.findViewById(R.id.listview_description);
            holder.color = (TextView) convertView.findViewById(R.id.listview_color);
            holder.price = (TextView) convertView.findViewById(R.id.listview_price);
            holder.cutDown = (ImageView) convertView.findViewById(R.id.listview_cut_down);
            holder.count = (TextView) convertView.findViewById(R.id.listview_count);
            holder.add = (ImageView) convertView.findViewById(R.id.listview_add);
            convertView.setTag(holder);
        } else {
            holder = (Viewhoder) convertView.getTag();
        }

        holder.checkBox.setChecked(list.get(position).isSelect());
        holder.description.setText(list.get(position).getTitle());
        holder.price.setText(list.get(position).getPrice() + "");
        Glide.with(context).load(list.get(position).getImageurl()).into(holder.imageView);

        final Viewhoder finalHolder = holder;
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).isSelect()){
                    list.get(position).setSelect(false);
                    if(onShopingChangeListener!=null){
                        onShopingChangeListener.onCheckStateChange(false);
                    }
                }else {
                    list.get(position).setSelect(true);
                    if(onShopingChangeListener!=null){
                        onShopingChangeListener.onCheckStateChange(true);
                    }
                }
                notifyDataSetChanged();
                addAndcut();
                Toast.makeText(context,"复选框被点击了",Toast.LENGTH_SHORT).show();
            }
        });

        holder.cutDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counts = Integer.parseInt(finalHolder.count.getText().toString());
                if (counts >= 2) {
                    counts--;
                    finalHolder.count.setText(counts+"");
                    list.get(position).setCount(counts);
                    addAndcut();
                    Toast.makeText(context,"按钮减被点击了",Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counts = Integer.parseInt(finalHolder.count.getText().toString());
                counts++;
                finalHolder.count.setText(counts + "");
                list.get(position).setCount(counts);
                addAndcut();
                Toast.makeText(context,"按钮加被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }






    int totalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < list.size(); i++) {
            Boolean aBoolean = list.get(i).isSelect();
            if (aBoolean) {
                ListViewBean listViewBean = list.get(i);
                int price = listViewBean.getPrice();
                int count = listViewBean.getCount();
                totalPrice += price * count;
            }
        }

        return totalPrice;
    }
    int totalSumCount(){
        int totalSumCount = 0;
        for (int i = 0; i < list.size(); i++) {
            Boolean aBoolean = list.get(i).isSelect();
            if (aBoolean) {
                ListViewBean listViewBean = list.get(i);
                int count = listViewBean.getCount();
                totalSumCount+=count;
            }
        }
        return totalSumCount;
    }

    class Viewhoder {
        CheckBox checkBox;
        ImageView imageView;
        TextView description;
        TextView color;
        TextView price;
        ImageView cutDown;
        TextView count;
        ImageView add;
    }
    //全选
    public void allSelect() {


        if (isAllSelected==true){
            isAllSelected = false;
            for (int i = 0; i <list.size();i++){
                list.get(i).setSelect(false);
            }
        }else {
            isAllSelected = true;
            for (int i = 0; i <list.size();i++){
                list.get(i).setSelect(true);
            }
        }


        int i = totalPrice();
        int i1 = totalSumCount();
        if (onShopingSelectAllListener!=null){
            onShopingSelectAllListener.onTotalPriceChange(i);
            onShopingSelectAllListener.onTotalCount(i1);
            onShopingSelectAllListener.onCheckStateChange(isAllSelected);
        }
        notifyDataSetChanged();

    }

    private void addAndcut() {
        int i = totalPrice();
        int i1 = totalSumCount();
        if (onShopingChangeListener!=null){
            onShopingChangeListener.onTotalPriceChange(i);
            onShopingChangeListener.onTotalCount(i1);
        }

    }

    //移除item
    public void removeItem(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }
}
