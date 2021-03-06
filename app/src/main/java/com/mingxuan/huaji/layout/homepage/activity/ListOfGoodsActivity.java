package com.mingxuan.huaji.layout.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.homepage.adapter.ShopListAdapter;
import com.mingxuan.huaji.layout.homepage.adapter.ShoppingListTopTypeAdapter;
import com.mingxuan.huaji.layout.homepage.bean.ShopListModel;
import com.mingxuan.huaji.layout.homepage.bean.ShoppingListTopTypeModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class ListOfGoodsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.top_recyclerview)
    RecyclerView toprecyclerview;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private List<ShopListModel.ResultBean> list;
    private List<ShoppingListTopTypeModel.ResultBean> toplist;
    private LoadingDialog loadingDialog;
    int type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_of_goods;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle("商城列表");
        Bundle bundle = getIntent().getExtras();
        type = bundle.getInt("type");
        parent_id = ""+type;

        loadingDialog = new LoadingDialog(this);
        toplist = new ArrayList<>();
        list = new ArrayList<>();
        swipe.setOnRefreshListener(this);//刷新

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListOfGoodsActivity.this,5);
        toprecyclerview.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        shoppingListTopTypeAdapter = new ShoppingListTopTypeAdapter(toplist,ListOfGoodsActivity.this);
        toprecyclerview.setAdapter(shoppingListTopTypeAdapter);
        shoppingListTopTypeAdapter.setDate(toplist);
        shoppingListTopTypeAdapter.setOnItemClickListener(new ShoppingListTopTypeAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int i) {
                switch (v.getId()){
                    case R.id.default_footer_title:
                        product_label =null;
                        getlist();
                        break;
                    case R.id.textView2:
                        product_label = toplist.get(i-1).getId();
                        getlist();
                        break;
                }
            }
        });

        shopListAdapter = new ShopListAdapter(list, this);
        recyclerview.setAdapter(shopListAdapter);
        shopListAdapter.setMyOnClickListener(new ShopListAdapter.MyOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(ListOfGoodsActivity.this, CommodityDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        getTopType();
        getlist();
    }

    /**
     * 顶部标签
     */
    ShoppingListTopTypeAdapter shoppingListTopTypeAdapter;
    String parent_id;
    private void getTopType(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        MainApi.getInstance(this).shoppinglisttopApi(type, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    List<ShoppingListTopTypeModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result,ShoppingListTopTypeModel.ResultBean.class);
                    toplist.clear();
                    toplist.addAll(resultBeans);

                    shoppingListTopTypeAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ListOfGoodsActivity.this,result);
            }
        });
    }

    /**
     * 商品列表
     */
    ShopListAdapter shopListAdapter;
    String product_label;
    String id;
    private void getlist(){
        MainApi.getInstance(this).shoppinglistApi(id ,parent_id,product_label, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ShopListModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result,ShopListModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    shopListAdapter.notifyDataSetChanged();
                }else
                    list.clear();
                    shopListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh() {
        getlist();
    }
}
