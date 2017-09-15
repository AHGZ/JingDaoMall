package com.hgz.test.jingdongmall.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.hgz.test.jingdongmall.app.MyApplication;
import com.hgz.test.jingdongmall.model.bean.TuijianBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GetShoppingNetworkDataModel {
    int page=1;
    public void getNetWorkData(@NonNull final DataCallBack<TuijianBean> dataCallBack){
        OkHttpClient okHttpClient = MyApplication.okHttpClient();
        Request request=new Request.Builder().url("http://apiv3.yangkeduo.com/v5/newlist?page="+page+"&size=20").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    String json = response.body().string();
                    Gson gson = new Gson();
                    TuijianBean tuijianBean = gson.fromJson(json, TuijianBean.class);
                    dataCallBack.onGetDataSucced(tuijianBean);
                    page++;
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

