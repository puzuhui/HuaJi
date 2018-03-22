package com.mingxuan.huaji.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.activity.InsertPasswordActivity;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class RegisitActivity extends Activity {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.please_enter_username)
    EditText pleaseEnterUsername;
    @BindView(R.id.please_enter_id_number)
    EditText pleaseEnterIdNumber;
    @BindView(R.id.please_enter_id_name)
    EditText pleaseEnterIdName;
    @BindView(R.id.please_enter_phone_number)
    EditText pleaseEnterPhoneNumber;
    @BindView(R.id.please_enter_verification_code)
    EditText pleaseEnterVerificationCode;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.set_possword)
    EditText setPossword;
    @BindView(R.id.please_enter_possword_two)
    EditText pleaseEnterPosswordTwo;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.login_tx)
    TextView loginTx;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //ScrollView压缩背景图片解决
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initView();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
    }

    @OnClick({R.id.login,R.id.get_code,R.id.submit,R.id.login_tx})
    public void setOnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.login:
                intent = new Intent(RegisitActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.get_code:
                if(!TextUtils.isEmpty(pleaseEnterPhoneNumber.getText())){
                    phone = pleaseEnterPhoneNumber.getText().toString();
                    if(UIUtils.isMobileNO(phone)){
                        countDownTimer.start();
                        choosePassword();
                    }else {
                        ToastUtil.makeToast(RegisitActivity.this,"手机号码格式错误");
                    }
                }else {
                    ToastUtil.makeToast(RegisitActivity.this,"请输入手机号码");
                }
                break;
            case R.id.login_tx:
                intent = new Intent(RegisitActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.submit:
                pid = pleaseEnterUsername.getText().toString();
                idcard = pleaseEnterIdNumber.getText().toString();
                name = pleaseEnterIdName.getText().toString();
                mobile = pleaseEnterPhoneNumber.getText().toString();
                getcode = pleaseEnterVerificationCode.getText().toString();
                password = pleaseEnterPosswordTwo.getText().toString();
                if(!TextUtils.isEmpty(pid) && !TextUtils.isEmpty(idcard) && !TextUtils.isEmpty(name)
                        && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(getcode) && !TextUtils.isEmpty(password)){
                    if(UIUtils.isIDCard(idcard)){
                        if(UIUtils.isMobileNO(mobile) && UIUtils.isMobileNO(pid)){
                            if( !mobile.equals(pid)){
                                if(setPossword.getText().toString().equals(password)){
                                    register();
                                }else {
                                    ToastUtil.makeToast(RegisitActivity.this,"两次密码输入不一致");
                                }
                            }else {
                                ToastUtil.makeToast(RegisitActivity.this,"手机号和推荐手机号一致");
                            }
                        }else {
                            ToastUtil.makeToast(RegisitActivity.this,"手机号码格式错误");
                        }
                    }else {
                        ToastUtil.makeToast(RegisitActivity.this,"身份证号码格式错误");
                    }
                }else {
                    ToastUtil.makeToast(RegisitActivity.this,"请填写完所有资料");
                }

                break;
        }
    }

    //倒计时
    CountDownTimer countDownTimer = new CountDownTimer(60*1000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getCode.setText(millisUntilFinished/1000+"秒后重新发送");
            getCode.setEnabled(false);
            //getCode.setBackgroundResource(R.drawable.get_cold_grey);
        }

        @Override
        public void onFinish() {
            getCode.setText("重新发送验证码");
            getCode.setEnabled(true);
            //getCode.setBackgroundResource(R.drawable.get_cold_red);
        }
    };

    /**
     * 获取验证码
     */
    String phone;
    private void choosePassword(){
        MainApi.getInstance(this).duanxin(phone, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){

                }else BaseApi.showErrMsg(RegisitActivity.this,result);
            }
        });
    }

    String pid;
    String idcard;
    String name;
    String mobile;
    String password;
    String getcode;
    private void register(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        MainApi.getInstance(this).register(pid,idcard,name, mobile,password,getcode,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type ==  Constants.TYPE_SUCCESS){
                    ToastUtil.makeToast(RegisitActivity.this,"注册成功");
                }else{
                    BaseApi.showErrMsg(RegisitActivity.this,result);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result);
                        String message = jsonObject.getString("message");
                        ToastUtil.makeToast(RegisitActivity.this,""+message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
