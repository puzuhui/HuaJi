package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.mingxuan.huaji.layout.mine.adapter.MyBankCardAdapter;
import com.mingxuan.huaji.layout.mine.bean.MyBankCardModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class MyBankCardActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.addcard)
    TextView addcard;
    List<MyBankCardModel.ResultBean> list;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_card;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        addBankCard();
    }

    @Override
    protected void initView() {
        setToolbarTitle(getString(R.string.bank_card));
        SharedPreferences sharedPreferences =getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        createi_id = sharedPreferences.getString("create_id","");

        list = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);

        myBankCardAdapter = new MyBankCardAdapter(MyBankCardActivity.this, list);
        recyclerview.setAdapter(myBankCardAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        myBankCardAdapter.setOnItemClickListener(new MyBankCardAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(int i, View view) {
                Intent intent = new Intent(MyBankCardActivity.this,BankCardDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bank", list.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        addcard.setOnClickListener(onClickListener);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {
        addBankCard();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
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
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        FourApi.getInstance(this).searchbankcardApi(createi_id, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        loadingDialog.dismiss();
                        if(type == Constants.TYPE_SUCCESS){
                            List<MyBankCardModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,MyBankCardModel.ResultBean.class);
                            list.clear();
                            list.addAll(resultBeans);

                            myBankCardAdapter.notifyDataSetChanged();
                        }else BaseApi.showErrMsg(MyBankCardActivity.this,result);
                    }
                });
    }
}
