package com.hgz.test.jingdongmall.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.hgz.test.jingdongmall.R;

/**
 * Created by Administrator on 2017/9/9.
 */

public class MyClassifyRecyclerAdapter extends RecyclerView.Adapter{
    private ViewGroup parent;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent=parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recyclerview_items, parent, false);
        MyClassifyViewholder myClassifyViewholder = new MyClassifyViewholder(view);
        return myClassifyViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyClassifyViewholder myholder= (MyClassifyViewholder) holder;
        myholder.textView.setText("精品男装");
        MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter(parent.getContext());
        myholder.gridView.setAdapter(myGridViewAdapter);
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

    @Override
    public int getItemCount() {
        return 30;
    }
}
