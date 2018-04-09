package com.mingxuan.huaji.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.activity.MyShoppingCartActivity;
import com.mingxuan.huaji.layout.two.adapter.PhoneCardAdapter;
import com.mingxuan.huaji.layout.two.model.AliPayModel;
import com.mingxuan.huaji.layout.two.model.PayModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;
import com.mingxuan.huaji.utils.alpay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class RechargeFragment extends Fragment {
    @BindView(R.id.et_find)
    EditText etFind;
    @BindView(R.id.tv_find)
    TextView tvFind;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_numb)
    TextView tvNumb;
    @BindView(R.id.pay)
    TextView pay;
    private View view;
    Unbinder unbinder;
    List<String> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recharge,null);
        unbinder = ButterKnife.bind(this,view);

        initView();
        return view;
    }

    private void initView() {
        list = new ArrayList<>();
        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(getActivity(),4);
        recyclerview.setLayoutManager(fullGridLayoutManager);
        int spanCount = 4;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        list.add("1个月");
        list.add("3个月");
        list.add("6个月");
        list.add("12个月");

        PhoneCardAdapter phoneCardAdapter = new PhoneCardAdapter(list,getActivity());
        recyclerview.setAdapter(phoneCardAdapter);
        phoneCardAdapter.setOnItemClickListener(new PhoneCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String number = list.get(position);
            }
        });
    }

    @OnClick({R.id.tv_find,R.id.pay})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_find:
                ToastUtil.makeToast(getActivity(),"此功能还未开通！！！");
                break;
            case R.id.pay:
//                ToastUtil.makeToast(getActivity(),"此功能还未开通！！！");
                showPayPopupWindow();
                break;
        }
    }

    CheckBox wxcheckBox,alcheckBox;
    LinearLayout linearwx,linearali;
    private void showPayPopupWindow(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_pay_type,null);
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
                out_trade_no = getOutTradeNo();
                if(wxcheckBox.isChecked()){
                    body = "华记黄埔商城";
                    total_fee = "1";
                    spbill_create_ip = UIUtils.getIPAddress(getActivity());
                    sendPayRequest();
                }else {
                    subject = "支付宝测试";
                    total_amount = "0.01";
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
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
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
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
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
                PayTask alipay = new PayTask(getActivity());
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
    String total_amount;
    private void getaliPayRequests(final View v){
        MainApi.getInstance(getActivity()).alipayApi(subject,out_trade_no,total_amount,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                AliPayModel aliPayModel = GsonUtil.fromJSONData(new Gson(),result,AliPayModel.class);
                Log.e("","得到数据"+aliPayModel.getResult());
                orderInfo = aliPayModel.getResult();

                payV2(v);
            }
        });
    }


    /**
     * 微信支付
     */
    IWXAPI api;
    String spbill_create_ip;
    String body;
    String out_trade_no;
    String total_fee;
    private void sendPayRequest(){
        MainApi.getInstance(getActivity()).wxpayApi(body,out_trade_no,total_fee,spbill_create_ip,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    PayModel.ResultBean resultData = GsonUtil.fromJSONData(new Gson(),result, PayModel.ResultBean.class);
                    PayModel.ResultBean.ResultData resultBeans = resultData.getResult();

                    api= WXAPIFactory.createWXAPI(getActivity(), null);
                    api.registerApp(Constants.APP_ID);
                    PayReq req = new PayReq();
                    req.appId = resultBeans.getAppid();
                    req.partnerId = resultBeans.getPartnerid();
                    req.prepayId = resultBeans.getPrepayid();
                    req.packageValue = "Sign=WXPay";
                    req.nonceStr = resultBeans.getNoncestr();
                    req.timeStamp = resultBeans.getTimestamp();
                    req.sign = resultBeans.getSign();
                    //3.调用微信支付sdk支付方法\
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
