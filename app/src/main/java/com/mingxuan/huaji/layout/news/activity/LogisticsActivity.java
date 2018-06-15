package com.mingxuan.huaji.layout.news.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.news.adpter.LogisticsAdapter;
import com.mingxuan.huaji.layout.news.bean.LogisticsModel;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.TwoApi;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogisticsActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<LogisticsModel.ResultBean> list;
    LogisticsAdapter logisticsAdapter;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void initView() {
        setToolbarTitle("号卡物流");
        loadingDialog = new LoadingDialog(this);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        createId = sharedPreferences.getString("create_id","");
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        logisticsAdapter = new LogisticsAdapter(this,list);
        recyclerview.setAdapter(logisticsAdapter);

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {
        getData();
    }

    String createId;
    private void getData(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        TwoApi.getInstance(this).logisticsnoApi(createId, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    ArrayList<LogisticsModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(), result, LogisticsModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    logisticsAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(LogisticsActivity.this,result);
            }
        });
    }

    @OnClick(R.id.back_btn)
    public void onViewClicked() {
        finish();
    }
}
