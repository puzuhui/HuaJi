package com.mingxuan.huaji.layout.mine.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class AreaModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"code":"11","name":"北京","level":"1"},{"code":"12","name":"天津","level":"1"},{"code":"13","name":"河北","level":"1"},{"code":"14","name":"山西","level":"1"},{"code":"15","name":"内蒙古","level":"1"},{"code":"21","name":"辽宁","level":"1"},{"code":"22","name":"吉林","level":"1"},{"code":"23","name":"黑龙江","level":"1"},{"code":"31","name":"上海","level":"1"},{"code":"32","name":"江苏","level":"1"},{"code":"33","name":"浙江","level":"1"},{"code":"34","name":"安徽","level":"1"},{"code":"35","name":"福建","level":"1"},{"code":"36","name":"江西","level":"1"},{"code":"37","name":"山东","level":"1"},{"code":"41","name":"河南","level":"1"},{"code":"42","name":"湖北","level":"1"},{"code":"43","name":"湖南","level":"1"},{"code":"44","name":"广东","level":"1"},{"code":"45","name":"广西","level":"1"},{"code":"46","name":"海南","level":"1"},{"code":"50","name":"重庆","level":"1"},{"code":"51","name":"四川","level":"1"},{"code":"52","name":"贵州","level":"1"},{"code":"53","name":"云南","level":"1"},{"code":"54","name":"西藏","level":"1"},{"code":"61","name":"陕西","level":"1"},{"code":"62","name":"甘肃","level":"1"},{"code":"63","name":"青海","level":"1"},{"code":"64","name":"宁夏","level":"1"},{"code":"65","name":"新疆","level":"1"}]
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
         * code : 11
         * name : 北京
         * level : 1
         */

        private String code;
        private String name;
        private String level;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
