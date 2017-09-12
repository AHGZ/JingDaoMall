package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.app.MyApplication;
import com.hgz.test.jingdongmall.bean.ClassifyTablayoutBean;
import com.hgz.test.jingdongmall.view.adapter.MyClassifyViewpagerAdapter;
import com.library.zxing.activity.QRCodeScanFragment;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * Created by Administrator on 2017/9/6.
 */

public class ClassifyFragment extends QRCodeScanFragment {
    private String[] titles = new String[]{"精选", "直播", "订阅", "视频购", "问答", "清单", "社区", "生活", "数码", "亲子", "风尚", "美食", "生活", "数码", "亲子", "风尚", "美食"};
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
        initData();
        classifySaoYiSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanQRCode();
            }
        });

    }

    private void initView() {
        tabLayout = (VerticalTabLayout) view.findViewById(R.id.classify_tab);
        viewPager = (ViewPager) view.findViewById(R.id.classify_viewpager);
        classifySaoYiSao = (ImageView) view.findViewById(R.id.classify_saoyisao);
    }

    private void initData() {
        OkHttpClient okHttpClient = MyApplication.okHttpClient();
//        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://169.254.254.18/mobile/index.php?act=goods_class").build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            private String json;

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    json = response.body().string();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            ClassifyTablayoutBean classifyTablayoutBean = gson.fromJson(json, ClassifyTablayoutBean.class);
                            List<ClassifyTablayoutBean.DatasBean.ClassListBean> classList = classifyTablayoutBean.getDatas().getClass_list();
                            MyClassifyViewpagerAdapter classifyViewpagerAdapter = new MyClassifyViewpagerAdapter(getActivity().getSupportFragmentManager(), classList);
                            viewPager.setAdapter(classifyViewpagerAdapter);
                            tabLayout.setupWithViewPager(viewPager);
                        }
                    });

                }

            }
        });
    }

}
