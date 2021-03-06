package com.mingxuan.huaji.network.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.network.url.BaseUrl;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.network.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.io.File;
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
     * 二级密码登录
     * @param username
     * @param password
     * @param callBack
     */
    public void twologinApi(String username,String password,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        map.put("pwd", password);
        getLoad(BaseUrl.twologin, map, callBack);
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
     * 我的积分
     * @param login_id
     * @param callBack
     */
    public void myintegralApi(String login_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("login_id", login_id);
        getLoad(BaseUrl.myintegral, map, callBack);
    }

    /**
     * 提现
     * @param txBanknumber
     * @param txPrint
     * @param createId
     * @param createName
     * @param callBack
     */
    public void tixian(String txBanknumber,String txPrint,String txPhone,
                       String createId,String createName,String idcard,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("txBanknumber", txBanknumber);
        map.put("txPrint", txPrint);
        map.put("createId", createId);
        map.put("createName", createName);
        map.put("idcard", idcard);
        map.put("txPhone", txPhone);
        postLoad(BaseUrl.tixian, map, callBack);
    }

    /**
     * 我的订单
     * @param create_id
     * @param callBack
     */
    public void getproducts_ordersApi(String create_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        getLoad(BaseUrl.myproducts_orders, map, callBack);
    }

    /**
     * 查询订单（追加评论）
     * @param id
     * @param callBack
     */
    public void gethjhp_products_commentApi(String id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        getLoad(BaseUrl.hjhp_products_comment, map, callBack);
    }

    /**
     * 添加评论
     */
    public void addProductsReviewsApi(String contingency_id,String products_id, int orders_id,String pic_dz,String comment_userid,
                                      String comment_content,String comment_level, String comment_type,
                                      String create_id,String create_name, String create_time,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("contingency_id", contingency_id);
        map.put("products_id", products_id);
        map.put("orders_id", ""+orders_id);
        map.put("comment_userid", comment_userid);
        if(!TextUtils.isEmpty(pic_dz)){
            map.put("pic_dz", pic_dz);
        }
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

    /**
     * 查询第一次评论的内容
     * @param callBack
     */
    public void searchreviewsApi(String create_id,String products_id,String contingency_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        map.put("products_id", products_id);
        map.put("contingency_id", contingency_id);
        getLoad(BaseUrl.searchreviews, map, callBack);
    }

    /**
     * 查询评论id
     * @param callBack
     */
    public void searchidApi(String create_id,String products_id,String contingency_id,String comment_type,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        map.put("products_id", products_id);
        map.put("contingency_id", contingency_id);
        map.put("comment_type", comment_type);
        getLoad(BaseUrl.searchid, map, callBack);
    }

    /**
     * 更新订单
     */
    public void updateOrderTypeApi(String id,String orders_flag,String update_id,String update_name,
                                   String update_time,String del_flag,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        if(!TextUtils.isEmpty(orders_flag)){
            map.put("orders_flag", orders_flag);
        }
        map.put("update_id", update_id);
        map.put("update_name", update_name);
        map.put("update_time", update_time);
        if(!TextUtils.isEmpty(del_flag)){
            map.put("del_flag", del_flag);
        }
        postLoad(BaseUrl.update_order_type, map, callBack);
    }

    /**
     * 评论上传文件
     */
    public void getuploadingfileApi(String products_id,String orders_id,String comment_id,String xzdz,String new_fname,
                                    String file_name, String create_id,String create_name,String create_time,
                                    String del_flag,String path, final GetResultCallBack callBack) {
        OkHttpClientManager.getUploadDelegate().postAsyn(BaseUrl.submitImage , "file", new File(path),
                new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("products_id", products_id),
                        new OkHttpClientManager.Param("orders_id", orders_id),
                        new OkHttpClientManager.Param("comment_id", comment_id),
                        new OkHttpClientManager.Param("xzdz", xzdz),
                        new OkHttpClientManager.Param("new_fname", new_fname),
                        new OkHttpClientManager.Param("file_name", file_name),
                        new OkHttpClientManager.Param("create_id", create_id),
                        new OkHttpClientManager.Param("create_name", create_name),
                        new OkHttpClientManager.Param("create_time", create_time),
                        new OkHttpClientManager.Param("del_flag", del_flag)},
                new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("onError","============"+request);
                        callBack.getResult(request.toString(),Constants.TYPE_FAIL);
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponse","============"+response);
                        try {
                            if(OkHttpClientManager.isParse(response)){
                                callBack.getResult(response,Constants.TYPE_SUCCESS);
                            }else {
                                callBack.getResult(response, Constants.TYPE_FAIL);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },mContext);
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
        getLoad(BaseUrl.setaddress, map, callBack);
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
     * 添加银行卡
     */
    public void addbankcardApi(String login_id,String bank_for_codetype,String bank_for_name,String bank_number, String bank_type,
                               String addresss,String bankzh,String bank_code, String bank,
                               String create_id,String create_name, String create_time,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("login_id", login_id);
        map.put("bank_for_codetype", bank_for_codetype);
        map.put("bank_for_name", bank_for_name);
        map.put("bank_number", bank_number);
        map.put("bank_type", bank_type);
        map.put("addresss", addresss);
        map.put("bankzh", bankzh);
        map.put("bank_code", bank_code);
        map.put("bank", bank);
        map.put("create_id", create_id);
        map.put("create_name", create_name);
        map.put("create_time", create_time);
        postLoad(BaseUrl.addbankcard, map, callBack);
    }

    /**
     * 查询银行卡
     * @param create_id
     * @param callBack
     */
    public void searchbankcardApi(String create_id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id);
        getLoad(BaseUrl.searchbankcard, map, callBack);
    }

    /**
     * 删除银行卡
     * @param id
     * @param callBack
     */
    public void delbankcardApi(String id,String del_flag,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("del_flag", del_flag);
        postLoad(BaseUrl.delbankcard, map, callBack);
    }

    /**
     * 我的信息
     * @param id
     * @param callBack
     */
    public void myInformationApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        postLoad(BaseUrl.myinformation, map, callBack);
    }


    /**
     * 绑定手机
     * @param id
     * @param phone
     * @param update_date
     * @param callBack
     */
    public void bindphoneApi(String id,String phone,String update_date,String getcode,String type,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("phone", phone);
        map.put("type", type);
        map.put("update_date", update_date);
        map.put("getcode", getcode);
        postLoad(BaseUrl.bindphone, map, callBack);
    }


    /**
     * 修改密码
     * @param id
     * @param password
     * @param callBack
     */
    public void changePasswordApi(String id, String password,int tag,String getcode,String phone,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("newPassword", password);
        map.put("tag", "" + tag);
        if(!TextUtils.isEmpty(getcode)){
            map.put("getcode", getcode);
        }
        if(!TextUtils.isEmpty(phone)){
            map.put("phone", phone);
        }
        postLoad(BaseUrl.virifyPassword, map, callBack);
    }

    /**
     * 充值查询
     * @param mobile
     * @param callBack
     */
    public void rechargeApi(String mobile,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        getLoad(BaseUrl.recharge, map, callBack);
    }

    /**
     * 查找服务号
     * @param pid
     * @param callBack
     */
    public void findServiceNumb(String pid,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("p_id", pid);
        getLoad(BaseUrl.findServiceNumb, map, callBack);
    }

    /**
     * 查询统计
     * @param user_phone
     * @param callBack
     */
    public void queryStatistics(String user_phone,String childphone,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("user_phone", user_phone);
        map.put("childphone", childphone);
        getLoad(BaseUrl.queryStatistics, map, callBack);
    }

    /**
     * //查询统计(统计更新)
     * @param fw_id
     * @param callBack
     */
    public void upDateQueryStatistics(String fw_id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("fw_id", fw_id);
        getLoad(BaseUrl.upDateQueryStatistics, map, callBack);
    }

    /**
     * 收益统计
     * @param user_phone
     * @param for_date
     * @param callBack
     */
    public void incomeStatisticsApi(String user_phone,String for_date,String qdyj_level,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("user_phone", user_phone);
        map.put("for_date", for_date);
        map.put("qdyj_level", qdyj_level);
        getLoad(BaseUrl.incomeStatistics, map, callBack);
    }


    /**
     * 服务查询
     * @param user_phone
     * @param for_date
     * @param p_id
     * @param callBack
     */
    public void serviceChannelApi(String user_phone,String for_date,String p_id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        if(!TextUtils.isEmpty(user_phone)){
            map.put("user_phone", user_phone);
        }
        map.put("for_date", for_date);
        map.put("p_id", p_id);
        getLoad(BaseUrl.serviceChannel, map, callBack);
    }

    //https://apimg.alipay.com/combo.png?d=cashier&t=CCB 查询银行logo
    //支付宝 https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=银行卡卡号&cardBinCheck=true
    //       DC:借记卡，CC:信用卡 ,SCC: "准贷记卡",PC: "预付费卡"
    public void bankcardApi(String _input_charset,String cardNo,boolean cardBinCheck,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("_input_charset", _input_charset);
        map.put("cardNo", cardNo);
        map.put("cardBinCheck", ""+cardBinCheck);
        getLoad(BaseUrl.bankcard, map, callBack);
    }

}
