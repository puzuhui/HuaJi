package com.mingxuan.huaji.layout.four.model;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class MyFriendsModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"real_name":"韦林","phone":"18956154213"},{"real_name":"th","phone":"15202364157"}]
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
         * real_name : 韦林
         * phone : 18956154213
         */

        private String real_name;
        private String phone;

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
