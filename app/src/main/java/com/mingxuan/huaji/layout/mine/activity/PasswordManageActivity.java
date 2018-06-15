package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/2.
 * 公司：铭轩科技
 */

public class PasswordManageActivity extends BaseActivity {
    @BindView(R.id.insertpassword)
    TextView insertpassword;
    @BindView(R.id.insertpassword_two)
    TextView insertpasswordTwo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_passwordmanage;
    }

    @Override
    protected void initView() {
        setToolbarTitle("密码管理");
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.insertpassword,R.id.insertpassword_two})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.insertpassword:
                intent = new Intent(this,InsertPasswordActivity.class);
                intent.putExtra("index",1);
                startActivity(intent);
                break;
            case R.id.insertpassword_two:
                intent = new Intent(this,InsertPasswordActivity.class);
                intent.putExtra("index",2);
                startActivity(intent);
                break;
        }
    }
}
