package com.hgz.test.jingdongmall.app;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Administrator on 2017/9/6.
 */

public class MyApplication extends Application{
    private static Context appcontext;
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appcontext=this;
        UMShareAPI.get(this);
    }
    public static Context context(){
        return  appcontext;
    }
}
