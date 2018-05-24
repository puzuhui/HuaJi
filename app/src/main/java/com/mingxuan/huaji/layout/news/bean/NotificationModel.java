package com.mingxuan.huaji.layout.news.bean;

import java.util.List;

/**
 * Created by Admin on 2018/5/16.
 * 公司：铭轩科技
 */

public class NotificationModel {
    /**
     * status : 200
     * result : [{"id":"220170","state":"2","title":"关于取消〔2018〕005号文件政策的公告","intro":"服务商直接办理5户及以上，奖励300元政策的活动名额已经全部抢完。将于2018年05月31日nbsp;<\/span><\/p><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px; font-family: Tahoma, Geneva, sans-serif; font-size: 12px; white-space: normal;\"><br/><\/p>","create_time":"2018-05-17 17:33:17"}]
     */

    private int status;
    private List<ResultBean> result;
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
         * id : 220170
         * state : 2
         * title : 关于取消〔2018〕005号文件政策的公告
         * intro : 服务商直接办理5户及以上，奖励300元政策的活动名额已经全部抢完。将于2018年05月31日24：00时截止。
         * content : <p style="text-align:center;line-height:37px"><span style="font-family: arial, helvetica, sans-serif; font-size: 10px;">（重庆）华记黄埔 〔2018〕018号</span></p><p style="line-height:150%"><span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;"></span></p><p style="line-height:150%"><span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">尊敬的各位服务商：</span></p><p style="text-indent:43px;line-height:150%"><span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">服务商直接办理5户及以上，奖励300元政策的活动名额已经全部抢完。截止2018年05月31日24：00时,将正式取消华记黄埔 〔2018〕005号公告文件政策。请各位服务商抓住这最后的时间。</span></p><p style="text-indent:43px;line-height:150%"><span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">再此感谢各位服务商的积极参与支持，后期我们将会有更加丰厚的活动奖励敬献给大家。敬请期待！同时也希望大家更加斗志昂扬冲向梦想的征途。</span></p><p style="text-align:center"><span style="line-height: 150%; color: rgb(62, 62, 62); font-family: arial, helvetica, sans-serif; font-size: 16px; background: white;"><img src="/files/inform/productImg/20180521/1526882468208050689.jpg" title="1526882468208050689.jpg" alt="5.21-2.jpg" width="732" height="781" style="width: 732px; height: 781px;"/></span></p><p style="text-indent:43px;line-height:150%"><span style="font-size:21px;line-height:150%;font-family:宋体">&nbsp;</span></p><p style="line-height: 150%; text-align: right;"><span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">&nbsp;</span></p><p style="margin-right:37px;text-align:right;text-indent:48px;line-height:29px"><span style="font-family: arial, helvetica, sans-serif; font-size: 16px;">重庆华记黄埔信息技术有限公司</span></p><p style="margin-right:37px;text-align:right;text-indent:48px;line-height:29px"><span style="font-family: arial, helvetica, sans-serif; font-size: 16px;">2018年05月21日</span></p><p style="margin-right: 80px; text-indent: 378px; line-height: 29px; text-align: right;"><br/></p><p><br/></p>
         * create_time : 2018-05-21 14:35:50
         */

        private String id;
        private String state;
        private String title;
        private String intro;
        private String content;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
