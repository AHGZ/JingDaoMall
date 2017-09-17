package com.hgz.test.jingdongmall.model.bean;

/**
 * Created by Administrator on 2017/9/14.
 */

public class ListViewBean {
    private String imageurl;
    private int price;
    private String title;
    private int count;
    private  boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ListViewBean(String imageurl, int price, String title,int count) {
        this.imageurl = imageurl;
        this.price = price;
        this.title = title;
        this.count = count;
    }
}
