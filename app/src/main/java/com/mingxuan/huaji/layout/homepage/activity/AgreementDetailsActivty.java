package com.mingxuan.huaji.layout.homepage.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/6/12.
 * 公司：铭轩科技
 */

public class AgreementDetailsActivty extends BaseActivity {
    @BindView(R.id.iv_agreement)
    ImageView ivAgreement;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement_details;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("index");
        if(index == 1){
            setToolbarTitle("用户入网服务协议");
            Glide.with(this).load(R.mipmap.mexport).into(ivAgreement);
        }else {
            setToolbarTitle("服务协议");
            Glide.with(this).load(R.mipmap.mexport).into(ivAgreement);
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {}

}
