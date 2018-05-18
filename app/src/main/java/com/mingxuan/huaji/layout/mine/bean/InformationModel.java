package com.mingxuan.huaji.layout.mine.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22 0022.
 */

public class InformationModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"name":"后台管理员","phone":"17318291312","real_name":"沈元","id_card":"500382199609271944"}]
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
         * name : 后台管理员
         * phone : 17318291312
         * real_name : 沈元
         * id_card : 500382199609271944
         */

        private String name;
        private String phone;
        private String real_name;
        private String id_card;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }
    }
}
