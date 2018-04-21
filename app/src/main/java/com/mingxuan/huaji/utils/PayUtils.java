package com.mingxuan.huaji.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.two.activity.ConfirmAnOrderActivity;
import com.mingxuan.huaji.layout.two.model.AliPayModel;
import com.mingxuan.huaji.layout.two.model.PayModel;
import com.mingxuan.huaji.utils.alpay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Created by Admin on 2018/3/22.
 * 公司：铭轩科技
 */

public class PayUtils {
    private static PayUtils mInstance;
    private Activity activity;
    private PayUtils(Activity activity){
        this.activity = activity;
    }

    public static PayUtils getInstance(Activity activity){
        if(mInstance == null){
            synchronized (PayUtils.class){
                if(mInstance == null){
                    mInstance = new PayUtils(activity);
                }
            }
        }
        return mInstance;
    }

    CheckBox wxcheckBox,alcheckBox;
    LinearLayout linearwx,linearali;
    public void showPayPopupWindow(final String name, final String price, final String spbill_create_ip){
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_pay_type,null);
        //获取屏幕宽高
        int weight = activity.getResources().getDisplayMetrics().widthPixels;
        int height = activity.getResources().getDisplayMetrics().heightPixels*2/5;
        linearwx = (LinearLayout) view.findViewById(R.id.linear_wx);
        linearali = (LinearLayout) view.findViewById(R.id.linear_ali);
        wxcheckBox = (CheckBox) view.findViewById(R.id.check_wx);
        alcheckBox = (CheckBox) view.findViewById(R.id.check_al);
        wxcheckBox.setChecked(true);

        final PopupWindow popupWindow = new PopupWindow(view,weight,height,true);
        popupWindow.setFocusable(true);

        linearali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxcheckBox.setChecked(false);
                alcheckBox.setChecked(true);
            }
        });

        linearwx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxcheckBox.setChecked(true);
                alcheckBox.setChecked(false);
            }
        });

        view.findViewById(R.id.immediate_payment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wx_out_trade_no = getOutTradeNo();
                out_trade_no = getOutTradeNo();
                if(wxcheckBox.isChecked()){
                    body = name;
                    total_fee = price;
                    sendPayRequest(spbill_create_ip);
                }else {
                    subject = name;
                    total_amount = price;
                    getaliPayRequests(v);
                }

                popupWindow.dismiss();
            }
        });

        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1.0f;
                activity.getWindow().setAttributes(lp);
            }
        });

        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        activity.getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
    }

    /**
     * 微信支付
     */
    IWXAPI api;
    String body;
    String wx_out_trade_no;
    String total_fee;
    private void sendPayRequest(String spbill_create_ip){
        MainApi.getInstance(activity).wxpayApi(body,wx_out_trade_no,total_fee,spbill_create_ip,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    PayModel.ResultBean resultData = GsonUtil.fromJSONData(new Gson(),result, PayModel.ResultBean.class);
                    PayModel.ResultBean.ResultData resultBeans = resultData.getResult();

                    api= WXAPIFactory.createWXAPI(activity, Constants.APP_ID,false);
                    api.registerApp(Constants.APP_ID);
                    PayReq req = new PayReq();
                    req.appId = Constants.APP_ID;
                    req.partnerId = resultBeans.getPartnerid();
                    req.prepayId = resultBeans.getPrepayid();
                    req.nonceStr = resultBeans.getNoncestr();
                    req.timeStamp = resultBeans.getTimestamp();
                    req.packageValue = "Sign=WXPay";
                    req.sign = resultBeans.getSign();
                    //3.调用微信支付sdk支付方法\
                    Log.e("===","appId="+req.appId+"\npartnerId="+req.partnerId+"\nprepayId="+req.prepayId+"\nnonceStr="+req.nonceStr
                            +"\ntimeStamp="+req.timeStamp+"\npackageValue="+req.packageValue+"\nsign="+req.sign);
                    api.sendReq(req);
                }
            }
        });
    }

    //生成订单
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        for (int i = 0; i <2 ; i++) {
            key = key + r.nextInt(10);
        }
        return key;
    }



    ///////////////支付宝支付///////////
    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    private void payV2(View v) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    String orderInfo;
    String subject;
    String out_trade_no;
    String total_amount;
    private void getaliPayRequests(final View v){
        MainApi.getInstance(activity).alipayApi(subject,out_trade_no,total_amount,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                AliPayModel aliPayModel = GsonUtil.fromJSONData(new Gson(),result,AliPayModel.class);
                Log.e("","得到数据"+aliPayModel.getResult());
                orderInfo = aliPayModel.getResult();

                payV2(v);
            }
        });
    }

}
