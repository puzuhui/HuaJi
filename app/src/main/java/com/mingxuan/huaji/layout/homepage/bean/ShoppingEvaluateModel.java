package com.mingxuan.huaji.layout.homepage.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public class ShoppingEvaluateModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"pic_dz":"1/2017112912371087.jpg","comment_content":"收到货了，非常的棒！","comment_level":"4","create_name":"th"},{"pic_dz":"1/2017112912451338.jpg","comment_content":"真的非常棒，推荐购买！","comment_level":"4","create_name":"th"}]
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
         * pic_dz : 1/2017112912371087.jpg
         * comment_content : 收到货了，非常的棒！
         * comment_level : 4
         * create_name : th
         */

        private String pic_dz;
        private String comment_content;
        private String comment_level;
        private String create_name;

        public String getPic_dz() {
            return pic_dz;
        }

        public void setPic_dz(String pic_dz) {
            this.pic_dz = pic_dz;
        }

        public String getComment_content() {
            return comment_content;
        }

        public void setComment_content(String comment_content) {
            this.comment_content = comment_content;
        }

        public String getComment_level() {
            return comment_level;
        }

        public void setComment_level(String comment_level) {
            this.comment_level = comment_level;
        }

        public String getCreate_name() {
            return create_name;
        }

        public void setCreate_name(String create_name) {
            this.create_name = create_name;
        }
    }
}
