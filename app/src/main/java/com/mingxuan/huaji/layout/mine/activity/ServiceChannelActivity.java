package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.DateAdapter;
import com.mingxuan.huaji.layout.mine.adapter.ServiceChannelAdapter;
import com.mingxuan.huaji.layout.mine.bean.DateMolder;
import com.mingxuan.huaji.layout.mine.bean.IncomeModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/26.
 * 公司：铭轩科技
 */

public class ServiceChannelActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tvdate)
    TextView tvdate;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<IncomeModel.TwoBean> list;
    List<DateMolder> datelist;
    ServiceChannelAdapter serviceChannelAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_channel);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        list = new ArrayList<>();
        datelist = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int i = 0;i<12 ;i++){
            calendar.add(Calendar.MONTH,-1);
            DateMolder dateMolder = new DateMolder();
            dateMolder.setDate(sdf.format(calendar.getTime()));
            datelist.add(dateMolder);
        }
        tvdate.setText(datelist.get(0).getDate());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        serviceChannelAdapter = new ServiceChannelAdapter(list, this);
        recyclerview.setAdapter(serviceChannelAdapter);

        for_date = datelist.get(2).getDate();
        p_id = sharedPreferences.getString("create_id","");
        showData();
    }

    public void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_listview, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final Dialog dialog = builder.show();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        DateAdapter dateAdapter = new DateAdapter(this, datelist);
        recyclerView.setAdapter(dateAdapter);
        dateAdapter.setOnItemClickListener(new DateAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                tvdate.setText(datelist.get(position).getDate());
                dialog.dismiss();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @OnClick({R.id.back_btn, R.id.tvdate, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.tvdate:
                showDialog();
                break;
            case R.id.tv_search:
                user_phone = etPhone.getText().toString();
                for_date = tvdate.getText().toString();
                showData();
                break;
        }
    }

    String user_phone,for_date, p_id;
    private void showData(){
        FourApi.getInstance(this).serviceChannelApi(user_phone, for_date, p_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<IncomeModel.TwoBean> twoBeans = GsonUtil.fromJSONResult(new Gson(),result,IncomeModel.TwoBean.class,"two");
                    list.clear();
                    list.addAll(twoBeans);

                    serviceChannelAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ServiceChannelActivity.this,result);
            }
        });
    }
}
