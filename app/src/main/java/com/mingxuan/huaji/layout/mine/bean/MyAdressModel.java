package com.mingxuan.huaji.layout.mine.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class MyAdressModel {
    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"1","consignee":"刘佳文","phone":"15202364158","default_flag":"0","seleadd_name":"重庆 重庆市 沙坪坝区","address":"重庆市沙坪区渝碚路"}]
     */

    private int status;
    private String message;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 60
         * consignee : 蒲祖辉
         * phone : 15823903420
         * default_flag : 0
         * seleadd_name : 北京 市辖区 东城区 东华门街道办事处
         * address : zhongguo
         * selete_address : 12,1202,120223,120223101
         */

        private String selete_address;

        public String getSelete_address() {
            return selete_address;
        }

        public void setSelete_address(String selete_address) {
            this.selete_address = selete_address;
        }

        private int id;
        private String consignee;
        private String phone;
        private int default_flag;
        private String seleadd_name;
        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getDefault_flag() {
            return default_flag;
        }

        public void setDefault_flag(int default_flag) {
            this.default_flag = default_flag;
        }

        public String getSeleadd_name() {
            return seleadd_name;
        }

        public void setSeleadd_name(String seleadd_name) {
            this.seleadd_name = seleadd_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }

}
