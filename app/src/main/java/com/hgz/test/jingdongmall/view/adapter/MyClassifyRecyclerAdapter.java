package com.hgz.test.jingdongmall.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.bean.ClassifyRecyclerviewTextBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */

public class MyClassifyRecyclerAdapter extends RecyclerView.Adapter{
    private ViewGroup parent;
    private List<ClassifyRecyclerviewTextBean.DatasBean.ClassListBean> classtext_list;
    public MyClassifyRecyclerAdapter(List<ClassifyRecyclerviewTextBean.DatasBean.ClassListBean> classtext_list) {
        this.classtext_list=classtext_list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent=parent;
        View view=null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recyclerview_items2, parent, false);
                holder=new MyClassifyViewholder2(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recyclerview_items, parent, false);
                holder=new MyClassifyViewholder(view);

                break;
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else {
            return 1;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 0:
                MyClassifyViewholder2 myHolder2= (MyClassifyViewholder2) holder;
                myHolder2.imageView.setImageResource(R.drawable.jsbundles_jdreactintlbrand_images_brand_enter_afterenter_bg);
                break;
            case 1:
                MyClassifyViewholder myHolder= (MyClassifyViewholder) holder;
                myHolder.textView.setText(classtext_list.get(position).getGc_name());
                MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter(parent.getContext());
                myHolder.gridView.setAdapter(myGridViewAdapter);
                break;
        }

    }
    public class MyClassifyViewholder extends RecyclerView.ViewHolder {
        private TextView textView;
        private GridView gridView;

        public MyClassifyViewholder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.classify_recyclerview_title);
            gridView = (GridView) itemView.findViewById(R.id.classify_recyclerview_gridview);
        }

    }
    public class MyClassifyViewholder2 extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public MyClassifyViewholder2(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.classify_recyclerview_image);
        }

    }

    @Override
    public int getItemCount() {
        return classtext_list.size();
    }
}
