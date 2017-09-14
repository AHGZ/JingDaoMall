package com.hgz.test.jingdongmall.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.bean.ListViewBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MyListviewAdapter extends BaseAdapter {
    private Context context;
    private List<ListViewBean> list;
    private HashMap<Integer, Boolean> hashMap;
    int pricess=0;
    int countss=0;
    int oneprices=0;
    private GetSumPrice getSumPrice;
    private GetAllSelectSumPrice getAllSelectSumPrice;
    private TextView price;
    private TextView count;

    public interface GetSumPrice{
        void sumprice(int sum,int count);
    }
    public void setGetSumPrice(GetSumPrice getSumPrice){
        this.getSumPrice=getSumPrice;
    }
    public interface GetAllSelectSumPrice{
        void allSelectSumPrice(int sum,int count);
    }
    public void setGetAllSelectSumPrice(GetAllSelectSumPrice getAllSelectSumPrice){
        this.getAllSelectSumPrice=getAllSelectSumPrice;
    }
    public MyListviewAdapter(Context context, List<ListViewBean> list) {
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
        price = (TextView) convertView.findViewById(R.id.listview_price);
        ImageView cutDown = (ImageView) convertView.findViewById(R.id.listview_cut_down);
        count = (TextView) convertView.findViewById(R.id.listview_count);
        ImageView add = (ImageView) convertView.findViewById(R.id.listview_add);
        description.setText(list.get(position).getTitle());
        price.setText(list.get(position).getPrice()+"");
        Glide.with(context).load(list.get(position).getImageurl()).into(imageView);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hashMap.put(position, !hashMap.get(position));
                notifyDataSetChanged();
                int oneprice = Integer.parseInt(price.getText().toString());
                int counts = Integer.parseInt(count.getText().toString());
                int prices=0;
                int countes=0;
                if (hashMap.get(position)==true){
                   prices=pricess+oneprice*counts;
                    countes=countss+counts;
                    pricess=prices;
                    countss=countes;
                    if (getSumPrice!=null){
                        getSumPrice.sumprice(prices,countes);
                    }
                }else{
                    if (getSumPrice!=null){
                        getSumPrice.sumprice(pricess-oneprice*counts,countss-counts);
                        countss=countss-counts;
                        pricess=pricess-oneprice*counts;
                    }
                }

            }
        });
        checkBox.setChecked(hashMap.get(position));
        cutDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counts = Integer.parseInt(count.getText().toString());
                int oneprice = Integer.parseInt(price.getText().toString());
                int prices=0;
                if (counts>1){
                    count.setText((counts-1)+"");
//                    if (hashMap.get(position)==true){
//                        prices+=oneprice*counts;
//                        if (getSumPrice!=null){
//                            getSumPrice.sumprice(prices,counts);
//                        }
//                    }
//
//                }else{
//                    count.setText(1+"");
//                    if (getSumPrice!=null){
//                        getSumPrice.sumprice(prices-oneprice*counts,counts-counts);
//                    }
                }

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counts = Integer.parseInt(count.getText().toString());
                count.setText((counts+1)+"");
//                int oneprice = Integer.parseInt(price.getText().toString());
//                int prices=0;
//                if (hashMap.get(position)==true){
//                    prices=pricess+oneprice*counts-oneprice*counts;
//                    pricess=prices;
//                    if (getSumPrice!=null){
//                        getSumPrice.sumprice(prices,counts);
//                    }
//                }else{
//                    if (getSumPrice!=null){
//                        getSumPrice.sumprice(prices-oneprice*counts,counts-counts);
//                    }
//                }
            }
        });

        return convertView;
    }



    //全选
    public void allSelect(){
        Set<Map.Entry<Integer, Boolean>> entries = hashMap.entrySet();
        boolean shouldSelectedAll = false;
        int num=0;
        for (Map.Entry<Integer, Boolean> entry: entries) {
            Boolean value = entry.getValue();
            if (!value){
                shouldSelectedAll=true;

                break;
            }

        }
        for (Map.Entry<Integer, Boolean> entry: entries) {
            entry.setValue(shouldSelectedAll);
            if (entry.getValue()==true){
                int oneprice = Integer.parseInt(price.getText().toString());
                int counts = Integer.parseInt(count.getText().toString());
                num++;
                if (getAllSelectSumPrice!=null){
                    getAllSelectSumPrice.allSelectSumPrice(oneprice*num,num);
                }
            }else{
                getAllSelectSumPrice.allSelectSumPrice(0,0);
            }
        }
        notifyDataSetChanged();
    }
    //移除item
    public void removeItem(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
}
