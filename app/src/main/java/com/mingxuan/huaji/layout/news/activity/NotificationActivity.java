package com.mingxuan.huaji.layout.news.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.layout.news.adpter.NotificationAdapter;
import com.mingxuan.huaji.layout.news.bean.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/5/16.
 * 公司：铭轩科技
 */

public class NotificationActivity extends BaseActivity {

    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<NotificationModel> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setTitile("第一天的标题啊第一天的标题啊第一天的标题啊第一天的标题啊第一天的标题啊第一天的标题啊啊第一天的标题啊第一天的标题啊");
            notificationModel.setMessage("关于补发分子佳节我公司名义开展超市名义来重庆滑稽换购离开家里空间关于补发分子佳节我公司名义开展超市名义来重庆滑稽换购离开家里空间关于补发分子佳节我公司名义开展超市名义来重庆滑稽换购离开家里空间关于补发分子佳节我公司名义开展超市名义来重庆滑稽换购离开家里空间");
            notificationModel.setTime("2018-12-17");
            list.add(notificationModel);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotificationActivity.this);
        recyclerview.setLayoutManager(linearLayoutManager);

        NotificationAdapter notificationAdapter = new NotificationAdapter(NotificationActivity.this,list,1);
        recyclerview.setAdapter(notificationAdapter);
    }

    @OnClick(R.id.back_btn)
    public void onViewClicked() {
        finish();
    }
}
