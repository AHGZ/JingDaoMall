package com.hgz.test.jingdongmall.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hgz.test.jingdongmall.bean.ClassifyTablayoutBean;
import com.hgz.test.jingdongmall.view.fragment.ClassifyViewPagerFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class MyClassifyViewpagerAdapter extends FragmentPagerAdapter {
    private  List<ClassifyTablayoutBean.DatasBean.ClassListBean> titles;
    public MyClassifyViewpagerAdapter(FragmentManager fm, List<ClassifyTablayoutBean.DatasBean.ClassListBean> titles) {
        super(fm);
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        ClassifyViewPagerFragment classifyViewPagerFragment = new ClassifyViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("classifytabname",titles.get(position).getGc_name());
        classifyViewPagerFragment.setArguments(bundle);
        return classifyViewPagerFragment;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getGc_name();
    }
}
