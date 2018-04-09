package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.adapter.DateAdapter;
import com.mingxuan.huaji.layout.four.model.DateMolder;
import com.mingxuan.huaji.layout.four.model.IncomeModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.math.BigDecimal;
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

public class IncomeStatisticsActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_due)
    TextView tvDue;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.et_ditch)
    EditText etDitch;
    @BindView(R.id.et_ditch_search)
    TextView etDitchSearch;
    @BindView(R.id.tv_numb)
    TextView tvNumb;
    @BindView(R.id.tv_add_up_count_ditch)
    TextView tvAddUpCountDitch;
    @BindView(R.id.tv_count_ditch)
    TextView tvCountDitch;
    @BindView(R.id.tv_add_up_count)
    TextView tvAddUpCount;
    @BindView(R.id.tv_commission_rate)
    TextView tvCommissionRate;
    @BindView(R.id.tv_one_numb)
    TextView tvOneNumb;
    @BindView(R.id.tv_deduct_ditch)
    TextView tvDeductDitch;
    @BindView(R.id.tv_two_numb)
    TextView tvTwoNumb;
    @BindView(R.id.tv_add_up_count_shangliang)
    TextView tvAddUpCountShangliang;
    @BindView(R.id.tv_commitment_award)
    TextView tvCommitmentAward;
    @BindView(R.id.tv_corporate_participation_award)
    TextView tvCorporateParticipationAward;
    @BindView(R.id.tv_total_company_sales)
    TextView tvTotalCompanySales;
    @BindView(R.id.tv_award_count)
    TextView tvAwardCount;
    @BindView(R.id.linear_one)
    LinearLayout linearOne;
    @BindView(R.id.linear_two)
    LinearLayout linearTwo;
    @BindView(R.id.linear_three)
    LinearLayout linearThree;
    List<DateMolder> list;
    List<IncomeModel.OneBean> onelist;
    List<IncomeModel.TwoBean> twolist;
    List<IncomeModel.ThreeBean> threelist;
    List<IncomeModel.QdBean> qdlist;
    DateMolder dateMolder;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_statistics);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
        user_phone = sharedPreferences.getString("phone","");

        initView();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();
        onelist = new ArrayList<>();
        twolist = new ArrayList<>();
        threelist = new ArrayList<>();
        qdlist = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int i = 0;i < 12 ; i++){
            calendar.add(Calendar.MONTH,-1);
            dateMolder = new DateMolder();
            dateMolder.setDate(sdf.format(calendar.getTime()));
            list.add(dateMolder);
        }
        tvDate.setText(list.get(0).getDate());
    }

    @OnClick({R.id.back_btn, R.id.tv_date,R.id.tv_search,  R.id.et_ditch_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.tv_date:
                showDialog();
                break;
            case R.id.tv_search:
                for_date = tvDate.getText().toString();
                showData();
                break;
            case R.id.et_ditch_search:
                qdyj_level = etDitch.getText().toString();
                for_date = tvDate.getText().toString();
                showData();
                break;
        }
    }


    public void showDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_listview,null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final Dialog dialog = builder.show();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        DateAdapter dateAdapter = new DateAdapter(this,list);
        recyclerView.setAdapter(dateAdapter);
        dateAdapter.setOnItemClickListener(new DateAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                tvDate.setText(list.get(position).getDate());
                dialog.dismiss();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    String user_phone;
    String for_date;
    String qdyj_level;
    private void showData(){
        loadingDialog.setLoadingContent("查询中...");
        loadingDialog.show();
        FourApi.getInstance(this).incomeStatisticsApi(user_phone, for_date,qdyj_level ,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                linearOne.setVisibility(View.VISIBLE);
                linearTwo.setVisibility(View.VISIBLE);
                linearThree.setVisibility(View.VISIBLE);
                if(type == Constants.TYPE_SUCCESS){
                    onelist.clear();
                    twolist.clear();
                    threelist.clear();
                    qdlist.clear();
                    List<IncomeModel.OneBean> oneBeanArrayList = GsonUtil.fromJSONResult(new Gson(),
                            result,IncomeModel.OneBean.class,"one");
                    onelist.addAll(oneBeanArrayList);

                    List<IncomeModel.TwoBean> twoBeanArrayList = GsonUtil.fromJSONResult(new Gson(),
                            result,IncomeModel.TwoBean.class,"two");
                    twolist.addAll(twoBeanArrayList);

                    List<IncomeModel.ThreeBean> threeBeanArrayList = GsonUtil.fromJSONResult(new Gson(),
                            result,IncomeModel.ThreeBean.class,"three");
                    threelist.addAll(threeBeanArrayList);

                    List<IncomeModel.QdBean> qdBeanList = GsonUtil.fromJSONResult(new Gson(),
                            result,IncomeModel.QdBean.class,"qd");
                    qdlist.addAll(qdBeanList);

                    Double one = 0.0;
                    Double two = 0.0;
                    Double three = 0.0;
                    if(!TextUtils.isEmpty(qdyj_level)){
                        tvNumb.setText("用户数量/户："+qdlist.get(0).getQdyj_yhsl()+"  |  " +onelist.get(0).getQdyj_yhsl()+ "户（本月）");
                        tvAddUpCountDitch.setText("累计有效套餐总额："+qdlist.get(0).getQdyj_ljyx() +"  |  "+onelist.get(0).getQdyj_ljyx()+ "元(本月)");
                        tvCountDitch.setText("渠道收入总计："+qdlist.get(0).getQdyj_qdsrzj() +"  |  "+onelist.get(0).getQdyj_qdsrzj()+ "元");
                    }else {
                        if(onelist.size() != 0 ){
                            tvNumb.setText("用户数量/户：" +onelist.get(0).getQdyj_yhsl()+ "户（本月）");
                            tvAddUpCountDitch.setText("累计有效套餐总额：" +onelist.get(0).getQdyj_ljyx()+ "元(本月)");
                            tvCountDitch.setText("渠道收入总计：" +onelist.get(0).getQdyj_qdsrzj()+ "元");
                            one = Double.parseDouble(onelist.get(0).getQdyj_qdsrzj());
                        }else {
                            tvNumb.setText("用户数量/户：" +0+ "户（本月）");
                            tvAddUpCountDitch.setText("累计有效套餐总额：" +0+ "元(本月)");
                            tvCountDitch.setText("渠道收入总计：" +0+ "元");
                        }
                    }

                    if(twolist.size() != 0){
                        tvAddUpCount.setText("累计有效套餐总额："  +twolist.get(0).getSljl_ljyxze()+ "元(本月)");
                        String CommissionRate;
                        double rate = 0;
                        try{
                            rate = Double.parseDouble(twolist.get(0).getSljl_ljyxze());
                        }catch (NumberFormatException e){
                            e.printStackTrace();
                        }
                        if(rate < 2000){
                            CommissionRate = "0%" ;
                        }else if(rate >= 2000 && rate < 5000){
                            CommissionRate = "2%" ;
                        }else if(rate >= 5000 && rate < 10000){
                            CommissionRate = "4%" ;
                        }else if(rate >= 10000 && rate < 30000){
                            CommissionRate = "6%" ;
                        }else if(rate >= 30000 && rate < 100000){
                            CommissionRate = "8%" ;
                        }else if(rate >= 100000 && rate < 400000){
                            CommissionRate = "10%" ;
                        }else if(rate >= 400000 && rate < 1000000){
                            CommissionRate = "11%" ;
                        }else if(rate >= 1000000 && rate < 3000000){
                            CommissionRate = "12%" ;
                        }else if(rate >= 3000000 && rate < 6000000){
                            CommissionRate = "13%" ;
                        }else{
                            CommissionRate = "14%" ;
                        }
                        tvCommissionRate.setText("佣金比例：" + CommissionRate);
                        tvOneNumb.setText("小计/元："  +twolist.get(0).getSljl_xjone()+ "元(本月)");
                        tvDeductDitch.setText("扣除渠道内上量奖励套餐总额："  +twolist.get(0).getSljl_kcqdze()+ "元");
                        tvTwoNumb.setText("小计/元："  +twolist.get(0).getSljl_xjtwo()+ "元(本月)");
                        tvAddUpCountShangliang.setText("上量奖励收入总计："  +twolist.get(0).getSljl_srzj()+ "元");
                        two = Double.parseDouble(twolist.get(0).getSljl_srzj());
                    }else {
                        tvAddUpCount.setText("累计有效套餐总额："  +0+ "元(本月)");
                        tvCommissionRate.setText("佣金比例：" + "0%");
                        tvOneNumb.setText("小计/元："  +0+ "元(本月)");
                        tvDeductDitch.setText("扣除渠道内上量奖励套餐总额："  +0+ "元");
                        tvTwoNumb.setText("小计/元："  +0+ "元(本月)");
                        tvAddUpCountShangliang.setText("上量奖励收入总计："  +0+ "元");
                    }

                    if(threelist.size() != 0){
                        tvCommitmentAward.setText("我贡献奖："  +threelist.get(0).getGxj_wgxj()+ "户(本月)");
                        tvCorporateParticipationAward.setText("公司参与奖："  +threelist.get(0).getGxj_gszcy()+ "元(本月)");
                        tvTotalCompanySales.setText("公司总销售额：" +threelist.get(0).getGxj_gszxse() + "元(本月)");
                        tvAwardCount.setText("贡献奖励收入总计："  +threelist.get(0).getGxj_gxjlsr()+ "元");
                        three = Double.parseDouble(threelist.get(0).getGxj_gxjlsr());
                    }else {
                        tvCommitmentAward.setText("我贡献奖："  +0+ "户(本月)");
                        tvCorporateParticipationAward.setText("公司参与奖：" +0+ "元(本月)");
                        tvTotalCompanySales.setText("公司总销售额：" +0 + "元(本月)");
                        tvAwardCount.setText("贡献奖励收入总计："  +0+ "元");
                    }

                    double s = one + two +three;
                    BigDecimal bg = new BigDecimal(s);
                    double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//小数点后取二位
                    tvDue.setText("本月应得佣金  " + f1 + "  元");
                    tvCount.setText("总累计应得佣金  " + f1 + "  元");

                    etDitch.setText("");
                }else BaseApi.showErrMsg(IncomeStatisticsActivity.this,result);
            }
        });
    }

}
