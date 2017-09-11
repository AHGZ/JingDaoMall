package com.hgz.test.jingdongmall.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.model.bean.LoginDataBean;
import com.hgz.test.jingdongmall.model.bean.RegisterDateBean;
import com.hgz.test.jingdongmall.presenter.RegisterAndLoginPresenter;
import com.hgz.test.jingdongmall.view.IView.RegisterAndLoginView;
import com.hgz.test.jingdongmall.view.fragment.ClassifyFragment;
import com.hgz.test.jingdongmall.view.fragment.FindFragment;
import com.hgz.test.jingdongmall.view.fragment.HomeFragment;
import com.hgz.test.jingdongmall.view.fragment.MineFragment;
import com.hgz.test.jingdongmall.view.fragment.ShoppingTrolleyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RegisterAndLoginView {

    private RegisterAndLoginPresenter registerAndLoginPresenter;
    private RadioGroup radioGroup;
    private FrameLayout frameLayout;
    private Fragment[] fragments;
    private int mIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenters();

    }

    private void initView() {
        initFragment();
        //替换Fragment的方法,设置默认第一fragment
//        changeFragment(new HomeFragment());
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        setFragmentHideOrShow(i);
                    }
                }
            }
        });


    }

    private void initPresenters() {
        registerAndLoginPresenter = new RegisterAndLoginPresenter(this);
    }

    private void initFragment() {
        HomeFragment homeFragment = new HomeFragment();
        ClassifyFragment classifyFragment = new ClassifyFragment();
        FindFragment findFragment = new FindFragment();
        ShoppingTrolleyFragment shoppingTrolleyFragment = new ShoppingTrolleyFragment();
        MineFragment mineFragment = new MineFragment();
        //添加到数组
        fragments = new Fragment[]{homeFragment, classifyFragment, findFragment, shoppingTrolleyFragment, mineFragment};
        //开启事务
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //添加首页
        transaction.add(R.id.frameLayout, homeFragment).commit();
        //默认设置第一个fragment显示
        setFragmentHideOrShow(0);
    }

    //显示隐藏fragment的方法
    private void setFragmentHideOrShow(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        //隐藏
        ft.hide(fragments[mIndex]);
        //判断是否添加
        if (!fragments[index].isAdded()) {
            ft.add(R.id.frameLayout, fragments[index]).show(fragments[index]);
        } else {
            ft.show(fragments[index]);
        }
        if (index == 0) {
            ((HomeFragment) fragments[index]).scrollScrollView();
        }

        ft.commit();
        //再次赋值
        mIndex = index;
    }

    //替换Fragment的方法
//    private void setFragmentHideOrShow(int index) {
//        switch (index) {
//            case 0:
//                changeFragment(new HomeFragment());
//                break;
//            case 1:
//                changeFragment(new ClassifyFragment());
//                break;
//            case 2:
//                changeFragment(new FindFragment());
//                break;
//            case 3:
//                changeFragment(new ShoppingTrolleyFragment());
//                break;
//            case 4:
//                changeFragment(new MineFragment());
//                break;
//            default:
//                break;
//        }
//    }
//    private void changeFragment(Fragment fm) {
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
//        transaction.replace(R.id.frameLayout, fm);
//        transaction.commit();
//    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRegisterSucced(RegisterDateBean dateBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRegisterFaild(String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLoginSucced(LoginDataBean dataBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLoginFaild(String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public Context context() {
        return this;
    }
}
