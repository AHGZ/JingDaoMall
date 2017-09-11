package com.hgz.test.jingdongmall.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgz.test.jingdongmall.R;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MyShoppingRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemsClickListener onItemsClickListener;
    public interface OnItemsClickListener{
        void setItemsOnClick(int position);
    }
    public void setOnOnItemsClickListener(OnItemsClickListener onItemsClickListener){
        this.onItemsClickListener=onItemsClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_recycler_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.description.setText("何国忠何国忠何国忠何国忠何国忠何国忠何国忠");
        myHolder.price.setText("¥50.00");
        myHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemsClickListener.setItemsOnClick(position);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView description;
        private TextView price;
        private ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.shopping_recyclerview_image);
            description = (TextView) itemView.findViewById(R.id.shopping_recyclerview_description);
            price = (TextView) itemView.findViewById(R.id.shopping_recyclerview_price);
            icon = (ImageView) itemView.findViewById(R.id.shopping_recyclerview_icon);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

}
