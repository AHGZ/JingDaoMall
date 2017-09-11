package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.view.adapter.MyClassifyRecyclerAdapter;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ClassifyViewPagerFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify_viewpager_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.classify_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        MyClassifyRecyclerAdapter classifyRecyclerAdapter = new MyClassifyRecyclerAdapter();
        recyclerView.setAdapter(classifyRecyclerAdapter);
    }
}
