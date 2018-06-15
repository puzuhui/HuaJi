package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.MyOrderAdapter;
import com.mingxuan.huaji.layout.mine.bean.MyOrderModel;
import com.mingxuan.huaji.layout.homepage.activity.CommodityDetailsActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.all_commodity)
    TextView allCommodity;
    @BindView(R.id.obligation)
    TextView obligation;
    @BindView(R.id.to_send_the_goods)
    TextView toSendTheGoods;
    @BindView(R.id.wait_for_receiving)
    TextView waitForReceiving;
    @BindView(R.id.off_the_stocks)
    TextView offTheStocks;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.line5)
    View line5;
    private List<MyOrderModel.ResultBean> list,list1,list2,list3,list4,list5;
    private MyOrderAdapter myOrderAdapter;
    int index = 0;
    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getOrder();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle(getString(R.string.my_order));
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");
        update_id = sharedPreferences.getString("create_id","");
        update_name  = sharedPreferences.getString("realName","");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        update_time = simpleDateFormat.format(new Date());

        loadingDialog = new LoadingDialog(this);
        list =new ArrayList<>();
        list1 =new ArrayList<>();
        list2 =new ArrayList<>();
        list3 =new ArrayList<>();
        list4 =new ArrayList<>();
        list5 =new ArrayList<>();

        myOrderAdapter = new MyOrderAdapter(list5,MyOrderActivity.this);
        recyclerview.setAdapter(myOrderAdapter);
        myOrderAdapter.setMyOnItemClickListener(myOnItemClickListener);
        LinearLayoutManager linearLayoutMange = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutMange);
    }

    @Override
    protected void initData() {
        getOrder();
    }

    MyOrderAdapter.MyOnItemClickListener myOnItemClickListener = new MyOrderAdapter.MyOnItemClickListener() {
        @Override
        public void onItemClickListener(View view, final int i) {
            Intent intent;
            String[] imageurl = list5.get(i).getProduct_intr().split(",");
            switch (view.getId()){
                case R.id.evaluate://评价
                    intent = new Intent(MyOrderActivity.this, ProductsReviewsActivity.class);
                    intent.putExtra("index",1);
                    intent.putExtra("id", list5.get(i).getId());
                    intent.putExtra("pid", list5.get(i).getProducts_id());
                    intent.putExtra("imageurl", Constants.IMAGE_URL+imageurl[0]);
                    intent.putExtra("content", list5.get(i).getProducts_name());
                    startActivity(intent);
                    break;
                case R.id.additional_comments://追加评价
                    intent = new Intent(MyOrderActivity.this, ProductsReviewsActivity.class);
                    intent.putExtra("index",2);
                    intent.putExtra("id", list5.get(i).getId());
                    intent.putExtra("pid", list5.get(i).getProducts_id());
                    intent.putExtra("imageurl", Constants.IMAGE_URL+imageurl[0]);
                    intent.putExtra("content", list5.get(i).getProducts_name());
                    startActivity(intent);
                    break;
                case R.id.btn://查看物流
                    Toast.makeText(MyOrderActivity.this, "你点击了物流", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.delete_order://删除订单
                    dialotitle = "删除订单";
                    dialogmessage = "确认删除该订单？";
                    showDialog();
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            del_flag = "1";
                            id = list5.get(i).getId();
                            list5.remove(i);
                            updateOrderType();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                    break;
                case R.id.cancellation_of_order://取消订单
                    orders_flag = "5";
                    id = list5.get(i).getId();
                    list5.remove(i);
                    updateOrderType();
                    break;
                case R.id.payment://付款
                    break;
                case R.id.confirm_receipt://确认收货
                    break;
                case R.id.layout:
                    intent = new Intent(MyOrderActivity.this, CommodityDetailsActivity.class);
                    intent.putExtra("id",list5.get(i).getProducts_id());
                    startActivity(intent);
                    break;

            }

        }
    };

    private String dialotitle;
    private String dialogmessage;
    private AlertDialog.Builder builder;
    private void showDialog(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle(dialotitle);
        builder.setMessage(dialogmessage);
    }

    @OnClick({R.id.all_commodity,R.id.obligation,R.id.to_send_the_goods,R.id.wait_for_receiving,R.id.off_the_stocks})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.all_commodity:
                index = 0;
                setListData();
                allCommodity.setTextColor(getResources().getColor(R.color.red));
                line1.setVisibility(View.VISIBLE);
                obligation.setTextColor(getResources().getColor(R.color.transparent80));
                line2.setVisibility(View.INVISIBLE);
                toSendTheGoods.setTextColor(getResources().getColor(R.color.transparent80));
                line3.setVisibility(View.INVISIBLE);
                waitForReceiving.setTextColor(getResources().getColor(R.color.transparent80));
                line4.setVisibility(View.INVISIBLE);
                offTheStocks.setTextColor(getResources().getColor(R.color.transparent80));
                line5.setVisibility(View.INVISIBLE);
                break;
            case R.id.obligation:
                index = 1;
                setListData();
                obligation.setTextColor(getResources().getColor(R.color.red));
                line2.setVisibility(View.VISIBLE);
                allCommodity.setTextColor(getResources().getColor(R.color.transparent80));
                line1.setVisibility(View.INVISIBLE);
                toSendTheGoods.setTextColor(getResources().getColor(R.color.transparent80));
                line3.setVisibility(View.INVISIBLE);
                waitForReceiving.setTextColor(getResources().getColor(R.color.transparent80));
                line4.setVisibility(View.INVISIBLE);
                offTheStocks.setTextColor(getResources().getColor(R.color.transparent80));
                line5.setVisibility(View.INVISIBLE);
                break;
            case R.id.to_send_the_goods:
                index = 2;
                setListData();
                toSendTheGoods.setTextColor(getResources().getColor(R.color.red));
                line3.setVisibility(View.VISIBLE);
                obligation.setTextColor(getResources().getColor(R.color.transparent80));
                line2.setVisibility(View.INVISIBLE);
                allCommodity.setTextColor(getResources().getColor(R.color.transparent80));
                line1.setVisibility(View.INVISIBLE);
                waitForReceiving.setTextColor(getResources().getColor(R.color.transparent80));
                line4.setVisibility(View.INVISIBLE);
                offTheStocks.setTextColor(getResources().getColor(R.color.transparent80));
                line5.setVisibility(View.INVISIBLE);
                break;
            case R.id.wait_for_receiving:
                index = 3;
                setListData();
                waitForReceiving.setTextColor(getResources().getColor(R.color.red));
                line4.setVisibility(View.VISIBLE);
                obligation.setTextColor(getResources().getColor(R.color.transparent80));
                line2.setVisibility(View.INVISIBLE);
                toSendTheGoods.setTextColor(getResources().getColor(R.color.transparent80));
                line3.setVisibility(View.INVISIBLE);
                allCommodity.setTextColor(getResources().getColor(R.color.transparent80));
                line1.setVisibility(View.INVISIBLE);
                offTheStocks.setTextColor(getResources().getColor(R.color.transparent80));
                line5.setVisibility(View.INVISIBLE);
                break;
            case R.id.off_the_stocks:
                index = 4;
                setListData();
                offTheStocks.setTextColor(getResources().getColor(R.color.red));
                line5.setVisibility(View.VISIBLE);
                obligation.setTextColor(getResources().getColor(R.color.transparent80));
                line2.setVisibility(View.INVISIBLE);
                toSendTheGoods.setTextColor(getResources().getColor(R.color.transparent80));
                line3.setVisibility(View.INVISIBLE);
                waitForReceiving.setTextColor(getResources().getColor(R.color.transparent80));
                line4.setVisibility(View.INVISIBLE);
                allCommodity.setTextColor(getResources().getColor(R.color.transparent80));
                line1.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void setListData(){
        list5.clear();
        if (index == 0)
            list5 .addAll(list );
        else if(index == 1)
            list5.addAll(list1);
        else if(index == 2)
            list5.addAll(list2);
        else if(index == 3)
            list5.addAll(list3);
        else if(index == 4)
            list5.addAll(list4);
        myOrderAdapter.notifyDataSetChanged();
    }

    /**
     * 获取订单数据
     */
    String create_id;
    private void getOrder(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).getproducts_ordersApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if (type == Constants.TYPE_SUCCESS) {
                    List<MyOrderModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(), result, MyOrderModel.ResultBean.class);
                    list.clear();
                    list1.clear();
                    list2.clear();
                    list3.clear();
                    list4.clear();
                    for(int i=0;i<resultBeans.size();i++){
                        if(resultBeans.get(i).getOrders_flag().equals("4")){
                            //待付款
                            list1.add(resultBeans.get(i));
                        }else if(resultBeans.get(i).getOrders_flag().equals("1")){
                            //待发货
                            list2.add(resultBeans.get(i));
                        }else if(resultBeans.get(i).getOrders_flag().equals("2")){
                            //待收货
                            list3.add(resultBeans.get(i));
                        }else if(resultBeans.get(i).getOrders_flag().equals("6")){
                            //已完成
                            list4.add(resultBeans.get(i));
                        }
                    }
                    list.addAll(resultBeans);
                    setListData();
                } else BaseApi.showErrMsg(MyOrderActivity.this, result);
            }
        });
    }

    /**
     * 更新订单数据（删除、取消订单）
     */
    String id;
    String del_flag;
    String orders_flag;
    String update_id;
    String update_name;
    String update_time;
    private void updateOrderType() {
        FourApi.getInstance(this).updateOrderTypeApi(id, orders_flag, update_id, update_name, update_time,del_flag, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    myOrderAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyOrderActivity.this, result);
            }
        });
    }
}
