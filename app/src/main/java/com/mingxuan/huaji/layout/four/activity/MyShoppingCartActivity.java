package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
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
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.adapter.MyShoppingCarAdapter;
import com.mingxuan.huaji.layout.four.model.MyShoppingCarModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class MyShoppingCartActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.head_shop)
    TextView headShop;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        ButterKnife.bind(this);

        initView();
        getShoppingCar();
    }

    private void initView() {
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
    }

    @OnClick(R.id.back_btn)
    public void setOnClick(View view){
        finish();
    }

    MyShoppingCarAdapter myShoppingCarAdapter;
    String create_id ="56c9f9556b2e46428bb53f85bbc1b234";
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

    int id;
    int del_flag = 1;
    private String update_id = "1";
    private String update_name = "sin";
    private String update_time = "1995-12-17";
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
