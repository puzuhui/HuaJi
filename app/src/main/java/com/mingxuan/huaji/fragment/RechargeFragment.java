package com.mingxuan.huaji.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.activity.MyShoppingCartActivity;
import com.mingxuan.huaji.layout.two.adapter.PhoneCardAdapter;
import com.mingxuan.huaji.layout.two.model.AliPayModel;
import com.mingxuan.huaji.layout.two.model.CardInfoModel;
import com.mingxuan.huaji.layout.two.model.PayModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.PayUtils;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;
import com.mingxuan.huaji.utils.alpay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class RechargeFragment extends Fragment {
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
    private View view;
    Unbinder unbinder;
    List<CardInfoModel.ResultBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recharge,null);
        unbinder = ButterKnife.bind(this,view);

        initView();
        return view;
    }

    private void initView() {
        list = new ArrayList<>();
        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(getActivity(),4);
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

        PhoneCardAdapter phoneCardAdapter = new PhoneCardAdapter(list,getActivity());
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
                ToastUtil.makeToast(getActivity(),"此功能还未开通！！！");
                break;
            case R.id.pay:
                PayUtils.getInstance(getActivity()).showPayPopupWindow("华记测试商品","1",UIUtils.getIPAddress(getActivity()));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
