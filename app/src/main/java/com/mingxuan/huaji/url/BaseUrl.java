package com.mingxuan.huaji.url;

/**
 * Created by Administrator on 2017/6/2.
 */
public class BaseUrl {

//    public final static String baseurl = "http://118.190.204.1:8080/huaji/index.php/Home/index/";//根地址

//    public final static String javaurl = "http://www.hjhp.cn/ws/user/";//根地址

    public final static String baseurl = "http://192.168.188.122/huaji/index.php/Home/index/";//根地址

    public final static String javaurl = "http://192.168.188.122:8080/huaji/ws/user/";//根地址

    public final static String login = javaurl + "login?";//登录

    public final static String duanxin = javaurl + "duanxin?";//获取短信验证码

    public final static String register = javaurl + "register?";//注册

    public final static String updatePassword = javaurl + "updatePassword?";//找回密码

    public final static String findolduser = javaurl + "findolduser?";//查找老用户

    public final static String olduser = javaurl + "olduser?";//完善资料

    public final static String bindphone = javaurl + "bindphone?";//绑定手机

    public final static String phoneCardInfo = javaurl + "phoneCardInfo?";//手机套餐信息

    public final static String choosephoneNumb = javaurl + "choosephoneNumb?";//16个随机号码

    public final static String updateNumberState = javaurl + "updateNumberState?";//16个随机号码

    public final static String savePhoneCardOrder = javaurl + "savePhoneCardOrder?";//保存电话卡订单

    public final static String findPhoneCardOrder = javaurl + "findPhoneCardOrder?";//查找未支付电话卡订单

    public final static String wxpay = javaurl + "wechatpay?";

    public final static String alipay = javaurl + "alipay?";

    public final static String virifyPassword = javaurl + "virifyPassword?";//修改密码

    public final static String shoppinglisttop = baseurl + "shoppinglisttop?";//商品列表顶部标签

    public final static String shoppinglist = baseurl + "shoppinglist?";//商品列表

    public final static String shoppingevaluate = baseurl + "shoppingevaluate?";//商品评价

    public final static String hotproduct = baseurl + "hotproduct?";//banner

    public final static String evaluate = baseurl + "evaluate?";//商品评价

    public final static String searchdefaultaddress = baseurl + "searchdefaultaddress?";//默认地址

    public final static String myfridends = baseurl + "myfridends?";//我的伙伴

    public final static String myintegral = baseurl + "myintegral?";//我的积分

    public final static String tixian = javaurl + "point?";//我的积分

    public final static String myproducts_cart = baseurl + "myproducts_cart?";//我的购物车

    public final static String searchshoppingcar = baseurl + "searchshoppingcar?";//添加购物车

    public final static String insertshoppingcar = baseurl + "insertshoppingcar?";//添加购物车

    public final static String addshoppingcar = baseurl + "addshoppingcar?";//添加购物车

    public final static String delproducts_cart = baseurl + "delproducts_cart?";//编辑我的购物车

    public final static String myproducts_orders = baseurl + "myproducts_orders?";//我的订单

    public final static String hjhp_products_comment = baseurl + "hjhp_products_comment?";//我的订单(查询追加评论)

    public final static String add_products_reviews = baseurl + "add_products_reviews?";//我的订单(添加评论)

    public final static String update_order_type = baseurl + "update_order_type?";//我的订单(更新订单状态)

    public final static String searchreviews = baseurl + "searchreviews?";//查询第一次评论的内容

    public final static String searchid = baseurl + "searchid?";//查询评论id

    public final static String submitImage = baseurl + "submitImage?";//评论上传图片

    public final static String myaddress = baseurl + "myaddress?";//我的地址(查询)

    public final static String addaddress = baseurl + "addaddress?";//我的地址(添加)

    public final static String xgaddress = baseurl + "xgaddress?";//我的地址(编辑)

    public final static String setaddress = baseurl + "setaddress?";//设置默认地址为0

    public final static String province = baseurl + "province?";//地区（省市县乡镇）

    public final static String myinformation = baseurl + "myinformation?";//我的信息

    public final static String addbankcard = baseurl + "addbankcard?";//添加银行卡

    public final static String searchbankcard = baseurl + "searchbankcard?";//添加银行卡

    public final static String bankcard = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?";//查询银行卡信息

    public final static String findServiceNumb = baseurl + "findServiceNumb?";//查找服务号

    public final static String queryStatistics = javaurl + "queryStatistics?";//查询统计

    public final static String upDateQueryStatistics = baseurl + "upDateQueryStatistics?";//查询统计(统计更新)

    public final static String incomeStatistics = baseurl + "incomeStatistics?";//查询统计(统计更新)

    public final static String serviceChannel = baseurl + "serviceChannel?";//服务查询
}