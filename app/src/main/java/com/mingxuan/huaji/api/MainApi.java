package com.mingxuan.huaji.api;

import android.content.Context;
import android.text.TextUtils;

import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.url.BaseUrl;

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

    public void wxpayApi( GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        getLoad(BaseUrl.wxpay, map, callBack);
    }
}