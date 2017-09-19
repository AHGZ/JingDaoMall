package com.hgz.test.jingdongmall.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.hgz.test.jingdongmall.model.bean.UserInfoBean;
import com.hgz.test.jingdongmall.model.dao.MyZhuceDao;
import com.hgz.test.jingdongmall.view.activity.LoginActivity;

import java.util.ArrayList;

/**
 * 判断是否登录，如果没有登录登录跳转到登录页面
 */

public abstract class MyListener implements View.OnClickListener {

    private String password;
    private String username;

    @Override
    public void onClick(View v) {
        MyZhuceDao myZhuceDao = new MyZhuceDao(getContent());
        ArrayList<UserInfoBean> userInfoBeen = myZhuceDao.selectInfo();
        for (UserInfoBean info : userInfoBeen) {
            password = info.getPassword();
            username = info.getUsername();
        }
        if (TextUtils.isEmpty(password) && TextUtils.isEmpty(username)) {
            Intent intent = new Intent(getContent(), LoginActivity.class);
            getContent().startActivity(intent);
        } else {
            Toast.makeText(getContent(), "按钮被点击了", Toast.LENGTH_SHORT).show();
        }
    }

    public abstract Context getContent();
}
