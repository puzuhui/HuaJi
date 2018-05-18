package com.mingxuan.huaji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.news.activity.MaterialActivity;
import com.mingxuan.huaji.layout.news.activity.NotificationActivity;
import com.mingxuan.huaji.layout.news.adpter.NotificationAdapter;
import com.mingxuan.huaji.layout.news.bean.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class NewsFragment extends Fragment {
    @BindView(R.id.tv_notification)
    TextView tvNotification;
    @BindView(R.id.tv_logistics)
    TextView tvLogistics;
    @BindView(R.id.tv_material)
    TextView tvMaterial;
    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private View view;
    List<NotificationModel> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, null);
        unbinder = ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setMessage("关于补发分子佳节我公司名义开展超市名义来重庆滑稽换购离开家里空间");
            notificationModel.setTitile("第一天的标题啊第一天的标题啊第一天的标题啊第一天的标题啊第一天的标题啊第一天的标题啊");
            notificationModel.setTime("2018-12-17");
            list.add(notificationModel);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerview.setLayoutManager(linearLayoutManager);


        NotificationAdapter notificationAdapter = new NotificationAdapter(getActivity(),list,2);
        recyclerview.setAdapter(notificationAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_notification, R.id.tv_logistics, R.id.tv_material, R.id.tv_introduce})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_notification:
                intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_logistics:
                intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_material:
                intent = new Intent(getActivity(), MaterialActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_introduce:
                intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
