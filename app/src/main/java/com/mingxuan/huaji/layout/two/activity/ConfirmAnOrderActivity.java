package com.mingxuan.huaji.layout.two.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.mingxuan.huaji.layout.two.model.PayModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.alpay.OrderInfoUtil2_0;
import com.mingxuan.huaji.utils.alpay.PayKeys;
import com.mingxuan.huaji.utils.alpay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private List<MyAdressModel.ResultBean> adresslist;

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
    }

    Bundle bundle;
    String createname;
    String imageurl;
    String productname;
    String productprice;
    private void getBundle() {
        bundle = getIntent().getExtras();
        if(bundle != null){
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

    @OnClick({R.id.no_default_adress,R.id.default_adress,R.id.settle_accounts})
    public void setOnClick(View view){
        Intent intent;
        switch (view.getId()){
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
    private void showPayPopupWindow(){
        View view = LayoutInflater.from(ConfirmAnOrderActivity.this).inflate(R.layout.layout_pay_type,null);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*2/5;
        wxcheckBox = (CheckBox) view.findViewById(R.id.check_wx);
        alcheckBox = (CheckBox) view.findViewById(R.id.check_al);
        wxcheckBox.setChecked(true);

        final PopupWindow popupWindow = new PopupWindow(view,weight,height,true);
        popupWindow.setFocusable(true);

        wxcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(wxcheckBox.isChecked()){
                    alcheckBox.setChecked(false);
                }
            }
        });

        alcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(alcheckBox.isChecked()){
                    wxcheckBox.setChecked(false);
                }
            }
        });

        view.findViewById(R.id.immediate_payment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wxcheckBox.isChecked()){
                    sendPayRequest();
                    ToastUtil.makeToast(ConfirmAnOrderActivity.this,"微信支付");
                }else {
                    payV2(v);
                    ToastUtil.makeToast(ConfirmAnOrderActivity.this,"支付宝支付");
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
    private void sendPayRequest(){
        MainApi.getInstance(this).wxpayApi(new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                api= WXAPIFactory.createWXAPI(ConfirmAnOrderActivity.this, Constants.APP_ID);
                if(result == null ){
                    Toast.makeText(ConfirmAnOrderActivity.this, "没有正常调起支付", Toast.LENGTH_SHORT).show();
                }
                api.registerApp(Constants.APP_ID);
                PayReq req = new PayReq();
                PayModel payModel = new PayModel();
                req.appId = Constants.APP_ID;
                req.partnerId = payModel.getPartnerid();
                req.prepayId = payModel.getPrepayid();
                req.nonceStr = payModel.getNoncestr();
                req.timeStamp = payModel.getTimestamp();
                req.packageValue = "Sign=WXPay";
                req.sign = payModel.getSign();
                Toast.makeText(ConfirmAnOrderActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                //3.调用微信支付sdk支付方法
                api.sendReq(req);
            }
        });
    }

    ///////////////支付宝支付///////////
    private static final int SDK_PAY_FLAG = 1;
    // 入参app_id
    public static final String PARTNER = PayKeys.APPID;   //这几个用了PayKey中的方法；
    // 商户收款账号
    public static final String SELLER = PayKeys.DEFAULT_SELLER;
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "";
    private static final String RSA2_PRIVATE = PayKeys.PRIVATE;

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
        if (TextUtils.isEmpty(PARTNER) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(PARTNER, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);

        final String orderInfo = orderParam + "&" + sign;

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


}
