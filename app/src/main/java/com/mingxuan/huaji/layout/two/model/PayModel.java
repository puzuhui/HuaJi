package com.mingxuan.huaji.layout.two.model;

/**
 * Created by Admin on 2018/1/15.
 *
 */

public class PayModel {

    /**
     * appid : wxb4ba3c02aa476ea1
     * partnerid : 1900006771
     * package : Sign=WXPay
     * noncestr : ec09db4c40290ead0f146c51633c1005
     * timestamp : 1515994298
     * prepayid : wx20180115133138dabadc88460653553382
     * sign : FB1F23ED2546E08704155C67A83F8ABB
     */

    private String appid;
    private String partnerid;
    private String packageX;
    private String noncestr;
    private String timestamp;
    private String prepayid;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
