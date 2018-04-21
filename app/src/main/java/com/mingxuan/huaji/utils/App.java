package com.mingxuan.huaji.utils;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Admin on 2018/4/9.
 * 公司：铭轩科技
 */

public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static App getInstance() {
        return mInstance;
    }

}
