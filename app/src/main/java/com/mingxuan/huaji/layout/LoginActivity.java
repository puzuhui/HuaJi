package com.mingxuan.huaji.layout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.homepage.bean.LoginModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class LoginActivity extends BaseActivity {
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
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setToolbarTitle("登录");
        setSubtitle("注册");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean showSubtitle() {
        return true;
    }

    @OnClick({R.id.toolbar_subtitle,R.id.submit,R.id.forget_the_password,R.id.old_user})
    public void setOnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.toolbar_subtitle:
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
                    loginlist.clear();
                    loginlist.addAll(resultBeans);
                    Intent intent = new Intent(LoginActivity.this,HomePageViewPagerActivity.class);
                    startActivity(intent);
                    create_id = loginlist.get(0).getId();
                    realName = loginlist.get(0).getRealName();
                    idCard = loginlist.get(0).getIdCard();
                    phone = loginlist.get(0).getPhone();
                    pid =loginlist.get(0).getPId();
                    mobile = loginlist.get(0).getMobile();
                    islogin = true;
                    saveSharedPreferences();
                    submit.setText(R.string.login);
                    finish();
                }else {
                    ToastUtil.makeToast(LoginActivity.this,"输入用户名或者密码错误");
                    submit.setText(R.string.login);
                }
            }
        });
    }

    String create_id;
    String realName,idCard,phone,pid,mobile;
    boolean islogin = false;
    private void saveSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("create_id",create_id);
        editor.putString("realName",realName);
        editor.putString("idCard",idCard);
        editor.putString("phone",phone);
        editor.putString("pid",pid);
        editor.putString("mobile",mobile);
        editor.putBoolean("islogin",islogin);
        editor.apply();
    }


}
