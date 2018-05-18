package com.mingxuan.huaji.layout.mine.bean;

import java.util.List;

/**
 * Created by Admin on 2018/4/2.
 * 公司：铭轩科技
 */

public class IncomeModel {
    /**
     * status : 200
     * message : 获取成功
     * one : [{"qdyj_yhsl":"163","qdyj_ljyx":"12185.00","renshu":"163","qdyj_qdsrzj":"275.72"}]
     * two : [{"id":"4423","sljl_ljyxze":"300364.0","sljl_xjone":"30036.40","sljl_kcqdze":"298871.00","sljl_xjtwo":"29887.10","sljl_srzj":"149.30","for_date":"201801","user_phone":"18580270018","name":"李强","xjsl":"16","newxj":"16","p_id":"5","newxjje":"300364.0"}]
     * three : [{"id":"2","gxj_wgxj":"100","gxj_gszcy":"100","gxj_gszxse":"100","gxj_gxjlsr":"100","for_date":"201801","user_phone":"18580270018"}]
     * qd : [{"qdyj_yhsl":"4","qdyj_ljyx":"228.00","qdyj_qdsrzj":"11.40"}]
     */

    private int status;
    private String message;
    private List<OneBean> one;
    private List<TwoBean> two;
    private List<ThreeBean> three;
    private List<QdBean> qd;

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

    public List<OneBean> getOne() {
        return one;
    }

    public void setOne(List<OneBean> one) {
        this.one = one;
    }

    public List<TwoBean> getTwo() {
        return two;
    }

    public void setTwo(List<TwoBean> two) {
        this.two = two;
    }

    public List<ThreeBean> getThree() {
        return three;
    }

    public void setThree(List<ThreeBean> three) {
        this.three = three;
    }

    public List<QdBean> getQd() {
        return qd;
    }

    public void setQd(List<QdBean> qd) {
        this.qd = qd;
    }

    public static class OneBean {
        /**
         * qdyj_yhsl : 163
         * qdyj_ljyx : 12185.00
         * renshu : 163
         * qdyj_qdsrzj : 275.72
         */

        private String qdyj_yhsl;
        private String qdyj_ljyx;
        private String renshu;
        private String qdyj_qdsrzj;

        public String getQdyj_yhsl() {
            return qdyj_yhsl;
        }

        public void setQdyj_yhsl(String qdyj_yhsl) {
            this.qdyj_yhsl = qdyj_yhsl;
        }

        public String getQdyj_ljyx() {
            return qdyj_ljyx;
        }

        public void setQdyj_ljyx(String qdyj_ljyx) {
            this.qdyj_ljyx = qdyj_ljyx;
        }

        public String getRenshu() {
            return renshu;
        }

        public void setRenshu(String renshu) {
            this.renshu = renshu;
        }

        public String getQdyj_qdsrzj() {
            return qdyj_qdsrzj;
        }

        public void setQdyj_qdsrzj(String qdyj_qdsrzj) {
            this.qdyj_qdsrzj = qdyj_qdsrzj;
        }
    }

    public static class TwoBean {
        /**
         * id : 4423
         * sljl_ljyxze : 300364.0
         * sljl_xjone : 30036.40
         * sljl_kcqdze : 298871.00
         * sljl_xjtwo : 29887.10
         * sljl_srzj : 149.30
         * for_date : 201801
         * user_phone : 18580270018
         * name : 李强
         * xjsl : 16
         * newxj : 16
         * p_id : 5
         * newxjje : 300364.0
         */

        private String id;
        private String sljl_ljyxze;
        private String sljl_xjone;
        private String sljl_kcqdze;
        private String sljl_xjtwo;
        private String sljl_srzj;
        private String for_date;
        private String user_phone;
        private String name;
        private String xjsl;
        private String newxj;
        private String p_id;
        private String newxjje;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSljl_ljyxze() {
            return sljl_ljyxze;
        }

