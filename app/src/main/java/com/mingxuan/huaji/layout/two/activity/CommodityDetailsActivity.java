package com.mingxuan.huaji.layout.two.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.adapter.ShopSizeAdapter;
import com.mingxuan.huaji.layout.two.model.ShopSizeModel;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.NetImageLocadHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class CommodityDetailsActivity extends Activity {
    @BindView(R.id.convenient)
    ConvenientBanner convenientBanner;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.all_evaluate)
    TextView allEvaluate;
    @BindView(R.id.add_to_shopping_cart)
    TextView addToShoppingCart;
    @BindView(R.id.settle_accounts)
    TextView settleAccounts;

    private String[] images = {"http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"};
    //轮播下面的小点
    private int[] indicator = {R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    private List<ShopSizeModel> sizelist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        sizelist = new ArrayList<>();
        bean = new ArrayList<>();
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
                Toast.makeText(CommodityDetailsActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });

        FullGridLayoutManager gridLayoutManager = new FullGridLayoutManager(this,4);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setLayoutManager(gridLayoutManager);
        int spanCount = 4;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount,spacing,false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        String[] s = {"185/64Y","185/64Y","185/64Y","185/64Y","185/64Y"};
        for (int i = 0; i < s.length; i++) {
            ShopSizeModel shopSizeModel = new ShopSizeModel();
            shopSizeModel.setSize(s[i]);
            sizelist.add(shopSizeModel);
        }

        ShopSizeAdapter shopSizeAdapter = new ShopSizeAdapter(CommodityDetailsActivity.this,sizelist);
        recyclerview.setAdapter(shopSizeAdapter);
        shopSizeAdapter.setMyOnClickListener(new ShopSizeAdapter.MyOnClickListener() {
            @Override
            public void onclick(View view, int position) {
                Toast.makeText(CommodityDetailsActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });

        addToShoppingCart.setOnClickListener(onClickListener);
        settleAccounts.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.add_to_shopping_cart:
                    Toast.makeText(CommodityDetailsActivity.this, "点击了加入购物车", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.settle_accounts:
                    intent = new Intent(CommodityDetailsActivity.this,ConfirmAnOrderActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
