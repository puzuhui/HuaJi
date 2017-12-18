package com.mingxuan.huaji.url;

/**
 * Created by Administrator on 2017/6/2.
 */
public class BaseUrl {

    public final static String baseurl = "http://192.168.188.123:88/huaji/index.php/Home/index/";//根地址

    public final static String javaurl = "http://192.168.188.122:8080/huaji/ws/user/";//根地址

    public final static String login = javaurl+"login?";//登录

    public final static String shoppinglisttop = baseurl+"shoppinglisttop?";//商品列表顶部标签

    public final static String shoppinglist = baseurl+"shoppinglist?";//商品列表

    public final static String shoppingevaluate = baseurl+"shoppingevaluate?";//商品评价

    public final static String evaluate = baseurl+"evaluate?";//商品评价

    public final static String searchdefaultaddress = baseurl+"searchdefaultaddress?";//默认地址

    public final static String myfridends = baseurl+"myfridends?";//我的伙伴

    public final static String myproducts_cart = baseurl+"myproducts_cart?";//我的购物车

    public final static String searchshoppingcar = baseurl+"searchshoppingcar?";//添加购物车

    public final static String insertshoppingcar = baseurl+"insertshoppingcar?";//添加购物车

    public final static String addshoppingcar = baseurl+"addshoppingcar?";//添加购物车

    public final static String delproducts_cart = baseurl+"delproducts_cart?";//编辑我的购物车

    public final static String myproducts_orders = baseurl+"myproducts_orders?";//我的订单

    public final static String hjhp_products_comment = baseurl+"hjhp_products_comment?";//我的订单(查询追加评论)

    public final static String add_products_reviews = baseurl+"add_products_reviews?";//我的订单(添加评论)

    public final static String update_order_type = baseurl+"update_order_type?";//我的订单(更新订单状态)

    public final static String searchreviews = baseurl+"searchreviews?";//查询第一次评论的内容

    public final static String searchid = baseurl+"searchid?";//查询评论id

    public final static String submitImage = baseurl+"submitImage?";//评论上传图片

    public final static String myaddress = baseurl+"myaddress?";//我的地址(查询)

    public final static String addaddress = baseurl+"addaddress?";//我的地址(添加)

    public final static String xgaddress = baseurl+"xgaddress?";//我的地址(编辑)

    public final static String setaddress = baseurl+"setaddress?";//设置默认地址为0

    public final static String province = baseurl+"province?";//地区（省市县乡镇）

    public final static String myinformation = baseurl+"myinformation?";//我的信息

    public final static String bankcard = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?";//查询银行卡信息
}
