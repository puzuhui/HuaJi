package com.mingxuan.huaji.network.api;

import android.content.Context;
import android.text.TextUtils;

import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.network.url.BaseUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
public class MainApi extends BaseApi {

    private static MainApi api;

    public static MainApi getInstance(Context context) {
        synchronized (MainApi.class) {
            if (api == null) {
                api = new MainApi(context);
            }
        }
        return api;
    }

    public MainApi(Context context) {
        super(context);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param callBack
     */
    public void login(String username,String password,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        map.put("pwd", password);
        getLoad(BaseUrl.login, map, callBack);
    }


    /**
     *获取验证码
     * @param phone
     * @param callBack
     */
    public void duanxin(String phone,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        postLoad(BaseUrl.duanxin, map, callBack);
    }

    /**
     * 注册
     * @param pid
     * @param idcard
     * @param name
     * @param mobile
     * @param password
     * @param getcode
     * @param callBack
     */
    public void register(String pid,String idcard,String name,String phone,String password,String getcode,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("pid", pid);
        map.put("idcard", idcard);
        map.put("name", name);
        map.put("phone", phone);
        map.put("password", password);
        map.put("getcode", getcode);
        postLoad(BaseUrl.register, map, callBack);
    }

    /**
     * 找回密码
     * @param id
     * @param newPassword
     * @param callBack
     */
    public void updatePassword(String mobile,String newPassword,String getcode,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("newPassword", newPassword);
        map.put("getcode", getcode);
        postLoad(BaseUrl.updatePassword, map, callBack);
    }

    /**
     * 查找老用户
     * @param oldcard
     * @param callBack
     */
    public void findolduser(String oldcard,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("oldcard", oldcard);
        getLoad(BaseUrl.findolduser, map, callBack);
    }

    /**
     * 老用户完善资料
     * @param oldcard
     * @param idcard
     * @param mobile
     * @param password
     * @param getcode
     * @param callBack
     */
    public void olduser(String oldcard,String idcard,String mobile,String password,String getcode,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("oldcard", oldcard);
        map.put("idcard", idcard);
        map.put("mobile", mobile);
        map.put("password", password);
        map.put("getcode", getcode);
        postLoad(BaseUrl.olduser, map, callBack);
    }

    /**
     * banner
     * @param type
     * @param callBack
     */
    public void hotproductApi(String type,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        getLoad(BaseUrl.hotproduct, map, callBack);
    }

    /**
     * 顶部标签
     * @param parent_id
     * @param callBack
     */
    public void shoppinglisttopApi(int parent_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("parent_id", ""+parent_id);
        getLoad(BaseUrl.shoppinglisttop, map, callBack);
    }

    /**
     * 商品列表
     * @param id
     * @param product_type
     * @param product_label
     * @param callBack
     */
    public void shoppinglistApi(String id,String product_type,String product_label,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        if(!TextUtils.isEmpty(id)){
            map.put("id", id);
        }
        if(!TextUtils.isEmpty(product_type)){
            map.put("product_type", product_type);
        }
        if(!TextUtils.isEmpty(product_label)){
            map.put("product_label", product_label);
        }
        getLoad(BaseUrl.shoppinglist, map, callBack);
    }


    /**
     * 商品评论
     * @param products_id
     * @param comment_type
     * @param callBack
     */
    public void shoppingevaluateApi(String products_id,String comment_type,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("products_id", products_id);
        map.put("comment_type", comment_type);
        getLoad(BaseUrl.shoppingevaluate, map, callBack);
    }

    /**
     * 评论
     * @param products_id
     * @param callBack
     */
    public void evaluateApi(String products_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("products_id", products_id);
        getLoad(BaseUrl.evaluate, map, callBack);
    }

    /**
     * 查询商品是否加入购物车
     * @param products_id
     * @param create_id
     * @param callBack
     */
    public void searchshoppingcarApi(String products_id,String create_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("products_id", products_id);
        map.put("create_id", create_id);
        getLoad(BaseUrl.searchshoppingcar, map, callBack);
    }


    /**
     * 添加购物车
     * @param products_id
     * @param products_num
     * @param create_id
     * @param create_name
     * @param create_time
     * @param callBack
     */
    public void addshoppingcarApi(String products_id,int products_num,String create_id,String create_name,
                                  String create_time, GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("products_id", products_id);
        map.put("products_num", ""+products_num);
        map.put("create_id", create_id);
        map.put("create_name", create_name);
        map.put("create_time", create_time);
        postLoad(BaseUrl.addshoppingcar, map, callBack);
    }


    /**
     * 修改购物车
     * @param products_num
     * @param update_id
     * @param update_name
     * @param update_time
     * @param callBack
     */
    public void insertshoppingcarApi(String id,int products_num,int del_flag,String update_id,String update_name,
                                  String update_time, GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("update_id", update_id);
        map.put("products_num", ""+products_num);
        map.put("del_flag", ""+del_flag);
        map.put("update_name", update_name);
        map.put("update_time", update_time);
        postLoad(BaseUrl.insertshoppingcar, map, callBack);
    }

    /**
     * 查询有无默认地址
     * @param create_id
     * @param default_flag
     * @param callBack
     */
    public void searchdefaultaddressApi(String create_id,String default_flag, GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        map.put("default_flag", default_flag);
        getLoad(BaseUrl.searchdefaultaddress, map, callBack);
    }

    /**
     *
     * @param body  内容
     * @param out_trade_no  订单id
     * @param total_fee 价格
     * @param spbill_create_ip ip
     * @param callBack
     */
    public void wxpayApi(String body,String out_trade_no,String total_fee,String spbill_create_ip,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("body",body);
        map.put("out_trade_no",out_trade_no);
        map.put("total_fee",total_fee);
        map.put("spbill_create_ip",spbill_create_ip);
        getLoad(BaseUrl.wxpay, map, callBack);
    }

    /**
     *
     * @param subject
     * @param out_trade_no
     * @param total_amount
     * @param callBack
     */
    public void alipayApi(String subject,String out_trade_no,String total_amount,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("subject",subject);
        map.put("out_trade_no",out_trade_no);
        map.put("total_amount",total_amount);
        getLoad(BaseUrl.alipay, map, callBack);
    }

    /**
     * 手机套餐信息
     * @param cardId
     * @param callBack
     */
    public void phoneCardInfoApi(int cardId,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("cardId", ""+cardId);
        getLoad(BaseUrl.phoneCardInfo, map, callBack);
    }

    /**
     * 查找未支付订单
     * @param createId
     * @param callBack
     */
    public void findPhoneCardOrderApi(String createId,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("createId", createId);
        getLoad(BaseUrl.findPhoneCardOrder, map, callBack);
    }

    /**
     * 16个随机号码
     * @param cardId
     * @param number
     * @param callBack
     */
    public void choosephoneNumbApi(int cardId,String number,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("cardId", ""+cardId);
        if(!TextUtils.isEmpty(number)){
            map.put("number", ""+number);
        }
        getLoad(BaseUrl.choosephoneNumb, map, callBack);
    }

    /**
     * 更新号码状态
     * @param oldNumber
     * @param i
     * @param callBack
     */
    public void updateNumberStateApi(String oldNumber, int i,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("oldNumber", oldNumber);
        map.put("i", ""+i);
        getLoad(BaseUrl.updateNumberState, map, callBack);
    }

    //保存电话卡订单
    public void savePhoneCardOrderApi(double price, int cardId,int infoId, String info,String idCard,
                                      String realName,String netArea,String state,String number,
                                      String orderInfo,String createId,String createName,String parentCardId,
                                      String createTime,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("price", ""+price);
        map.put("cardId", ""+cardId);
        map.put("infoId", ""+infoId);
        map.put("info", ""+info);
        map.put("idCard", ""+idCard);
        map.put("realName", ""+realName);
        map.put("netArea", ""+netArea);
        map.put("state", ""+state);
        map.put("orderInfo", ""+orderInfo);
        map.put("number", ""+number);
        map.put("createId", ""+createId);
        map.put("createName", ""+createName);
        map.put("parentCardId", ""+parentCardId);
        map.put("createTime", ""+createTime);
        postLoad(BaseUrl.savePhoneCardOrder, map, callBack);
    }
}