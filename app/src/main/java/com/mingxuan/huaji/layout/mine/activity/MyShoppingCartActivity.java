package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.MyShoppingCarAdapter;
import com.mingxuan.huaji.layout.mine.bean.MyShoppingCarModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.PayUtils;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class MyShoppingCartActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.check_all)
    CheckBox checkAll;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.settle_accounts)
    TextView settleAccounts;
    private List<MyShoppingCarModel.ResultBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_car;
    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");
        update_id = sharedPreferences.getString("create_id","");
        update_name = sharedPreferences.getString("realName","");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        update_time = simpleDateFormat.format(new Date());

        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManger);
        checkAll.setChecked(false);

        myShoppingCarAdapter = new MyShoppingCarAdapter(this,list,checkAll,money);
        recyclerview.setAdapter(myShoppingCarAdapter);
        myShoppingCarAdapter.setOnItemClickListener(new MyShoppingCarAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                switch (view.getId()){
                    case R.id.del_btn:
                        id = list.get(position).getId();
                        list.remove(position);
                        delProductsCar();
                        break;
                }
            }
        });

        setToolbarTitle("我的购物车");
    }

    @Override
    protected void initData() {
        getShoppingCar();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @OnClick({R.id.settle_accounts})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.settle_accounts:
                if(list.size()!=0 && !money.getText().toString().equals("0.0")){
                   // showPayPopupWindow();
                    PayUtils.getInstance(this).showPayPopupWindow("华记测试商品","1",UIUtils.getIPAddress(this));
                }else {
                    ToastUtil.makeToast(this,"请选择商品");
                }
                break;
        }
    }

    MyShoppingCarAdapter myShoppingCarAdapter;
    String create_id;
    private void getShoppingCar(){
        FourApi.getInstance(this).getproductscartApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    List<MyShoppingCarModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,MyShoppingCarModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    myShoppingCarAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyShoppingCartActivity.this, result);
            }
        });
    }

    //删除购物车
    int id;
    int del_flag = 1;
    private String update_id;
    private String update_name;
    private String update_time;
    private void delProductsCar(){
        FourApi.getInstance(this).delproducts_cartApi(id, update_id, update_name, update_time,del_flag,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {

                    myShoppingCarAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyShoppingCartActivity.this, result);
            }
        });
    }

}
