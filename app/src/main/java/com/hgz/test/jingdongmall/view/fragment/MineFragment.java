package com.hgz.test.jingdongmall.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.view.activity.LoginActivity;

/**
 * Created by Administrator on 2017/9/6.
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RelativeLayout loginOrRegister;
    private ImageView ivLoginOrRegister;
    private TextView tvLoginOrRegister;
    private String name;
    private String iconurl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        loginOrRegister.setOnClickListener(this);

    }
    private void initView(){
        loginOrRegister = (RelativeLayout) view.findViewById(R.id.rel_LoginOrRegister);
        ivLoginOrRegister = (ImageView) view.findViewById(R.id.Iv_LoginOrRegister);
        tvLoginOrRegister = (TextView) view.findViewById(R.id.tv_LoginOrRegister);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.rel_LoginOrRegister:
               Intent intent = new Intent(getActivity(), LoginActivity.class);
               startActivity(intent);
               break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("qqdata", Context.MODE_PRIVATE);
        name = sp.getString("name", "登陆/注册");
        iconurl = sp.getString("iconurl", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504860474260&di=7c436753cab55939b34545ba59c1874c&imgtype=0&src=http%3A%2F%2F58pic.ooopic.com%2F58pic%2F12%2F81%2F62%2F16C58PICFGE.jpg");
        tvLoginOrRegister.setText(name);
        //设置圆形图片
        Glide.with(this).load(iconurl).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivLoginOrRegister) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivLoginOrRegister.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}