package com.mingxuan.huaji.layout.two.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/2/28.
 * 公司：铭轩科技
 */

public class ChoosePhoneNumberActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.search_btn)
    TextView searchBtn;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.huan)
    Button huan;
    @BindView(R.id.confirm)
    Button confirm;
    List<String> list;
    String phonenumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_phone_number);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        list = new ArrayList<>();

        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(this,2);
        recyclerview.setLayoutManager(fullGridLayoutManager);
        int spanCount = 2;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        for (int i = 0; i < 9; i++) {
            list.add("15823903220");
        }

        PhoneCardAdapter phoneCardAdapter = new PhoneCardAdapter(list,ChoosePhoneNumberActivity.this);
        recyclerview.setAdapter(phoneCardAdapter);
        phoneCardAdapter.setOnItemClickListener(new PhoneCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                phonenumber = list.get(position);
            }
        });
    }

    @OnClick({R.id.back_btn,R.id.confirm})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.confirm:
                if(!TextUtils.isEmpty(phonenumber)){
                    Intent intent = new Intent();
                    intent.putExtra("result", phonenumber);
                    setResult(1001,intent);
                    finish();
                }else {
                    ToastUtil.makeToast(this,"请选择手机号码！");
                }
                break;
        }
    }
}
