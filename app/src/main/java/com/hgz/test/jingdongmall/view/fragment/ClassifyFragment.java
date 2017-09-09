package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.view.adapter.MyClassifyViewpagerAdapter;

import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * Created by Administrator on 2017/9/6.
 */

public class ClassifyFragment extends Fragment {
    private String[] titles=new String[]{"精选","直播","订阅","视频购","问答","清单","社区","生活","数码","亲子","风尚","美食","生活","数码","亲子","风尚","美食"};
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        VerticalTabLayout tabLayout = (VerticalTabLayout) view.findViewById(R.id.classify_tab);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.classify_viewpager);
        MyClassifyViewpagerAdapter classifyViewpagerAdapter = new MyClassifyViewpagerAdapter(getActivity().getSupportFragmentManager(), titles);
        viewPager.setAdapter(classifyViewpagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
