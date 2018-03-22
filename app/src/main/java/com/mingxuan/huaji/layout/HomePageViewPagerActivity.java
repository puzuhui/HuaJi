package com.mingxuan.huaji.layout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.fragment.HomePageFragment;
import com.mingxuan.huaji.fragment.MineFragment;
import com.mingxuan.huaji.fragment.RechargeFragment;
import com.mingxuan.huaji.fragment.ShoppingMallFragment;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.activity.MyInformationActivity;
import com.mingxuan.huaji.layout.four.model.InformationModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class HomePageViewPagerActivity extends FragmentActivity{
    private ViewPager viewPager;
    private RadioButton me;
    private RadioButton shopping_mall;
    private RadioButton recharge;
    private ArrayList<Fragment> fragmentArrayList;
    private MineFragment mineFragment;
    private RechargeFragment rechargeFragment;
    private ShoppingMallFragment shoppingFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_viewpager);

        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    private void initView() {
        me = (RadioButton) findViewById(R.id.me);
        shopping_mall = (RadioButton) findViewById(R.id.shopping_mall);
        recharge = (RadioButton) findViewById(R.id.recharge);
        viewPager = (ViewPager) findViewById(R.id.id_viewpage);
        fragmentArrayList = new ArrayList<>();
        mineFragment = new MineFragment();
        rechargeFragment = new RechargeFragment();
        shoppingFragment = new ShoppingMallFragment();
        fragmentArrayList.add(shoppingFragment);
        fragmentArrayList.add(rechargeFragment);
        fragmentArrayList.add(mineFragment);

        HuaJiPagerAdapter instantaneousPagerAdapter = new HuaJiPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(instantaneousPagerAdapter);
        viewPager.setCurrentItem(0);//设置默认显示
        shopping_mall.setChecked(true);
        viewPager.setOffscreenPageLimit(fragmentArrayList.size());
        shopping_mall.setTextColor(getResources().getColor(R.color.red));
        shopping_mall.setTextSize(14);
        shopping_mall.setOnClickListener(onClickListener);
        shopping_mall.setOnClickListener(onClickListener);
        recharge.setOnClickListener(onClickListener);
        me.setOnClickListener(onClickListener);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    //    重写FragmentPagerAdapter方法
    public class HuaJiPagerAdapter extends FragmentPagerAdapter {
        public HuaJiPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    shopping_mall.setChecked(true);
                    shopping_mall.setTextColor(getResources().getColor(R.color.red));
                    recharge.setTextColor(getResources().getColor(R.color.transparent80));
                    me.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextSize(14);
                    recharge.setTextSize(12);
                    me.setTextSize(12);
                    break;
                case 1:
                    recharge.setChecked(true);
                    recharge.setTextColor(getResources().getColor(R.color.red));
                    me.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextSize(12);
                    recharge.setTextSize(14);
                    me.setTextSize(12);
                    break;
                case 2:
                    me.setChecked(true);
                    me.setTextColor(getResources().getColor(R.color.red));
                    recharge.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextSize(12);
                    recharge.setTextSize(12);
                    me.setTextSize(14);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.shopping_mall:
                    viewPager.setCurrentItem(0);
                    shopping_mall.setTextColor(getResources().getColor(R.color.red));
                    recharge.setTextColor(getResources().getColor(R.color.transparent80));
                    me.setTextColor(getResources().getColor(R.color.transparent80));
                    break;
                case R.id.recharge:
                    viewPager.setCurrentItem(1);
                    recharge.setTextColor(getResources().getColor(R.color.red));
                    me.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextColor(getResources().getColor(R.color.transparent80));
                    break;
                case R.id.me:
                    viewPager.setCurrentItem(2);
                    me.setTextColor(getResources().getColor(R.color.red));
                    recharge.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextColor(getResources().getColor(R.color.transparent80));
                    break;
            }
        }
    };

    /**
     * 实现点击两次退出
     */
    private boolean flag = true;
    private static final int BACK_FLAG = 1;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK  && flag){
            Toast.makeText(HomePageViewPagerActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            flag = false;
            handler.sendEmptyMessageDelayed(BACK_FLAG,2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BACK_FLAG:
                    flag = true;
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