        public void setSljl_ljyxze(String sljl_ljyxze) {
            this.sljl_ljyxze = sljl_ljyxze;
        }

        public String getSljl_xjone() {
            return sljl_xjone;
        }

        public void setSljl_xjone(String sljl_xjone) {
            this.sljl_xjone = sljl_xjone;
        }

        public String getSljl_kcqdze() {
            return sljl_kcqdze;
        }

        public void setSljl_kcqdze(String sljl_kcqdze) {
            this.sljl_kcqdze = sljl_kcqdze;
        }

        public String getSljl_xjtwo() {
            return sljl_xjtwo;
        }

        public void setSljl_xjtwo(String sljl_xjtwo) {
            this.sljl_xjtwo = sljl_xjtwo;
        }

        public String getSljl_srzj() {
            return sljl_srzj;
        }

        public void setSljl_srzj(String sljl_srzj) {
            this.sljl_srzj = sljl_srzj;
        }

        public String getFor_date() {
            return for_date;
        }

        public void setFor_date(String for_date) {
            this.for_date = for_date;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getXjsl() {
            return xjsl;
        }

        public void setXjsl(String xjsl) {
            this.xjsl = xjsl;
        }

        public String getNewxj() {
            return newxj;
        }

        public void setNewxj(String newxj) {
            this.newxj = newxj;
        }

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }

        public String getNewxjje() {
            return newxjje;
        }

        public void setNewxjje(String newxjje) {
            this.newxjje = newxjje;
        }
    }

    public static class ThreeBean {
        /**
         * id : 2
         * gxj_wgxj : 100
         * gxj_gszcy : 100
         * gxj_gszxse : 100
         * gxj_gxjlsr : 100
         * for_date : 201801
         * user_phone : 18580270018
         */

        private String id;
        private String gxj_wgxj;
        private String gxj_gszcy;
        private String gxj_gszxse;
        private String gxj_gxjlsr;
        private String for_date;
        private String user_phone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGxj_wgxj() {
            return gxj_wgxj;
        }

        public void setGxj_wgxj(String gxj_wgxj) {
            this.gxj_wgxj = gxj_wgxj;
        }

        public String getGxj_gszcy() {
            return gxj_gszcy;
        }

        public void setGxj_gszcy(String gxj_gszcy) {
            this.gxj_gszcy = gxj_gszcy;
        }

        public String getGxj_gszxse() {
            return gxj_gszxse;
        }

        public void setGxj_gszxse(String gxj_gszxse) {
            this.gxj_gszxse = gxj_gszxse;
        }

        public String getGxj_gxjlsr() {
            return gxj_gxjlsr;
        }

        public void setGxj_gxjlsr(String gxj_gxjlsr) {
            this.gxj_gxjlsr = gxj_gxjlsr;
        }

        public String getFor_date() {
            return for_date;
        }

        public void setFor_date(String for_date) {
            this.for_date = for_date;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }
    }

    public static class QdBean {
        /**
         * qdyj_yhsl : 4
         * qdyj_ljyx : 228.00
         * qdyj_qdsrzj : 11.40
         */

        private String qdyj_yhsl;
        private String qdyj_ljyx;
        private String qdyj_qdsrzj;

        public String getQdyj_yhsl() {
            return qdyj_yhsl;
        }

        public void setQdyj_yhsl(String qdyj_yhsl) {
            this.qdyj_yhsl = qdyj_yhsl;
        }

        public String getQdyj_ljyx() {
            return qdyj_ljyx;
        }

        public void setQdyj_ljyx(String qdyj_ljyx) {
            this.qdyj_ljyx = qdyj_ljyx;
        }

        public String getQdyj_qdsrzj() {
            return qdyj_qdsrzj;
        }

        public void setQdyj_qdsrzj(String qdyj_qdsrzj) {
            this.qdyj_qdsrzj = qdyj_qdsrzj;
        }
    }
}
