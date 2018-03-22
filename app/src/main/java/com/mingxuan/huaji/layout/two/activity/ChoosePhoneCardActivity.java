package com.mingxuan.huaji.layout.two.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.adapter.PhoneCardAdapter;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.NetImageLocadHolder;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/2/1.
 * 公司：铭轩科技
 */

public class ChoosePhoneCardActivity extends Activity{
    @BindView(R.id.price)
    TextView price;
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
    private ConvenientBanner convenientBanner;
    private String[] images = {"http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"};
    //轮播下面的小点
    private int[] indicator = {R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    private List<String> list;
    String idCard;
    String real_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_phone_card);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
        idCard = sharedPreferences.getString("idCard","");
        real_name = sharedPreferences.getString("realName","");
        Log.e("cc=====",""+idCard+real_name);
        initView();
    }

    private void initView() {
        bean = new ArrayList<>();
        list = new ArrayList<>();
        convenientBanner = (ConvenientBanner) findViewById(R.id.converientBanner);

        showBanner();

        FullGridLayoutManager fullGridLayoutManager = new FullGridLayoutManager(this,2);
        recyclerview.setLayoutManager(fullGridLayoutManager);
        int spanCount = 2;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
        recyclerview.addItemDecoration(gridSpacingItemDecoration);

        price.setText("1000-15000");
        marketPrice.setText("￥"+"1000-15000");
        marketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);//中划线
        repertory.setText("库存："+"1065");
        express.setText("顺丰速递");
        money.setText("快递 ￥："+"10");
        realname.setText(real_name);
        idnumber.setText(idCard);

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

    @OnClick({R.id.back_btn,R.id.choose_number,R.id.buy})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.choose_number:
                intent = new Intent(ChoosePhoneCardActivity.this,ChoosePhoneNumberActivity.class);
                startActivityForResult(intent,1001);
                break;
            case R.id.buy:
                if(chooseNumber.getText().toString().equals("选择号码")){
                    ToastUtil.makeToast(this,"请选择号码");
                }else {
                    intent = new Intent(ChoosePhoneCardActivity.this,ConfirmAnOrderActivity.class);
                    intent.putExtra("index",1);
                    intent.putExtra("createname","华记黄埔");
                    intent.putExtra("image",bean.get(0));
                    intent.putExtra("productname","1562测试套餐");
                    intent.putExtra("productprice","1616");
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }else {
            if(requestCode == 1001 ){
                String result = data.getStringExtra("result");
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
