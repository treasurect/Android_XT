package com.treasure_ct.android_xt.cartfragment;

/**
 * Created by treasure on 2016.10.22.
 */

public class CartModel {
    private long produceId;
    private String productName;
    private String productIcon;
    private float productPrice;
    private int count;
    //TODO:CheckBox的选择
    //为了解决选中时 滑下 不被其他item复用  划上 可以继续显示原有状态
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public long getProduceId() {
        return produceId;
    }

    public void setProduceId(long produceId) {
        this.produceId = produceId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
