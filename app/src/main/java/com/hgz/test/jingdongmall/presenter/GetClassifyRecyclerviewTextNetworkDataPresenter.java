package com.hgz.test.jingdongmall.presenter;

import com.hgz.test.jingdongmall.model.GetClassifyRecyclerviewTextNetworkDataModel;
import com.hgz.test.jingdongmall.model.bean.ClassifyRecyclerviewTextBean;
import com.hgz.test.jingdongmall.view.IView.IGetClassifyRecyclerviewTextNetworkDataView;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GetClassifyRecyclerviewTextNetworkDataPresenter extends BasePresenter<IGetClassifyRecyclerviewTextNetworkDataView>{

    private String id;
    private GetClassifyRecyclerviewTextNetworkDataModel getClassifyRecyclerviewTextNetworkDataModel;

    public GetClassifyRecyclerviewTextNetworkDataPresenter(IGetClassifyRecyclerviewTextNetworkDataView iView,String id) {
        super(iView);
        this.id=id;
        getClassifyRecyclerviewTextNetworkDataModel = new GetClassifyRecyclerviewTextNetworkDataModel(id);
    }

    @Override
    protected void initModel() {

    }
    public void getClassifyRecyclerViewTextNetworkData(){
        getClassifyRecyclerviewTextNetworkDataModel.getNetWorkData(new GetClassifyRecyclerviewTextNetworkDataModel.DataCallBack<ClassifyRecyclerviewTextBean>() {
            @Override
            public void onGetDataSucced(ClassifyRecyclerviewTextBean classifyRecyclerviewTextBean) {
                iView.onGetNetWorkDateSucced(classifyRecyclerviewTextBean);
            }

            @Override
            public void onGetDataFail(String exception) {
                iView.onGetNetWorkDataFaild(exception);
            }
        });
    }
}
