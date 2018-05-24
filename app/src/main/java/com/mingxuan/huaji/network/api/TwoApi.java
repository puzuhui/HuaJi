package com.mingxuan.huaji.network.api;

import android.content.Context;

import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.network.url.BaseUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2018/5/17.
 * 公司：铭轩科技
 */

public class TwoApi extends BaseApi{
    private static TwoApi api;

    public TwoApi(Context context) {
        super(context);
    }

    public static TwoApi getInstance(Context context){
        synchronized (MainApi.class) {
            if (api == null) {
                api = new TwoApi(context);
            }
        }
        return api;
    }

    /**
     * 下载地址
     * @param callBack
     */
    public void getdownloadApi(GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        getLoad(BaseUrl.download, map, callBack);
    }

    /**
     * 通知公告
     * @param receive_id
     * @param callBack
     */
    public void notificationApi(String receive_id,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("receive_id",receive_id);
        getLoad(BaseUrl.notification, map, callBack);
    }

    /**
     * 资费介绍
     * @param callBack
     */
    public void introduceApi(GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        getLoad(BaseUrl.introduce, map, callBack);
    }

    /**
     * 号卡物流
     * @param createId
     * @param callBack
     */
    public void logisticsnoApi(String createId,GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        map.put("create_id",createId);
        getLoad(BaseUrl.logisticsno, map, callBack);
    }
}
