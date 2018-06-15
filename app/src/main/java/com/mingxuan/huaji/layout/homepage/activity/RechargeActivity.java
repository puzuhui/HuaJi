package com.mingxuan.huaji.layout.homepage.activity;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.homepage.adapter.PhoneCardAdapter;
import com.mingxuan.huaji.layout.homepage.bean.CardInfoModel;
import com.mingxuan.huaji.layout.mine.bean.RechargeModel;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
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
    @BindView(R.id.tv_name)
    TextView tvName;
    List<CardInfoModel.ResultBean> list;
    List<RechargeModel.ResultBean> rechargeList;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recharge;
    }

    @Override
    protected void initView() {
        setToolbarTitle(getString(R.string.recharge));
        list = new ArrayList<>();
        rechargeList = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);
        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(RechargeActivity.this, 4);
        recyclerview.setLayoutManager(fullGridLayoutManager);
        int spanCount = 4;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        CardInfoModel.ResultBean resultBean = new CardInfoModel.ResultBean();
        resultBean.setName("1个月");
        list.add(resultBean);
        CardInfoModel.ResultBean resultBean1 = new CardInfoModel.ResultBean();
        resultBean1.setName("3个月");
        list.add(resultBean1);
        CardInfoModel.ResultBean resultBean2 = new CardInfoModel.ResultBean();
        resultBean2.setName("6个月");
        list.add(resultBean2);
        CardInfoModel.ResultBean resultBean3 = new CardInfoModel.ResultBean();
        resultBean3.setName("12个月");
        list.add(resultBean3);

        PhoneCardAdapter phoneCardAdapter = new PhoneCardAdapter(list, RechargeActivity.this);
        recyclerview.setAdapter(phoneCardAdapter);
        phoneCardAdapter.setOnItemClickListener(new PhoneCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        tvNumb.setText(Html.fromHtml(getString(R.string.recharge_numb, "" + price * 1)));
                        break;
                    case 1:
                        tvNumb.setText(Html.fromHtml(getString(R.string.recharge_numb, "" + price * 3)));
                        break;
                    case 2:
                        tvNumb.setText(Html.fromHtml(getString(R.string.recharge_numb, "" + price * 6)));
                        break;
                    case 3:
                        tvNumb.setText(Html.fromHtml(getString(R.string.recharge_numb, "" + price * 12)));
                        break;
                }

            }
        });
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_find, R.id.pay})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_find:
                if (UIUtils.isMobile(etFind.getText().toString())) {
                    mobile = etFind.getText().toString();
                    showData();
                } else {
                    ToastUtil.makeToast(RechargeActivity.this, "目前仅限电信号码充值！");
                }
                break;
            case R.id.pay:
                if (!TextUtils.isEmpty(etFind.getText())) {
                    if (!tvNumb.getText().toString().equals("充值金额：0元")) {
                        PayUtils.getInstance(RechargeActivity.this).showPayPopupWindow("华记测试商品", "1", UIUtils.getIPAddress(RechargeActivity.this));
                    } else {
                        ToastUtil.makeToast(RechargeActivity.this, "请选择充值月份！");
                    }
                } else {
                    ToastUtil.makeToast(RechargeActivity.this, "请填写手机号码！");
                }
                break;
        }
    }

    //查询套餐信息
    String mobile;
    double price;

    private void showData() {
        loadingDialog.setLoadingContent("请等待...");
        loadingDialog.show();
        FourApi.getInstance(this).rechargeApi(mobile, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if (type == Constants.TYPE_SUCCESS) {
                    List<RechargeModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(), result, RechargeModel.ResultBean.class);
                    rechargeList.clear();
                    rechargeList.addAll(resultBeans);

                    tvName.setVisibility(View.VISIBLE);
                    tvName.setText("服务商：" + resultBeans.get(0).getRealName() + "[" + rechargeList.get(0).getInfoName() + "]");
                    price = resultBeans.get(0).getInfoPrice();
                } else BaseApi.showErrMsg(RechargeActivity.this, result);
            }
        });
    }
}
