package com.mingxuan.huaji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.adapter.PhoneCardAdapter;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

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
    List<String> list;
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

        list.add("1个月");
        list.add("3个月");
        list.add("6个月");
        list.add("12个月");

        PhoneCardAdapter phoneCardAdapter = new PhoneCardAdapter(list,getActivity());
        recyclerview.setAdapter(phoneCardAdapter);
        phoneCardAdapter.setOnItemClickListener(new PhoneCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String number = list.get(position);
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
                ToastUtil.makeToast(getActivity(),"此功能还未开通！！！");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
