package com.mingxuan.huaji.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.LoginActivity;
import com.mingxuan.huaji.layout.news.activity.IntroduceActivity;
import com.mingxuan.huaji.layout.news.activity.LogisticsActivity;
import com.mingxuan.huaji.layout.news.activity.MaterialActivity;
import com.mingxuan.huaji.layout.news.activity.NotificationActivity;
import com.mingxuan.huaji.layout.news.activity.NotificationDetailsActivity;
import com.mingxuan.huaji.layout.news.adpter.NotificationAdapter;
import com.mingxuan.huaji.layout.news.bean.NotificationModel;
import com.mingxuan.huaji.network.api.TwoApi;
import com.mingxuan.huaji.utils.GsonUtil;

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
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.tv_logistics)
    TextView tvLogistics;
    @BindView(R.id.tv_material)
    TextView tvMaterial;
    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_numb)
    TextView tvNumb;
    Unbinder unbinder;
    private View view;
    List<NotificationModel.ResultBean> list;
    NotificationAdapter notificationAdapter;
    boolean islogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, null);
        unbinder = ButterKnife.bind(this, view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        receiveId = sharedPreferences.getString("create_id","");
        islogin = sharedPreferences.getBoolean("islogin", false);

        initView();
        return view;
    }

    private void initView() {
        list = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerview.setLayoutManager(linearLayoutManager);

        notificationAdapter = new NotificationAdapter(getActivity(),list,2);
        recyclerview.setAdapter(notificationAdapter);

        notificationAdapter.setOnClickListenter(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(getActivity(), NotificationDetailsActivity.class);
                intent.putExtra("details",list.get(i).getContent());
                startActivity(intent);
            }
        });

        getData();
    }

    @OnClick({R.id.linear, R.id.tv_logistics, R.id.tv_material, R.id.tv_introduce})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.linear:
                if(islogin){
                    intent = new Intent(getActivity(), NotificationActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_logistics:
                if(islogin){
                    intent = new Intent(getActivity(), LogisticsActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_material:
                if(islogin){
                    intent = new Intent(getActivity(), MaterialActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_introduce:
                if(islogin){
                    intent = new Intent(getActivity(), IntroduceActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    String receiveId;
    private void getData(){
        TwoApi.getInstance(getActivity()).notificationApi(receiveId, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<NotificationModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result,NotificationModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    if(list.size() != 0){//未读消息
                        int weidu = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i).getState().equals("1")){
                                weidu ++;
                            }
                        }
                        tvNumb.setVisibility(View.VISIBLE);
                        tvNumb.setText(""+weidu);
                    }
                    notificationAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
