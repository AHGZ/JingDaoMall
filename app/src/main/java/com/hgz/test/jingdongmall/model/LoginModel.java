package com.hgz.test.jingdongmall.model;

import android.support.annotation.NonNull;

import com.hgz.test.jingdongmall.model.bean.LoginDataBean;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/9/6.
 */

public class LoginModel {
    public void login(@NonNull final DataCallBack<LoginDataBean> dataCallBack){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://op.juhe.cn/onebox/movie/video?key=a5f20eb4408e7539bdcd56f9f5f167c7&q=哥斯拉");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int code = connection.getResponseCode();
                    if (code==200){
                        LoginDataBean dateBean = new LoginDataBean("成功");
                        dataCallBack.getDataSucced(dateBean);
                    }else{
                        String message = connection.getResponseMessage();
                        dataCallBack.getDataFaild(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    public interface DataCallBack<T>{
        void getDataSucced(T t);
        void getDataFaild(String s);
    }
}
