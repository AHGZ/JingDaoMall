package com.hgz.test.jingdongmall.view.fragment;

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
import com.google.gson.Gson;
import com.hgz.test.jingdongmall.R;
import com.hgz.test.jingdongmall.app.MyApplication;
import com.hgz.test.jingdongmall.bean.TuijianBean;
import com.hgz.test.jingdongmall.view.adapter.MyListviewAdapter;
import com.hgz.test.jingdongmall.view.adapter.MyShoppingRecyclerviewAdapter;
import com.hgz.test.jingdongmall.view.utils.CalculatedHeightUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/6.
 */

public class ShoppingTrolleyFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ListView listView;
    private CheckBox allselect;
    List<TuijianBean.GoodsListBean> goods_lists;
    private MyListviewAdapter myListviewAdapter;
    private ImageView weizhi;
    private TextView heji;
    private Button jiesuan;
    private SwipyRefreshLayout refreshLayout;
    private Handler handler = null;
    int page = 1;
    private MyShoppingRecyclerviewAdapter myShoppingRecyclerviewAdapter;

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
        initNetWorkData();
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
                        page++;
                        initNetWorkData();
                        Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }

            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        initNetWorkData();
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });



        allselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myListviewAdapter.allSelect();
                myListviewAdapter.setGetAllSelectSumPrice(new MyListviewAdapter.GetAllSelectSumPrice() {
                    @Override
                    public void allSelectSumPrice(int sum, int count) {
                        heji.setText("合计：¥" + sum + ".00");
                        jiesuan.setText("去结算" + "(" + count + ")");
                    }
                });

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
                goods_lists.remove(position);
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

    private void initNetWorkData() {
        OkHttpClient okHttpClient = MyApplication.okHttpClient();
        Request request = new Request.Builder().url("http://apiv3.yangkeduo.com/v5/newlist?page="+page+"&size=20").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        TuijianBean tuijianBean = gson.fromJson(json, TuijianBean.class);
                        final List<TuijianBean.GoodsListBean> goods_list = tuijianBean.getGoods_list();
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        myShoppingRecyclerviewAdapter = new MyShoppingRecyclerviewAdapter(getActivity(), goods_list);
                        recyclerView.setAdapter(myShoppingRecyclerviewAdapter);
                        myShoppingRecyclerviewAdapter.setOnOnItemsClickListener(new MyShoppingRecyclerviewAdapter.OnItemsClickListener() {
                            @Override
                            public void setItemsOnClick(int position) {
                                TuijianBean.GoodsListBean goodsListBean = new TuijianBean.GoodsListBean();
                                goods_lists.add(position,goodsListBean);
                                myListviewAdapter = new MyListviewAdapter(getContext(), goods_lists);
                                listView.setAdapter(myListviewAdapter);
                                CalculatedHeightUtil.setListHeight(listView);
                                myListviewAdapter.notifyDataSetChanged();
                                myListviewAdapter.setGetSumPrice(new MyListviewAdapter.GetSumPrice() {
                                    @Override
                                    public void sumprice(int sum, int count) {
                                        heji.setText("合计：¥" + sum + ".00");
                                        jiesuan.setText("去结算" + "(" + count + ")");
                                    }

                                });

                            }
                        });
                    }
                });


            }
        });
    }
}
