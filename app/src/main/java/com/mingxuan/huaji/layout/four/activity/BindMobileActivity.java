package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.model.InformationModel;
import com.mingxuan.huaji.utils.Constants;
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

public class BindMobileActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.binding)
    TextView binding;
    List<InformationModel.ResultBean> list;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindmobile);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("huaji",Context.MODE_PRIVATE);
        id = sharedPreferences.getString("create_id","");
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getInformation();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();

        getInformation();
//        SharedPreferences sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
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

    @OnClick({R.id.back_btn,R.id.linear,R.id.binding})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
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
