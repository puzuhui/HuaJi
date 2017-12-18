package com.mingxuan.huaji.layout.two.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.two.adapter.ShopListAdapter;
import com.mingxuan.huaji.layout.two.adapter.ShoppingListTopTypeAdapter;
import com.mingxuan.huaji.layout.two.model.ShopListModel;
import com.mingxuan.huaji.layout.two.model.ShoppingListTopTypeModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class ListOfGoodsActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.head_shop)
    TextView headShop;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_goods);
        ButterKnife.bind(this);

        getBundle();
        initView();
        getTopType();
        getlist();
    }

    private void getBundle(){
        Bundle bundle = getIntent().getExtras();
        type = bundle.getInt("type");
        parent_id = ""+type;
    }

    private void initView() {
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
        shoppingListTopTypeAdapter.setOnItemClickListener(new ShoppingListTopTypeAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int i) {
                switch (v.getId()){
                    case R.id.default_footer_title:
                        Toast.makeText(ListOfGoodsActivity.this, "点击了全部" , Toast.LENGTH_SHORT).show();
                        product_label =null;
                        getlist();
                        break;
                    case R.id.textView2:
                        Toast.makeText(ListOfGoodsActivity.this, "点击了" + toplist.get(i-1).getName(), Toast.LENGTH_SHORT).show();
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
                Log.e("点击====", "" + list.get(position).getId());
                startActivity(intent);
            }
        });
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
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        MainApi.getInstance(this).shoppinglistApi(id ,parent_id,product_label, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    List<ShopListModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result,ShopListModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    shopListAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ListOfGoodsActivity.this,result);
            }
        });
    }


    @OnClick(R.id.back_btn)
     public void setOnClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;

        }
    }

    @Override
    public void onRefresh() {
        getlist();
    }
}
