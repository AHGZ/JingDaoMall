package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    private ImageView weizhi;
    private TextView heji;
    private Button jiesuan;

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
                myListviewAdapter = new MyListviewAdapter(getContext(), list);
                listView.setAdapter(myListviewAdapter);
                CalculatedHeightUtil.setListHeight(listView);
                myListviewAdapter.notifyDataSetChanged();
                myListviewAdapter.setGetSumPrice(new MyListviewAdapter.GetSumPrice() {
                    @Override
                    public void sumprice(int sum, int count) {
                        heji.setText("合计：¥" + sum + ".00");
                        jiesuan.setText("去结算"+"("+count+")");
                    }

                });

            }
        });

        allselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myListviewAdapter.allSelect();
                myListviewAdapter.setGetAllSelectSumPrice(new MyListviewAdapter.GetAllSelectSumPrice() {
                    @Override
                    public void allSelectSumPrice(int sum, int count) {
                        heji.setText("合计：¥" + sum + ".00");
                        jiesuan.setText("去结算"+"("+count+")");
                    }
                });

            }
        });
        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sumPrice = heji.getText().toString();
                String counts = jiesuan.getText().toString();
                showJieSuanDialog("共计："+counts+"个"+","+"总共："+sumPrice+"元");
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                listView.setAdapter(myListviewAdapter);
                myListviewAdapter.notifyDataSetChanged();
                CalculatedHeightUtil.setListHeight(listView);
                return true;
            }
        });


    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.shopping_trolley_recyclerview);
        listView = (ListView) view.findViewById(R.id.listview);
        allselect = (CheckBox) view.findViewById(R.id.check_allselect);
        weizhi = (ImageView) view.findViewById(R.id.iv_weizhi);
        heji = (TextView) view.findViewById(R.id.tv_heji);
        jiesuan = (Button) view.findViewById(R.id.btn_jiesuan);
    }
    private void showJieSuanDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("结算结果");
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }

}
