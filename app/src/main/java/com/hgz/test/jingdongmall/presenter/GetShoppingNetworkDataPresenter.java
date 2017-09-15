package com.hgz.test.jingdongmall.presenter;

import com.hgz.test.jingdongmall.model.GetHomeNetworkDataModel;
import com.hgz.test.jingdongmall.model.bean.TuijianBean;
import com.hgz.test.jingdongmall.view.IView.IGetHomeNetworkDataView;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GetShoppingNetworkDataPresenter extends BasePresenter<IGetHomeNetworkDataView>{

    private GetHomeNetworkDataModel getShouyeNetworkDataModel;

    public GetShoppingNetworkDataPresenter(IGetHomeNetworkDataView iView) {
        super(iView);
    }

    @Override
    protected void initModel() {
        getShouyeNetworkDataModel = new GetHomeNetworkDataModel();
    }
    public void getShouYeNetworkData(){
        getShouyeNetworkDataModel.getNetWorkData(new GetHomeNetworkDataModel.DataCallBack<TuijianBean>() {
            @Override
            public void onGetDataSucced(TuijianBean tuijianBean) {
                iView.onGetNetWorkDateSucced(tuijianBean);
            }

            @Override
            public void onGetDataFail(String exception) {
                iView.onGetNetWorkDataFaild(exception);
            }
        });
    }
}
