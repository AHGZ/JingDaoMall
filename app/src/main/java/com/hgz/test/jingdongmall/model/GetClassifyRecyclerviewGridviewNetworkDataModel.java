package com.hgz.test.jingdongmall.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.hgz.test.jingdongmall.app.MyApplication;
import com.hgz.test.jingdongmall.model.bean.ClassifyGridViewBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GetClassifyRecyclerviewGridviewNetworkDataModel {
    private String id;
    public GetClassifyRecyclerviewGridviewNetworkDataModel(String id){
        this.id=id;
       ;
    }
    public void getNetWorkData(@NonNull final DataCallBack<ClassifyGridViewBean> dataCallBack){
        OkHttpClient okHttpClient = MyApplication.okHttpClient();
        Request request=new Request.Builder().url("http://169.254.254.18/mobile/index.php?act=goods_class&gc_id="+id).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    String json = response.body().string();
                    Gson gson = new Gson();
                    ClassifyGridViewBean classifyGridViewBean = gson.fromJson(json, ClassifyGridViewBean.class);
                    dataCallBack.onGetDataSucced(classifyGridViewBean);
                }else{
                    String responseMessage = response.message();
                    dataCallBack.onGetDataFail(responseMessage);
                }

            }
        });

    }
    //数据回调接口, 成功之后,把相应的结果类型传给presenter, 这个相应的类型就是我们json生成的bean
    public interface DataCallBack<T> {
        void onGetDataSucced(T t);

        void onGetDataFail(String exception);
    }
}

