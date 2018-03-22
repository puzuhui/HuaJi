package com.mingxuan.huaji.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.two.model.LoginModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class LoginActivity extends Activity {
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.please_enter_proof_of_identity)
    EditText pleaseEnterProofOfIdentity;
    @BindView(R.id.please_enter_possword)
    EditText pleaseEnterPossword;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.forget_the_password)
    TextView forgetThePassword;
    @BindView(R.id.old_user)
    TextView oldUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.register,R.id.submit,R.id.forget_the_password,R.id.old_user})
    public void setOnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.register:
                intent = new Intent(LoginActivity.this,RegisitActivity.class);
                startActivity(intent);
                break;
            case R.id.submit:
                username = pleaseEnterProofOfIdentity.getText().toString();
                password = pleaseEnterPossword.getText().toString();
                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
                    login();
                }else {
                    ToastUtil.makeToast(this,"请填写账号和密码");
                }

                break;
            case R.id.forget_the_password:
                intent = new Intent(LoginActivity.this,ForgetThePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.old_user:
                intent = new Intent(LoginActivity.this,OldUserActivity.class);
                startActivity(intent);
                break;
        }
    }

    String username;
    String password;
    List<LoginModel.ResultBean> loginlist = new ArrayList<>();
    private void login(){
        submit.setText("登录中...");
        MainApi.getInstance(this).login(username, password, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<LoginModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,LoginModel.ResultBean.class);
                    loginlist.addAll(resultBeans);
                    Intent intent = new Intent(LoginActivity.this,HomePageViewPagerActivity.class);
                    startActivity(intent);
                    create_id = loginlist.get(0).getId();
                    create_name = loginlist.get(0).getName();
                    realName = loginlist.get(0).getRealName();
                    idCard = loginlist.get(0).getIdCard();
                    phone = loginlist.get(0).getPhone();
                    islogin = true;
                    saveSharedPreferences();
                    submit.setText(R.string.login);
                }else {
                    ToastUtil.makeToast(LoginActivity.this,"输入用户名或者密码错误");
                    submit.setText(R.string.login);
                }
            }
        });
    }

    String create_id;
    String create_name,realName,idCard,phone;
    boolean islogin = false;
    private void saveSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("create_id",create_id);
        editor.putString("create_name",create_name);
        editor.putString("realName",realName);
        editor.putString("idCard",idCard);
        editor.putString("phone",phone);
        editor.putBoolean("islogin",islogin);
        editor.apply();
    }
}
