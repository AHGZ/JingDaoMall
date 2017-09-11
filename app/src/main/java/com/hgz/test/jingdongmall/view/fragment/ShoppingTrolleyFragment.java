package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.view.adapter.MyListviewAdapter;
import com.hgz.test.jingdongmall.view.adapter.MyShoppingRecyclerviewAdapter;
import com.hgz.test.jingdongmall.view.utils.CalculatedHeightUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/6.
 */

public class ShoppingTrolleyFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ListView listView;
    private ArrayList<String> list = new ArrayList<>();
    private CheckBox allselect;
    private MyListviewAdapter myListviewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping_trolley, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        MyShoppingRecyclerviewAdapter myShoppingRecyclerviewAdapter = new MyShoppingRecyclerviewAdapter();
        recyclerView.setAdapter(myShoppingRecyclerviewAdapter);
        myShoppingRecyclerviewAdapter.setOnOnItemsClickListener(new MyShoppingRecyclerviewAdapter.OnItemsClickListener() {
            @Override
            public void setItemsOnClick(int position) {
                list.add("1");
                myListviewAdapter = new MyListviewAdapter(getContext(),list);
                listView.setAdapter(myListviewAdapter);
                CalculatedHeightUtil.setListHeight(listView);
                myListviewAdapter.notifyDataSetChanged();
            }
        });
        allselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myListviewAdapter.allSelect();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                listView.setAdapter(myListviewAdapter);
                myListviewAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"长安了",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    private void initView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.shopping_trolley_recyclerview);
        listView = (ListView) view.findViewById(R.id.listview);
        allselect = (CheckBox) view.findViewById(R.id.check_allselect);
    }
}
