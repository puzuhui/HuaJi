package com.mingxuan.huaji.layout.homepage.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.homepage.adapter.AddressMangeAdapter;
import com.mingxuan.huaji.layout.mine.activity.MyAdressActivity;
import com.mingxuan.huaji.layout.mine.bean.MyAdressModel;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/5/29.
 * 公司：铭轩科技
 */

public class AddressManageActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_address_manage)
    TextView tvAddressManage;
    List<MyAdressModel.ResultBean> list;
    AddressMangeAdapter addressMangeAdapter;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_manage;
    }

    @Override
    protected void initView() {
        setToolbarTitle("选择收货地址");
        loadingDialog = new LoadingDialog(this);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");

        list = new ArrayList<>();

        addressMangeAdapter = new AddressMangeAdapter(this,list);
        recyclerview.setAdapter(addressMangeAdapter);

        addressMangeAdapter.setOnItemClickListener(new AddressMangeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("name",list.get(position).getConsignee());
                intent.putExtra("phone",list.get(position).getPhone());
                intent.putExtra("address",list.get(position).getAddress());
                setResult(1004,intent);
                finish();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        getmyaddress();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @OnClick({ R.id.tv_address_manage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address_manage:
                Intent intent = new Intent(AddressManageActivity.this, MyAdressActivity.class);
                startActivity(intent);
                break;
        }
    }

    private String create_id;
    private void getmyaddress() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).getmyaddressApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if (type == Constants.TYPE_SUCCESS) {
                    List<MyAdressModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(), result, MyAdressModel.ResultBean.class);
                    list.addAll(resultBeen);

                    addressMangeAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(AddressManageActivity.this, result);
            }
        });
    }
}
