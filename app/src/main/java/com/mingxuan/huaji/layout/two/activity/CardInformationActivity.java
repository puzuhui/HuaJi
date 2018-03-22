package com.mingxuan.huaji.layout.two.activity;

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
import com.mingxuan.huaji.layout.two.adapter.CardInformationAdapter;
import com.mingxuan.huaji.layout.two.model.CardInfor;
import com.mingxuan.huaji.utils.FullyLinearLayoutManager;
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
    List<CardInfor> list;
    Boolean islogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_information);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
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

        for(int i= 0;i< 7 ;i++){
            CardInfor cardInfor = new CardInfor();
            cardInfor.setName("乐享家"+(79+(2*i)*10));
            cardInfor.setContent("月费"+(79+(2*i)*10)+"元，国内流量3.3g");
            list.add(cardInfor);
        }

        CardInformationAdapter cardInformationAdapter = new CardInformationAdapter(this,list);
        recyclerview.setAdapter(cardInformationAdapter);
        cardInformationAdapter.setOnClickListener(new CardInformationAdapter.MyOnClickListener() {
            @Override
            public void onClick(View view, int i) {
                Intent intent;
                if(islogin){
                    intent = new Intent(CardInformationActivity.this,ChoosePhoneCardActivity.class);
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
}
