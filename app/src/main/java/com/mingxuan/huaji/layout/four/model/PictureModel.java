package com.mingxuan.huaji.layout.four.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class PictureModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"54","comment_content":"收到货了，非常的棒！","comment_level":"4"}]
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
         * id : 54
         * comment_content : 收到货了，非常的棒！
         * comment_level : 4
         */

        private String id;
        public String imagePath; //图片路径
        private String comment_content;
        private int comment_level;

        public String getComment_content() {
            return comment_content;
        }

        public void setComment_content(String comment_content) {
            this.comment_content = comment_content;
        }

        public int getComment_level() {
            return comment_level;
        }

        public void setComment_level(int comment_level) {
            this.comment_level = comment_level;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }


    }
}
