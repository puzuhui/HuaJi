package com.mingxuan.huaji.layout.two.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.utils.NetImageLocadHolder;

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

public class PhoneCardActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.head_shop)
    TextView headShop;
    @BindView(R.id.converientBanner)
    ConvenientBanner converientBanner;
    @BindView(R.id.phone_btn)
    TextView phoneBtn;
    private ConvenientBanner convenientBanner;
    private String[] images = {"http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"};
    //轮播下面的小点
    private int[] indicator = {R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_card);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        bean = new ArrayList<>();

        convenientBanner = (ConvenientBanner) findViewById(R.id.converientBanner);
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
                Toast.makeText(PhoneCardActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.back_btn,R.id.phone_btn})
    public void OnClic(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.phone_btn:
                showPopupWindow();
                break;
        }
    }

    TextView title;
    TextView content;
    TextView confirmBtn;
    TextView cancelBtn;
    private void showPopupWindow(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_phone_window,null);
        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);
        confirmBtn = (TextView) view.findViewById(R.id.confirm_btn);
        cancelBtn = (TextView) view.findViewById(R.id.cancel_btn);

        title.setText(R.string.hint_title);
        content.setText(R.string.business_hint);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels*4/5;
        int height = getResources().getDisplayMetrics().heightPixels*3/5;
        final PopupWindow popupWindow = new PopupWindow(view,weight,height,true);
        popupWindow.setFocusable(true);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneCardActivity.this,ChoosePhoneCardActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
    }

}
