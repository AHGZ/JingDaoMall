package com.hgz.test.jingdongmall.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.model.bean.ClassifyTablayoutBean;
import com.hgz.test.jingdongmall.presenter.GetClassifyTabNetworkDataPresenter;
import com.hgz.test.jingdongmall.view.IView.IGetClassifyTabNetworkDataView;
import com.hgz.test.jingdongmall.view.adapter.MyClassifyViewpagerAdapter;
import com.library.zxing.activity.QRCodeScanFragment;

import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * Created by Administrator on 2017/9/6.
 */

public class ClassifyFragment extends QRCodeScanFragment implements IGetClassifyTabNetworkDataView{
    private View view;
    private VerticalTabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView classifySaoYiSao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        classifySaoYiSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanQRCode();
            }
        });
        GetClassifyTabNetworkDataPresenter getClassifyTabNetworkDataPresenter = new GetClassifyTabNetworkDataPresenter(this);
        getClassifyTabNetworkDataPresenter.getClassifyTabNetworkData();
    }

    private void initView() {
        tabLayout = (VerticalTabLayout) view.findViewById(R.id.classify_tab);
        viewPager = (ViewPager) view.findViewById(R.id.classify_viewpager);
        classifySaoYiSao = (ImageView) view.findViewById(R.id.classify_saoyisao);
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onGetNetWorkDateSucced(final ClassifyTablayoutBean dataBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                List<ClassifyTablayoutBean.DatasBean.ClassListBean> classList = dataBean.getDatas().getClass_list();
                MyClassifyViewpagerAdapter classifyViewpagerAdapter = new MyClassifyViewpagerAdapter(getActivity().getSupportFragmentManager(), classList);
                viewPager.setAdapter(classifyViewpagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    @Override
    public void onGetNetWorkDataFaild(String exception) {

    }
}
