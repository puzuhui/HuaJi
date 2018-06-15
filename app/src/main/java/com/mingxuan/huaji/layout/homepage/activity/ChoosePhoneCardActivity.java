package com.mingxuan.huaji.layout.homepage.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.homepage.adapter.PhoneCardAdapter;
import com.mingxuan.huaji.layout.homepage.bean.CardInfoModel;
import com.mingxuan.huaji.layout.homepage.bean.CardOrderModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.NetImageLocadHolder;
import com.mingxuan.huaji.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/2/1.
 * 公司：铭轩科技
 */

public class ChoosePhoneCardActivity extends BaseActivity{
    @BindView(R.id.price)
    TextView tvprice;
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
    @BindView(R.id.realname)
    EditText realname;
    @BindView(R.id.idnumber)
    EditText idnumber;
    @BindView(R.id.choose_number)
    Button chooseNumber;
    @BindView(R.id.buy)
    TextView buy;
    @BindView(R.id.content)
    WebView wvContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ConvenientBanner convenientBanner;
    private String[] images = {"http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg"};
    //轮播下面的小点
    private int[] indicator = {R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    private List<CardInfoModel.ResultBean> list;
    private List<CardOrderModel.ResultBean> orderlist;
    boolean ischecked = false;
    LoadingDialog loadingDialog;
    SimpleDateFormat simpleDateFormat;
    int index;
    String mobile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_phone_card;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle("选择电话卡");
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        idCard = sharedPreferences.getString("idCard","");
        realName = sharedPreferences.getString("realName","");
        createId = sharedPreferences.getString("create_id","");
        parentCardId = sharedPreferences.getString("pid","");
        mobile = sharedPreferences.getString("mobile","");
        if(!TextUtils.isEmpty(mobile)){
            buy.setBackgroundResource(R.color.greylight);
        }

        Bundle bundle =getIntent().getExtras();
        index = bundle.getInt("index");
        if(index == 1){
            cardId = 1;
            tvTitle.setText("电信电话卡");
        }else {
            cardId = 2;
            tvTitle.setText("联通电话卡");
        }

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        createTime = simpleDateFormat.format(new Date());

        loadingDialog = new LoadingDialog(this);
        bean = new ArrayList<>();
        list = new ArrayList<>();
        orderlist = new ArrayList<>();
        convenientBanner = (ConvenientBanner) findViewById(R.id.converientBanner);

        showBanner();

        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(this,2);
        recyclerview.setLayoutManager(fullGridLayoutManager);
        int spanCount = 2;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        realname.setText(realName);
        idnumber.setText(idCard);

        phoneCardAdapter = new PhoneCardAdapter(list,ChoosePhoneCardActivity.this);
        recyclerview.setAdapter(phoneCardAdapter);
        phoneCardAdapter.setOnItemClickListener(new PhoneCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ischecked = true;
                price = list.get(position).getActivityPrice();
                infoId = list.get(position).getId();
                info = list.get(position).getName();

                tvprice.setText(""+list.get(position).getActivityPrice());
                marketPrice.setText(""+list.get(position).getPrice());
                marketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);//中划线
                wvContent.setVisibility(View.VISIBLE);
                wvContent.loadDataWithBaseURL(null, list.get(position).getIntro(), "text/html" , "utf-8", null);
            }
        });

    }

    @Override
    protected void initData() {
        getData();
        findOrder();
    }

    @OnClick({R.id.choose_number,R.id.buy})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.choose_number:
                intent = new Intent(ChoosePhoneCardActivity.this,ChoosePhoneNumberActivity.class);
                intent.putExtra("index",index);
                startActivityForResult(intent,1001);
                break;
            case R.id.buy:
                if(TextUtils.isEmpty(mobile)){
                    if(chooseNumber.getText().toString().equals("选择号码")){
                        ToastUtil.makeToast(this,"请选择号码");
                    }else {
                        if(ischecked){
                            Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
                            int day=aCalendar.getActualMaximum(Calendar.DATE) - aCalendar.get(Calendar.DAY_OF_MONTH);//当月剩余多少天
                            double money = price / aCalendar.getActualMaximum(Calendar.DATE) * day ;
                            if(index == 1){
                                cardId = 1;
                                orderInfo = "电信号码首次开通需要预存3个月套餐费和本月剩余"+day+"天共计"+ Math.ceil(money) +"元";
                            }else {
                                cardId = 2;
                                orderInfo = "联通号码首次开通需要预存3个月套餐费和本月剩余"+day+"天共计"+ Math.ceil(money) +"元";
                            }
                            saveData();
                        }else {
                            ToastUtil.makeToast(this,"请选择套餐");
                        }
                    }
                }else {
                    ToastUtil.makeToast(this,"每人只能购买一张卡");
                }
                break;
        }
    }

    PhoneCardAdapter phoneCardAdapter;
    private void getData(){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).phoneCardInfoApi(cardId, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<CardInfoModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result, CardInfoModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    tvprice.setText(list.get(0).getActivityPrice()+"-"+list.get(list.size()-1).getActivityPrice());
                    marketPrice.setText("￥"+list.get(0).getPrice()+"-"+list.get(list.size()-1).getPrice());
                    marketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);//中划线

                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        repertory.setText("库存："+jsonObject.getString("repertory"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    express.setText("顺丰速递");
                    money.setText("快递 ￥："+"9");
                    phoneCardAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    double price;
    int cardId;
    int infoId;
    String info;
    String idCard;
    String realName;
    String netArea = "重庆";
    String state = "0";
    String number ;
    String orderInfo;
    String createId;
//    String createName;
    String parentCardId;
    String createTime;
    private void saveData(){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).savePhoneCardOrderApi( price,  cardId, infoId,  info, idCard, realName, netArea, state, number,
                 orderInfo, createId, realName, parentCardId, createTime, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    Intent intent = new Intent(ChoosePhoneCardActivity.this,ConfirmAnOrderActivity.class);
                    intent.putExtra("index",1);
                    intent.putExtra("createname","华记黄埔");
                    intent.putExtra("image",bean.get(0));
                    intent.putExtra("productname",info);
                    intent.putExtra("productprice",price);
                    intent.putExtra("createTime",createTime);
                    startActivity(intent);
                }else BaseApi.showErrMsg(ChoosePhoneCardActivity.this,result);
            }
        });
    }

    private void findOrder(){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).findPhoneCardOrderApi(createId, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        loadingDialog.dismiss();
                        if(type == Constants.TYPE_SUCCESS){
                            List<CardOrderModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                                    result,CardOrderModel.ResultBean.class);
                            orderlist.clear();
                            orderlist.addAll(resultBeans);

                            long time = orderlist.get(0).getCreateTime();
                            showDialog(orderlist.get(0).getInfo(),Double.parseDouble(orderlist.get(0).getPrice()),
                                    simpleDateFormat.format(new Date(time)));
                        }
                    }
                });
    }

    private void showDialog(final String info, final double price,final String createTime){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("提示");
        builder.setMessage("您有未完成的订单，是否前往支付？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ChoosePhoneCardActivity.this,ConfirmAnOrderActivity.class);
                intent.putExtra("index",1);
                intent.putExtra("createname",orderlist.get(0).getCreateName());
                intent.putExtra("image",bean.get(0));
                intent.putExtra("productname",info);
                intent.putExtra("productprice",price);
                intent.putExtra("createTime",createTime);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }else {
            if(requestCode == 1001 ){
                String result = data.getStringExtra("result");
                number = result;
                chooseNumber.setText(result);
                chooseNumber.setTextColor(ContextCompat.getColor(this,R.color.white));
                chooseNumber.setBackgroundResource(R.drawable.login_btn_red);
            }
        }

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
