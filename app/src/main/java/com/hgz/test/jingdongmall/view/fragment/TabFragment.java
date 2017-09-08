package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.view.adapter.MyRecyclerViewAdapter;

/**
 * Created by Administrator on 2017/9/7.
 */

public class TabFragment extends Fragment{

    private View view;
    private RecyclerView recyclerView;
    private SwipyRefreshLayout recyclerRefresh;
    private Handler handler=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(myRecyclerViewAdapter);
        handler=new Handler();

        //设置颜色
        recyclerRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, android.R.color.holo_red_dark);
        //设置是否支持刷新和加载更多
        recyclerRefresh.setDirection(SwipyRefreshLayoutDirection.BOTH);
        recyclerRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerRefresh.setRefreshing(false);
                        Toast.makeText(getActivity(),"加载成功", Toast.LENGTH_SHORT).show();
                    }
                },2000);
            }

            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerRefresh.setRefreshing(false);
                        Toast.makeText(getActivity(),"加载成功", Toast.LENGTH_SHORT).show();
                    }
                },2000);
            }
        });
    }
    private void initView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerRefresh = (SwipyRefreshLayout) view.findViewById(R.id.recyclerRefresh);
    }
}
