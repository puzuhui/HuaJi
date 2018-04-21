package com.mingxuan.huaji.layout.four.activity;

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
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.adapter.MyBankCardAdapter;
import com.mingxuan.huaji.layout.four.model.MyBankCardModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class MyBankCardActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.addcard)
    TextView addcard;
    List<MyBankCardModel.ResultBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences =getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        createi_id = sharedPreferences.getString("create_id","");
        setListener();
        initView();
        addBankCard();
    }

    private void initView() {
        list = new ArrayList<>();

        myBankCardAdapter = new MyBankCardAdapter(MyBankCardActivity.this, list);
        recyclerview.setAdapter(myBankCardAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
    }

    private void setListener() {
        addcard.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.addcard:
                    intent = new Intent(MyBankCardActivity.this,AddBankCardActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    /**
     * 查询银行卡
     */
    String createi_id;
    MyBankCardAdapter myBankCardAdapter;
    private void addBankCard(){
        FourApi.getInstance(this).searchbankcardApi(createi_id, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            List<MyBankCardModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,MyBankCardModel.ResultBean.class);
                            list.addAll(resultBeans);

                            myBankCardAdapter.notifyDataSetChanged();
                        }else BaseApi.showErrMsg(MyBankCardActivity.this,result);
                    }
                });
    }
}
