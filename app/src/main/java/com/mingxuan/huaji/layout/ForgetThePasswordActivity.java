package com.mingxuan.huaji.layout;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.base.Constants;
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

public class ForgetThePasswordActivity extends BaseActivity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
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

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gorgetthe_password);
//        ButterKnife.bind(this);
//
//        initView();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gorgetthe_password;
    }

    @Override
    protected void initView() {
        pleaseEnterPhoneNumber.addTextChangedListener(new NewEditText(pleaseEnterPhoneNumber));
        pleaseEnterVerificationCode.addTextChangedListener(new NewEditText(pleaseEnterVerificationCode));
        setPossword.addTextChangedListener(new NewEditText(setPossword));
        pleaseEnterPosswordTwo.addTextChangedListener(new NewEditText(pleaseEnterPosswordTwo));
    }

    @OnClick({R.id.back_btn,R.id.get_code,R.id.submit})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.get_code:
                mobile = pleaseEnterPhoneNumber.getText().toString();
                if(!TextUtils.isEmpty(mobile)){
                    choosePassword();
                    countDownTimer.start();
                }else {
                    ToastUtil.makeToast(this,"请填写手机号码");
                }
                break;
            case R.id.submit:
                mobile = pleaseEnterPhoneNumber.getText().toString();
                getcode = pleaseEnterVerificationCode.getText().toString();
                password = setPossword.getText().toString();
                newPassword = pleaseEnterPosswordTwo.getText().toString();
                if(!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(getcode)&& !TextUtils.isEmpty(password)&& !TextUtils.isEmpty(newPassword)){
                    if(UIUtils.isMobileNO(mobile)){
                        if(password.equals(newPassword)){
                            updatePassword();
                            finish();
                        }else {
                            ToastUtil.makeToast(ForgetThePasswordActivity.this,"两次密码不一致");
                        }
                    }else {
                        ToastUtil.makeToast(ForgetThePasswordActivity.this,"手机号码格式错误");
                    }
                }else {
                    ToastUtil.makeToast(ForgetThePasswordActivity.this,"请填写所有资料");
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
    private void choosePassword(){
        MainApi.getInstance(this).duanxin(mobile, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){

                }else BaseApi.showErrMsg(ForgetThePasswordActivity.this,result);
            }
        });
    }

    String newPassword;
    String password;
    String getcode;
    private void updatePassword(){
        MainApi.getInstance(this).updatePassword(mobile,newPassword,getcode, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){
                    ToastUtil.makeToast(ForgetThePasswordActivity.this,"修改成功");
                }else {
                    BaseApi.showErrMsg(ForgetThePasswordActivity.this,result);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result);
                        String message = jsonObject.getString("message");
                        ToastUtil.makeToast(ForgetThePasswordActivity.this,""+message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
