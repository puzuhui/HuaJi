package com.mingxuan.huaji.layout.two.model;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class ShoppingListTopTypeModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"40","parent_id":"31","name":"冰箱"},{"id":"41","parent_id":"31","name":"洗衣机"},{"id":"42","parent_id":"31","name":"吸尘器"},{"id":"43","parent_id":"31","name":"电视机"},{"id":"44","parent_id":"31","name":"暖风机"},{"id":"45","parent_id":"31","name":" 净化器"},{"id":"46","parent_id":"31","name":"电饭锅"},{"id":"47","parent_id":"31","name":"扫地机"},{"id":"48","parent_id":"31","name":"除湿器"},{"id":"49","parent_id":"31","name":"吹风机"},{"id":"50","parent_id":"31","name":"电磁炉"},{"id":"55","parent_id":"31","name":"燃水器"}]
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
         * id : 40
         * parent_id : 31
         * name : 冰箱
         */

        private String id;
        private String parent_id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
