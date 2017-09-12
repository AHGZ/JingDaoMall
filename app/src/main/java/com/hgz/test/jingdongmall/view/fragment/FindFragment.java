package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.view.adapter.MyTabViewpagerAdapter;
import com.library.zxing.activity.QRCodeScanFragment;

/**
 * Created by Administrator on 2017/9/6.
 */

public class FindFragment extends QRCodeScanFragment {

    private View view;
    private String[] titles=new String[]{"精选","直播","订阅","视频购","问答","清单","社区","生活","数码","亲子","风尚","美食"};
    private ImageView findSaoYiSao;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        MyTabViewpagerAdapter myTabViewpagerAdapter = new MyTabViewpagerAdapter(getActivity().getSupportFragmentManager(), titles);
//        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(myTabViewpagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        findSaoYiSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanQRCode();
            }
        });
    }
    private void initView(){
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.findViewpager);
        findSaoYiSao = (ImageView) view.findViewById(R.id.find_saoyisao);
    }
}
