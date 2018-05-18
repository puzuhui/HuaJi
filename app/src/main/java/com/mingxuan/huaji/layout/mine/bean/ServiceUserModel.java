package com.mingxuan.huaji.layout.mine.bean;

import java.util.List;

/**
 * Created by Admin on 2018/3/22.
 * 公司：铭轩科技
 */

public class ServiceUserModel {
    /**
     * status : 200
     * message : 获取成功
     * result : [{"mobile":null,"info_name":null,"net_time":"2018-01-01 00:00:00","real_name":"付利芳","phone":"13212378826"}]
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
         * mobile : null
         * info_name : null
         * net_time : 2018-01-01 00:00:00
         * real_name : 付利芳
         * phone : 13212378826
         */

        private String mobile;
        private String info_name;
        private String net_time;
        private String real_name;
        private String phone;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getInfo_name() {
            return info_name;
        }

        public void setInfo_name(String info_name) {
            this.info_name = info_name;
        }

        public String getNet_time() {
            return net_time;
        }

        public void setNet_time(String net_time) {
            this.net_time = net_time;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}
