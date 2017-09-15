package com.hgz.test.jingdongmall.view.IView;

import com.hgz.test.jingdongmall.model.bean.ClassifyGridViewBean;

/**
 * Created by Administrator on 2017/9/14.
 */

public interface IGetClassifyRecyclerviewGridviewNetworkDataView extends IView{
    void onGetNetWorkDateSucced(ClassifyGridViewBean dataBean);

    void onGetNetWorkDataFaild(String exception);
}
