package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class MyPhoneCardActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.service_user)
    TextView serviceUser;
    @BindView(R.id.query_statistics)
    TextView queryStatistics;
    @BindView(R.id.income_statistics)
    TextView incomeStatistics;
    @BindView(R.id.service_channel)
    TextView serviceChannel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_phone_card);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        tvName.setText(sharedPreferences.getString("realName", ""));
        tvCard.setText(sharedPreferences.getString("idCard", ""));
        tvPhone.setText(sharedPreferences.getString("phone", ""));
    }

    @OnClick({R.id.back_btn,R.id.service_user,R.id.query_statistics,R.id.income_statistics,R.id.service_channel})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.service_user:
                intent = new Intent(MyPhoneCardActivity.this,ServiceUserActivity.class);
                startActivity(intent);
                break;
            case R.id.query_statistics:
                intent = new Intent(MyPhoneCardActivity.this,QueryStatisticsActivity.class);
                startActivity(intent);
                break;
            case R.id.income_statistics:
                intent = new Intent(MyPhoneCardActivity.this,IncomeStatisticsActivity.class);
                startActivity(intent);
                break;
            case R.id.service_channel:
                intent = new Intent(MyPhoneCardActivity.this,ServiceChannelActivity.class);
                startActivity(intent);
                break;
        }
    }
}
