package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.ForgetThePasswordActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.utils.NewEditText;
import com.mingxuan.huaji.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/2.
 * 公司：铭轩科技
 */

public class InsertPasswordActivity extends BaseActivity {
    int index;
    @BindView(R.id.password)
    EditText etPassword;
    @BindView(R.id.password_new)
    EditText etPasswordNew;
    @BindView(R.id.password_confirm)
    EditText etPasswordConfirm;
    @BindView(R.id.linear_one)
    LinearLayout linearOne;
    @BindView(R.id.phone)
    TextView tvphone;
    @BindView(R.id.please_enter_verification_code)
    EditText etPleaseEnterVerificationCode;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.two_password_new)
    EditText etTwoPasswordNew;
    @BindView(R.id.linear_two)
    LinearLayout linearTwo;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_insertpassword;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle("修改密码");
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        cid = sharedPreferences.getString("create_id", "");
        phone = sharedPreferences.getString("phone", "");

        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        if(index == 1){
            linearOne.setVisibility(View.VISIBLE);
        }else {
            linearTwo.setVisibility(View.VISIBLE);
            tvphone.setText(phone);
        }

        etPassword.addTextChangedListener(new NewEditText(etPassword));
        etPasswordNew.addTextChangedListener(new NewEditText(etPasswordNew));
        etPasswordConfirm.addTextChangedListener(new NewEditText(etPasswordConfirm));
        etPleaseEnterVerificationCode.addTextChangedListener(new NewEditText(etPleaseEnterVerificationCode));
        etTwoPasswordNew.addTextChangedListener(new NewEditText(etTwoPasswordNew));
    }

    @Override
    protected void initData() {

    }

    @OnClick({ R.id.submit, R.id.get_code})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.get_code:
                mobile = tvphone.getText().toString();
                if(!TextUtils.isEmpty(mobile)){
                    getCode();
                    countDownTimer.start();
                }else {
                    ToastUtil.makeToast(this,"请填写手机号码");
                }
                break;
            case R.id.submit:
                if(index == 1 ){
                    if (!TextUtils.isEmpty(etPassword.getText())) {
                        if (!TextUtils.isEmpty(etPasswordNew.getText()) && etPasswordNew.getText().length() > 5) {
                            if (!TextUtils.isEmpty(etPasswordConfirm.getText())) {
                                if (etPasswordConfirm.getText().toString().equals(etPasswordNew.getText().toString())) {
                                    password = etPasswordNew.getText().toString();
                                    tag = 1;
                                    choosePassword();
                                } else {
                                    ToastUtil.makeToast(this, "新密码和确认密码必须一致");
                                }
                            } else {
                                ToastUtil.makeToast(this, "确认密码不能为空");
                            }
                        } else {
                            ToastUtil.makeToast(this, "新密码不能为空并且大于等于6位");
                        }
                    } else {
                        ToastUtil.makeToast(this, "原密码不能为空");
                    }
                }else {
                    if (!TextUtils.isEmpty(etPleaseEnterVerificationCode.getText())) {
                        if (!TextUtils.isEmpty(etTwoPasswordNew.getText())&& etTwoPasswordNew.length()>5) {
                            tag = 2;
                            password = etTwoPasswordNew.getText().toString();
                            getcode = etPleaseEnterVerificationCode.getText().toString();
                            choosePassword();
                        } else {
                            ToastUtil.makeToast(this, "新密码不能为空且大于5位");
                        }
                    } else {
                        ToastUtil.makeToast(this, "验证码不能为空");
                    }
                }
                break;
        }
    }

    //倒计时
    CountDownTimer countDownTimer = new CountDownTimer(60*1000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getCode.setText(millisUntilFinished / 1000+"后可重新发送");
            getCode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            getCode.setText("重新发送验证码");
            getCode.setEnabled(true);
        }
    };

    /**
     * 获取验证码
     */
    String mobile;
    private void getCode(){
        MainApi.getInstance(this).duanxin(mobile, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){

                }else BaseApi.showErrMsg(InsertPasswordActivity.this,result);
            }
        });
    }


    String cid;
    String password;
    int tag;
    String getcode;
    String phone;
    private void choosePassword() {
        FourApi.getInstance(this).changePasswordApi(cid, password, tag,getcode,phone, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    ToastUtil.makeToast(InsertPasswordActivity.this, "修改密码成功");
                    finish();
                } else BaseApi.showErrMsg(InsertPasswordActivity.this, result);
            }
        });
    }
}
