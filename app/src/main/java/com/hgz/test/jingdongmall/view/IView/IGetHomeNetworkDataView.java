package com.hgz.test.jingdongmall.view.IView;

import com.hgz.test.jingdongmall.model.bean.TuijianBean;

/**
 * Created by Administrator on 2017/9/14.
 */

public interface IGetHomeNetworkDataView extends IView{
    void onGetNetWorkDateSucced(TuijianBean dataBean);

    void onGetNetWorkDataFaild(String exception);
}
