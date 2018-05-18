package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.NewEditText;
import com.mingxuan.huaji.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/2.
 * 公司：铭轩科技
 */

public class InsertPasswordActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.password)
    EditText et_password;
    @BindView(R.id.password_new)
    EditText et_passwordNew;
    @BindView(R.id.password_confirm)
    EditText et_passwordConfirm;
    @BindView(R.id.submit)
    Button submit;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertpassword);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        cid = sharedPreferences.getString("create_id","");

        Bundle bundle =getIntent().getExtras();
        index = bundle.getInt("index");

        initView();
    }

    private void initView() {
        et_password.addTextChangedListener(new NewEditText(et_password));
        et_passwordNew.addTextChangedListener(new NewEditText(et_passwordNew));
        et_passwordConfirm.addTextChangedListener(new NewEditText(et_passwordConfirm));
    }

    @OnClick({R.id.back_btn,R.id.submit})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.submit:
                if(!TextUtils.isEmpty(et_password.getText())){
                    if(!TextUtils.isEmpty(et_passwordNew.getText()) && et_passwordNew.getText().length() > 7){
                        if(!TextUtils.isEmpty(et_passwordConfirm.getText())){
                            if(et_passwordConfirm.getText().toString().equals(et_passwordNew.getText().toString())){
                                if(index == 1){
                                    password = et_passwordNew.getText().toString();
                                }else {
                                    two_password = et_passwordNew.getText().toString();
                                }
                                choosePassword();
                                finish();
                            }else {
                                ToastUtil.makeToast(this,"新密码和确认密码必须一致");
                            }
                        }else {
                            ToastUtil.makeToast(this,"确认密码不能为空");
                        }
                    }else {
                        ToastUtil.makeToast(this,"新密码不能为空并且大于等于8位");
                    }
                }else {
                    ToastUtil.makeToast(this,"原密码不能为空");
                }
                break;
        }
    }

    String cid;
    String password;
    String two_password;
    private void choosePassword(){
        FourApi.getInstance(this).changePasswordApi(cid, password,two_password, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){
                    ToastUtil.makeToast(InsertPasswordActivity.this,"修改密码成功");
                }else
                    BaseApi.showErrMsg(InsertPasswordActivity.this,result);
            }
        });
    }
}
