package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.view.adapter.MyHomeRecyclerviewAdapter;
import com.hgz.test.jingdongmall.view.adapter.MyHomeViewPagerAdapter;
import com.library.zxing.activity.QRCodeScanFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/6.
 */

public class HomeFragment extends QRCodeScanFragment {

    private View view;
    private ArrayList<Integer> imageViews;
    private ViewPager homeViewpager;
    private ArrayList<Fragment> views;
    private RadioGroup radioGroup;
    Integer[] images = new Integer[]{R.drawable.a9t,R.drawable.a9w,R.drawable.adk};
    private Banner homeBanner1;
    private Banner homeBanner2;
    private MyHomeViewPagerAdapter myHomeViewPagerAdapter;
    private LinearLayout homesaoyisao;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

        myHomeViewPagerAdapter = new MyHomeViewPagerAdapter(getActivity().getSupportFragmentManager(), views);
        homeViewpager.setAdapter(myHomeViewPagerAdapter);
        homeViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        homesaoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanQRCode();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        MyHomeRecyclerviewAdapter myHomeRecyclerviewAdapter = new MyHomeRecyclerviewAdapter();
        recyclerView.setAdapter(myHomeRecyclerviewAdapter);
    }
    ScrollView  scrollView;
    private void initView() {
          scrollView = (ScrollView) view.findViewById(R.id.pull_refresh_scroll);
        homeViewpager = (ViewPager) view.findViewById(R.id.homeViewpager);
        radioGroup = (RadioGroup) view.findViewById(R.id.homeViewpager_radioGroup);
        homeBanner1 = (Banner) view.findViewById(R.id.home_banner);
        homesaoyisao = (LinearLayout) view.findViewById(R.id.homesaoyisao);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        //设置homeBanner1样式
        homeBanner1.setBannerStyle(Banner.CIRCLE_INDICATOR);
        homeBanner1.setIndicatorGravity(Banner.CENTER);
        homeBanner1.setDelayTime(5000);//设置轮播间隔时间
        homeBanner1.setImages(images);//可以选择设置图片网址，或者资源文件，默认用Glide加载
        RadioButton radioButton2 = (RadioButton) radioGroup.getChildAt(0);
        radioButton2.setChecked(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        scrollView.smoothScrollTo(0,0);
    }

    public void scrollScrollView(){
        if(scrollView!=null){
            scrollView.smoothScrollTo(0,0);
        }
    }



    private void initData() {
        imageViews = new ArrayList<>();
        imageViews.add(R.drawable.afv);
        imageViews.add(R.drawable.as5);
        imageViews.add(R.drawable.guidepage);
        views = new ArrayList<>();
        HomeViewpager1Fragment homeViewpager1Fragment = new HomeViewpager1Fragment();
        HomeViewpager2Fragment homeViewpager2Fragment = new HomeViewpager2Fragment();
        views.add(homeViewpager1Fragment);
        views.add(homeViewpager2Fragment);
    }
}
