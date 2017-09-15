package com.hgz.test.jingdongmall.view.fragment;

import android.content.Context;
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
import com.hgz.test.jingdongmall.model.bean.ClassifyRecyclerviewTextBean;
import com.hgz.test.jingdongmall.presenter.GetClassifyRecyclerviewTextNetworkDataPresenter;
import com.hgz.test.jingdongmall.view.IView.IGetClassifyRecyclerviewTextNetworkDataView;
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

public class ClassifyViewPagerFragment extends Fragment implements IGetClassifyRecyclerviewTextNetworkDataView{

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
    }

    @Override
    public void onResume() {
        super.onResume();
        String classifytabid = getArguments().getString("classifytabid");
        GetClassifyRecyclerviewTextNetworkDataPresenter getClassifyRecyclerviewTextNetworkDataPresenter = new GetClassifyRecyclerviewTextNetworkDataPresenter(this,classifytabid);
        getClassifyRecyclerviewTextNetworkDataPresenter.getClassifyRecyclerViewTextNetworkData();
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.classify_recyclerview);
    }

    private void initData() {
        final String classifytabid = getArguments().getString("classifytabid");
        OkHttpClient okHttpClient = MyApplication.okHttpClient();
        Request request = new Request.Builder()
                .url("http://169.254.254.18/mobile/index.php?act=goods_class&gc_id="+classifytabid)
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
                            MyClassifyRecyclerAdapter classifyRecyclerAdapter = new MyClassifyRecyclerAdapter(getActivity(),classtext_list);
                            recyclerView.setAdapter(classifyRecyclerAdapter);
                        }
                    });

                }
            }
        });
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onGetNetWorkDateSucced(final ClassifyRecyclerviewTextBean dataBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                List<ClassifyRecyclerviewTextBean.DatasBean.ClassListBean> classtext_list = dataBean.getDatas().getClass_list();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                MyClassifyRecyclerAdapter classifyRecyclerAdapter = new MyClassifyRecyclerAdapter(getActivity(),classtext_list);
                recyclerView.setAdapter(classifyRecyclerAdapter);
            }
        });
    }

    @Override
    public void onGetNetWorkDataFaild(String exception) {

    }
}
