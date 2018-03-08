package com.mingxuan.huaji.layout.two.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.activity.MyAdressActivity;
import com.mingxuan.huaji.layout.four.model.MyAdressModel;
import com.mingxuan.huaji.layout.two.model.AliPayModel;
import com.mingxuan.huaji.layout.two.model.PayModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.UIUtils;
import com.mingxuan.huaji.utils.alpay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class ConfirmAnOrderActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.no_default_adress)
    TextView noDefaultAdress;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.default_adress)
    LinearLayout defaultAdress;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.shop_content)
    TextView shopContent;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.settle_accounts)
    TextView settleAccounts;
    @BindView(R.id.productprice)
    TextView productPrice;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.optional)
    TextView optional;
    private List<MyAdressModel.ResultBean> adresslist;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim_an_order);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");

        getBundle();
        initView();
        searchdefaultaddress();
        spbill_create_ip = UIUtils.getIPAddress(this);
        Log.e("",""+spbill_create_ip);
    }

    Bundle bundle;
    String createname;
    String imageurl;
    String productname;
    String productprice;
    private void getBundle() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day=aCalendar.getActualMaximum(Calendar.DATE) - aCalendar.get(Calendar.DAY_OF_MONTH);

        bundle = getIntent().getExtras();
        if(bundle != null){
            index = bundle.getInt("index");
            if(index == 1 ){
                optional.setText("(备注:电信号码首次开通需要预存3个月套餐费和本月剩余"+day+"天共计"+3.0+"元)");//这里还需要修改
            }
            createname = bundle.getString("createname");
            imageurl = bundle.getString("image");
            productname = bundle.getString("productname");
            productprice = bundle.getString("productprice");
        }
    }


    private void initView() {
        adresslist = new ArrayList<>();
        if(bundle != null){
            shopName.setText(createname);
            Glide.with(this).load(imageurl).into(image);
            shopContent.setText(productname);
            productPrice.setText("￥"+productprice);
            price.setText(productprice);
            money.setText(productprice);
        }
    }

    @OnClick({R.id.back_btn,R.id.no_default_adress,R.id.default_adress,R.id.settle_accounts})
    public void setOnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.no_default_adress:
                intent = new Intent(ConfirmAnOrderActivity.this, MyAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.default_adress:
                intent = new Intent(ConfirmAnOrderActivity.this, MyAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.settle_accounts:
                showPayPopupWindow();
                break;
        }
    }

    CheckBox wxcheckBox,alcheckBox;
    LinearLayout linearwx,linearali;
    private void showPayPopupWindow(){
        View view = LayoutInflater.from(ConfirmAnOrderActivity.this).inflate(R.layout.layout_pay_type,null);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*2/5;
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
                    body = "华记黄埔商城";
                    total_fee = "100";
                    sendPayRequest();
                }else {
                    subject = "支付宝测试";
                    total_amount = "0.01";
                    getaliPayRequests(v);
                    Log.e("ali_out_trade_no===",""+out_trade_no);
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
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
    }

    String create_id;
    String default_flag = "1";
    private void searchdefaultaddress() {
        MainApi.getInstance(this).searchdefaultaddressApi(create_id, default_flag, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<MyAdressModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,MyAdressModel.ResultBean.class);
                    adresslist.addAll(resultBeans);

                    defaultAdress.setVisibility(View.VISIBLE);
                    name.setText(adresslist.get(0).getConsignee());
                    phone.setText(adresslist.get(0).getPhone());
                    adress.setText(adresslist.get(0).getAddress());
                }else
                    noDefaultAdress.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 微信支付
     */
    IWXAPI api;
    String spbill_create_ip;
    String body;
    String wx_out_trade_no;
    String total_fee;
    private void sendPayRequest(){
        MainApi.getInstance(this).wxpayApi(body,wx_out_trade_no,total_fee,spbill_create_ip,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    PayModel.ResultBean resultData = GsonUtil.fromJSONData(new Gson(),result, PayModel.ResultBean.class);
                    PayModel.ResultBean.ResultData resultBeans = resultData.getResult();

                    api= WXAPIFactory.createWXAPI(ConfirmAnOrderActivity.this, Constants.APP_ID,false);
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
                        Toast.makeText(ConfirmAnOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ConfirmAnOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    public void payV2(View v) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(ConfirmAnOrderActivity.this);
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
        MainApi.getInstance(this).alipayApi(subject,out_trade_no,total_amount,new GetResultCallBack() {
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
