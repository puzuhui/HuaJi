package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.IntergralAdapter;
import com.mingxuan.huaji.layout.mine.bean.IntergralMolder;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class MyIntergralActivity extends BaseActivity {
    @BindView(R.id.tv_numb)
    TextView tv_numb;
    @BindView(R.id.tv_withdrawals)
    TextView tv_withdrawals;
    @BindView(R.id.tv_consumption_integral)//消费积分
    TextView tv_consumption_integral;
    @BindView(R.id.tv_commission_integral)//佣金积分
    TextView tv_commission_integral;
    @BindView(R.id.tv_detailed)
    TextView tv_detailed;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<IntergralMolder.ResultBean> list;
    IntergralAdapter intergralAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_intergral;
    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        login_id =sharedPreferences.getString("create_id","");

        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        intergralAdapter = new IntergralAdapter(MyIntergralActivity.this,list);
        recyclerview.setAdapter(intergralAdapter);

        setToolbarTitle("我的积分");
    }

    @Override
    protected void initData() {
        getIntegral();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getIntegral();
    }

    @OnClick({R.id.tv_withdrawals,R.id.tv_consumption_integral,R.id.tv_commission_integral})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_withdrawals:
                Intent intent = new Intent(MyIntergralActivity.this, TiXianActivity.class);
                intent.putExtra("intergral",tv_numb.getText().toString() );
                startActivity(intent);
                break;
            case R.id.tv_consumption_integral:
                tv_consumption_integral.setTextColor(getResources().getColor(R.color.white));
                tv_consumption_integral.setBackgroundResource(R.color.redDark);
                tv_commission_integral.setTextColor(getResources().getColor(R.color.black));
                tv_commission_integral.setBackgroundResource(R.color.white);
                tv_detailed.setText("消费积分明细");
                break;
            case R.id.tv_commission_integral:
                tv_commission_integral.setTextColor(getResources().getColor(R.color.white));
                tv_commission_integral.setBackgroundResource(R.color.redDark);
                tv_consumption_integral.setTextColor(getResources().getColor(R.color.black));
                tv_consumption_integral.setBackgroundResource(R.color.white);
                tv_detailed.setText("可提现积分明细");
                break;
        }
    }


    String login_id;
    private void getIntegral() {
        FourApi.getInstance(this).myintegralApi(login_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    List<IntergralMolder.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(), result, IntergralMolder.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    tv_numb.setText(list.get(0).getMoney());
                    tv_commission_integral.setText("佣金积分("+list.get(0).getMoney()+"）");

                    intergralAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyIntergralActivity.this, result);
            }
        });
    }


}

