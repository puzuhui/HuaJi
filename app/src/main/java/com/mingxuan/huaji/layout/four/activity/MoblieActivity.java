package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/15.
 * 公司：铭轩科技
 */

public class MoblieActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.bind)
    TextView bind;
    @BindView(R.id.please_enter_verification_code)
    EditText pleaseEnterVerificationCode;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.lin_code)
    LinearLayout linCode;
    @BindView(R.id.binding)
    TextView binding;
    @BindView(R.id.binding_linear)
    LinearLayout bindingLinear;
    @BindView(R.id.please_enter_phone_number)
    EditText pleaseEnterPhoneNumber;
    @BindView(R.id.please_enter_verification_unbinding_code)
    EditText pleaseEnterVerificationUnbindingCode;
    @BindView(R.id.get_unbinding_code)
    TextView getUnbindingCode;
    @BindView(R.id.unbinding)
    TextView unbinding;
    @BindView(R.id.unbinding_linear)
    LinearLayout unbindingLinear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        ButterKnife.bind(this);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        update_date = simpleDateFormat.format(new Date());
        initView();
    }

    String phonenumber;
    SharedPreferences sharedPreferences;
    private void initView() {
        sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("create_id", "");

        Bundle bundle =getIntent().getExtras();
        phonenumber = bundle.getString("phone","");
        if (!TextUtils.isEmpty(phonenumber)) {
            bindingLinear.setVisibility(View.VISIBLE);
            unbindingLinear.setVisibility(View.GONE);
            bind.setText("绑定手机号："+phonenumber);
        } else {
            bindingLinear.setVisibility(View.GONE);
            unbindingLinear.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.back_btn, R.id.get_code, R.id.binding,R.id.get_unbinding_code, R.id.unbinding})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.get_code:
                if (UIUtils.isMobileNO(phonenumber)) {
                    choosePassword();
                    countDownTimer.start();
                } else {
                    ToastUtil.makeToast(MoblieActivity.this, "电话号码格式错误");
                }
                break;
            case R.id.binding://解除绑定
                if (!TextUtils.isEmpty(pleaseEnterVerificationCode.getText().toString())) {
                    Log.e("验证码=====",""+pleaseEnterVerificationCode.getText().toString());
                    ToastUtil.makeToast(MoblieActivity.this, "解除绑定");
                    codetype ="1";
                    code = pleaseEnterVerificationCode.getText().toString();
                    bindphone();
                } else {
                    ToastUtil.makeToast(MoblieActivity.this, "验证码未填写");
                }
                break;
            case R.id.get_unbinding_code:
                if (UIUtils.isMobileNO(pleaseEnterPhoneNumber.getText().toString())) {
                    phonenumber = pleaseEnterPhoneNumber.getText().toString();
                    choosePassword();
                    countDownTimer.start();
                } else {
                    ToastUtil.makeToast(MoblieActivity.this, "电话号码格式错误");
                }
                break;
            case R.id.unbinding://重新绑定
                if (!TextUtils.isEmpty(pleaseEnterPhoneNumber.getText().toString())) {
                    ToastUtil.makeToast(MoblieActivity.this, "绑定");
                    phonenumber = pleaseEnterPhoneNumber.getText().toString();
                    Log.e("手机号=====",""+phonenumber);
                    code = pleaseEnterVerificationUnbindingCode.getText().toString();
                    codetype ="2";
                    bindphone();
                } else {
                    ToastUtil.makeToast(MoblieActivity.this, "验证码未填写");
                }
                break;
        }
    }


    //倒计时
    CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getCode.setText(millisUntilFinished / 1000 + "秒后重新发送");
            getCode.setEnabled(false);
            getUnbindingCode.setText(millisUntilFinished / 1000 + "秒后重新发送");
            getUnbindingCode.setEnabled(false);
            //getCode.setBackgroundResource(R.drawable.get_cold_grey);
        }

        @Override
        public void onFinish() {
            getCode.setText("重新发送验证码");
            getCode.setEnabled(true);
            getUnbindingCode.setText("重新发送验证码");
            getUnbindingCode.setEnabled(true);
            //getCode.setBackgroundResource(R.drawable.get_cold_red);
        }
    };

    /**
     * 获取验证码
     */
    private void choosePassword() {
        MainApi.getInstance(this).duanxin(phonenumber, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {

                } else BaseApi.showErrMsg(MoblieActivity.this, result);
            }
        });
    }


    String id;
    String update_date;
    String code;
    String codetype;//codetype 1;解除绑定 2 绑定
    private void bindphone() {
        FourApi.getInstance(this).bindphoneApi( id, phonenumber, update_date,code, codetype,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if(codetype.equals("1")){
                        editor.putString("phone","");
                    }else {
                        editor.putString("phone",phonenumber);
                    }
                    editor.apply();
                    Intent intent = new Intent(MoblieActivity.this,BindMobileActivity.class);
                    startActivity(intent);
                    finish();
                } else BaseApi.showErrMsg(MoblieActivity.this, result);
            }
        });
    }
}
