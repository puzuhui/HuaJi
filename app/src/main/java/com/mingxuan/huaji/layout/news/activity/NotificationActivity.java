package com.mingxuan.huaji.layout.news.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.news.adpter.NotificationAdapter;
import com.mingxuan.huaji.layout.news.bean.NotificationModel;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.TwoApi;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/5/16.
 * 公司：铭轩科技
 */

public class NotificationActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<NotificationModel.ResultBean> list;
    NotificationAdapter notificationAdapter;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        setToolbarTitle("消息公告");
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);

        receiveId = sharedPreferences.getString("create_id","");
        list = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotificationActivity.this);
        recyclerview.setLayoutManager(linearLayoutManager);

        notificationAdapter = new NotificationAdapter(NotificationActivity.this,list,1);
        recyclerview.setAdapter(notificationAdapter);

        notificationAdapter.setOnClickListenter(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(NotificationActivity.this, NotificationDetailsActivity.class);
                intent.putExtra("details",list.get(i).getContent());
                startActivity(intent);
                //当通知状态为1时发送修改状态
                if(list.get(i).getState().equals("1")){
                    postState(list.get(i).getId(),"2");
                    notificationAdapter.notifyItemChanged(i);
                }
            }
        });

    }

    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    String receiveId;
    private void getData(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        TwoApi.getInstance(this).notificationApi(receiveId, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<NotificationModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,NotificationModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    notificationAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    //修改状态
    private void postState(String id,String state){
        TwoApi.getInstance(this).xgnotification(id,state, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){

                }else BaseApi.showErrMsg(NotificationActivity.this,result);
            }
        });
    }
}
