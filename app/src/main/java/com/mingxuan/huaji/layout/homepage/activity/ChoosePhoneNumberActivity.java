package com.mingxuan.huaji.layout.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.homepage.adapter.ChoosePhoneAdapter;
import com.mingxuan.huaji.layout.homepage.bean.CardInfor;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
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

public class ChoosePhoneNumberActivity extends BaseActivity {
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
    List<CardInfor.ResultBean> list;
    String phonenumber;
    ChoosePhoneAdapter choosePhoneAdapter;
    LoadingDialog loadingDialog;

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_phone_number;
    }

    @Override
    protected void initView() {
        setToolbarTitle("选择电话号码");
        Bundle bundle = getIntent().getExtras();
        cardId = bundle.getInt("index");

        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();

        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(this,2);
        recyclerview.setLayoutManager(fullGridLayoutManager);
        int spanCount = 2;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        choosePhoneAdapter = new ChoosePhoneAdapter(list,ChoosePhoneNumberActivity.this);
        recyclerview.setAdapter(choosePhoneAdapter);
        choosePhoneAdapter.setOnItemClickListener(new ChoosePhoneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                phonenumber = list.get(position).getNumber();
            }
        });
    }

    @Override
    protected void initData() {
        getData();
    }

    @OnClick({R.id.confirm,R.id.huan,R.id.search_btn})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.search_btn:
                if(!TextUtils.isEmpty(search.getText())){
                    number = search.getText().toString();
                    getData();
                }else {
                    ToastUtil.makeToast(this,"请输入电话号码");
                }
                break;
            case R.id.huan:
                getData();
                break;
            case R.id.confirm:
                if(!TextUtils.isEmpty(phonenumber)){
                    updateData(phonenumber,1);
                }else {
                    ToastUtil.makeToast(this,"请选择手机号码！");
                }
                break;
        }
    }

    String number;
    int cardId;
    private void getData(){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).choosephoneNumbApi(cardId,number, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<CardInfor.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result, CardInfor.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    choosePhoneAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void updateData(String oldNumber, int i){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).updateNumberStateApi(oldNumber,i, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    Intent intent = new Intent();
                    intent.putExtra("result", phonenumber);
                    setResult(1001,intent);
                    finish();
                }else {
                    BaseApi.showErrMsg(ChoosePhoneNumberActivity.this,result);
                }
            }
        });
    }
}
