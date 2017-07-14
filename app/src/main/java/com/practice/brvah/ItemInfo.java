package com.practice.brvah;

/**
 * Created by zhuyakun on 2017/7/14.
 */

public class ItemInfo {

    private int imageRes;
    private String text;

    public ItemInfo(int imageRes, String text) {
        this.imageRes = imageRes;
        this.text = text;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
