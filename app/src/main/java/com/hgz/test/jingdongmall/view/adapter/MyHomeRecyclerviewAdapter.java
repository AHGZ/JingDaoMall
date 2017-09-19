package com.hgz.test.jingdongmall.view.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.model.bean.TuijianBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MyHomeRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemsClickListener onItemsClickListener;
    private List<TuijianBean.GoodsListBean> goods_list;
    private FragmentActivity activity;
    public MyHomeRecyclerviewAdapter(FragmentActivity activity, List<TuijianBean.GoodsListBean> goods_list) {
        this.goods_list=goods_list;
        this.activity=activity;
    }

    public interface OnItemsClickListener{
        void setItemsOnClick(int position);
    }
    public void setOnOnItemsClickListener(OnItemsClickListener onItemsClickListener){
        this.onItemsClickListener=onItemsClickListener;
    }
    public void loadMore(List<TuijianBean.GoodsListBean> goods_lists,boolean flag){
        for (TuijianBean.GoodsListBean list: goods_lists) {
            if (true){
                goods_list.add(0,list);
            }else{
                goods_list.add(list);
            }
        }

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.description.setText(goods_list.get(position).getGoods_name());
        myHolder.price.setText("¥"+goods_list.get(position).getNormal_price());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemsClickListener!=null) {
                    onItemsClickListener.setItemsOnClick(position);
                }
            }
        });
        Glide.with(activity).load(goods_list.get(position).getThumb_url()).into(myHolder.image);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView description;
        private TextView price;
        private ImageView icon;
        View itemView;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            image = (ImageView) itemView.findViewById(R.id.home_recyclerview_image);
            description = (TextView) itemView.findViewById(R.id.home_recyclerview_description);
            price = (TextView) itemView.findViewById(R.id.home_recyclerview_price);
            icon = (ImageView) itemView.findViewById(R.id.home_recyclerview_icon);
        }
    }

    @Override
    public int getItemCount() {
        return goods_list.size();
    }

}
