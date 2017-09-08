package com.hgz.test.jingdongmall.presenter;

import android.content.Context;

import com.hgz.test.jingdongmall.app.MyApplication;
import com.hgz.test.jingdongmall.view.IView.IView;

/**
 * Created by Administrator on 2017/9/6.
 */

public abstract class BasePresenter<T extends IView>{
    protected T iView;
    public BasePresenter(T iView){
        this.iView=iView;
        initModel();
    }
    protected abstract void initModel();
    Context context(){
        if (iView!=null&&iView.context()!=null){
            return  iView.context();
        }
        return MyApplication.context();
    }
}
