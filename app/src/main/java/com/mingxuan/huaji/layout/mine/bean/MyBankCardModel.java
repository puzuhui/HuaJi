package com.mingxuan.huaji.layout.mine.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class MyBankCardModel{

    /**
     * bank : CCB
     * validated : true
     * cardType : DC
     * key : 6236683760009754232
     * messages : []
     * stat : ok
     */

    private String bank;
    private boolean validated;
    private String cardType;
    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"102","login_id":"7","bank_for_codetype":"ICBC","bank_for_name":"李强","bank_number":"6222023100023706109","bank_type":"1","addresss":"","bankzh":"重庆市高科技歇台子支行","bank":"中国工商银行","phone":null,"create_id":"7","create_name":"李强","create_time":"2018-01-31 16:11:23","del_flag":"0"}]
     */

    private int status;
    private String message;
    private List<ResultBean> result;


    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

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


    public static class ResultBean implements Serializable{
        /**
         * id : 102
         * login_id : 7
         * bank_for_codetype : ICBC
         * bank_for_name : 李强
         * bank_number : 6222023100023706109
         * bank_type : 1
         * addresss :
         * bankzh : 重庆市高科技歇台子支行
         * bank : 中国工商银行
         * phone : null
         * create_id : 7
         * create_name : 李强
         * create_time : 2018-01-31 16:11:23
         * del_flag : 0
         */

        private String id;
        private String login_id;
        private String bank_for_codetype;
        private String bank_for_name;
        private String bank_number;
        private String bank_type;
        private String addresss;
        private String bankzh;
        @SerializedName("bank")
        private String bankX;
        private Object phone;
        private String create_id;
        private String create_name;
        private String create_time;
        private String del_flag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogin_id() {
            return login_id;
        }

        public void setLogin_id(String login_id) {
            this.login_id = login_id;
        }

        public String getBank_for_codetype() {
            return bank_for_codetype;
        }

        public void setBank_for_codetype(String bank_for_codetype) {
            this.bank_for_codetype = bank_for_codetype;
        }

        public String getBank_for_name() {
            return bank_for_name;
        }

        public void setBank_for_name(String bank_for_name) {
            this.bank_for_name = bank_for_name;
        }

        public String getBank_number() {
            return bank_number;
        }

        public void setBank_number(String bank_number) {
            this.bank_number = bank_number;
        }

        public String getBank_type() {
            return bank_type;
        }

        public void setBank_type(String bank_type) {
            this.bank_type = bank_type;
        }

        public String getAddresss() {
            return addresss;
        }

        public void setAddresss(String addresss) {
            this.addresss = addresss;
        }

        public String getBankzh() {
            return bankzh;
        }

        public void setBankzh(String bankzh) {
            this.bankzh = bankzh;
        }

        public String getBankX() {
            return bankX;
        }

        public void setBankX(String bankX) {
            this.bankX = bankX;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
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
    }
}
