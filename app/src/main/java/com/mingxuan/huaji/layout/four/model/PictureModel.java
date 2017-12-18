package com.mingxuan.huaji.layout.four.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class PictureModel {

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
         * contingency_id : 76
         * pic_dz : 1/2017112912451338.jpg
         * comment_content : 真的非常棒，推荐购买！
         * comment_type : 1
         * comment_level : 4
         * create_name : th
         */
        private String pic_dz;
        private String create_name;
        private String id;
        public String imagePath; //图片路径
        private String comment_content;
        private int comment_level;
        private String contingency_id;
        private String comment_type;
        private String create_time;
        private List<PictureModel.ResultBean> list;

        public List<ResultBean> getList() {
            return list;
        }

        public void setList(List<ResultBean> list) {
            this.list = list;
        }

        public ResultBean getReplyTo() {
            return replyTo;
        }

        public void setReplyTo(ResultBean replyTo) {
            this.replyTo = replyTo;
        }

        private ResultBean replyTo;

        public String getCreate_time(){
            return create_time;
        }

        public void setCreate_time(String create_time){
            this.create_time = create_time;
        }

        public String getContingency_id() {
            return contingency_id;
        }

        public void setContingency_id(String contingency_id) {
            this.contingency_id = contingency_id;
        }

        public String getComment_type() {
            return comment_type;
        }

        public void setComment_type(String comment_type) {
            this.comment_type = comment_type;
        }


        public String getPic_dz() {
            return pic_dz;
        }

        public void setPic_dz(String pic_dz) {
            this.pic_dz = pic_dz;
        }


        public String getCreate_name() {
            return create_name;
        }

        public void setCreate_name(String create_name) {
            this.create_name = create_name;
        }


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
