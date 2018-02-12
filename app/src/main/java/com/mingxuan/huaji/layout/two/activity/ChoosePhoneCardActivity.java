package com.mingxuan.huaji.layout.two.activity;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.NetImageLocadHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2018/2/1.
 * 公司：铭轩科技
 */

public class ChoosePhoneCardActivity extends Activity{
    @BindView(R.id.market_price)
    TextView marketPrice;
    @BindView(R.id.repertory)
    TextView repertory;
    @BindView(R.id.express)
    TextView express;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ConvenientBanner convenientBanner;
    private String[] images = {"http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"};
    //轮播下面的小点
    private int[] indicator = {R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    private List<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_phone_card);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        bean = new ArrayList<>();
        list = new ArrayList<>();
        convenientBanner = (ConvenientBanner) findViewById(R.id.converientBanner);

        showBanner();

        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(this,3);
        recyclerview.setLayoutManager(fullGridLayoutManager);
        int spanCount = 3;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        marketPrice.setText("￥"+"1000-15000");
        marketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);//中划线
        repertory.setText("库存："+"1065");
        express.setText("顺丰速递");
        money.setText("快递 ￥："+"10");

        for (int i = 0; i < 9; i++) {
            list.add(i*10+49+"元测试套餐");
        }
        PhoneCardAdapter phoneCardAdapter = new PhoneCardAdapter(list,ChoosePhoneCardActivity.this);
        recyclerview.setAdapter(phoneCardAdapter);
        phoneCardAdapter.setOnItemClickListener(new PhoneCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("","点击");
            }
        });
    }

    private void showBanner() {
        bean = Arrays.asList(images);
        //设置指示器是否可见
        convenientBanner.setPointViewVisible(true);
        //设置小点
        convenientBanner.setPageIndicator(indicator);
        //允许手动轮播
        convenientBanner.setManualPageable(true);
        //设置自动轮播的时间
        convenientBanner.startTurning(3000);
        //设置点击事件    泛型为具体实现类ImageLoaderHolder
        convenientBanner.setPages(new CBViewHolderCreator<NetImageLocadHolder>() {
            @Override
            public NetImageLocadHolder createHolder() {
                return new NetImageLocadHolder();
            }
        }, bean);

        //设置指示器的方向
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ChoosePhoneCardActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
