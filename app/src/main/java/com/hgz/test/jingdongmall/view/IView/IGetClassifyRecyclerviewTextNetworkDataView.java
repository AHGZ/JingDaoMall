package com.hgz.test.jingdongmall.view.IView;

import com.hgz.test.jingdongmall.model.bean.ClassifyGridViewBean;
import com.hgz.test.jingdongmall.model.bean.ClassifyRecyclerviewTextBean;

/**
 * Created by Administrator on 2017/9/14.
 */

public interface IGetClassifyRecyclerviewTextNetworkDataView extends IView{
    void onGetNetWorkDateSucced(ClassifyRecyclerviewTextBean dataBean);

    void onGetNetWorkDataFaild(String exception);
}
