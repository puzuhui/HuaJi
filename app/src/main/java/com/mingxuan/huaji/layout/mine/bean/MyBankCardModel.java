package com.mingxuan.huaji.layout.mine.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class MyBankCardModel {

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
     * result : [{"id":"73","login_id":"d1e964159cd04e0d909677bd72ab89e6","bank_for_name":"蒲祖辉","bank_number":"6236683760009754232","bank_type":"0","bank":"中国建设银行","phone":"15823903420","create_id":"d1e964159cd04e0d909677bd72ab89e6","create_name":"振宇","create_time":"2018-01-02 17:05:37","del_flag":"0"},{"id":"74","login_id":"d1e964159cd04e0d909677bd72ab89e6","bank_for_name":"苏琳","bank_number":"6236683760009754232","bank_type":"0","bank":"中国建设银行","phone":"15823903420","create_id":"d1e964159cd04e0d909677bd72ab89e6","create_name":"振宇","create_time":"2018-01-02 17:05:37","del_flag":"0"},{"id":"75","login_id":"d1e964159cd04e0d909677bd72ab89e6","bank_for_name":"蒲祖辉","bank_number":"6236683760009754232","bank_type":"1","bank":"中国建设银行","phone":"15823903420","create_id":"d1e964159cd04e0d909677bd72ab89e6","create_name":"振宇","create_time":"2018-01-02 17:33:28","del_flag":"0"}]
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


    public static class ResultBean {
        /**
         * id : 73
         * login_id : d1e964159cd04e0d909677bd72ab89e6
         * bank_for_name : 蒲祖辉
         * bank_number : 6236683760009754232
         * bank_type : 0
         * bank : 中国建设银行
         * phone : 15823903420
         * create_id : d1e964159cd04e0d909677bd72ab89e6
         * create_name : 振宇
         * create_time : 2018-01-02 17:05:37
         * del_flag : 0
         */

        private String id;
        private String login_id;
        private String bank_for_name;
        private String bank_number;
        private String bank_type;
        private String bank;
        private String phone;
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

        public String getBank() {
            return bank;
        }

        public void setBank(String bankX) {
            this.bank = bankX;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
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
