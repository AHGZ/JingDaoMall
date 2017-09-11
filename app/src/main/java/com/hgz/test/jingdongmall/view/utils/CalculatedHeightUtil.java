package com.hgz.test.jingdongmall.view.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 动态设置listview的高度
 */

public class CalculatedHeightUtil {
    public static void setListHeight(ListView listview) {
        // 重新计算下Listview的高度
        ListAdapter adapter = listview.getAdapter();
        if (adapter == null) {
            return;
        }
        // 统计listview的总高度
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            // 拿到一条item视图
            View view = adapter.getView(i, null, listview);
            // 重新计算一个一条item的宽高
            view.measure(0, 0);
            // 把这条view的高度加到总高度上
            totalHeight += view.getMeasuredHeight();
        }
        // item的总高度加上间隔线的总高度
        totalHeight += listview.getDividerHeight() * (adapter.getCount() - 1);

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight;
        listview.setLayoutParams(params);
    }
}
