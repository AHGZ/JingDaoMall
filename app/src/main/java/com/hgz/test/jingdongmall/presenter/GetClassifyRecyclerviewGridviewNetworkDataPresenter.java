package com.hgz.test.jingdongmall.presenter;

import com.hgz.test.jingdongmall.model.GetClassifyRecyclerviewGridviewNetworkDataModel;
import com.hgz.test.jingdongmall.model.bean.ClassifyGridViewBean;
import com.hgz.test.jingdongmall.view.IView.IGetClassifyRecyclerviewGridviewNetworkDataView;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GetClassifyRecyclerviewGridviewNetworkDataPresenter extends BasePresenter<IGetClassifyRecyclerviewGridviewNetworkDataView>{

    private String id;
    private final GetClassifyRecyclerviewGridviewNetworkDataModel getClassifyRecyclerviewGridviewNetworkDataModel;

    public GetClassifyRecyclerviewGridviewNetworkDataPresenter(IGetClassifyRecyclerviewGridviewNetworkDataView iView, String id) {
        super(iView);
        this.id=id;
        System.out.println("==============="+id);
        getClassifyRecyclerviewGridviewNetworkDataModel = new GetClassifyRecyclerviewGridviewNetworkDataModel(id);
    }

    @Override
    protected void initModel() {

    }
    public void getClassifyRecyclerViewGridViewNetworkData(){
        getClassifyRecyclerviewGridviewNetworkDataModel.getNetWorkData(new GetClassifyRecyclerviewGridviewNetworkDataModel.DataCallBack<ClassifyGridViewBean>() {
            @Override
            public void onGetDataSucced(ClassifyGridViewBean classifyGridViewBean) {
                iView.onGetNetWorkDateSucced(classifyGridViewBean);
            }

            @Override
            public void onGetDataFail(String exception) {
                iView.onGetNetWorkDataFaild(exception);
            }
        });
    }
}
