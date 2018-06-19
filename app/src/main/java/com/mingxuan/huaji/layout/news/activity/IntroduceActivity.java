package com.mingxuan.huaji.layout.news.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.news.adpter.IntroduceAdapter;
import com.mingxuan.huaji.layout.news.bean.IntroduceModel;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.TwoApi;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroduceActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<IntroduceModel.ResultBean> list;
    IntroduceAdapter introduceAdapter;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_introduce;
    }

    @Override
    protected void initView() {
        setToolbarTitle("资费介绍");
        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        introduceAdapter = new IntroduceAdapter(this,list);
        recyclerview.setAdapter(introduceAdapter);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        TwoApi.getInstance(this).introduceApi( new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<IntroduceModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result, IntroduceModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    introduceAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(IntroduceActivity.this,result);
            }
        });
    }
}
