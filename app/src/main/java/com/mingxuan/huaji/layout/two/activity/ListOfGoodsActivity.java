package com.mingxuan.huaji.layout.two.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.adapter.ShopListAdapter;
import com.mingxuan.huaji.layout.two.model.ShopListModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class ListOfGoodsActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.head_shop)
    TextView headShop;
    @BindView(R.id.btn_all)
    TextView btnAll;
    @BindView(R.id.btn_one)
    TextView btnOne;
    @BindView(R.id.btn_two)
    TextView btnTwo;
    @BindView(R.id.btn_three)
    TextView btnThree;
    @BindView(R.id.btn_four)
    TextView btnFour;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private List<ShopListModel> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_goods);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        list = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        String[] s = {"爱生活", "限时折扣", "热门推荐", "家居生活", "值得买"};
        int[] m = {1000, 2000, 3000, 5000, 6000};
        for (int i = 0; i < s.length; i++) {
            ShopListModel shopListModel = new ShopListModel();
            shopListModel.setShopname(s[i]);
            shopListModel.setMoney(m[i]);
            list.add(shopListModel);
        }

        ShopListAdapter shopListAdapter = new ShopListAdapter(list, this);
        recyclerview.setAdapter(shopListAdapter);
        shopListAdapter.setMyOnClickListener(new ShopListAdapter.MyOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(ListOfGoodsActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListOfGoodsActivity.this, CommodityDetailsActivity.class);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.back_btn,R.id.btn_all,R.id.btn_one,R.id.btn_two,R.id.btn_three,R.id.btn_four})
     public void setOnClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.btn_all:
                btnAll.setTextColor(getResources().getColor(R.color.red));
                btnOne.setTextColor(getResources().getColor(R.color.transparent80));
                btnTwo.setTextColor(getResources().getColor(R.color.transparent80));
                btnThree.setTextColor(getResources().getColor(R.color.transparent80));
                btnFour.setTextColor(getResources().getColor(R.color.transparent80));
                break;
            case R.id.btn_one:
                btnOne.setTextColor(getResources().getColor(R.color.red));
                btnAll.setTextColor(getResources().getColor(R.color.transparent80));
                btnTwo.setTextColor(getResources().getColor(R.color.transparent80));
                btnThree.setTextColor(getResources().getColor(R.color.transparent80));
                btnFour.setTextColor(getResources().getColor(R.color.transparent80));
                break;
            case R.id.btn_two:
                btnTwo.setTextColor(getResources().getColor(R.color.red));
                btnOne.setTextColor(getResources().getColor(R.color.transparent80));
                btnAll.setTextColor(getResources().getColor(R.color.transparent80));
                btnThree.setTextColor(getResources().getColor(R.color.transparent80));
                btnFour.setTextColor(getResources().getColor(R.color.transparent80));
                break;
            case R.id.btn_three:
                btnThree.setTextColor(getResources().getColor(R.color.red));
                btnOne.setTextColor(getResources().getColor(R.color.transparent80));
                btnTwo.setTextColor(getResources().getColor(R.color.transparent80));
                btnAll.setTextColor(getResources().getColor(R.color.transparent80));
                btnFour.setTextColor(getResources().getColor(R.color.transparent80));
                break;
            case R.id.btn_four:
                btnFour.setTextColor(getResources().getColor(R.color.red));
                btnOne.setTextColor(getResources().getColor(R.color.transparent80));
                btnTwo.setTextColor(getResources().getColor(R.color.transparent80));
                btnThree.setTextColor(getResources().getColor(R.color.transparent80));
                btnAll.setTextColor(getResources().getColor(R.color.transparent80));
                break;
        }
    }
}
