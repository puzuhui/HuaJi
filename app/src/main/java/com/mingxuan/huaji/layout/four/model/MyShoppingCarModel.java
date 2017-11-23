package com.mingxuan.huaji.layout.four.model;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class MyShoppingCarModel {
    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"2","products_id":"22","products_num":"2","product_type":"31","product_name":"250斤+的胖子 现在不用9999 不要8888 现在优惠价只要998 998胖胖子带回家 你买的是吃亏 买的是上单","product_price":"5000.00","product_inventory":"1000","create_name":"后台管理员"}]
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
         * id : 2
         * products_id : 22
         * products_num : 2
         * product_intr : /files/test01/productImg/20171012/1507772067978052880.jpg,/files/test01/productImg/20171012/1507772067978050478.jpg,/files/test01/productImg/20171012/1507772068019029508.jpg
         * product_type : 31
         * product_name : 250斤+的胖子 现在不用9999 不要8888 现在优惠价只要998 998胖胖子带回家 你买的是吃亏 买的是上单
         * product_price : 5000.00
         * product_inventory : 1000
         * create_name : 后台管理员
         */

        private String product_intr;

        public String getProduct_intr() {
            return product_intr;
        }

        public void setProduct_intr(String product_intr) {
            this.product_intr = product_intr;
        }

        private int id;
        private String products_id;
        private String products_num;
        private String product_type;
        private String product_name;
        private String product_price;
        private int product_inventory;
        private String create_name;
        boolean isSelected =false;
        int number = 1;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProducts_id() {
            return products_id;
        }

        public void setProducts_id(String products_id) {
            this.products_id = products_id;
        }

        public String getProducts_num() {
            return products_num;
        }

        public void setProducts_num(String products_num) {
            this.products_num = products_num;
        }

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public int getProduct_inventory() {
            return product_inventory;
        }

        public void setProduct_inventory(int product_inventory) {
            this.product_inventory = product_inventory;
        }

        public String getCreate_name() {
            return create_name;
        }

        public void setCreate_name(String create_name) {
            this.create_name = create_name;
        }

    }
//    String shopname;
//    String shopcontent;
//    double shopmoney;
//    boolean isSelected;
//    int number = 1;
//
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//    public boolean isSelected() {
//        return isSelected;
//    }
//
//    public void setSelected(boolean selected) {
//        isSelected = selected;
//    }
//
//    public String getShopname() {
//        return shopname;
//    }
//
//    public void setShopname(String shopname) {
//        this.shopname = shopname;
//    }
//
//    public String getShopcontent() {
//        return shopcontent;
//    }
//
//    public void setShopcontent(String shopcontent) {
//        this.shopcontent = shopcontent;
//    }
//
//    public double getShopmoney() {
//        return shopmoney;
//    }
//
//    public void setShopmoney(double shopmoney) {
//        this.shopmoney = shopmoney;
//    }

}
