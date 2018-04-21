package com.mingxuan.huaji.layout.two.model;

import java.util.List;

/**
 * Created by Admin on 2018/3/16.
 * 公司：铭轩科技
 */

public class CardInfor {

    /**
     * result : [{"id":397,"number":"15330522925","cardId":1,"delFlag":0,"deadline":1522764777000},{"id":386,"number":"15330518983","cardId":1,"delFlag":0,"deadline":1522763747000},{"id":381,"number":"15330517275","cardId":1,"delFlag":0,"deadline":1522772143000},{"id":380,"number":"15330517075","cardId":1,"delFlag":0},{"id":377,"number":"15330515101","cardId":1,"delFlag":0,"deadline":1522738649000},{"id":371,"number":"15330509892","cardId":1,"delFlag":0,"deadline":1522810434000},{"id":366,"number":"15330506760","cardId":1,"delFlag":0},{"id":363,"number":"15330506063","cardId":1,"delFlag":0},{"id":355,"number":"15330503101","cardId":1,"delFlag":0},{"id":353,"number":"15330502767","cardId":1,"delFlag":0},{"id":351,"number":"15330500807","cardId":1,"delFlag":0,"deadline":1522681447000},{"id":335,"number":"15320893356","cardId":1,"delFlag":0},{"id":334,"number":"15320891615","cardId":1,"delFlag":0,"deadline":1522803150000},{"id":333,"number":"15320891139","cardId":1,"delFlag":0,"deadline":1522750414000},{"id":328,"number":"15320889552","cardId":1,"delFlag":0},{"id":320,"number":"15320838776","cardId":1,"delFlag":0}]
     * status : 200
     */

    private int status;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 397
         * number : 15330522925
         * cardId : 1
         * delFlag : 0
         * deadline : 1522764777000
         */

        private int id;
        private String number;
        private int cardId;
        private int delFlag;
        private long deadline;
        private boolean ischecked;

        public boolean isIschecked() {
            return ischecked;
        }

        public void setIschecked(boolean ischecked) {
            this.ischecked = ischecked;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public long getDeadline() {
            return deadline;
        }

        public void setDeadline(long deadline) {
            this.deadline = deadline;
        }
    }
}
