package com.mingxuan.huaji.layout.homepage.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public class LoginModel {

    /**
     * result : [{"searchFromPage":false,"createDate":"2017-10-09 03:07:34","updateDate":"2017-10-09 03:07:34","delFlag":"0","id":"d1e964159cd04e0d909677bd72ab89e6","name":"振宇","realName":"蒲祖辉","idCard":"500234199512172599","phone":"15823903420","pId":"56c9f9556b2e46428bb53f85bbc1b234","admin":false}]
     * message : 登录成功
     * status : 200
     */

    private String message;
    private int status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
         * searchFromPage : false
         * createDate : 2017-10-09 03:07:34
         * updateDate : 2017-10-09 03:07:34
         * delFlag : 0
         * id : d1e964159cd04e0d909677bd72ab89e6
         * name : 振宇
         * realName : 蒲祖辉
         * idCard : 500234199512172599
         * phone : 15823903420
         * pId : 56c9f9556b2e46428bb53f85bbc1b234
         * admin : false
         */

        private boolean searchFromPage;
        private String createDate;
        private String updateDate;
        private String delFlag;
        private String id;
        private String name;
        private String realName;
        private String idCard;
        private String phone;
        private String pId;
        private boolean admin;
        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public boolean isSearchFromPage() {
            return searchFromPage;
        }

        public void setSearchFromPage(boolean searchFromPage) {
            this.searchFromPage = searchFromPage;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }
    }
}
