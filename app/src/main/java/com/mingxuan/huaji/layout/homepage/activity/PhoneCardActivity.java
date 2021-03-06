package com.mingxuan.huaji.layout.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
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
import com.mingxuan.huaji.layout.homepage.bean.BannerModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.NetImageLocadHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/2/1.
 * 公司：铭轩科技
 */

public class PhoneCardActivity extends BaseActivity {
    @BindView(R.id.converientBanner)
    ConvenientBanner converientBanner;
    @BindView(R.id.phone_btn)
    TextView phoneBtn;
    @BindView(R.id.telecom_phone_btn)
    TextView telecomPhoneBtn;
    @BindView(R.id.link_phone_btn)
    TextView linkPhoneBtn;
    @BindView(R.id.linear)
    LinearLayout linearLayout;
    private ConvenientBanner convenientBanner;
    //轮播下面的小点
    private int[] indicator = {R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    private List<BannerModel.ResultBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_card;
    }

    @Override
    protected void initView() {
        setToolbarTitle("我的电话卡");
        bean = new ArrayList<>();
        list = new ArrayList<>();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {
        getImage("2");
    }

    @OnClick({R.id.phone_btn,R.id.telecom_phone_btn,R.id.link_phone_btn})
    public void OnClic(View view){
        switch (view.getId()){
            case R.id.phone_btn:
                showPopupWindow(1,R.string.hint_title,R.string.business_hint);
                break;
            case R.id.telecom_phone_btn:
                showPopupWindow(2,R.string.one,R.string.two);
                break;
            case R.id.link_phone_btn:
                showPopupWindow(3,R.string.hint_title,R.string.three);
                break;
        }
    }

    TextView title;
    TextView content;
    TextView confirmBtn;
    TextView cancelBtn;
    ScrollView sv_content;
    private void showPopupWindow(final int index,int titlehint,int hint){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_phone_window,null);
        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);
        confirmBtn = (TextView) view.findViewById(R.id.confirm_btn);
        cancelBtn = (TextView) view.findViewById(R.id.cancel_btn);
        sv_content = (ScrollView) view.findViewById(R.id.sv_content);

        sv_content.setVisibility(View.VISIBLE);

        title.setText(titlehint);
        content.setText(hint);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels*4/5;
        int height = getResources().getDisplayMetrics().heightPixels*3/5;
        final PopupWindow popupWindow = new PopupWindow(view,weight,height,true);
        popupWindow.setFocusable(true);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == 1){
                    linearLayout.setVisibility(View.VISIBLE);
                }else if(index == 2){
                    Intent intent = new Intent(PhoneCardActivity.this,ChoosePhoneCardActivity.class);
                    intent.putExtra("index",1);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(PhoneCardActivity.this,ChoosePhoneCardActivity.class);
                    intent.putExtra("index",2);
                    startActivity(intent);
                }

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

    private void getImage(final String type){
        MainApi.getInstance(this).hotproductApi(type, new GetResultCallBack() {
            @Override
            public void getResult(String result, int types) {
                if(types == Constants.TYPE_SUCCESS){
                    List<BannerModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result,BannerModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    for (int i=0;i<resultBeans.size();i++){
                        bean.add(Constants.IMAGE_URL+list.get(i).getPic());
                        showBanner();
                    }
                }else BaseApi.showErrMsg(PhoneCardActivity.this,result);
            }
        });
    }

    private void showBanner(){
        convenientBanner = (ConvenientBanner) findViewById(R.id.converientBanner);
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

}
