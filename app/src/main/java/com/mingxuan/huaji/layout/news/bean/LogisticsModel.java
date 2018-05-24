package com.mingxuan.huaji.layout.news.bean;

import java.util.List;

/**
 * Created by Admin on 2018/5/24.
 * 公司：铭轩科技
 */

public class LogisticsModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"logistics_no":"457852600580","create_time":"2018-03-29 12:11:23","info":"乐享家79套餐","number":"15320823002"}]
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
         * logistics_no : 457852600580
         * create_time : 2018-03-29 12:11:23
         * info : 乐享家79套餐
         * number : 15320823002
         */

        private String logistics_no;
        private String create_time;
        private String info;
        private String number;

        public String getLogistics_no() {
            return logistics_no;
        }

        public void setLogistics_no(String logistics_no) {
            this.logistics_no = logistics_no;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
