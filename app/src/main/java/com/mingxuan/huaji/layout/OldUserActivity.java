package com.mingxuan.huaji.layout;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.NewEditText;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/6.
 * 公司：铭轩科技
 */

public class OldUserActivity extends BaseActivity {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.login_numb)
    EditText loginNumb;
    @BindView(R.id.sure)
    TextView sure;
    @BindView(R.id.no_numb)
    TextView noNumb;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.please_enter_id_number)
    EditText pleaseEnterIdNumber;
    @BindView(R.id.please_enter_phone_number)
    EditText pleaseEnterPhoneNumber;
    @BindView(R.id.please_enter_verification_code)
    EditText pleaseEnterVerificationCode;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.old)
    LinearLayout old;
    @BindView(R.id.set_possword)
    EditText setPossword;
    @BindView(R.id.please_enter_possword_two)
    EditText pleaseEnterPosswordTwo;
    @BindView(R.id.submit)
    TextView submit;
    LoadingDialog loadingDialog;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_old_user);
//        ButterKnife.bind(this);
//
//        initView();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_old_user;
    }

    @Override
    protected void initView() {
        loadingDialog = new LoadingDialog(this);
        loginNumb.addTextChangedListener(new NewEditText(loginNumb));
        pleaseEnterIdNumber.addTextChangedListener(new NewEditText(pleaseEnterIdNumber));
        pleaseEnterPhoneNumber.addTextChangedListener(new NewEditText(pleaseEnterPhoneNumber));
        pleaseEnterVerificationCode.addTextChangedListener(new NewEditText(pleaseEnterVerificationCode));
        setPossword.addTextChangedListener(new NewEditText(setPossword));
        pleaseEnterPosswordTwo.addTextChangedListener(new NewEditText(pleaseEnterPosswordTwo));
    }

    @OnClick({R.id.login,R.id.sure,R.id.get_code,R.id.submit})
    public void OnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.login:
                intent = new Intent(OldUserActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.sure:
                phone = loginNumb.getText().toString();
                find();
                break;
            case R.id.get_code:
                phone = pleaseEnterPhoneNumber.getText().toString();
                if(!TextUtils.isEmpty(phone)){
                    if(UIUtils.isMobileNO(phone)){
                        countDownTimer.start();
                        choosePassword();
                    }else {
                        ToastUtil.makeToast(OldUserActivity.this,"手机号码格式错误");
                    }
                }else {
                    ToastUtil.makeToast(OldUserActivity.this,"请输入手机号码");
                }
                break;
            case R.id.submit:
                oldcard = loginNumb.getText().toString();
                idcard = pleaseEnterIdNumber.getText().toString();
                mobile = pleaseEnterPhoneNumber.getText().toString();
                getcode = pleaseEnterVerificationCode.getText().toString();
                password = setPossword.getText().toString();
                if(!TextUtils.isEmpty(idcard) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(getcode)&& !TextUtils.isEmpty(password)&& !TextUtils.isEmpty(pleaseEnterPosswordTwo.getText())){
                    if(UIUtils.isIDCard(idcard)){
                        if(UIUtils.isMobileNO(mobile)){
                            if(password.equals(pleaseEnterPosswordTwo.getText().toString())){
                                olduser();
                            }else {
                                ToastUtil.makeToast(OldUserActivity.this,"两次输入密码不一致");
                            }
                        }else {
                            ToastUtil.makeToast(OldUserActivity.this,"手机号码格式错误");
                        }
                    }else {
                        ToastUtil.makeToast(OldUserActivity.this,"身份证格式错误");
                    }
                }else {
                    ToastUtil.makeToast(OldUserActivity.this,"请输入所有选项");
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

    //短信验证码
    private void choosePassword(){
        MainApi.getInstance(this).duanxin(phone, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){

                }else BaseApi.showErrMsg(OldUserActivity.this,result);
            }
        });
    }

    //查找老用户
    String phone;
    private void find(){
        loadingDialog.setLoadingContent("查找中...");
        loadingDialog.show();
        MainApi.getInstance(this).findolduser(phone, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type ==  Constants.TYPE_SUCCESS){
                    old.setVisibility(View.VISIBLE);
                    noNumb.setVisibility(View.GONE);
                    loginNumb.setEnabled(false);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String message = jsonObject.getString("message");
                        name.setText(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    noNumb.setVisibility(View.VISIBLE);
                    old.setVisibility(View.GONE);
                    BaseApi.showErrMsg(OldUserActivity.this,result);
                }
            }
        });
    }

    String oldcard;
    String idcard;
    String mobile;
    String password;
    String getcode;
    private void olduser(){
        MainApi.getInstance(this).olduser(oldcard,idcard,mobile,password,getcode, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){
                    finish();
                }else{
                    BaseApi.showErrMsg(OldUserActivity.this,result);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result);
                        String message = jsonObject.getString("message");
                        ToastUtil.makeToast(OldUserActivity.this,""+message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
