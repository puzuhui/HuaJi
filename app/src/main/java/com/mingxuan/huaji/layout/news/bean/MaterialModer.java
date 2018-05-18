package com.mingxuan.huaji.layout.news.bean;

import java.util.List;

/**
 * Created by Admin on 2018/5/17.
 * 公司：铭轩科技
 */

public class MaterialModer {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"7","title":"天翼小白自助实名模式介绍","intro":"收到卡后的自助实名步骤，请详细阅读后进行实名认证。","href":"http://hjhp.cn/file/downFile/1526520228842.pptx","state":"","create_id":"","create_time":null,"del_flag":"0"},{"id":"8","title":"用户入网协议","intro":"","href":"http://hjhp.cn/file/downFile/1526520357141.jpg","state":"","create_id":"","create_time":null,"del_flag":"0"},{"id":"9","title":"服务协议","intro":"","href":"http://hjhp.cn/file/downFile/1526520430584.png","state":"","create_id":"","create_time":null,"del_flag":"0"},{"id":"10","title":"公司资质","intro":"","href":"http://hjhp.cn/file/downFile/1526520461532.pdf","state":"","create_id":"","create_time":null,"del_flag":"0"},{"id":"11","title":"华记黄埔大数据产业园","intro":"重庆午新闻直播","href":"http://hjhp.cn/file/downFile/1526520554820.flv","state":"","create_id":"","create_time":null,"del_flag":"0"},{"id":"12","title":"测试","intro":"测试","href":"http://sw.bos.baidu.com/sw-search-sp/software/d4e97ccd4bd9f/jdk-8u144-windows-i586_8.0.1440.1.exe","state":"","create_id":"","create_time":null,"del_flag":"0"}]
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
         * id : 7
         * title : 天翼小白自助实名模式介绍
         * intro : 收到卡后的自助实名步骤，请详细阅读后进行实名认证。
         * href : http://hjhp.cn/file/downFile/1526520228842.pptx
         * state :
         * create_id :
         * create_time : null
         * del_flag : 0
         */

        private String id;
        private String title;
        private String intro;
        private String href;
        private String state;
        private String create_id;
        private Object create_time;
        private String del_flag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreate_id() {
            return create_id;
        }

        public void setCreate_id(String create_id) {
            this.create_id = create_id;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
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
