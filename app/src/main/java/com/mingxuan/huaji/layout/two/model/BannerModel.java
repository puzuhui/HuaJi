package com.mingxuan.huaji.layout.two.model;

import java.util.List;

/**
 * Created by Admin on 2018/3/9.
 * 公司：铭轩科技
 */

public class BannerModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"17","name":"1","pic":"/files/bannerImg/productImg/20180119/1516344980990094713.png","type":"3","sort":"0","url":"http://hjhp.cn/fstore/product/productDetail?id=85","create_id":"1"},{"id":"18","name":"2","pic":"/files/bannerImg/productImg/20180123/1516696722881041075.jpg","type":"3","sort":"1","url":"http://hjhp.cn/fstore/phoneCard/toTelecomBannerInfo","create_id":"1"},{"id":"19","name":"3","pic":"/files/bannerImg/productImg/20180119/1516350618256001954.jpg","type":"3","sort":"3","url":"http://hjhp.cn/fstore/product/productDetail?id=81","create_id":"1"},{"id":"20","name":"4","pic":"/files/bannerImg/productImg/20180119/1516343563568083269.jpg","type":"3","sort":"4","url":"http://hjhp.cn/fstore/product/productDetail?id=84","create_id":"1"}]
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
         * id : 17
         * name : 1
         * pic : /files/bannerImg/productImg/20180119/1516344980990094713.png
         * type : 3
         * sort : 0
         * url : http://hjhp.cn/fstore/product/productDetail?id=85
         * create_id : 1
         */

        private String id;
        private String name;
        private String pic;
        private String type;
        private String sort;
        private String url;
        private String create_id;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreate_id() {
            return create_id;
        }

        public void setCreate_id(String create_id) {
            this.create_id = create_id;
        }
    }
}
