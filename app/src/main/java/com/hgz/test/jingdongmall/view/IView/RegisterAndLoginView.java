package com.hgz.test.jingdongmall.view.IView;

import com.hgz.test.jingdongmall.model.bean.LoginDataBean;
import com.hgz.test.jingdongmall.model.bean.RegisterDateBean;

/**
 * Created by Administrator on 2017/9/6.
 */

public interface RegisterAndLoginView extends IView{
    void onRegisterSucced(RegisterDateBean dateBean);

    void onRegisterFaild(String s);

    void onLoginSucced(LoginDataBean dataBean);

    void onLoginFaild(String s);
}
