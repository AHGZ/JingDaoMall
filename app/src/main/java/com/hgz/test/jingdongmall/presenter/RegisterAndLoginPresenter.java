package com.hgz.test.jingdongmall.presenter;

import android.support.annotation.NonNull;

import com.hgz.test.jingdongmall.model.LoginModel;
import com.hgz.test.jingdongmall.model.RegisterModel;
import com.hgz.test.jingdongmall.model.bean.LoginDataBean;
import com.hgz.test.jingdongmall.model.bean.RegisterDateBean;
import com.hgz.test.jingdongmall.view.IView.RegisterAndLoginView;

/**
 * Created by Administrator on 2017/9/6.
 */

public class RegisterAndLoginPresenter extends BasePresenter<RegisterAndLoginView>{

    private RegisterModel registerModel;
    private LoginModel loginModel;

    public RegisterAndLoginPresenter(@NonNull RegisterAndLoginView registerAndLoginView){
        super(registerAndLoginView);
    }

    @Override
    protected void initModel() {
        registerModel = new RegisterModel();
        loginModel = new LoginModel();
    }

    public void register(){
        registerModel.register(new RegisterModel.DataCallBack<RegisterDateBean>() {
            @Override
            public void getDataSucced(RegisterDateBean registerDateBean) {
                iView.onRegisterSucced(registerDateBean);

            }

            @Override
            public void getDataFaild(String s) {
                iView.onRegisterFaild(s);
            }
        });
    }
    public void login(){
        loginModel.login(new LoginModel.DataCallBack<LoginDataBean>() {
            @Override
            public void getDataSucced(LoginDataBean dataBean) {
                iView.onLoginSucced(dataBean);
            }

            @Override
            public void getDataFaild(String s) {
                iView.onLoginFaild(s);
            }
        });
    }
}
