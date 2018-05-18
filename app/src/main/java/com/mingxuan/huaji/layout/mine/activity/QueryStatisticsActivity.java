package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.bean.QueryStatisticsModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

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

public class QueryStatisticsActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.statistics)
    TextView statistics;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.tv_statistics)
    TextView tvStatistics;
    @BindView(R.id.tv_none)
    TextView tvNone;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_rental)
    TextView tvRental;
    @BindView(R.id.tv_add_up)
    TextView tvAddUp;
    @BindView(R.id.tv_add_up_rental)
    TextView tvAddUpRental;
    @BindView(R.id.layout_statistics)
    LinearLayout layoutStatistics;
    SimpleDateFormat simpleDateFormat;
    List<QueryStatisticsModel.ResultBean> list;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_statistics);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-2);
        for_date =simpleDateFormat1.format(calendar.getTime());

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        user_phone = sharedPreferences.getString("phone","");
        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();

    }

    @OnClick({R.id.back_btn, R.id.tv_search, R.id.tv_statistics})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.tv_search:
                childphone = etPhone.getText().toString();
                if(!TextUtils.isEmpty(childphone)){
                    if(UIUtils.isMobileNO(childphone)){
                        findData();
                    }else {
                        ToastUtil.makeToast(this,"格式错误");
                    }
                }else {
                    ToastUtil.makeToast(this,"服务号码不能为空");
                }

                break;
            case R.id.tv_statistics:
                layoutStatistics.setVisibility(View.VISIBLE);
                tvNone.setVisibility(View.GONE);
                showData();
                break;
        }
    }

    // isflg==1 为渠道内用户 isflg==2 不是服务用户
    // info==1 有效 info==2 无效
    String childphone;
    public void findData(){
        loadingDialog.setLoadingContent("查询中...");
        loadingDialog.show();
        FourApi.getInstance(this).queryStatistics(user_phone,childphone, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if(status == Constants.TYPE_SUCCESS){
                        int isflg = jsonObject.getInt("isflg");
                        int info = jsonObject.getInt("info");
                        int level = jsonObject.getInt("level");
                        if (isflg == 1) {
                            if (info == 1) {
                                if(level > 11){
                                    tvResult.setText("恭喜您！【" + etPhone.getText().toString() + "】是您的渠道之外用户，请继续努力！");
                                }
                                tvResult.setText("恭喜您！【" + etPhone.getText().toString() + "】是您的有效第" + level + "个渠道用户，请继续努力做好售后！");
                                tvResult.setTextColor(Color.GREEN);
                            } else {
                                tvResult.setText("很遗憾！【" + etPhone.getText().toString() + "】是您的无效第" + level + "个渠道用户，他很懒，快去帮助他吧！'");
                                tvResult.setTextColor(Color.BLACK);
                            }
                        } else {
                            tvResult.setText("非常抱歉！【"+etPhone.getText().toString()+"】不是您的服务用户");
                            tvResult.setTextColor(Color.RED);
                        }
                    }else {
                        String message = jsonObject.getString("message");
                        tvResult.setText(message);
                        tvResult.setTextColor(Color.RED);
                    }
                    etPhone.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        });
    }

    String user_phone;
    String for_date;
    public void showData(){
        loadingDialog.setLoadingContent("查询中...");
        loadingDialog.show();
        FourApi.getInstance(this).upDateQueryStatistics(user_phone, for_date, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                date.setText("[统计截止到："+simpleDateFormat.format(new Date())+"]");
                if(type == Constants.TYPE_SUCCESS){
                    List<QueryStatisticsModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result,QueryStatisticsModel.ResultBean.class);
                    list.addAll(resultBeans);
                    tvAdd.setText("本月新增开户："+list.get(0).getNewxj()+"户");
                    tvRental.setText("本月套餐总额："+list.get(0).getNewxjje()+"户");
                    tvAddUp.setText("累计开户："+list.get(0).getXjsl()+"户");
                    tvAddUpRental.setText("累计套餐总额："+list.get(0).getSljl_ljyxze()+"元");
                }else BaseApi.showErrMsg(QueryStatisticsActivity.this,result);
            }
        });
    }


}
