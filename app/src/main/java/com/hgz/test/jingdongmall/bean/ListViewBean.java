package com.hgz.test.jingdongmall.bean;

/**
 * Created by Administrator on 2017/9/14.
 */

public class ListViewBean {
    private String imageurl;
    private int price;
    private String title;

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

    public ListViewBean(String imageurl, int price, String title) {
        this.imageurl = imageurl;
        this.price = price;
        this.title = title;
    }
}
