package com.mingxuan.huaji.layout.mine.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.RegisitActivity;
import com.mingxuan.huaji.layout.mine.adapter.DateAdapter;
import com.mingxuan.huaji.layout.mine.bean.DateMolder;
import com.mingxuan.huaji.layout.mine.bean.IntergralMolder;
import com.mingxuan.huaji.layout.mine.bean.MyBankCardModel;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.NewEditText;
import com.mingxuan.huaji.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/5/28.
 * 公司：铭轩科技
 */

public class TiXianActivity extends BaseActivity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.tv_tianxian_numb)
    TextView tvTianxianNumb;
    @BindView(R.id.tv_tianxian_bank)
    TextView tvTianxianBank;
    @BindView(R.id.tv_bank_numb)
    TextView tvBankNumb;
    @BindView(R.id.tv_tianxian)
    TextView tvTianxian;
    @BindView(R.id.et_tianxian_numb)
    EditText etTianxianNumb;
    List<DateMolder> list;
    String createi_id;
    LoadingDialog loadingDialog;
    View view;
    String intergral;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tixian;
    }

    @Override
    protected void initView() {
        setToolbarTitle("现金积分提现");
        SharedPreferences sharedPreferences =getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        createi_id = sharedPreferences.getString("create_id","");
        phone = sharedPreferences.getString("phone","");
        createName = sharedPreferences.getString("realName","");
        idcard = sharedPreferences.getString("idCard","");

        list = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);
        Bundle bundle = getIntent().getExtras();
        intergral = bundle.getString("intergral","");
        String numb = getString(R.string.intergral, intergral);
        tvIntegral.setText(numb);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {
        addBankCard();
    }

    @OnClick({R.id.tv_bank_numb, R.id.tv_tianxian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bank_numb:
                showCardDialog();
                break;
            case R.id.tv_tianxian:
                if(!TextUtils.isEmpty(etTianxianNumb.getText())){
                    if(Double.parseDouble(etTianxianNumb.getText().toString()) <= Double.parseDouble(intergral)){
                        if(!tvBankNumb.getText().toString().equals("请选择提现银行卡号")){
                            showDialog();
                        }else {
                            ToastUtil.makeToast(TiXianActivity.this,"请选择提现银行卡");
                        }
                    }else {
                        ToastUtil.makeToast(TiXianActivity.this,"您的现金积分不足");
                    }
                }else {
                    ToastUtil.makeToast(TiXianActivity.this,"请输入提现积分");
                }
                break;
        }
    }

    //验证码倒计时
    CountDownTimer countDownTimer = new CountDownTimer(60*1000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetcode.setText(millisUntilFinished/1000+"秒后重新发送");
            tvGetcode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            tvGetcode.setText("重新发送验证码");
            tvGetcode.setEnabled(true);
        }
    };

    //选择银行卡
    public void showCardDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_listview,null);
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(view);
        final Dialog dialog = builder.show();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        DateAdapter dateAdapter = new DateAdapter(this,list);
        recyclerView.setAdapter(dateAdapter);
        dateAdapter.setOnItemClickListener(new DateAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                tvBankNumb.setText(list.get(position).getDate());
                dialog.dismiss();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //提现验证码弹出框
    TextView tvGetcode;
    private void showDialog() {
        view = LayoutInflater.from(this).inflate(R.layout.dialog_tixian, null);
        EditText etCode = (EditText) view.findViewById(R.id.et_code);
        tvGetcode = (TextView) view.findViewById(R.id.tv_getcode);
        TextView tvTianxianDialog = (TextView) view.findViewById(R.id.tv_tianxian_dialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(TiXianActivity.this);
        builder.setView(view)
                .create();

        final AlertDialog alertDialog = builder.show();

        etCode.addTextChangedListener(new NewEditText(etCode));
        tvGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.start();
                choosePassword();
            }
        });
        tvTianxianDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txBanknumber = tvBankNumb.getText().toString();
                txPrint = etTianxianNumb.getText().toString();
                getTixan();
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 获取验证码
     */
    String phone;
    private void choosePassword(){
        MainApi.getInstance(this).duanxin(phone, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){

                }else BaseApi.showErrMsg(TiXianActivity.this,result);
            }
        });
    }

    //获取银行卡号
    private void addBankCard(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).searchbankcardApi(createi_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<MyBankCardModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,MyBankCardModel.ResultBean.class);
                    for (int i = 0; i < resultBeans.size(); i++) {
                        DateMolder dateMolder = new DateMolder();
                        dateMolder.setDate(resultBeans.get(i).getBank_number());
                        dateMolder.setBank(resultBeans.get(i).getBankX());
                        list.add(dateMolder);
                    }
                }else BaseApi.showErrMsg(TiXianActivity.this,result);
            }
        });
    }

    //提现
    String txBanknumber;
    String txPrint;
    String createName;
    String idcard;
    private void getTixan() {
        FourApi.getInstance(this).tixian(txBanknumber, txPrint,phone,createi_id,createName,idcard, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    ToastUtil.makeToast(TiXianActivity.this,"提现申请成功,请等待...");
                    Intent intent = new Intent(TiXianActivity.this,MyIntergralActivity.class);
                    startActivity(intent);
                } else BaseApi.showErrMsg(TiXianActivity.this, result);
            }
        });
    }
}
