package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.bean.InformationModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/15.
 * 公司：铭轩科技
 */

public class BindMobileActivity extends BaseActivity {
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.binding)
    TextView binding;
    List<InformationModel.ResultBean> list;
    LoadingDialog loadingDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bindmobile;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getInformation();
    }

    @Override
    protected void initView() {
        setToolbarTitle("绑定手机");
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI,Context.MODE_PRIVATE);
        id = sharedPreferences.getString("create_id","");

        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();
//        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
//        String phonenumber =sharedPreferences.getString("phone","");
//        if(!TextUtils.isEmpty(phonenumber)){
//            linear.setVisibility(View.VISIBLE);
//            binding.setVisibility(View.GONE);
//            phone.setText(phonenumber.replace(phonenumber.substring(0,phonenumber.length()-3),"*** **** *"));
//        }else {
//            linear.setVisibility(View.GONE);
//            binding.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    protected void initData() {
        getInformation();
    }

    @OnClick({R.id.linear,R.id.binding})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.linear:
                intent = new Intent(BindMobileActivity.this,MoblieActivity.class);
                intent.putExtra("phone",phonenumber);
                startActivity(intent);
                break;
            case R.id.binding:
                intent = new Intent(BindMobileActivity.this,MoblieActivity.class);
                intent.putExtra("phone",phonenumber);
                startActivity(intent);
                break;
        }
    }

    String id,phonenumber;
    private void getInformation(){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        FourApi.getInstance(this).myInformationApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<InformationModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,InformationModel.ResultBean.class);
                    list.addAll(resultBeans);

                    phonenumber = list.get(0).getPhone();
                    if(!TextUtils.isEmpty(phonenumber)){
                        linear.setVisibility(View.VISIBLE);
                        binding.setVisibility(View.GONE);
                        phone.setText(phonenumber.replace(phonenumber.substring(0,phonenumber.length()-3),"*** **** *"));
                    }else {
                        linear.setVisibility(View.GONE);
                        binding.setVisibility(View.VISIBLE);
                    }
                }else BaseApi.showErrMsg(BindMobileActivity.this,result);
            }
        });
    }
}
