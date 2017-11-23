package com.mingxuan.huaji.layout.four.model;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22 0022.
 */

public class InformationModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"name":"th","phone":"15202364157","id_card":"500233199611202160"}]
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
         * name : th
         * phone : 15202364157
         * id_card : 500233199611202160
         */

        private String name;
        private String phone;
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

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }
    }
}
