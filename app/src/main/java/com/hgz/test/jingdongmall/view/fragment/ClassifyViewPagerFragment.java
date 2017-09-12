package com.hgz.test.jingdongmall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.app.MyApplication;
import com.hgz.test.jingdongmall.bean.ClassifyRecyclerviewTextBean;
import com.hgz.test.jingdongmall.view.adapter.MyClassifyRecyclerAdapter;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ClassifyViewPagerFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify_viewpager_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.classify_recyclerview);
    }

    private void initData() {
        int gcId=0;
        String classifytabname = getArguments().getString("classifytabname");
        if (classifytabname.equals("服饰鞋帽")){
            gcId=1;
        }else if (classifytabname.equals("礼品箱包")){
            gcId=2;
        }else if (classifytabname.equals("家居家装")){
            gcId=3;
        }else if (classifytabname.equals("数码办公")){
            gcId=4;
        }else if (classifytabname.equals("家用电器")){
            gcId=5;
        }else if (classifytabname.equals("个护化妆")){
            gcId=6;
        }else if (classifytabname.equals("珠宝手表")){
            gcId=7;
        }else if (classifytabname.equals("食品饮料")){
            gcId=8;
        }else if (classifytabname.equals("运动健康")){
            gcId=9;
        }else if (classifytabname.equals("汽车用品")){
            gcId=10;
        }else if (classifytabname.equals("玩具乐器")){
            gcId=11;
        }else if (classifytabname.equals("厨具")){
            gcId=12;
        }else if (classifytabname.equals("母婴用品")){
            gcId=13;
        }else if (classifytabname.equals("虚拟充值")){
            gcId=14;
        }
        OkHttpClient okHttpClient = MyApplication.okHttpClient();
        Request request = new Request.Builder()
                .url("http://169.254.254.18/mobile/index.php?act=goods_class&gc_id="+gcId)
                .build();
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
                            ClassifyRecyclerviewTextBean classifyRecyclerviewTextBean = gson.fromJson(json, ClassifyRecyclerviewTextBean.class);
                            List<ClassifyRecyclerviewTextBean.DatasBean.ClassListBean> classtext_list = classifyRecyclerviewTextBean.getDatas().getClass_list();
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(linearLayoutManager);
                            MyClassifyRecyclerAdapter classifyRecyclerAdapter = new MyClassifyRecyclerAdapter(classtext_list);
                            recyclerView.setAdapter(classifyRecyclerAdapter);
                        }
                    });

                }
            }
        });
    }
}
