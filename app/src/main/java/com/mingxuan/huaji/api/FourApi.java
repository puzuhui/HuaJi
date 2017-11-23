package com.mingxuan.huaji.api;

import android.content.Context;
import android.text.TextUtils;

import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.url.BaseUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class FourApi extends BaseApi {
    private static FourApi api;

    public FourApi(Context context) {
        super(context);
    }

    public static FourApi getInstance(Context context) {
        synchronized (MainApi.class) {
            if (api == null) {
                api = new FourApi(context);
            }
        }
        return api;
    }

    /**
     * 我的伙伴
     * @param p_id
     * @param callBack
     */
    public void getfriendApi(String p_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("p_id", p_id);
        getLoad(BaseUrl.myfridends, map, callBack);
    }

    /**
     * 我的伙伴
     * @param create_id
     * @param callBack
     */
    public void getproducts_ordersApi(String create_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        getLoad(BaseUrl.myproducts_orders, map, callBack);
    }

    /**
     * 我的地址（查询）
     */
    public void getmyaddressApi(String create_id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        getLoad(BaseUrl.myaddress, map, callBack);
    }

    /**
     * 地区
     */
    public void getprovinceApi(int level,String code,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("level", "" +level);
        if(!TextUtils.isEmpty(code)){
            map.put("code", code);
        }
        getLoad(BaseUrl.province, map, callBack);
    }

    /**
     * 我的地址（添加）
     */
    public void getaddaddressApi(String consignee,String phone,int default_flag,String selete_address,
                                 String seleadd_name, String address,String create_id,String create_name,
                                 String create_time, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("consignee", consignee);
        map.put("phone", phone);
        map.put("default_flag", ""+default_flag);
        map.put("selete_address", selete_address);
        map.put("seleadd_name", seleadd_name);
        map.put("address", address);
        map.put("create_id", create_id);
        map.put("create_name", create_name);
        map.put("create_time", create_time);
        postLoad(BaseUrl.addaddress, map, callBack);
    }

    /**
     * 我的地址（编辑）
     */
    public void getxgaddressApi(int id,String consignee,String phone,int default_flag,String selete_address,
                                 String seleadd_name, String address,String update_id,String update_name,
                                 String update_time,int del_flag, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", ""+id);
        if(!TextUtils.isEmpty(consignee)){
            map.put("consignee", consignee);
        }
        if(!TextUtils.isEmpty(phone)){
            map.put("phone", phone);
        }
        if(!TextUtils.isEmpty(""+default_flag)){
            map.put("default_flag", ""+default_flag);
        }
        if(!TextUtils.isEmpty(selete_address)){
            map.put("selete_address", selete_address);
        }
        if(!TextUtils.isEmpty(seleadd_name)){
            map.put("seleadd_name", seleadd_name);
        }
        if(!TextUtils.isEmpty(address)){
            map.put("address", address);
        }
        if(!TextUtils.isEmpty(selete_address)){
            map.put("selete_address", selete_address);
        }
        if(!TextUtils.isEmpty(""+del_flag)){
            map.put("del_flag", ""+del_flag);
        }
        map.put("update_id", update_id);
        map.put("update_name", update_name);
        map.put("update_time", update_time);
        postLoad(BaseUrl.xgaddress, map, callBack);
    }

    /**
     * 设置默认地址为0
     */
    public void setaddressApi(String create_id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        postLoad(BaseUrl.setaddress, map, callBack);
    }

    /**
     * 购物车
     */
    public void getproductscartApi(String create_id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        getLoad(BaseUrl.myproducts_cart, map, callBack);
    }

    /**
     * 删除购物车
     */
    public void delproducts_cartApi(int id,String update_id,String update_name, String update_time,
                                    int del_fiag,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", ""+id);
        map.put("update_id", update_id);
        map.put("update_name", update_name);
        map.put("update_time", update_time);
        map.put("del_flag", ""+del_fiag);
        postLoad(BaseUrl.delproducts_cart, map, callBack);
    }


    /**
     * 我的信息
     */
    public void myInformationApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        postLoad(BaseUrl.myinformation, map, callBack);
    }

    /**
     * 添加评论
     */
    public void addProductsReviewsApi(String contingency_id,String products_id, int orders_id,String comment_userid,
                                      String comment_content,String comment_level, String comment_type,
                                      String create_id,String create_name, String create_time,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("contingency_id", contingency_id);
        map.put("products_id", products_id);
        map.put("orders_id", ""+orders_id);
        map.put("comment_userid", comment_userid);
        if(!TextUtils.isEmpty(comment_content)){
            map.put("comment_content", comment_content);
        }
        map.put("comment_level", comment_level);
        map.put("comment_type", comment_type);
        map.put("create_id", create_id);
        map.put("create_name", create_name);
        map.put("create_time", create_time);
        postLoad(BaseUrl.add_products_reviews, map, callBack);
    }
}
