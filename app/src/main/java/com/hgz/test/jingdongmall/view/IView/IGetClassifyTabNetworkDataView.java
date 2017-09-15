package com.hgz.test.jingdongmall.view.IView;

import com.hgz.test.jingdongmall.model.bean.ClassifyTablayoutBean;

/**
 * Created by Administrator on 2017/9/14.
 */

public interface IGetClassifyTabNetworkDataView extends IView{
    void onGetNetWorkDateSucced(ClassifyTablayoutBean dataBean);

    void onGetNetWorkDataFaild(String exception);
}
