package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.view.adapter.MyTabViewpagerAdapter;

/**
 * Created by Administrator on 2017/9/6.
 */

public class FindFragment extends Fragment {

    private View view;
    private String[] titles=new String[]{"精选","直播","订阅","视频购","问答","清单","社区","生活","数码","亲子","风尚","美食"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.findViewpager);
        MyTabViewpagerAdapter myTabViewpagerAdapter = new MyTabViewpagerAdapter(getActivity().getSupportFragmentManager(), titles);
//        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(myTabViewpagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
