package com.mingxuan.huaji.layout.mine.bean;

import java.util.List;

/**
 * Created by Admin on 2018/6/12.
 * 公司：铭轩科技
 */

public class RechargeModel {

    /**
     * message : 查询成功
     * result : [{"id":"fbe2e1606ec742b2b046c16bbf761c86","password":"d4031cc368c2f2fbd6a10fc1bf0d6a799c550994772d9bd10bd83744","pId":"618194","realName":"曹尚晖","idCard":"432321193709047433","phone":"15173764472","mobile":"15310763779","netTime":1525102983000,"basOrcode":"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\r\nLGABC1jAAhawgAUsYAELWMACFrCAdQWsFgjS4ADrMwO5dXG+SuLq/AILWMACFrCABSxgAQtYwAIW\r\nsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgtYGVNjBbD/a2ztmShy0IWmBteZDZkh9gAQtYwAIW\r\nsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFrDawthK3lSDg7kKWds6tSAM97eIB\r\nFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwGoD62oht87Z8oDTw9Qb\r\nF+HWPk/OO7CABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAJWA1hbD88a\r\nE5c8SO0gpoGSts/WORP6EFjAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUs\r\nYAGrAay0aGn0n6XYArd9MFrqtXWe6b76ZD2wgAUsYAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jA\r\nAhawgAUsYAELWMACVjNY7Ylrh7iloafz03JBtudt+ns/2R9YwAIWsIAFLGABC1jAAhawgAUsYAEL\r\nWMACFrCABSxgAQtYwAIWsIAFLGABqwGs6YdhVwf4Kuhb/dACU8s+0+uf7DdgAQtYwAIWsIAFLGAB\r\nC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFrAaw0hqiJba+Kw2+tAssbf92iBPgAxawgAUs\r\nYAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMBqACutMD9HowVKcOxebFfzDyxg\r\nAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIB1HayEh2EJkLUUeCv/aeC2\r\nXDBbebj0u8ACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtY3wTWdMOl\r\nwbpV+Jb9r0KwdYG11+uT3wUWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFLGAB\r\nC1jAughWWoOmQdmyzxa4aQPfAk37RfjJeYAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIW\r\nsIAFLGABC1jAAhawGsBKg6mlQdOgTANlOtLq23IhtVxswAIWsIAFLGABC1jAAhawgAUsYAELWMAC\r\nFrCABSxgAQtYwAIWsIAFLGABC1htYLVH2gCkDcyTjZUMd8t5Wi7aR3sSWMACFrCABSxgAQtYwAIW\r\nsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAasArPaHgu0FTntQevV32y/O6fxv1eudfYAFLGAB\r\nC1jAAgewgAUsYAELWMACFrCABSxgAQscwAIWsIDld4EFLGABC1jAagYrrfBpDZQGbgsEWwOWNsBp\r\n8G3N41t/CyxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIB1EKy0gUlr\r\noBYItqBM65O0C6OlXk/WBVjAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUs\r\nYAELWP1gpcU0TGlwp61vmYu0/AALWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMAC\r\nFrCABSxgAesWWO0gtvxuQqNP7D/dD1v9n/y7wAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxg\r\nAQtYwAIWsIAFLGABC1gXwWofyJbB22rENLiv9kMLNAnzDixgAQtYwNKgwAIWsIAFLGABC1jAAhaw\r\ngAUsDQos/QAsYAELWMACFrCABSxgNYOVFmmNmNZAaY249b3tF0la3afPAyxgAQtYwAIWsIAFLGAB\r\nC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIB1BSwhhEgOYAkhgCWEEMASQnxt/AF80fPBlRFd\r\nowAAAABJRU5ErkJggg==","infoName":"乐享家79套餐","infoPrice":"79.0","weChat":"oWcRq0Sak28RWQ84NgEOjSALMS6k","createDate":1525095861000,"updateDate":1525102983000,"delFlag":"0"}]
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
         * id : fbe2e1606ec742b2b046c16bbf761c86
         * password : d4031cc368c2f2fbd6a10fc1bf0d6a799c550994772d9bd10bd83744
         * pId : 618194
         * realName : 曹尚晖
         * idCard : 432321193709047433
         * phone : 15173764472
         * mobile : 15310763779
         * netTime : 1525102983000
         * basOrcode : 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
         LGABC1jAAhaw2sDaKuTV/dsH+Go+Wy4A3wUsYAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhaw
         gAUsYAELWMACFrCABazf+OCth6AtA7l1npY6bgGR9rst8wUsYAELWMACFrCABSxgAQtYwAIWsIAF
         LGABC1jAAhawgAUsYAELWMACFrCAdQWsFgjS4ADrMwO5dXG+SuLq/AILWMACFrCABSxgAQtYwAIW
         sIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgtYGVNjBbD/a2ztmShy0IWmBteZDZkh9gAQtYwAIW
         sIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFrDawthK3lSDg7kKWds6tSAM97eIB
         FrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwGoD62oht87Z8oDTw9Qb
         F+HWPk/OO7CABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAJWA1hbD88a
         E5c8SO0gpoGSts/WORP6EFjAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUs
         YAGrAay0aGn0n6XYArd9MFrqtXWe6b76ZD2wgAUsYAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jA
         AhawgAUsYAELWMACVjNY7Ylrh7iloafz03JBtudt+ns/2R9YwAIWsIAFLGABC1jAAhawgAUsYAEL
         WMACFrCABSxgAQtYwAIWsIAFLGABqwGs6YdhVwf4Kuhb/dACU8s+0+uf7DdgAQtYwAIWsIAFLGAB
         C1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFrAaw0hqiJba+Kw2+tAssbf92iBPgAxawgAUs
         YAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMBqACutMD9HowVKcOxebFfzDyxg
         AQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIB1HayEh2EJkLUUeCv/aeC2
         XDBbebj0u8ACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtY3wTWdMOl
         wbpV+Jb9r0KwdYG11+uT3wUWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFLGAB
         C1jAughWWoOmQdmyzxa4aQPfAk37RfjJeYAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIW
         sIAFLGABC1jAAhawGsBKg6mlQdOgTANlOtLq23IhtVxswAIWsIAFLGABC1jAAhawgAUsYAELWMAC
         FrCABSxgAQtYwAIWsIAFLGABC1htYLVH2gCkDcyTjZUMd8t5Wi7aR3sSWMACFrCABSxgAQtYwAIW
         sIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAasArPaHgu0FTntQevV32y/O6fxv1eudfYAFLGAB
         C1jAAgewgAUsYAELWMACFrCABSxgAQscwAIWsIDld4EFLGABC1jAagYrrfBpDZQGbgsEWwOWNsBp
         8G3N41t/CyxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIB1EKy0gUlr
         oBYItqBM65O0C6OlXk/WBVjAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUs
         YAELWP1gpcU0TGlwp61vmYu0/AALWMACFrCABSxgAQtYwAIWsIAFLGABC1jAAhawgAUsYAELWMAC
         FrCABSxgAesWWO0gtvxuQqNP7D/dD1v9n/y7wAIWsIAFLGABC1jAAhawgAUsYAELWMACFrCABSxg
         AQtYwAIWsIAFLGABC1gXwWofyJbB22rENLiv9kMLNAnzDixgAQtYwNKgwAIWsIAFLGABC1jAAhaw
         gAUsDQos/QAsYAELWMACFrCABSxgNYOVFmmNmNZAaY249b3tF0la3afPAyxgAQtYwAIWsIAFLGAB
         C1jAAhawgAUsYAELWMACFrCABSxgAQtYwAIWsIB1BSwhhEgOYAkhgCWEEMASQnxt/AF80fPBlRFd
         owAAAABJRU5ErkJggg==
         * infoName : 乐享家79套餐
         * infoPrice : 79.0
         * weChat : oWcRq0Sak28RWQ84NgEOjSALMS6k
         * createDate : 1525095861000
         * updateDate : 1525102983000
         * delFlag : 0
         */

        private String id;
        private String password;
        private String pId;
        private String realName;
        private String idCard;
        private String phone;
        private String mobile;
        private long netTime;
        private String basOrcode;
        private String infoName;
        private double infoPrice;
        private String weChat;
        private long createDate;
        private long updateDate;
        private String delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public long getNetTime() {
            return netTime;
        }

        public void setNetTime(long netTime) {
            this.netTime = netTime;
        }

        public String getBasOrcode() {
            return basOrcode;
        }

        public void setBasOrcode(String basOrcode) {
            this.basOrcode = basOrcode;
        }

        public String getInfoName() {
            return infoName;
        }

        public void setInfoName(String infoName) {
            this.infoName = infoName;
        }

        public double getInfoPrice() {
            return infoPrice;
        }

        public void setInfoPrice(double infoPrice) {
            this.infoPrice = infoPrice;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
