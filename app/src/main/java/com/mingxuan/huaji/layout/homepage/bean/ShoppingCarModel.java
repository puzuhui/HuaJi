package com.mingxuan.huaji.layout.homepage.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public class ShoppingCarModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"28","products_id":"52","products_num":"1","create_id":"56c9f9556b2e46428bb53f85bbc1b234","create_name":"th","create_time":"2017-11-29 11:53:02","del_flag":"0","update_id":"56c9f9556b2e46428bb53f85bbc1b234","update_name":"th","update_time":"2017-11-29 11:53:02"}]
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
         * id : 28
         * products_id : 52
         * products_num : 1
         * create_id : 56c9f9556b2e46428bb53f85bbc1b234
         * create_name : th
         * create_time : 2017-11-29 11:53:02
         * del_flag : 0
         * update_id : 56c9f9556b2e46428bb53f85bbc1b234
         * update_name : th
         * update_time : 2017-11-29 11:53:02
         */

        private String id;
        private String products_id;
        private int products_num;
        private String create_id;
        private String create_name;
        private String create_time;
        private String del_flag;
        private String update_id;
        private String update_name;
        private String update_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProducts_id() {
            return products_id;
        }

        public void setProducts_id(String products_id) {
            this.products_id = products_id;
        }

        public int getProducts_num() {
            return products_num;
        }

        public void setProducts_num(int products_num) {
            this.products_num = products_num;
        }

        public String getCreate_id() {
            return create_id;
        }

        public void setCreate_id(String create_id) {
            this.create_id = create_id;
        }

        public String getCreate_name() {
            return create_name;
        }

        public void setCreate_name(String create_name) {
            this.create_name = create_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(String del_flag) {
            this.del_flag = del_flag;
        }

        public String getUpdate_id() {
            return update_id;
        }

        public void setUpdate_id(String update_id) {
            this.update_id = update_id;
        }

        public String getUpdate_name() {
            return update_name;
        }

        public void setUpdate_name(String update_name) {
            this.update_name = update_name;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
