package com.hgz.test.jingdongmall.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.hgz.test.jingdongmall.R;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/9/19.
 */

public class MyDialog extends Dialog {
    private Activity context;
    private String url;
    public MyDialog(@NonNull Activity context,String url) {
        super(context,R.style.dialog_theme);
        this.context = context;
        this.url=url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        //拿屏幕的宽高,只有activity有getWindowManager这个方法,所有我们要强转成activity,
        // dialog是挂载到activity上的,直接getContext就是他的activity
        WindowManager windowManager = context.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        //设置diaolog为全屏
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.width = widthPixels;
        attributes.height = heightPixels;
        window.setAttributes(attributes);

        PhotoView image = (PhotoView) findViewById(R.id.show_image);
        Glide.with(context).load(url).into(image);

    }
}
