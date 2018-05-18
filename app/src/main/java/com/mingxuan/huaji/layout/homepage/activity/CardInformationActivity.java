package com.mingxuan.huaji.layout.homepage.activity;

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

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.LoginActivity;
import com.mingxuan.huaji.layout.homepage.adapter.CardInformationAdapter;
import com.mingxuan.huaji.layout.homepage.bean.CardInfoModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/16.
 * 公司：铭轩科技
 */

public class CardInformationActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<CardInfoModel.ResultBean> list;
    Boolean islogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_information);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        islogin = sharedPreferences.getBoolean("islogin",false);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
//        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;//禁止滑动
            }
        };
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(new SpacesItemDecoration(10));

        CardInfoModel.ResultBean resultBean = new CardInfoModel.ResultBean();
        resultBean.setName("乐享家79元套餐");
        resultBean.setConetnt("月费79元，国内流量1.3GB，本地流量2.7GB，可转换分钟数800分钟，可转换短信300条");
        list.add(resultBean);
        CardInfoModel.ResultBean resultBean1 = new CardInfoModel.ResultBean();
        resultBean1.setName("乐享家99元套餐");
        resultBean1.setConetnt("月费99元，国内流量1.6GB，本地流量2.9GB，可转换分钟数800分钟，可转换短信300条");
        list.add(resultBean1);
        CardInfoModel.ResultBean resultBean2 = new CardInfoModel.ResultBean();
        resultBean2.setName("乐享家129元套餐");
        resultBean2.setConetnt("月费129元，国内流量2GB，本地流量3GB，可转换分钟数800分钟，可转换短信500条");
        list.add(resultBean2);
        CardInfoModel.ResultBean resultBean3 = new CardInfoModel.ResultBean();
        resultBean3.setName("乐享家169元套餐");
        resultBean3.setConetnt("月费169元，国内流量3GB，本地流量3GB，可转换分钟数1000分钟，可转换短信500条");
        list.add(resultBean3);
        CardInfoModel.ResultBean resultBean4 = new CardInfoModel.ResultBean();
        resultBean4.setName("乐享家199元套餐");
        resultBean4.setConetnt("月费199元，国内流量4GB，本地流量3GB，可转换分钟数2000分钟，可转换短信500条");
        list.add(resultBean4);

        cardInformationAdapter = new CardInformationAdapter(this,list);
        recyclerview.setAdapter(cardInformationAdapter);
        cardInformationAdapter.setOnClickListener(new CardInformationAdapter.MyOnClickListener() {
            @Override
            public void onClick(View view, int i) {
                Intent intent;
                if(islogin){
                    intent = new Intent(CardInformationActivity.this,ChoosePhoneCardActivity.class);
                    intent.putExtra("index",1);
                    startActivity(intent);
                }else {
                    intent = new Intent(CardInformationActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @OnClick(R.id.back_btn)
    public void OnClick(View view){
        finish();
    }

    CardInformationAdapter cardInformationAdapter;
//    private void getData(){
////        MainApi.getInstance(this).phoneCardInfoApi(1, new GetResultCallBack() {
////            @Override
////            public void getResult(String result, int type) {
////                if(type == Constants.TYPE_SUCCESS){
////                    List<CardInfoModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result, CardInfoModel.ResultBean.class);
////                    list.clear();
////                    list.addAll(resultBeans);
////
////                    cardInformationAdapter.notifyDataSetChanged();
////                }
////            }
////        });
//    }
}
