package com.mingxuan.huaji.layout.homepage.bean;

/**
 * Created by Admin on 2018/4/19.
 * 公司：铭轩科技
 */

public class CardOrderModel {

    /**
     * message : 有未支付的订单
     * result : {"id":464,"infoId":5,"info":"乐享家129套餐","price":"129","orderInfo":"电信号码首次开通需要预存3个月套餐费和本月剩余12天共计52.0元","cardId":1,"idCard":"513623198304274913","realName":"李强","parentCardId":"5","parentName":"史敬英","netArea":"重庆","number":"15320899282","state":0,"createId":"7","createName":"李强","createTime":1523991260000,"delFlag":0}
     * status : 200
     */

    private String message;
    private ResultBean result;
    private int status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * id : 464
         * infoId : 5
         * info : 乐享家129套餐
         * price : 129
         * orderInfo : 电信号码首次开通需要预存3个月套餐费和本月剩余12天共计52.0元
         * cardId : 1
         * idCard : 513623198304274913
         * realName : 李强
         * parentCardId : 5
         * parentName : 史敬英
         * netArea : 重庆
         * number : 15320899282
         * state : 0
         * createId : 7
         * createName : 李强
         * createTime : 1523991260000
         * delFlag : 0
         */

        private int id;
        private int infoId;
        private String info;
        private String price;
        private String orderInfo;
        private int cardId;
        private String idCard;
        private String realName;
        private String parentCardId;
        private String parentName;
        private String netArea;
        private String number;
        private int state;
        private String createId;
        private String createName;
        private long createTime;
        private int delFlag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(String orderInfo) {
            this.orderInfo = orderInfo;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getParentCardId() {
            return parentCardId;
        }

        public void setParentCardId(String parentCardId) {
            this.parentCardId = parentCardId;
        }

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public String getNetArea() {
            return netArea;
        }

        public void setNetArea(String netArea) {
            this.netArea = netArea;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateId() {
            return createId;
        }

        public void setCreateId(String createId) {
            this.createId = createId;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }
    }
}
