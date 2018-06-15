package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
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
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.ServiceUserAdapter;
import com.mingxuan.huaji.layout.mine.bean.ServiceUserModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/22.
 * 公司：铭轩科技
 */

public class ServiceUserActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_person_numb)
    TextView tvPersonNumb;
    List<ServiceUserModel.ResultBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_user);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        pid = sharedPreferences.getString("create_id", "");
        initView();
        showDate();
    }

    private void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        serviceUserAdapter = new ServiceUserAdapter(ServiceUserActivity.this,list);
        recyclerview.setAdapter(serviceUserAdapter);
    }

    @OnClick(R.id.back_btn)
    public void OnClick(View view){
        finish();
    }

    ServiceUserAdapter serviceUserAdapter;
    String pid;
    public void showDate(){
        FourApi.getInstance(this).findServiceNumb(pid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<ServiceUserModel.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),result,ServiceUserModel.ResultBean.class);
                    list.addAll(resultBean);

                    serviceUserAdapter.notifyDataSetChanged();
                    tvPersonNumb.setText("共"+list.size()+"人");
                }else BaseApi.showErrMsg(ServiceUserActivity.this,result);
            }
        });
    }
}
