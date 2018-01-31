package com.mingxuan.huaji.layout.two.model;


/**
 * Created by Admin on 2018/1/15.
 *
 */

public class PayModel {

    /**
     * result : {"appid":"wxa08821893494a1a5","nonce_str":"h7bQ0bUaAKPsouwi","package":"Sign=WXPay","partnerid":"1496565902","prepayid":"20180118125346","sign":"AA7F37E36755EBAF87B1826EEC811E25","timestamp":"http://hjhp.cn/pay/"}
     * status : 200
     */

    private ResultBean result;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class ResultBean {

        private ResultData result;

        public ResultData getResult() {
            return result;
        }

        /**
         * appid : wxa08821893494a1a5
         * nonce_str : h7bQ0bUaAKPsouwi
         * package : Sign=WXPay
         * partnerid : 1496565902
         * prepayid : 20180118125346
         * sign : AA7F37E36755EBAF87B1826EEC811E25
         * timestamp : http://hjhp.cn/pay/
         */
        public class ResultData{
            private String appid;
            private String noncestr;
            private String packageX;
            private String partnerid;
            private String prepayid;
            private String sign;
            private String timestamp;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String nonce_str) {
                this.noncestr = nonce_str;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
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

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }

    }
}
