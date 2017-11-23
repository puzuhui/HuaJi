package com.mingxuan.huaji.layout;

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

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.fragment.HomePageFragment;
import com.mingxuan.huaji.fragment.MineFragment;
import com.mingxuan.huaji.fragment.RechargeFragment;
import com.mingxuan.huaji.fragment.ShoppingMallFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class HomePageViewPagerActivity extends FragmentActivity{
    private ViewPager viewPager;
    private RadioGroup homepagebtn;
    private RadioButton me;
    private RadioButton homepage;
    private RadioButton shopping_mall;
    private RadioButton recharge;
    private ArrayList<Fragment> fragmentArrayList;
    private HomePageFragment homePageFragment;
    private MineFragment mineFragment;
    private RechargeFragment rechargeFragment;
    private ShoppingMallFragment shoppingFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_viewpager);

        initView();
    }

    private void initView() {
        homepagebtn = (RadioGroup) findViewById(R.id.homepage_btn);
        me = (RadioButton) findViewById(R.id.me);
        homepage = (RadioButton) findViewById(R.id.homepage);
        shopping_mall = (RadioButton) findViewById(R.id.shopping_mall);
        recharge = (RadioButton) findViewById(R.id.recharge);
        viewPager = (ViewPager) findViewById(R.id.id_viewpage);
        fragmentArrayList = new ArrayList<>();
        homePageFragment = new HomePageFragment();
        mineFragment = new MineFragment();
        rechargeFragment = new RechargeFragment();
        shoppingFragment = new ShoppingMallFragment();
        fragmentArrayList.add(homePageFragment);
        fragmentArrayList.add(shoppingFragment);
        fragmentArrayList.add(rechargeFragment);
        fragmentArrayList.add(mineFragment);

        HuaJiPagerAdapter instantaneousPagerAdapter = new HuaJiPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(instantaneousPagerAdapter);
        viewPager.setCurrentItem(0);//设置默认显示
        homepage.setChecked(true);
        viewPager.setOffscreenPageLimit(fragmentArrayList.size());
        homepage.setTextColor(getResources().getColor(R.color.red));
        homepage.setOnClickListener(onClickListener);
        shopping_mall.setOnClickListener(onClickListener);
        recharge.setOnClickListener(onClickListener);
        me.setOnClickListener(onClickListener);
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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.homepage:
                    viewPager.setCurrentItem(0);
                    homepage.setTextColor(getResources().getColor(R.color.red));
                    recharge.setTextColor(getResources().getColor(R.color.transparent80));
                    me.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextColor(getResources().getColor(R.color.transparent80));
                    break;
                case R.id.shopping_mall:
                    viewPager.setCurrentItem(1);
                    shopping_mall.setTextColor(getResources().getColor(R.color.red));
                    homepage.setTextColor(getResources().getColor(R.color.transparent80));
                    recharge.setTextColor(getResources().getColor(R.color.transparent80));
                    me.setTextColor(getResources().getColor(R.color.transparent80));
                    break;
                case R.id.recharge:
                    viewPager.setCurrentItem(2);
                    recharge.setTextColor(getResources().getColor(R.color.red));
                    homepage.setTextColor(getResources().getColor(R.color.transparent80));
                    me.setTextColor(getResources().getColor(R.color.transparent80));
                    shopping_mall.setTextColor(getResources().getColor(R.color.transparent80));
                    break;

                case R.id.me:
                    viewPager.setCurrentItem(3);
                    me.setTextColor(getResources().getColor(R.color.red));
                    homepage.setTextColor(getResources().getColor(R.color.transparent80));
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
