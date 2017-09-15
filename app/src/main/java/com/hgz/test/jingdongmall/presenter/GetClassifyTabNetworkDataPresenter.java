package com.hgz.test.jingdongmall.presenter;

import com.hgz.test.jingdongmall.model.GetClassifyTabNetworkDataModel;
import com.hgz.test.jingdongmall.model.bean.ClassifyTablayoutBean;
import com.hgz.test.jingdongmall.view.IView.IGetClassifyTabNetworkDataView;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GetClassifyTabNetworkDataPresenter extends BasePresenter<IGetClassifyTabNetworkDataView>{


    private GetClassifyTabNetworkDataModel getClassifyTabNetworkDataModel;

    public GetClassifyTabNetworkDataPresenter(IGetClassifyTabNetworkDataView iView) {
        super(iView);
    }

    @Override
    protected void initModel() {
        getClassifyTabNetworkDataModel = new GetClassifyTabNetworkDataModel();
    }
    public void getClassifyTabNetworkData(){
        getClassifyTabNetworkDataModel.getNetWorkData(new GetClassifyTabNetworkDataModel.DataCallBack<ClassifyTablayoutBean>() {
            @Override
            public void onGetDataSucced(ClassifyTablayoutBean classifyTablayoutBean) {
                iView.onGetNetWorkDateSucced(classifyTablayoutBean);
            }

            @Override
            public void onGetDataFail(String exception) {
                iView.onGetNetWorkDataFaild(exception);
            }
        });
    }
}
