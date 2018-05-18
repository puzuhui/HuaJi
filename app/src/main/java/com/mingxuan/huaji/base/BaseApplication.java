package com.mingxuan.huaji.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.tencent.bugly.crashreport.CrashReport;

import cn.jpush.android.api.JPushInterface;

public class BaseApplication extends Application {
    //在整个应用执行过程中，需要提供的变量
    public static Context context;//需要使用的上下文对象：application实例
    public static Handler handler;//需要使用的handler
    public static Thread mainThread;//提供主线程对象
    public static int mainThreadId;//提供主线程对象的id

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();//实例化当前Application的线程即为主线程
        mainThreadId = android.os.Process.myTid();//获取当前线程的id

        //bugly
        CrashReport.initCrashReport(getApplicationContext(), "74962aaeb5", true);
        //极光
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
