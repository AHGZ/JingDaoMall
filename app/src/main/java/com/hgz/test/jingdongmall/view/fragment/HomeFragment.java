package com.hgz.test.jingdongmall.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.model.bean.TuijianBean;
import com.hgz.test.jingdongmall.presenter.GetHomeNetworkDataPresenter;
import com.hgz.test.jingdongmall.view.IView.IGetHomeNetworkDataView;
import com.hgz.test.jingdongmall.view.adapter.MyHomeRecyclerviewAdapter;
import com.hgz.test.jingdongmall.view.adapter.MyHomeViewPagerAdapter;
import com.library.zxing.activity.QRCodeScanFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

public class HomeFragment extends QRCodeScanFragment implements IGetHomeNetworkDataView {

    private View view;
    private ArrayList<Integer> imageViews;
    private ViewPager homeViewpager;
    private ArrayList<Fragment> views;
    private RadioGroup radioGroup;
    private String[] images;
    private Banner homeBanner1;
    private Banner homeBanner2;
    private MyHomeViewPagerAdapter myHomeViewPagerAdapter;
    private LinearLayout homesaoyisao;
    private RecyclerView recyclerView;
    private MyHomeRecyclerviewAdapter myHomeRecyclerviewAdapter;
    private boolean flag;
    private SwipyRefreshLayout swipyRefreshLayout;
    private Handler handler = null;
    private GetHomeNetworkDataPresenter getShouyeNetworkDataPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new Handler();
        initData();
        initView();
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
        getShouyeNetworkDataPresenter = new GetHomeNetworkDataPresenter(this);
        getShouyeNetworkDataPresenter.getShouYeNetworkData();
//        initNetWorkData();
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getShouyeNetworkDataPresenter.getShouYeNetworkData();
//                        initNetWorkData();
                        swipyRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }

            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getShouyeNetworkDataPresenter.getShouYeNetworkData();
//                        initNetWorkData();
                        swipyRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });

    }

    ScrollView scrollView;

    private void initView() {
        scrollView = (ScrollView) view.findViewById(R.id.pull_refresh_scroll);
        homeViewpager = (ViewPager) view.findViewById(R.id.homeViewpager);
        radioGroup = (RadioGroup) view.findViewById(R.id.homeViewpager_radioGroup);
        homeBanner1 = (Banner) view.findViewById(R.id.home_banner);
        homesaoyisao = (LinearLayout) view.findViewById(R.id.homesaoyisao);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        swipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.home_refresh);
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
        scrollView.smoothScrollTo(0, 0);
    }

    public void scrollScrollView() {
        if (scrollView != null) {
            scrollView.smoothScrollTo(0, 0);
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
        images = new String[]{"https://img14.360buyimg.com/da/jfs/t8380/119/300584693/62704/5d07a5d/59a4bf60N67e35778.jpg",
                "https://img1.360buyimg.com/da/jfs/t7324/354/2665000409/94483/41814fb/59b2551eNb4f04183.jpg",
                "https://img1.360buyimg.com/da/jfs/t8431/284/477854473/99917/47e31702/59a91e71N1c4a1448.jpg",
                "https://img12.360buyimg.com/babel/jfs/t7546/303/2895014485/114582/dc04f9e8/59b65103N2085ffa9.jpg",
                "https://img20.360buyimg.com/da/jfs/t9163/71/400427467/41277/8dbf5f28/59a7a48aN7a42d3fe.jpg",
                "https://img14.360buyimg.com/babel/jfs/t8638/200/1234149431/119735/6c3312d1/59b62500Nbdb53d03.jpg",
                "https://img10.360buyimg.com/babel/jfs/t8320/150/1227766208/84920/c9c11e2/59b6674eNfa4d8466.jpg",
                "https://img11.360buyimg.com/babel/jfs/t8899/346/1243045779/95475/1dac304c/59b626bbNeaf14b36.jpg"};
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onGetNetWorkDateSucced(final TuijianBean dataBean) {
       getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<TuijianBean.GoodsListBean> goods_list = dataBean.getGoods_list();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                if (myHomeRecyclerviewAdapter == null) {
                    myHomeRecyclerviewAdapter = new MyHomeRecyclerviewAdapter(getActivity(), goods_list);
                    recyclerView.setAdapter(myHomeRecyclerviewAdapter);
                } else {
                    myHomeRecyclerviewAdapter.loadMore(goods_list, flag);
                    myHomeRecyclerviewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onGetNetWorkDataFaild(String exception) {

    }
}
