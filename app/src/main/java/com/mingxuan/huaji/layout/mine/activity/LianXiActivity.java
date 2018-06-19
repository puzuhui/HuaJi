package com.mingxuan.huaji.layout.mine.activity;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;

/**
 * Created by Admin on 2018/6/15.
 * 公司：铭轩科技
 */

public class LianXiActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lianxi;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle("联系我们");
    }

    @Override
    protected void initData() {

    }
}
