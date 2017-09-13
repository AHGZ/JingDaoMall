package com.hgz.test.jingdongmall.app;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/9/6.
 */

public class MyApplication extends Application{
    private static Context appcontext;
    private static OkHttpClient okHttpClient;

    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appcontext=this;
        UMShareAPI.get(this);
        okHttpClient = new OkHttpClient();
        CrashReport.initCrashReport(getApplicationContext(), "a5728f5d1c", true);//建议在测试阶段建议设置成true，发布时设置为false
    }
    public static Context context(){
        return  appcontext;
    }
    public static OkHttpClient okHttpClient(){
        return okHttpClient;
    }
}
