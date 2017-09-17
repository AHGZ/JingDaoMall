package com.hgz.test.jingdongmall.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.model.bean.ListViewBean;
import com.hgz.test.jingdongmall.model.bean.TuijianBean;
import com.hgz.test.jingdongmall.presenter.GetShoppingNetworkDataPresenter;
import com.hgz.test.jingdongmall.utils.CalculatedHeightUtil;
import com.hgz.test.jingdongmall.view.IView.IGetHomeNetworkDataView;
import com.hgz.test.jingdongmall.view.adapter.MyListviewAdapter;
import com.hgz.test.jingdongmall.view.adapter.MyShoppingRecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

public class ShoppingTrolleyFragment extends Fragment implements IGetHomeNetworkDataView{

    private View view;
    private RecyclerView recyclerView;
    private ListView listView;
    private CheckBox allselect;
    private List<ListViewBean> lists= new ArrayList<>();
    private MyListviewAdapter myListviewAdapter;
    private ImageView weizhi;
    private TextView heji;
    private Button jiesuan;
    private SwipyRefreshLayout refreshLayout;
    private Handler handler = null;
    private MyShoppingRecyclerviewAdapter myShoppingRecyclerviewAdapter;
    private GetShoppingNetworkDataPresenter getShoppingNetworkDataPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping_trolley, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getShoppingNetworkDataPresenter = new GetShoppingNetworkDataPresenter(this);
        getShoppingNetworkDataPresenter.getShouYeNetworkData();
        handler = new Handler();
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, android.R.color.holo_red_dark);
        refreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        refreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        getShoppingNetworkDataPresenter.getShouYeNetworkData();
                        Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }

            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        getShoppingNetworkDataPresenter.getShouYeNetworkData();
                        Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });

        allselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (myListviewAdapter==null){
                    return;
                }
                myListviewAdapter.allSelect();
            }
        });


        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sumPrice = heji.getText().toString();
                String counts = jiesuan.getText().toString();
                showJieSuanDialog("共计：" + counts + "个" + "," + "总共：" + sumPrice + "元");
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lists.remove(position);
                listView.setAdapter(myListviewAdapter);
                myListviewAdapter.notifyDataSetChanged();
                CalculatedHeightUtil.setListHeight(listView);
                return true;
            }
        });


    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.shopping_trolley_recyclerview);
        listView = (ListView) view.findViewById(R.id.listview);
        allselect = (CheckBox) view.findViewById(R.id.check_allselect);
        weizhi = (ImageView) view.findViewById(R.id.iv_weizhi);
        heji = (TextView) view.findViewById(R.id.tv_heji);
        jiesuan = (Button) view.findViewById(R.id.btn_jiesuan);
        refreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.shopping_refresh);
    }

    private void showJieSuanDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("结算结果");
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onGetNetWorkDateSucced(final TuijianBean dataBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<TuijianBean.GoodsListBean> goods_list = dataBean.getGoods_list();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                myShoppingRecyclerviewAdapter = new MyShoppingRecyclerviewAdapter(getActivity(), goods_list);
                recyclerView.setAdapter(myShoppingRecyclerviewAdapter);
                myShoppingRecyclerviewAdapter.setOnOnItemsClickListener(new MyShoppingRecyclerviewAdapter.OnItemsClickListener() {
                    @Override
                    public void setItemsOnClick(int position) {
                        ListViewBean listViewBean = new ListViewBean(goods_list.get(position).getThumb_url(), goods_list.get(position).getNormal_price(), goods_list.get(position).getGoods_name(),1);
                        lists.add(listViewBean);
                        myListviewAdapter = new MyListviewAdapter(getContext(), lists);
                        listView.setAdapter(myListviewAdapter);
                        CalculatedHeightUtil.setListHeight(listView);
                        myListviewAdapter.notifyDataSetChanged();
                        myListviewAdapter.setOnShopingSelectAllListener(new MyListviewAdapter.OnShopingSelectAllListener() {
                            @Override
                            public void onCheckStateChange(boolean isAllChecked) {

                            }

                            @Override
                            public void onTotalPriceChange(int totalPrice) {
                                heji.setText("合计：¥" + totalPrice + ".00");
                            }

                            @Override
                            public void onTotalCount(int totalCount) {
                                jiesuan.setText("去结算" + "(" + totalCount + ")");
                            }
                        });
                        myListviewAdapter.setOnShopingChangeListener(new MyListviewAdapter.OnShopingChangeListener() {
                            @Override
                            public void onCheckStateChange(boolean isAllChecked) {

                            }

                            @Override
                            public void onTotalPriceChange(int totalPrice) {
                                heji.setText("合计：¥" + totalPrice + ".00");

                            }

                            @Override
                            public void onTotalCount(int totalCount) {
                                jiesuan.setText("去结算" + "(" + totalCount + ")");

                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void onGetNetWorkDataFaild(String exception) {

    }

}
