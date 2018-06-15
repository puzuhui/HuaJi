package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/2/26.
 * 公司：铭轩科技
 */

public class MyQrcodeActivity extends BaseActivity {
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void initView() {
        setToolbarTitle("我的二维码");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3})
    public void OnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv1:
                intent =new Intent(MyQrcodeActivity.this,QrcodeActivity.class);
                intent.putExtra("index",1);
                startActivity(intent);
                break;
            case R.id.iv2:
                intent =new Intent(MyQrcodeActivity.this,QrcodeActivity.class);
                intent.putExtra("index",2);
                startActivity(intent);
                break;
            case R.id.iv3:
                intent =new Intent(MyQrcodeActivity.this,QrcodeActivity.class);
                intent.putExtra("index",3);
                startActivity(intent);
                break;
        }
    }


}
