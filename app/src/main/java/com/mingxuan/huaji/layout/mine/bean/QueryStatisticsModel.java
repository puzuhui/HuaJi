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
     * result : [{"id":"6673095","fw_id":"7","byxzkh":"3","bytcze":"0.00","ljkh":"568482","ljtcze":"35011352.01"}]
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
         * id : 6673095
         * fw_id : 7
         * byxzkh : 3
         * bytcze : 0.00
         * ljkh : 568482
         * ljtcze : 35011352.01
         */

        private String id;
        private String fw_id;
        private String byxzkh;
        private String bytcze;
        private String ljkh;
        private String ljtcze;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFw_id() {
            return fw_id;
        }

        public void setFw_id(String fw_id) {
            this.fw_id = fw_id;
        }

        public String getByxzkh() {
            return byxzkh;
        }

        public void setByxzkh(String byxzkh) {
            this.byxzkh = byxzkh;
        }

        public String getBytcze() {
            return bytcze;
        }

        public void setBytcze(String bytcze) {
            this.bytcze = bytcze;
        }

        public String getLjkh() {
            return ljkh;
        }

        public void setLjkh(String ljkh) {
            this.ljkh = ljkh;
        }

        public String getLjtcze() {
            return ljtcze;
        }

        public void setLjtcze(String ljtcze) {
            this.ljtcze = ljtcze;
        }
    }
}
