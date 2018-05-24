package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.MyShoppingCarAdapter;
import com.mingxuan.huaji.layout.mine.bean.MyShoppingCarModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.PayUtils;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class MyShoppingCartActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.head_shop)
    TextView headShop;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.check_all)
    CheckBox checkAll;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.settle_accounts)
    TextView settleAccounts;
    private List<MyShoppingCarModel.ResultBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");
        update_id = sharedPreferences.getString("create_id","");
        update_name = sharedPreferences.getString("create_name","");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        update_time = simpleDateFormat.format(new Date());
        initView();
        getShoppingCar();
    }

    private void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManger);
        checkAll.setChecked(false);

        myShoppingCarAdapter = new MyShoppingCarAdapter(this,list,checkAll,money);
        recyclerview.setAdapter(myShoppingCarAdapter);
        myShoppingCarAdapter.setOnItemClickListener(new MyShoppingCarAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                switch (view.getId()){
                    case R.id.del_btn:
                        id = list.get(position).getId();
                        list.remove(position);
                        delProductsCar();
                        break;
                }
            }
        });
    }

    @OnClick({R.id.back_btn,R.id.settle_accounts})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.settle_accounts:
                if(list.size()!=0 && !money.getText().toString().equals("0.0")){
                   // showPayPopupWindow();
                    PayUtils.getInstance(this).showPayPopupWindow("华记测试商品","1",UIUtils.getIPAddress(this));
                }else {
                    ToastUtil.makeToast(this,"请选择商品");
                }
                break;
        }
    }

//    CheckBox wxcheckBox,alcheckBox;
//    LinearLayout linearwx,linearali;
//    private void showPayPopupWindow(){
//        View view = LayoutInflater.from(MyShoppingCartActivity.this).inflate(R.layout.layout_pay_type,null);
//        //获取屏幕宽高
//        int weight = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels*2/5;
//        linearwx = (LinearLayout) view.findViewById(R.id.linear_wx);
//        linearali = (LinearLayout) view.findViewById(R.id.linear_ali);
//        wxcheckBox = (CheckBox) view.findViewById(R.id.check_wx);
//        alcheckBox = (CheckBox) view.findViewById(R.id.check_al);
//        wxcheckBox.setChecked(true);
//
//        final PopupWindow popupWindow = new PopupWindow(view,weight,height,true);
//        popupWindow.setFocusable(true);
//
//        linearali.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wxcheckBox.setChecked(false);
//                alcheckBox.setChecked(true);
//            }
//        });
//
//        linearwx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wxcheckBox.setChecked(true);
//                alcheckBox.setChecked(false);
//            }
//        });
//
//        view.findViewById(R.id.immediate_payment).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                out_trade_no = getOutTradeNo();
//                if(wxcheckBox.isChecked()){
//                    body = "华记黄埔商城";
//                    total_fee = "1";
//                    spbill_create_ip = UIUtils.getIPAddress(MyShoppingCartActivity.this);
//                    sendPayRequest();
//                }else {
//                    subject = "支付宝测试";
//                    total_amount = "0.01";
//                    getaliPayRequests(v);
//                }
//
//                popupWindow.dismiss();
//            }
//        });
//
//        //点击外部popueWindow消失
//        popupWindow.setOutsideTouchable(true);
//        //popupWindow消失屏幕变为不透明
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = getWindow().getAttributes();
//                lp.alpha = 1.0f;
//                getWindow().setAttributes(lp);
//            }
//        });
//
//        //popupWindow出现屏幕变为半透明
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = 0.5f;
//        getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
//    }
//
//    ///////////////支付宝支付///////////
//    private static final int SDK_PAY_FLAG = 1;
//    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @SuppressWarnings("unused")
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SDK_PAY_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                    /**
//                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                    String resultStatus = payResult.getResultStatus();
//                    // 判断resultStatus 为9000则代表支付成功
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(MyShoppingCartActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        Toast.makeText(MyShoppingCartActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                }
//                default:
//                    break;
//            }
//        };
//    };
//
//    public void payV2(View v) {
//        Runnable payRunnable = new Runnable() {
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(MyShoppingCartActivity.this);
//                Map<String, String> result = alipay.payV2(orderInfo, true);
//                Log.i("msp", result.toString());
//
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }

//    String orderInfo;
//    String subject;
//    String total_amount;
//    private void getaliPayRequests(final View v){
//        MainApi.getInstance(this).alipayApi(subject,out_trade_no,total_amount,new GetResultCallBack() {
//            @Override
//            public void getResult(String result, int type) {
//                AliPayModel aliPayModel = GsonUtil.fromJSONData(new Gson(),result,AliPayModel.class);
//                Log.e("","得到数据"+aliPayModel.getResult());
//                orderInfo = aliPayModel.getResult();
//
//                payV2(v);
//            }
//        });
//    }
//
//
//    /**
//     * 微信支付
//     */
//    IWXAPI api;
//    String spbill_create_ip;
//    String body;
//    String out_trade_no;
//    String total_fee;
//    private void sendPayRequest(){
//        MainApi.getInstance(this).wxpayApi(body,out_trade_no,total_fee,spbill_create_ip,new GetResultCallBack() {
//            @Override
//            public void getResult(String result, int type) {
//                if(type == Constants.TYPE_SUCCESS){
//                    PayModel.ResultBean resultData = GsonUtil.fromJSONData(new Gson(),result, PayModel.ResultBean.class);
//                    PayModel.ResultBean.ResultData resultBeans = resultData.getResult();
//
//                    api= WXAPIFactory.createWXAPI(MyShoppingCartActivity.this, null);
//                    api.registerApp(Constants.APP_ID);
//                    PayReq req = new PayReq();
//                    req.appId = resultBeans.getAppid();
//                    req.partnerId = resultBeans.getPartnerid();
//                    req.prepayId = resultBeans.getPrepayid();
//                    req.packageValue = "Sign=WXPay";
//                    req.nonceStr = resultBeans.getNoncestr();
//                    req.timeStamp = resultBeans.getTimestamp();
//                    req.sign = resultBeans.getSign();
//                    //3.调用微信支付sdk支付方法
//                    api.sendReq(req);
//                }
//            }
//        });
//    }
//
//    //生成订单
//    private static String getOutTradeNo() {
//        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
//        Date date = new Date();
//        String key = format.format(date);
//        Random r = new Random();
//        for (int i = 0; i <2 ; i++) {
//            key = key + r.nextInt(10);
//        }
//        return key;
//    }


    MyShoppingCarAdapter myShoppingCarAdapter;
    String create_id;
    private void getShoppingCar(){
        FourApi.getInstance(this).getproductscartApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    List<MyShoppingCarModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,MyShoppingCarModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    myShoppingCarAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyShoppingCartActivity.this, result);
            }
        });
    }

    //删除购物车
    int id;
    int del_flag = 1;
    private String update_id;
    private String update_name;
    private String update_time;
    private void delProductsCar(){
        FourApi.getInstance(this).delproducts_cartApi(id, update_id, update_name, update_time,del_flag,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {

                    myShoppingCarAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyShoppingCartActivity.this, result);
            }
        });
    }

}