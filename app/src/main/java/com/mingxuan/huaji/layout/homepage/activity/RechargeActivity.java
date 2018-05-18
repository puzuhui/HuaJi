package com.mingxuan.huaji.layout.homepage.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.layout.homepage.adapter.PhoneCardAdapter;
import com.mingxuan.huaji.layout.homepage.bean.CardInfoModel;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.PayUtils;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class RechargeActivity extends BaseActivity {
    @BindView(R.id.et_find)
    EditText etFind;
    @BindView(R.id.tv_find)
    TextView tvFind;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_numb)
    TextView tvNumb;
    @BindView(R.id.pay)
    TextView pay;
    List<CardInfoModel.ResultBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recharge;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(RechargeActivity.this,4);
        recyclerview.setLayoutManager(fullGridLayoutManager);
        int spanCount = 4;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        CardInfoModel.ResultBean resultBean = new CardInfoModel.ResultBean();
        resultBean.setName("3个月");
        list.add(resultBean);
        CardInfoModel.ResultBean resultBean1 = new CardInfoModel.ResultBean();
        resultBean1.setName("6个月");
        list.add(resultBean1);
        CardInfoModel.ResultBean resultBean2 = new CardInfoModel.ResultBean();
        resultBean2.setName("9个月");
        list.add(resultBean2);
        CardInfoModel.ResultBean resultBean3 = new CardInfoModel.ResultBean();
        resultBean3.setName("12个月");
        list.add(resultBean3);

        PhoneCardAdapter phoneCardAdapter = new PhoneCardAdapter(list,RechargeActivity.this);
        recyclerview.setAdapter(phoneCardAdapter);
        phoneCardAdapter.setOnItemClickListener(new PhoneCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @OnClick({R.id.tv_find,R.id.pay})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_find:
                ToastUtil.makeToast(RechargeActivity.this,"此功能还未开通！！！");
                break;
            case R.id.pay:
                PayUtils.getInstance(RechargeActivity.this).showPayPopupWindow("华记测试商品","1",UIUtils.getIPAddress(RechargeActivity.this));
                break;
        }
    }

}
