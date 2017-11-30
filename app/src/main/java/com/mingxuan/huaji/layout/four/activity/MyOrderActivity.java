package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.adapter.MyOrderAdapter;
import com.mingxuan.huaji.layout.four.model.MyOrderModel;
import com.mingxuan.huaji.layout.two.activity.CommodityDetailsActivity;
import com.mingxuan.huaji.utils.Constants;
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

public class MyOrderActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        update_time = simpleDateFormat.format(new Date());
        initView();
        getOrder();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getOrder();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        list =new ArrayList<>();
        list1 =new ArrayList<>();
        list2 =new ArrayList<>();
        list3 =new ArrayList<>();
        list4 =new ArrayList<>();
        list5 =new ArrayList<>();

        myOrderAdapter = new MyOrderAdapter(list5,MyOrderActivity.this);
        recyclerview.setAdapter(myOrderAdapter);
        myOrderAdapter.setMyOnItemClickListener(new MyOrderAdapter.MyOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int i) {
                Intent intent;
                String[] imageurl = list5.get(i).getProduct_intr().split(",");
                switch (view.getId()){
                    case R.id.evaluate://评价
                        intent = new Intent(MyOrderActivity.this, ProductsReviewsActivity.class);
                        intent.putExtra("index",1);
                        intent.putExtra("id", list5.get(i).getId());
                        intent.putExtra("pid", list5.get(i).getProducts_id());
                        intent.putExtra("imageurl", "http://125.65.82.219:8080"+imageurl[0]);
                        intent.putExtra("content", list5.get(i).getProducts_name());
                        startActivity(intent);
                        break;
                    case R.id.additional_comments://追加评价
                        intent = new Intent(MyOrderActivity.this, ProductsReviewsActivity.class);
                        intent.putExtra("index",2);
                        intent.putExtra("id", list5.get(i).getId());
                        intent.putExtra("pid", list5.get(i).getProducts_id());
                        intent.putExtra("imageurl", "http://125.65.82.219:8080"+imageurl[0]);
                        intent.putExtra("content", list5.get(i).getProducts_name());
                        startActivity(intent);
                        break;
                    case R.id.btn://查看物流
                        Toast.makeText(MyOrderActivity.this, "你点击了物流", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.delete_order://删除订单
                        del_flag = "1";
                        list5.remove(i);
                        id = list5.get(i).getId();
                        updateOrderType();
                        break;
                    case R.id.cancellation_of_order://取消订单
                        orders_flag = "5";
                        list5.remove(i);
                        id = list5.get(i).getId();
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
        });

        LinearLayoutManager linearLayoutMange = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutMange);
    }

    @OnClick({R.id.back_btn,R.id.all_commodity,R.id.obligation,R.id.to_send_the_goods,R.id.wait_for_receiving,R.id.off_the_stocks})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
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
    String create_id = "d1e964159cd04e0d909677bd72ab89e6";
    private void getOrder(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).getproducts_ordersApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    loadingDialog.dismiss();
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
                    Log.e("====",""+list.size());
                    Log.e("====",""+list1.size());
                    Log.e("====",""+list2.size());
                    Log.e("====",""+list3.size());
                    Log.e("====",""+list4.size());
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
    String update_id = "d1e964159cd04e0d909677bd72ab89e6";
    String update_name = "th";
    String update_time;
    private void updateOrderType() {
        FourApi.getInstance(this).updateOrderTypeApi(id, orders_flag, update_id, update_name, update_time,del_flag, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    Log.e("", "删除成功");
                    myOrderAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyOrderActivity.this, result);
            }
        });
    }
}
