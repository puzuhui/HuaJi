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
}
