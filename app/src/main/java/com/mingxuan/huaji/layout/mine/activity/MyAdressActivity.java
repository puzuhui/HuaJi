package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.MyAdressAdapter;
import com.mingxuan.huaji.layout.mine.bean.MyAdressModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class MyAdressActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.add_address)
    TextView addAddress;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private List<MyAdressModel.ResultBean> list;
    private MyAdressAdapter myAdressAdapter;
    private LoadingDialog loadingDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_address;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getmyaddress();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle(getString(R.string.my_address));
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");
        update_id = sharedPreferences.getString("create_id","");
        update_name = sharedPreferences.getString("create_name","");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        update_time = simpleDateFormat.format(new Date());

        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();
        addAddress.setOnClickListener(onClickListener);
        swipe.setOnRefreshListener(this);

        myAdressAdapter = new MyAdressAdapter(MyAdressActivity.this, list);
        recyclerview.setAdapter(myAdressAdapter);

        myAdressAdapter.setAdressOnClickListener(new MyAdressAdapter.AdressOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.checkbox:
                        //先把所有的都设置为0，在把点击的设置为1
                        id = list.get(position).getId();
                        setaddress();
                        break;
                    case R.id.compile:
                        Intent intent = new Intent(MyAdressActivity.this, AddAddressActivity.class);
                        intent.putExtra("index", 102);
                        intent.putExtra("id", list.get(position).getId());
                        intent.putExtra("address", list.get(position).getAddress());
                        intent.putExtra("consingee", list.get(position).getConsignee());
                        intent.putExtra("phone", list.get(position).getPhone());
                        intent.putExtra("default_flag", list.get(position).getDefault_flag());
                        intent.putExtra("seleadd_name", list.get(position).getSeleadd_name());
                        intent.putExtra("seleadd_address", list.get(position).getSelete_address());
                        startActivity(intent);
                        break;
                    case R.id.del:
                        delDialog(position);
                        break;
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        getmyaddress();
    }

    private void delDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认删除地址");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                id = list.get(position).getId();
                list.remove(position);
                del_flag = 1;
                xgaddress();
            }
        });

        builder.create();
        builder.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_address:
                    Intent intent = new Intent(MyAdressActivity.this, AddAddressActivity.class);
                    intent.putExtra("index", 101);
                    startActivity(intent);
                    break;
            }
        }
    };

    /**
     * 查询
     */
    private String create_id;
    private void getmyaddress() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).getmyaddressApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                swipe.setRefreshing(false);
                if (type == Constants.TYPE_SUCCESS) {
                    List<MyAdressModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(), result, MyAdressModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeen);

                    myAdressAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyAdressActivity.this, result);
            }
        });
    }

    /**
     * 将defau_flag设为0
     */
    private void setaddress() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).setaddressApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if (type == Constants.TYPE_SUCCESS) {
                    default_flag = 1;
                    xgaddress();
                } else BaseApi.showErrMsg(MyAdressActivity.this, result);
            }
        });
    }

    /**
     * 编辑(删除将del_flag设为0)
     */
    private String aconsignee;
    private String phone;
    private int default_flag;
    private String selete_address;
    private String seleadd_name;
    private String aaddress;
    private String update_id;
    private String update_name;
    private String update_time;
    private int del_flag;
    private int id;
    private void xgaddress() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).getxgaddressApi(id, aconsignee, phone, default_flag, selete_address, seleadd_name, aaddress,
                update_id, update_name, update_time,del_flag, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        loadingDialog.dismiss();
                        if (type == Constants.TYPE_SUCCESS) {
                            onRefresh();
                            myAdressAdapter.notifyDataSetChanged();
                        } else BaseApi.showErrMsg(MyAdressActivity.this, result);
                    }
                });
    }

    @Override
    public void onRefresh() {
        getmyaddress();
    }
}
