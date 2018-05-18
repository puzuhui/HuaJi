package com.mingxuan.huaji.layout.mine.bean;

import java.util.List;

/**
 * Created by Admin on 2018/3/29.
 * 公司：铭轩科技
 */

public class QueryStatisticsModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"newxj":"5","newxjje":"8471.0","xjsl":"5","sljl_ljyxze":"8471.0"}]
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
         * newxj : 5
         * newxjje : 8471.0
         * xjsl : 5
         * sljl_ljyxze : 8471.0
         */

        private String newxj;
        private String newxjje;
        private String xjsl;
        private String sljl_ljyxze;

        public String getNewxj() {
            return newxj;
        }

        public void setNewxj(String newxj) {
            this.newxj = newxj;
        }

        public String getNewxjje() {
            return newxjje;
        }

        public void setNewxjje(String newxjje) {
            this.newxjje = newxjje;
        }

        public String getXjsl() {
            return xjsl;
        }

        public void setXjsl(String xjsl) {
            this.xjsl = xjsl;
        }

        public String getSljl_ljyxze() {
            return sljl_ljyxze;
        }

        public void setSljl_ljyxze(String sljl_ljyxze) {
            this.sljl_ljyxze = sljl_ljyxze;
        }
    }
}
